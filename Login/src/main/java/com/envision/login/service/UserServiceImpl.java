package com.envision.login.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.envision.common.exception.UserNotFoundException;
import com.envision.login.database.dao.UserDAO;
import com.envision.login.database.objects.SystemAccessTbl;
import com.envision.login.database.objects.SystemRoleTbl;
import com.envision.login.database.objects.User;
import com.envision.login.model.UserPojo;

@Service
@Qualifier("userService")
public class UserServiceImpl{

	@Autowired
	UserDAO userRepo;
	
	@Cacheable(value="userLoginDetilsCache", key = "'LoginUserCache'+#userName")
	public UserPojo fetchUserbyName(String  userName) throws UserNotFoundException {
		Optional<User> iptUsers = userRepo.findByUserName(userName);
		if (iptUsers.isPresent()) {
			User userData=iptUsers.get();
			UserPojo user=new UserPojo();
			user.setEmail(userData.getEmail());
			user.setUserId(userData.getUserName());
			user.setPassword(userData.getPassword());
	        user.setIsactive("Y");
	        user.setRolesAndPermission(new ArrayList<String>());
	        for (SystemRoleTbl userRole : userData.getUserRole()) {
	        	user.getRolesAndPermission().add("ROLE_"+userRole.getRoleName());
	        	for (SystemAccessTbl permissions : userRole.getSystemAccess()) {
	        		user.getRolesAndPermission().add(userRole.getRoleType()+"_"+permissions.getPrivilegeName());
	        		user.getRolesAndPermission().add(permissions.getPrivilegeType()+"_"+permissions.getPrivilegeName());
				}
			}
	        
			return user;
		}else {
			throw new UserNotFoundException("User Not Found");
		}
	}
	@CacheEvict(value="userLoginDetilsCache", key = "'LoginUserCache'+#userName")
	public void removeUserCache(String  userName) {
		
	}
	public UserPojo fetchUserbyNameforLogin(String  userName) throws UserNotFoundException {

		Optional<User> iptUsers = userRepo.findByUserNameAndActive(userName,true);
		if (iptUsers.isPresent()) {
			User userData=iptUsers.get();
			UserPojo user=new UserPojo();
			user.setEmail(userData.getEmail());
			user.setUserId(userData.getUserName());
			user.setPassword(userData.getPassword());
	        user.setIsactive("Y");
	        user.setRolesAndPermission(new ArrayList<String>());
	        for (SystemRoleTbl userRole : userData.getUserRole()) {
	        	user.getRolesAndPermission().add("ROLE_"+userRole.getRoleName());
	        	for (SystemAccessTbl permissions : userRole.getSystemAccess()) {
	        		if (permissions.getPrivilegeType().equalsIgnoreCase("UI")) {

		        		user.getRolesAndPermission().add(userRole.getRoleType()+"_"+permissions.getPrivilegeName());
					}
				}
			}
	        
			return user;
		}else {
			throw new UserNotFoundException("User Not Found");
		}
	}
	public UserPojo fetchUserbymobileNoforLogin(String mobileNo) {
		Optional<User> iptUsers = userRepo.findByPrimaryMobileNumberAndActive(mobileNo,true);
		if (iptUsers.isPresent()) {
			User userData=iptUsers.get();
			UserPojo user=new UserPojo();
			user.setEmail(userData.getEmail());
			user.setUserId(userData.getUserName());
			user.setPassword(userData.getPassword());
	        user.setIsactive("Y");
	        user.setRolesAndPermission(new ArrayList<String>());
	        for (SystemRoleTbl userRole : userData.getUserRole()) {
	        	user.getRolesAndPermission().add("ROLE_"+userRole.getRoleName());
	        	for (SystemAccessTbl permissions : userRole.getSystemAccess()) {
	        		if (permissions.getPrivilegeType().equalsIgnoreCase("UI")) {

		        		user.getRolesAndPermission().add(userRole.getRoleType()+"_"+permissions.getPrivilegeName());
					}
				}
			}
	        
			return user;
		}else {
			throw new UserNotFoundException("User Not Found");
		}
	}
	

}
