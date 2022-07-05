package com.envision.login.database.dao;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.envision.login.database.objects.SystemRoleTbl;
import com.envision.login.database.projections.SystemRoleListResponse;
import com.envision.login.database.projections.SystemRoleResponse;

public interface SystemRoleDAO  extends JpaRepository<SystemRoleTbl, Long> { 

	Set<SystemRoleTbl> findByRoleNameIn(List<String> rolesList);

	SystemRoleTbl findByRoleName(String rolesName);
	
	List<SystemRoleResponse> findAllRolesBy();

	List<SystemRoleResponse> findByRoleType(String roleType);
	
	SystemRoleResponse findByRoleNameAndRoleType(String roleName,String roleType);

	Set<SystemRoleTbl> findByRoleNameInAndRoleTypeIn(List<String> roleName,List<String> roleType);

	Page<SystemRoleListResponse> findAllRolesBy(Pageable pageableObj);
	Page<SystemRoleListResponse> findByRoleType(String roleType, Pageable pageableObj);
	

	Page<SystemRoleListResponse> findAllRolesByRoleNameContainingIgnoreCase(String roleName,Pageable pageableObj);
	Page<SystemRoleListResponse> findByRoleTypeAndRoleNameContainingIgnoreCase(String roleType,String roleName, Pageable pageableObj);
}
