package com.envision.login.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.envision.common.exception.DataNotFoundException;
import com.envision.login.LoginUtilities;
import com.envision.login.database.dao.SystemRoleDAO;
import com.envision.login.database.dao.UserDAO;
import com.envision.login.database.objects.SystemRoleTbl;
import com.envision.login.database.objects.User;
import com.envision.login.database.objects.UserDiscom;
import com.envision.login.database.projections.SystemUserListResponse;
import com.envision.login.model.UserCreation;
import com.envision.login.model.UserDiscomList;
import com.envision.login.model.UserRolesList;

//@Service
@Configuration
public class SystemUserService {

	@Autowired
	UserDAO userRepo;
	
	@Autowired
	SystemRoleDAO roleDAO;

	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	LoginUtilities loginUtilities;
	@Autowired
	UserServiceImpl userService;
	
	public Page<SystemUserListResponse> fetchAllusers(Pageable pageableObj) {
		return userRepo.findAllUsersBy(pageableObj);
	}


	public Page<SystemUserListResponse> fetchAllusersWithFilter( String firstName, String userName,
			String employeeId, Pageable pageableObj) {
		// TODO Auto-generated method stub
		return userRepo.findByFirstNameContainingIgnoreCaseAndUserNameContainingIgnoreCaseAndEmployeeIdContainingIgnoreCase( firstName, userName, employeeId, pageableObj);
	}
	public void checkUserNameAlreadyExists(String user) throws DataNotFoundException {
		
		Optional<User> userDataOpt = userRepo.findByUserName(user);
		if (!userDataOpt.isPresent()) {
			throw new DataNotFoundException("User Not found");
		}
	}
	public UserCreation fetchUserData(String user) throws DataNotFoundException {
		
		Optional<User> userDataOpt = userRepo.findByUserName(user);
		if (!userDataOpt.isPresent()) {
			throw new DataNotFoundException("User Not found");
		}
		User userData= userDataOpt.get();

		UserCreation resposneData=new UserCreation(userData.getFirstName(), userData.getLastName(), userData.getOrgName(), userData.getEmployeeId(), userData.getEmail(), userData.getPrimaryMobileNumber(), userData.getUserName(),userData.getActive(), null, null, null);
		resposneData.setDiscomDataList(new ArrayList<UserDiscomList>());
		resposneData.setUserRolesList(new ArrayList<UserRolesList>());
		
		for (UserDiscom userDiscom : userData.getUserDiscom()) {
			UserDiscomList discomList=new UserDiscomList(userDiscom.getDiscomName(), userDiscom.getDiscomDetails().getState(), userDiscom.getIsdefault(), userDiscom.getStartDate(), userDiscom.getEndDate());
			resposneData.getDiscomDataList().add(discomList);
		}
		
		for (SystemRoleTbl userRole : userData.getUserRole()) {
			UserRolesList roledata=new UserRolesList(userRole.getRoleName(), userRole.getRoleDesc(), userRole.getRoleType());
			resposneData.getUserRolesList().add(roledata);
		}
		
		return resposneData;
		
	}
	
	
	@Transactional
	public void saveUserData(UserCreation userData) throws DataNotFoundException {
		
		Optional<User> userDataOpt = userRepo.findByUserName(userData.getUserId());
		if (!userDataOpt.isPresent()) {
			throw new DataNotFoundException("User Not found");
		}

		User user= userDataOpt.get();
		user.setFirstName(userData.getFirstName());
		user.setLastName(userData.getLastName());
		user.setOrgName(userData.getOrgName());
		user.setEmployeeId(userData.getEmployeeId());
		user.setEmail(userData.getEmail());
		user.setPrimaryMobileNumber(userData.getPhonoNo());
		user.setActive(userData.isActive());
		if (userData.getPassword()!=null && !userData.getPassword().equals("")) {
			user.setPassword(passwordEncoder.encode( userData.getPassword()));
		}

		List<String> roleList=new ArrayList<String>();
		List<String> roleType=new ArrayList<String>();
		Set<SystemRoleTbl> userRolesList=new HashSet<SystemRoleTbl>();
		
		for (UserRolesList roleInput : userData.getUserRolesList()) {
			roleList.add(roleInput.getRoleName());
			roleType.add(roleInput.getRoleType());
		}
		
		if (roleList.size()>0) {
			userRolesList=roleDAO.findByRoleNameInAndRoleTypeIn(roleList, roleType);
			user.setUserRole(userRolesList);
		}
		
		Map<String,UserDiscom> userDiscomM=new HashMap<String, UserDiscom>();
		for (UserDiscom userDiscom : user.getUserDiscom()) {
			userDiscomM.put(userDiscom.getDiscomName(), userDiscom);
		} 
		
		
		
//		user.setUserDiscom(new HashSet<UserDiscom>());
		for (UserDiscomList discomData : userData.getDiscomDataList()) {
			
			UserDiscom discom;
			if (userDiscomM.containsKey(discomData.getDiscomName())) {
				discom=userDiscomM.get(discomData.getDiscomName());
				discom.setDiscomName(discomData.getDiscomName());
				discom.setIsdefault(discomData.isUserDefault());
				discom.setStartDate(discomData.getStart());
				discom.setEndDate(discomData.getEnd());
				discom.setUser(userData.getUserId());
			}else {
				discom=new UserDiscom();
				discom.setDiscomName(discomData.getDiscomName());
				discom.setIsdefault(discomData.isUserDefault());
				discom.setStartDate(discomData.getStart());
				discom.setEndDate(discomData.getEnd());
				discom.setUser(userData.getUserId());
				user.getUserDiscom().add(discom);
			}
			
		}
		
		
		userRepo.saveAndFlush(user);
		userService.removeUserCache(user.getUserName());
	}
	
	

	@Transactional
	public void createUserData(UserCreation userData) throws DataNotFoundException {
		
		User user= new User();
		user.setFirstName(userData.getFirstName());
		user.setLastName(userData.getLastName());
		user.setOrgName(userData.getOrgName());
		user.setEmployeeId(userData.getEmployeeId());
		user.setEmail(userData.getEmail());
		user.setPrimaryMobileNumber(userData.getPhonoNo());
		user.setPassword(passwordEncoder.encode( userData.getPassword()));
		user.setUserName(userData.getUserId());
		user.setActive(userData.isActive());

		List<String> roleList=new ArrayList<String>();
		List<String> roleType=new ArrayList<String>();
		Set<SystemRoleTbl> userRolesList=new HashSet<SystemRoleTbl>();
		
		for (UserRolesList roleInput : userData.getUserRolesList()) {
			roleList.add(roleInput.getRoleName());
			roleType.add(roleInput.getRoleType());
		}
		
		if (roleList.size()>0) {
			userRolesList=roleDAO.findByRoleNameInAndRoleTypeIn(roleList, roleType);
			user.setUserRole(userRolesList);
		}
		
		user.setUserDiscom(new HashSet<UserDiscom>());
		for (UserDiscomList discomData  : userData.getDiscomDataList()) {
			UserDiscom discom=new UserDiscom();
			discom.setDiscomName(discomData.getDiscomName());
			discom.setIsdefault(discomData.isUserDefault());
			discom.setStartDate(discomData.getStart());
			discom.setEndDate(discomData.getEnd());
			discom.setUser(userData.getUserId());
			user.getUserDiscom().add(discom);
		}
		
		userRepo.saveAndFlush(user);
		
	}
}
