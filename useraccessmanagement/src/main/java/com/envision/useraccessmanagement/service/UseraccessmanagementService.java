package com.envision.useraccessmanagement.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.persistence.criteria.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.envision.approvalprocess.database.objects.ApproverDetails;
import com.envision.approvalprocess.modal.ApprovalProcessVO;
import com.envision.approvalprocess.modal.ApprovalStepsVO;
import com.envision.approvalprocess.modal.ApproverDetailsProjection;
import com.envision.approvalprocess.service.ApprovalProcessService;
import com.envision.common.CommonConstants;
import com.envision.common.database.dao.ConfigCodeDAO;
import com.envision.common.exception.DataNotFoundException;
import com.envision.common.service.MailService;
import com.envision.login.database.objects.User;
import com.envision.useraccessmanagement.UseraccessmanagementConstants;
import com.envision.useraccessmanagement.UseraccessmanagementUtilities;
import com.envision.useraccessmanagement.database.dao.UserRegistrationApproverDAO;
import com.envision.useraccessmanagement.database.dao.UserRegistrationMasterDAO;
import com.envision.useraccessmanagement.database.objects.UserRegistrationApproverRemarks;
import com.envision.useraccessmanagement.database.objects.UserRegistrationDetailsTable;
import com.envision.useraccessmanagement.database.objects.UserRegistrationMasterTable;
import com.envision.useraccessmanagement.database.objects.UserRegistrationResourceTable;
import com.envision.useraccessmanagement.database.projection.MasterDataProjection;
import com.envision.useraccessmanagement.modal.ApplicationAccess;
import com.envision.useraccessmanagement.modal.ApplicationDetails;
import com.envision.useraccessmanagement.modal.ApplicationRequested;
import com.envision.useraccessmanagement.modal.ApproverRemarks;
import com.envision.useraccessmanagement.modal.ApproverRequest;
import com.envision.useraccessmanagement.modal.EmploymentDetails;
import com.envision.useraccessmanagement.modal.LocationDetails;
import com.envision.useraccessmanagement.modal.NextApproverData;
import com.envision.useraccessmanagement.modal.RemarksData;
import com.envision.useraccessmanagement.modal.SpocDetail;
import com.envision.useraccessmanagement.modal.UAMCompleteRequest;
import com.envision.useraccessmanagement.modal.UAMDetailsVO;
import com.envision.useraccessmanagement.modal.UAMRegisteredVO;
import com.envision.useraccessmanagement.modal.UAMRequest;
import com.envision.useraccessmanagement.modal.UserDetails;
import com.envision.useraccessmanagement.modal.VPNdetails;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UseraccessmanagementService {
	@Autowired
	UseraccessmanagementUtilities utilities;

	@Autowired
	UserRegistrationMasterDAO masterDAO;
	
	@Autowired
	UserRegistrationApproverDAO approverRemarksDAO ;

	@Autowired
	ApprovalProcessService approvalProcessService;

	@Autowired
	MailService mailService;

	@Autowired
	ConfigCodeDAO configCodeDAO;

	@Value("${MailFromAddress}")
	String mailFromAddress;

	@Transactional
	public UAMRegisteredVO uamRegistration(UAMRequest request) throws Exception {
		mailService.sendMail(mailFromAddress, "Data Submitted Successfully", "abishekkumar.ngl@outlook.com",
				"Reference Id:1234567489 URL:http://127.0.0.1:8080/public/viewUAMStatus/123456789");

		UserRegistrationMasterTable masterTable = new UserRegistrationMasterTable();

//		User Details
		masterTable.setSalutation(request.getUserDetails().getSalutation());
		masterTable.setFirstName(request.getUserDetails().getFirstName());
		masterTable.setLastName(request.getUserDetails().getLastName());
		masterTable.setEmail(request.getUserDetails().getEmail());
		masterTable.setMobileNo(request.getUserDetails().getMobileNo());

//		Employee Details
		masterTable.setEmployeeId(request.getEmploymentDetails().getEmployeeId());
		masterTable.setDepartment(request.getEmploymentDetails().getDepartment());
		masterTable.setDesignation(request.getEmploymentDetails().getDesignation());

//		Location Details
		masterTable.setDiscomName(request.getLocationDetails().getDiscomName());
		masterTable.setCity(request.getLocationDetails().getCity());
		masterTable.setZone(request.getLocationDetails().getZone());
		masterTable.setCircle(request.getLocationDetails().getCircle());
		masterTable.setDivision(request.getLocationDetails().getDivision());
		masterTable.setSubdivision(request.getLocationDetails().getSubdivision());

//		Request Details
		masterTable.setUserType(request.getApplicationDetails().getUserType());
		masterTable.setRequestFor(request.getApplicationDetails().getRequestFor());
		masterTable.setExistingVpnUsername(request.getApplicationDetails().getExistingVPNName());
		masterTable.setState(request.getApplicationDetails().getState());
		StringBuffer discoms = new StringBuffer();

		if (request.getApplicationDetails().getDiscomList().size() > 1) {

			request.getApplicationDetails().getDiscomList().forEach(code -> {
				discoms.append(code.getShortCode());
				discoms.append("|");

			});
			masterTable.setStatus(UseraccessmanagementConstants.AWAITING_APPROVAL);
		} else {

			request.getApplicationDetails().getDiscomList().forEach(code -> {
				discoms.append(code.getShortCode());

			});
			masterTable.setStatus(UseraccessmanagementConstants.INPROGRESS);
		}

		masterTable.setDiscom(discoms.toString());

//		VPN details
		masterTable.setVpnAccessFromIndia(request.getVpnDetails().getVpnAccessFromIndia());
		masterTable.setLanMacAddress(request.getVpnDetails().getLanMACAddress());
		masterTable.setWifiMacAddress(request.getVpnDetails().getWifiMACAddress());
		masterTable.setCountryNameOutsideIndia(request.getVpnDetails().getCountryName());

//		application Access Child Obj
		masterTable.setUserRegistrationDetailsTable(new ArrayList<UserRegistrationDetailsTable>());

//		set application remarks
		masterTable.setUserRemarks(request.getRemarksData().getUserRemarks());

		for (ApplicationAccess application : request.getApplicationAccess()) {

			if (application.getIsRequired()) {
				UserRegistrationDetailsTable appDetail = new UserRegistrationDetailsTable();
				appDetail.setApplicationName(application.getApplicationName());
				appDetail.setAccessType(application.getAccesType());
				appDetail.setRemarks(application.getRemarks());
				appDetail.setAccessValidityFrom(application.getStart());
				appDetail.setAccessValidityTo(application.getEnd());
				appDetail.setRequired(application.getIsRequired());
				appDetail.setUserRegistrationMasterTable(masterTable);
				appDetail.setLastApprovalStatus(UseraccessmanagementConstants.INITIAL_APPLICATION_STATUS);
				masterTable.getUserRegistrationDetailsTable().add(appDetail);
			}

		}
		masterTable.setUuid(UUID.randomUUID());
		masterTable = masterDAO.saveAndFlush(masterTable);
		UAMRegisteredVO registeredVO = new UAMRegisteredVO();
		registeredVO.setReferenceNo(masterTable.getReferenceId());

		mailService.sendMail(mailFromAddress, "Data Submitted Successfully", masterTable.getEmail(),utilities.getFormattedRegistrationEmail(masterTable.getUuid(), masterTable.getReferenceId()));

		return registeredVO;

	}

	public UAMDetailsVO fetchUAMUserRecord(String referenceId) throws DataNotFoundException {
		Optional<UserRegistrationMasterTable> masterDataOpt = masterDAO.findById(referenceId);
		if (!masterDataOpt.isPresent()) {
			throw new DataNotFoundException("Reference Id not Found");
		}

		return mapEntityToVO(masterDataOpt.get());
	}

	public UAMDetailsVO fetchUAMUserRecordByUUID(UUID uuid) throws DataNotFoundException {
		Optional<UserRegistrationMasterTable> masterDataOpt = masterDAO.findByUuid(uuid);
		if (!masterDataOpt.isPresent()) {
			throw new DataNotFoundException("Reference Id not Found");
		}

		return mapEntityToVO(masterDataOpt.get());
	}

	private UAMDetailsVO mapEntityToVO(UserRegistrationMasterTable masterData) {

		UserDetails userDetails = new UserDetails();
		EmploymentDetails employmentDetails = new EmploymentDetails();
		LocationDetails locationDetails = new LocationDetails();
		ApplicationDetails applicationDetails = new ApplicationDetails();
		List<ApplicationRequested> applicationRequested = new ArrayList<ApplicationRequested>();
		VPNdetails vpnDetails = new VPNdetails();
		List<ApproverRemarks> approverRemarks = new ArrayList<ApproverRemarks>();
		List<SpocDetail> spocDetailList = new ArrayList<SpocDetail>();

//		set UserDetails
		userDetails.setSalutation(masterData.getSalutation());
		userDetails.setFirstName(masterData.getFirstName());
		userDetails.setLastName(masterData.getLastName());
		userDetails.setMobileNo(masterData.getMobileNo());
		userDetails.setEmail(masterData.getEmail());

//	    set employmentDetails
		employmentDetails.setDepartment(masterData.getDepartment());
		employmentDetails.setDesignation(masterData.getDesignation());
		employmentDetails.setEmployeeId(masterData.getEmployeeId());

//	    set locationDetails
		locationDetails.setDiscomName(masterData.getDiscomName());
		locationDetails.setCity(masterData.getCity());
		locationDetails.setCircle(masterData.getCircle());
		locationDetails.setDivision(masterData.getDivision());
		locationDetails.setSubdivision(masterData.getSubdivision());
		locationDetails.setZone(masterData.getZone());

//	    set applicationDetails
		applicationDetails.setExistingVPNName(masterData.getExistingVpnUsername());
		applicationDetails.setRequestFor(masterData.getRequestFor());
		applicationDetails.setUserType(masterData.getUserType());
		applicationDetails.setState(masterData.getState());
		applicationDetails.setStatus(masterData.getStatus());
		String[] discomArray = masterData.getDiscom().split("\\|");
		applicationDetails.setDiscomList(configCodeDAO.findBycodeTypeAndShortCodeIn(CommonConstants.CODE_TYPE_DISCOM,
				Arrays.asList(discomArray)));

//	    set ApplicationRequested
		for (UserRegistrationDetailsTable uamDetails : masterData.getUserRegistrationDetailsTable()) {
			
			if (uamDetails.getRequired()) {
				ApplicationRequested applicationRequestedRec = new ApplicationRequested();
				applicationRequestedRec.setAccesType(uamDetails.getAccessType());
				applicationRequestedRec.setApplicationName(uamDetails.getApplicationName());
				applicationRequestedRec.setStart(uamDetails.getAccessValidityFrom());
				applicationRequestedRec.setEnd(uamDetails.getAccessValidityTo());
				applicationRequestedRec.setIsRequired(uamDetails.getRequired());
				applicationRequestedRec.setRemarks(uamDetails.getRemarks());
				applicationRequestedRec.setStatus(uamDetails.getLastApprovalStatus());
				applicationRequested.add(applicationRequestedRec);
				
			}
		}

//	    set Vpn Details
		vpnDetails.setCountryName(masterData.getCountryNameOutsideIndia());
		vpnDetails.setLanMACAddress(masterData.getLanMacAddress());
		vpnDetails.setWifiMACAddress(masterData.getWifiMacAddress());
		vpnDetails.setVpnAccessFromIndia(masterData.getVpnAccessFromIndia());
		for (UserRegistrationApproverRemarks approverHistory : masterData.getUserRegistrationApproverRemarks()) {
			ApproverRemarks approverRemarksRec = new ApproverRemarks();
			approverRemarksRec.setApprovedDate(approverHistory.getCreatedDate());
			approverRemarksRec.setApproverLevel(approverHistory.getApproverLevel());
			approverRemarksRec.setApproverName(approverHistory.getCreatedBy());
			approverRemarksRec.setRemarks(approverHistory.getRemarks());
			approverRemarks.add(approverRemarksRec);
		}
		RemarksData remarksData=new RemarksData();
		remarksData.setUserRemarks(masterData.getUserRemarks());
		remarksData.setSpocRemarks(masterData.getSpocRemarks());
		

		for (UserRegistrationResourceTable resource : masterData.getUserRegistrationResourceTable()) {
			SpocDetail detail=new SpocDetail();
			
			detail.setUsername(resource.getUsername());
			detail.setAppUrls(resource.getAppUrls());
			detail.setCustomPorts(resource.getCustomPorts());
			detail.setOuName(resource.getOuName());
			detail.setSecurityGroups(resource.getSecurityGroups());
			detail.setServiceSshHttps(resource.getServiceSshHttps());
			detail.setSystemDestinationIpSubnets(resource.getSystemDestinationIpSubnets());
			
			spocDetailList.add(detail);
		}
		
		
		UAMDetailsVO detailsVO = new UAMDetailsVO(userDetails, employmentDetails, locationDetails, applicationDetails,
				applicationRequested, vpnDetails, approverRemarks,remarksData,spocDetailList);
		return detailsVO;
	}

	@Transactional
	public void approveUAMUserRecord(Authentication authentication, ApproverRequest approveReq,String refId) throws Exception {
		Boolean isApproved=false;
		
		Optional<UserRegistrationMasterTable> masterDataOpt = masterDAO.findById(refId);
		if (!masterDataOpt.isPresent()) {
			throw new DataNotFoundException("Reference Id not Found");
		}
		UserRegistrationMasterTable masterData = masterDataOpt.get();

		approvalProcessService.isValidApprover(authentication, UseraccessmanagementConstants.APPROVAL_PROCESS,
				masterData.getStatus(), UseraccessmanagementConstants.DISCOM_STR, masterData.getDiscom());

		UserRegistrationApproverRemarks approverRemarks = new UserRegistrationApproverRemarks();

		approverRemarks.setRemarks(approveReq.getRemarks());

		if (masterData.getUserRegistrationApproverRemarks() != null) {

			approverRemarks.setApproverLevel(masterData.getUserRegistrationApproverRemarks().size() + 1);
		} else {
			approverRemarks.setApproverLevel(1);
			masterData.setUserRegistrationApproverRemarks(new ArrayList<UserRegistrationApproverRemarks>());
		}
		approverRemarks.setUserRegistrationMasterTable(masterData);
		masterData.getUserRegistrationApproverRemarks().add(approverRemarks);

		if (approveReq.getApproveReject().equalsIgnoreCase(UseraccessmanagementConstants.RECORD_APPROVED)) {
			isApproved=true;
		}else {
			isApproved=false;
		}
		masterData.setStatus(approvalProcessService.executeStep(UseraccessmanagementConstants.APPROVAL_PROCESS, masterData.getStatus(), isApproved));
		
		Map<String, UserRegistrationDetailsTable> applciationMap=new HashMap<String, UserRegistrationDetailsTable>();
		
		masterData.getUserRegistrationDetailsTable().forEach(k->{
			applciationMap.put(k.getApplicationName(), k);
		});
		
		
	for (ApplicationRequested applciation : approveReq.getMasterData()) {
		UserRegistrationDetailsTable applcaitiontable=applciationMap.get(applciation.getApplicationName());
		if (masterData.getStatus().equalsIgnoreCase(UseraccessmanagementConstants.APPROVED)) {
			applcaitiontable.setLastApprovalStatus(applciation.getStatus());
		}else if (applciation.getStatus().equalsIgnoreCase("Reject")) {
			applcaitiontable.setLastApprovalStatus(applciation.getStatus());
		}
		applcaitiontable.setAccessValidityFrom(applciation.getStart());
		applcaitiontable.setAccessValidityTo(applciation.getEnd());
	}
	
	approverRemarksDAO.save(approverRemarks);
	
		masterDAO.save(masterData);
		approverRemarksDAO.flush();
		masterDAO.flush();
	}

	
	public Set<String> findAllValidStatusForApprover(Authentication authentication) {
		ApprovalProcessVO UAMApprovalProcess = approvalProcessService
				.getApprovalProcessData(UseraccessmanagementConstants.APPROVAL_PROCESS);

		List<String> approverIdentity = new ArrayList<String>();

		Map<String, List<String>> identityToStatusMap = new HashMap<String, List<String>>();

		for (ApprovalStepsVO step : UAMApprovalProcess.getApprovalSteps()) {
			if (step.getApproverIdentity() != null) {
				approverIdentity.add(step.getApproverIdentity());
				if (!identityToStatusMap.containsKey(step.getApproverIdentity())) {
					identityToStatusMap.put(step.getApproverIdentity(), new ArrayList<String>());

				}

				identityToStatusMap.get(step.getApproverIdentity()).add(step.getRecordStage());
			}
		}

		List<ApproverDetails> identityList = approvalProcessService.findApproverByName(authentication.getName(),
				approverIdentity);
		
		Set<String> validStatus=new HashSet<String>();
		
		identityList.forEach(k->{
			validStatus.addAll(identityToStatusMap.get(k.getApproverIdentity()));
		
		});
		
		return validStatus;
	}
	
	public Page<MasterDataProjection> getAllUAMRecords(Authentication authentication, Pageable pageableObj,
			Boolean listView) {

		List<String> statusList = new ArrayList<String>();

		if (listView) {
			statusList.add(UseraccessmanagementConstants.COMPLETED);

//			return masterDAO.findByStatus(UseraccessmanagementConstants.COMPLETED,pageableObj);
			return masterDAO.findAllBy(pageableObj);

		} else {

			ApprovalProcessVO UAMApprovalProcess = approvalProcessService
					.getApprovalProcessData(UseraccessmanagementConstants.APPROVAL_PROCESS);

			List<String> approverIdentity = new ArrayList<String>();

			Map<String, List<String>> identityToStatusMap = new HashMap<String, List<String>>();

			for (ApprovalStepsVO step : UAMApprovalProcess.getApprovalSteps()) {
				if (step.getApproverIdentity() != null) {
					approverIdentity.add(step.getApproverIdentity());
					if (!identityToStatusMap.containsKey(step.getApproverIdentity())) {
						identityToStatusMap.put(step.getApproverIdentity(), new ArrayList<String>());

					}

					identityToStatusMap.get(step.getApproverIdentity()).add(step.getRecordStage());
				}
			}

			List<ApproverDetails> identityList = approvalProcessService.findApproverByName(authentication.getName(),
					approverIdentity);
			Page<UserRegistrationMasterTable> resposne = findApprovalRecords(identityToStatusMap, identityList,
					pageableObj);
			SpelAwareProxyProjectionFactory pf = new SpelAwareProxyProjectionFactory();
			List<MasterDataProjection> projectionList = resposne.getContent().stream()
					.map(c -> pf.createProjection(MasterDataProjection.class, c)).collect(Collectors.toList());
			Page<MasterDataProjection> projectionResponse = new PageImpl<MasterDataProjection>(projectionList,
					pageableObj, projectionList.size());
			return projectionResponse;
		}

	}

	public Page<UserRegistrationMasterTable> findApprovalRecords(Map<String, List<String>> identityToStatusMap,
			List<ApproverDetails> identityList, Pageable pageable) {
		Specification<UserRegistrationMasterTable> spec;

		spec = (root, query, cb) -> {
			List<Predicate> predicates = new ArrayList<>();
			for (ApproverDetails approverDetail : identityList) {

//	        	 predicates.add(root.get("status").in(identityToStatusMap.get(approverDetail.getApproverIdentity())));
			
			Predicate predicateforStatus = root.get("status").in(identityToStatusMap.get(approverDetail.getApproverIdentity()));
			Predicate predicateforDiscom = cb.equal(root.get("discom"), approverDetail.getApproverFilter());


				if (approverDetail.getApprovalFor() != null && !approverDetail.getApprovalFor().equalsIgnoreCase("")) {
					
					
					predicates.add(cb.and(predicateforStatus, predicateforDiscom));
//					predicates.add(cb.and(root.get("status").in(identityToStatusMap.get(approverDetail.getApproverIdentity())),cb.equal(root.get("discom"), approverDetail.getApproverFilter()) ));
//							cb.and(root.get("status").in(identityToStatusMap.get(approverDetail.getApproverIdentity())),
//									cb.equal(root.get("discom"), approverDetail.getApproverFilter())));
				} else {
//					predicates.add(root.get("status").in(identityToStatusMap.get(approverDetail.getApproverIdentity())));
					predicates.add(predicateforStatus);
				}
			}

			return cb.or(predicates.toArray(new Predicate[predicates.size()]));
		};

		return masterDAO.findAll(spec, pageable);
	}

	public Page<MasterDataProjection> getAllUAMRecordsWithFilter(Authentication authentication, Pageable pageableObj,
			Boolean listView, String refId, String status) {

		List<String> statusList = new ArrayList<String>();

		if (listView) {
//			statusList.add(UseraccessmanagementConstants.COMPLETED);
			if (status==null) {
				status="";
			}
			if(status.equals("Submitted")) {

				statusList.add(UseraccessmanagementConstants.AWAITING_APPROVAL);
				statusList.add(UseraccessmanagementConstants.INPROGRESS);
				
			}else if(status.equals("Approved 1")) {
				
				statusList.add(UseraccessmanagementConstants.APPROVED1);
				statusList.add(UseraccessmanagementConstants.UPPCL_APPROVER1);
				
			}else if(status.equals("Approved 2")) {

				statusList.add(UseraccessmanagementConstants.APPROVED2);
				
			}else if(status.equals("Approved 3")) {

				statusList.add(UseraccessmanagementConstants.APPROVED3);
				
			}else if(status.equals("Approved 4")) {

				statusList.add(UseraccessmanagementConstants.APPROVED4);
				
			}else if(status.equals("Approved")) {

				statusList.add(UseraccessmanagementConstants.APPROVED);
				
			}else if(status.equals("Completed")) {
				
				statusList.add(UseraccessmanagementConstants.COMPLETED);
				
			}else if(status.equals("Rejected")) {
				
				statusList.add(UseraccessmanagementConstants.REJECTED);
				
			}else {

				statusList.add(UseraccessmanagementConstants.AWAITING_APPROVAL);
				statusList.add(UseraccessmanagementConstants.INPROGRESS);
				statusList.add(UseraccessmanagementConstants.APPROVED1);
				statusList.add(UseraccessmanagementConstants.UPPCL_APPROVER1);
				statusList.add(UseraccessmanagementConstants.APPROVED2);
				statusList.add(UseraccessmanagementConstants.APPROVED3);
				statusList.add(UseraccessmanagementConstants.APPROVED4);
				statusList.add(UseraccessmanagementConstants.APPROVED);
				statusList.add(UseraccessmanagementConstants.COMPLETED);
				statusList.add(UseraccessmanagementConstants.REJECTED);
				
			}

			
			if (refId==null || refId.equalsIgnoreCase("")) {
				return masterDAO.findByStatusIn(statusList,pageableObj);
			}else {
				return masterDAO.findByStatusInAndReferenceIdContaining(statusList,refId,pageableObj);
			}

		} else {

			ApprovalProcessVO UAMApprovalProcess = approvalProcessService
					.getApprovalProcessData(UseraccessmanagementConstants.APPROVAL_PROCESS);

			List<String> approverIdentity = new ArrayList<String>();

			Map<String, List<String>> identityToStatusMap = new HashMap<String, List<String>>();

			for (ApprovalStepsVO step : UAMApprovalProcess.getApprovalSteps()) {
				if (step.getApproverIdentity() != null) {
					approverIdentity.add(step.getApproverIdentity());
					if (!identityToStatusMap.containsKey(step.getApproverIdentity())) {
						identityToStatusMap.put(step.getApproverIdentity(), new ArrayList<String>());

					}

					identityToStatusMap.get(step.getApproverIdentity()).add(step.getRecordStage());
				}
			}

			List<ApproverDetails> identityList = approvalProcessService.findApproverByName(authentication.getName(),
					approverIdentity);
			Page<UserRegistrationMasterTable> resposne = findApprovalRecordswithFilter(identityToStatusMap, identityList,
					pageableObj,refId,status);
			SpelAwareProxyProjectionFactory pf = new SpelAwareProxyProjectionFactory();
			List<MasterDataProjection> projectionList = resposne.getContent().stream()
					.map(c -> pf.createProjection(MasterDataProjection.class, c)).collect(Collectors.toList());
			Page<MasterDataProjection> projectionResponse = new PageImpl<MasterDataProjection>(projectionList,
					pageableObj, projectionList.size());
			return projectionResponse;
		}

	}

	public Page<UserRegistrationMasterTable> findApprovalRecordswithFilter(Map<String, List<String>> identityToStatusMap,
			List<ApproverDetails> identityList, Pageable pageable, String refId, String status) {
		Specification<UserRegistrationMasterTable> spec;

		spec = (root, query, cb) -> {
			List<Predicate> predicates = new ArrayList<>();
			List<Predicate> loopPredicates = new ArrayList<>();
			for (ApproverDetails approverDetail : identityList) {

//	        	 predicates.add(root.get("status").in(identityToStatusMap.get(approverDetail.getApproverIdentity())));
			
			Predicate predicateforStatus = root.get("status").in(identityToStatusMap.get(approverDetail.getApproverIdentity()));
			Predicate predicateforDiscom = cb.equal(root.get("discom"), approverDetail.getApproverFilter());


				if (approverDetail.getApprovalFor() != null && !approverDetail.getApprovalFor().equalsIgnoreCase("")) {
					
					
					loopPredicates.add(cb.and(predicateforStatus, predicateforDiscom));
//					predicates.add(cb.and(root.get("status").in(identityToStatusMap.get(approverDetail.getApproverIdentity())),cb.equal(root.get("discom"), approverDetail.getApproverFilter()) ));
//							cb.and(root.get("status").in(identityToStatusMap.get(approverDetail.getApproverIdentity())),
//									cb.equal(root.get("discom"), approverDetail.getApproverFilter())));
				} else {
//					predicates.add(root.get("status").in(identityToStatusMap.get(approverDetail.getApproverIdentity())));
					loopPredicates.add(predicateforStatus);
				}
				
				
				
			}

			predicates.add(cb.or(loopPredicates.toArray(new Predicate[loopPredicates.size()])));
			if (refId !=null && !refId.equalsIgnoreCase("")) {
					predicates.add(cb.like(root.get("referenceId"), "%"+refId+"%"));
			}

			if (status !=null && !status.equalsIgnoreCase("")) {
					predicates.add(cb.equal(root.get("status"), status));
			}
			
			
			
			return cb.and(predicates.toArray(new Predicate[predicates.size()]));
		};

		return masterDAO.findAll(spec, pageable);
	}

	@Transactional
	public void completeUAMProcess(Authentication authentication, UAMCompleteRequest req, String refId) throws Exception {
		approvalProcessService.isValidApprover(authentication, UseraccessmanagementConstants.APPROVAL_PROCESS,
				UseraccessmanagementConstants.APPROVED,"", "");
		
		
		Optional<UserRegistrationMasterTable> masterDataOpt = masterDAO.findById(refId);
		
		if (!masterDataOpt.isPresent()) {
			 throw new DataNotFoundException("Reference Id Invalid");
		}
		
		UserRegistrationMasterTable masterData =masterDataOpt.get();
		
		masterData.setStatus(UseraccessmanagementConstants.COMPLETED);
		masterData.setSpocRemarks(req.getSpocRemarks());
		masterData.setUserRegistrationResourceTable(new ArrayList<UserRegistrationResourceTable>());
		req.getSpocdetails().forEach(detail->{
			UserRegistrationResourceTable resourceTable=new UserRegistrationResourceTable();
			
			resourceTable.setUsername(detail.getUsername());
			resourceTable.setAppUrls(detail.getAppUrls());
			resourceTable.setOuName(detail.getOuName());
			resourceTable.setSecurityGroups(detail.getSecurityGroups());
			resourceTable.setSystemDestinationIpSubnets(detail.getSystemDestinationIpSubnets());
			resourceTable.setServiceSshHttps(detail.getServiceSshHttps());
			resourceTable.setCustomPorts(detail.getCustomPorts());
			resourceTable.setUserRegistrationMasterTable(masterData);
			masterData.getUserRegistrationResourceTable().add(resourceTable);
		});
		
//		masterDAO.save(masterData);
		
	}
	
	
	public NextApproverData fetchNextApproverData(String status,String discom) throws DataNotFoundException {
		
		User approver;
		
		if (discom!=null && !discom.equalsIgnoreCase("")) {

			approver =approvalProcessService.getNextApproverDetails(UseraccessmanagementConstants.APPROVAL_PROCESS,status,UseraccessmanagementConstants.DISCOM_STR, discom);
		}else {
			approver=approvalProcessService.getNextApproverDetails(UseraccessmanagementConstants.APPROVAL_PROCESS,status,"", "");
		}
		if (approver!=null) {
			return new NextApproverData(approver.getFirstName(), approver.getOrgName(), approver.getEmployeeId(), approver.getEmail(), approver.getPrimaryMobileNumber());
			
		}else {
			throw new DataNotFoundException("Approver Data Not Avaialble");
		}
	}
	
}