package com.envision.approvalprocess.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.envision.approvalprocess.ApprovalProcessUtilities;
import com.envision.approvalprocess.database.dao.ApprovalProcessDAO;
import com.envision.approvalprocess.database.dao.ApprovalStepsDAO;
import com.envision.approvalprocess.database.dao.ApproverDetailsDAO;
import com.envision.approvalprocess.database.objects.ApprovalSteps;
import com.envision.approvalprocess.database.objects.ApproverDetails;
import com.envision.approvalprocess.modal.ApprovalProcessVO;
import com.envision.approvalprocess.modal.ApprovalStepsVO;
import com.envision.approvalprocess.modal.ApproverCreationPOJO;
import com.envision.approvalprocess.modal.ApproverDetailsProjection;
import com.envision.common.exception.DataNotFoundException;
import com.envision.login.database.dao.UserDAO;
import com.envision.login.database.objects.User;
import com.envision.login.service.UserServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ApprovalProcessService {
	@Autowired
	ApprovalProcessUtilities utilities;

	@Autowired
	ApprovalProcessDAO approvalProcessDAO;
	@Autowired
	ApproverDetailsDAO approverDetailsDAO;
	@Autowired
	ApprovalStepsDAO approvalStepsDAO ;

	@Autowired
	UserDAO userRepo;

	public ApprovalProcessVO getApprovalProcessData(String approvalProcess) {
		return approvalProcessDAO.findByProcessName(approvalProcess);
	}

	public Page<ApproverDetailsProjection> getAllApprovers(Pageable pageableObj) {
		return approverDetailsDAO.findAllBy(pageableObj);
	}
	
	

	public List<ApproverDetails> getAllApproverIdentity(String userName) {
		return approverDetailsDAO.findByUserName(userName);
	}


	public Map<String, Object> submitForApproval(String approvalProcess) {
		Map<String, Object> approvalMap = new HashMap<String, Object>();

		ApprovalProcessVO approvalProcessVO = getApprovalProcessData(approvalProcess);
		approvalMap.put("SET_STAGE", approvalProcessVO.getApprovalSteps().get(0).getRecordStage());

		return approvalMap;
	}

	public User getNextApproverDetails(String approvalProcess, String stage,String approverFor,String approverFilter)  {

		List<ApprovalSteps> identityList = approvalStepsDAO.findByApprovalProcessNameAndRecordStage(approvalProcess, stage);
		
		for (ApprovalSteps approvalSteps : identityList) {
			String requiredIdentity=approvalSteps.getApproverIdentity();
			
			List<ApproverDetails> approverList  =null;
			if (approverFor==null ||approverFor.equalsIgnoreCase("")) {

				approverList=approverDetailsDAO.findByApproverIdentity(requiredIdentity);
			}
			else {

				approverList=approverDetailsDAO.findByApproverIdentityAndApprovalForAndApproverFilter(requiredIdentity,approverFor,approverFilter);
			}
			if (approverList!=null) {
				if (approverList.size()==0) {
					approverList=approverDetailsDAO.findByApproverIdentityAndApprovalForAndApproverFilter(requiredIdentity,"","");
				}

				if (approverList.size()>0) {
					String approverUserName=approverList.get(0).getUserName();
					Optional<User> userData = userRepo.findByUserName(approverUserName);
					
					return userData.get();
				}
				
			}
		}
		return null;
	}
		
	public void isValidApprover(Authentication authentication,String approvalProcess, String stage,String approverFor,String approverFilter) throws Exception {
		
		
		List<ApprovalSteps> identityList = approvalStepsDAO.findByApprovalProcessNameAndRecordStage(approvalProcess, stage);
		
		for (ApprovalSteps approvalSteps : identityList) {
			String requiredIdentity=approvalSteps.getApproverIdentity();
			
			List<ApproverDetails> approverList  =null;
			if (approverFor==null ||approverFor.equalsIgnoreCase("")) {

				approverList=approverDetailsDAO.findByUserNameAndApproverIdentity(authentication.getName(),requiredIdentity);
			}
			else {

				approverList=approverDetailsDAO.findByUserNameAndApproverIdentityAndApprovalForAndApproverFilter(authentication.getName(),requiredIdentity,approverFor,approverFilter);
			}
			if (approverList!=null) {
				if (approverList.size()==0) {
					approverList=approverDetailsDAO.findByUserNameAndApproverIdentityAndApprovalForAndApproverFilter(authentication.getName(),requiredIdentity,"","");
				}

				if (approverList.size()>0) {
					return;
				}
				
			}
		}
		
		throw new Exception("User Cannot Apporove this Request");
	}
	
	
	public String executeStep(String approvalProcess, String currentStage, boolean isApproved)
			throws Exception {

//	validate if user have permission for approve reject-- to be done

//		Map<String, Object> approvalMap = new HashMap<String, Object>();

		ApprovalProcessVO approvalProcessVO = getApprovalProcessData(approvalProcess);
		Optional<ApprovalStepsVO> currentStepOpt = approvalProcessVO.getApprovalSteps().stream()
				.filter(nameStartingWithPrefix(currentStage)).findFirst();

		if (!currentStepOpt.isPresent()) {
			throw new Exception("Cannot Process the Approval");
		}
		ApprovalStepsVO currentStep = currentStepOpt.get();

		if (isApproved) {
//			approvalMap.put("SET_STAGE", currentStep.getApprovedStatus());
			return currentStep.getApprovedStatus();
		} else {
//			approvalMap.put("SET_STAGE", currentStep.getRejectStatus());
			return currentStep.getRejectStatus();
		}


	}

	public static Predicate<ApprovalStepsVO> nameStartingWithPrefix(String currentStage) {
		return new Predicate<ApprovalStepsVO>() {

			@Override
			public boolean test(ApprovalStepsVO s) {
				return s.getRecordStage().equalsIgnoreCase(currentStage);
			}
		};
	}

	public Page<ApproverDetailsProjection> findApprovers(String userName, String approverIdentity, Pageable pageableObj) {

		return approverDetailsDAO.findAllByApproverIdentityAndUserNameContainingIgnoreCase(approverIdentity,userName,pageableObj);
	}

	@Transactional
	public void createApprover(ApproverCreationPOJO approverCreationPOJO) throws DataNotFoundException {
		if (!userRepo.findByUserName(approverCreationPOJO.getUserName()).isPresent()) {
			throw new DataNotFoundException("Approver Not Found");
		}
		
		List<ApproverDetails> approverDetailsList=new ArrayList<ApproverDetails>();
		approverCreationPOJO.getApproverDataList().forEach(details->{
			ApproverDetails approverDetails=new ApproverDetails();
			
			approverDetails.setUserName(approverCreationPOJO.getUserName());
			approverDetails.setApproverIdentity(details.getApproverIdentity());
			approverDetails.setApprovalFor(details.getApprovalFor());
			approverDetails.setApproverFilter(details.getApproverFilter());
			approverDetailsList.add(approverDetails);
		});
		approverDetailsDAO.saveAllAndFlush(approverDetailsList);
	}
	
	@Transactional
	public void saveApprover(ApproverCreationPOJO approverCreationPOJO) throws DataNotFoundException {
		List<ApproverDetails> approverDetailsList=approverDetailsDAO.findByUserName(approverCreationPOJO.getUserName());
		List<ApproverDetails> approverDetailsListToDelete=new ArrayList<ApproverDetails>();
		if (approverDetailsList.isEmpty()) {
			throw new DataNotFoundException("Approver Not Found");
		}
		
		Map<String,ApproverDetails> currentRecord=new HashMap<String, ApproverDetails>();
		Set<String> inputSet=new HashSet<String>();
		approverDetailsList.forEach(details->{
			String key=details.getApproverIdentity()+":"+details.getApprovalFor()+":"+details.getApproverFilter();
			currentRecord.put(key, details);
			
		});

		approverCreationPOJO.getApproverDataList().forEach(details->{
			String key=details.getApproverIdentity()+":"+details.getApprovalFor()+":"+details.getApproverFilter();
			inputSet.add(key);
		});
		
		currentRecord.forEach((key,value)->{
			if (!inputSet.contains(key)) {
				approverDetailsListToDelete.add(value);
			}
		});
		
		
		approverCreationPOJO.getApproverDataList().forEach(details->{
			String key=details.getApproverIdentity()+":"+details.getApprovalFor()+":"+details.getApproverFilter();
			ApproverDetails approverDetails=new ApproverDetails();
			if (currentRecord.containsKey(key)) {
				approverDetails=currentRecord.get(key);
			}
			approverDetails.setUserName(approverCreationPOJO.getUserName());
			approverDetails.setApproverIdentity(details.getApproverIdentity());
			approverDetails.setApprovalFor(details.getApprovalFor());
			approverDetails.setApproverFilter(details.getApproverFilter());
			approverDetailsList.add(approverDetails);
		});

		approverDetailsDAO.saveAll(approverDetailsList);
		approverDetailsDAO.deleteAll(approverDetailsListToDelete);
		approverDetailsDAO.flush();
	}

	public List<ApproverDetails> findApproverByName(String userName, List<String> approverIdentity) {
		return approverDetailsDAO.findByUserNameAndApproverIdentityIn(userName,approverIdentity);
		
		
	}

	public void deleteApprover(String userName) {
		List<ApproverDetails> approverDetailsList=approverDetailsDAO.findByUserName(userName);
		approverDetailsDAO.deleteAll(approverDetailsList);
		approverDetailsDAO.flush();
		
	}

	public List<ApproverDetailsProjection> findApproverByName(String userName) {

		return approverDetailsDAO.findApproverByUserName(userName);
	}
	
}