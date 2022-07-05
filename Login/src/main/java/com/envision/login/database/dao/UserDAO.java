package com.envision.login.database.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.envision.login.database.objects.User;
import com.envision.login.database.projections.SystemUserListResponse;
@Repository
@Qualifier("userRepo")
public interface UserDAO extends JpaRepository<User, String> { 

	public Optional<User> findByUserName(String userName);
	public Optional<User> findByUserNameAndActive(String userName,Boolean active);
	public Page<SystemUserListResponse> findAllUsersBy(Pageable pageableObj);
	public Page<SystemUserListResponse> findByFirstNameContainingIgnoreCaseAndUserNameContainingIgnoreCaseAndEmployeeIdContainingIgnoreCase(
			String firstName, String userName, String employeeId, Pageable pageableObj);
	

	public Optional<User> findByPrimaryMobileNumberAndActive(String mobileNo,Boolean active);
	
}
