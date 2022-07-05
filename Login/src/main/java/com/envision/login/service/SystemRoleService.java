package com.envision.login.service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.envision.common.exception.DataNotFoundException;
import com.envision.login.database.dao.SystemAccessDAO;
import com.envision.login.database.dao.SystemRoleDAO;
import com.envision.login.database.dao.UserDiscomDAO;
import com.envision.login.database.objects.SystemAccessTbl;
import com.envision.login.database.objects.SystemRoleTbl;
import com.envision.login.database.projections.ModuleResponse;
import com.envision.login.database.projections.SystemAccessResponse;
import com.envision.login.database.projections.SystemRoleListResponse;
import com.envision.login.database.projections.SystemRoleResponse;
import com.envision.login.database.projections.UserDiscomData;
import com.envision.login.model.SystemAccessRequest;
import com.envision.login.model.SystemRoleRequest;

@Service
public class SystemRoleService {

	@Autowired
	SystemRoleDAO roleDAO;
	@Autowired
	SystemAccessDAO systemAccessDAO;

	@Autowired
	UserDiscomDAO userDiscomDAO;
	
	public List<SystemRoleResponse> fetchAllRoles(String roleType) {
		return roleDAO.findByRoleType(roleType);

	}

	public Page<SystemRoleListResponse> fetchAllRoles(Pageable pageableObj) {
		// TODO Auto-generated method stub
		return roleDAO.findAllRolesBy(pageableObj);
	}

	public Page<SystemRoleListResponse> fetchByRoleType(String roleType, Pageable pageableObj) {
		// TODO Auto-generated method stub

		return roleDAO.findByRoleType(roleType, pageableObj);
	}

	public Page<SystemRoleListResponse> fetchAllRolesFilterWithRoleName(String roleName, Pageable pageableObj) {
		// TODO Auto-generated method stub
		return roleDAO.findAllRolesByRoleNameContainingIgnoreCase(roleName, pageableObj);
	}

	public Page<SystemRoleListResponse> fetchAllRolesFilterWithRoleName(String roleType, String roleName,
			Pageable pageableObj) {
		// TODO Auto-generated method stub

		return roleDAO.findByRoleTypeAndRoleNameContainingIgnoreCase(roleType, roleName, pageableObj);
	}

	public SystemRoleResponse findRoleDetails(String roleName, String roleType) {
		return roleDAO.findByRoleNameAndRoleType(roleName, roleType);
	}

	public List<ModuleResponse> fetchAllModules() {
		// TODO Auto-generated method stub
		return systemAccessDAO.findDistinctModuleBy();
	}
	

	public List<SystemAccessResponse> fetchAllPermissionForModule(String module) {
		// TODO Auto-generated method stub
		return systemAccessDAO.findByModule(module);
	}
	
	@Transactional
	public void saveRole(SystemRoleRequest roleReq) throws DataNotFoundException {

		SystemRoleTbl existingRole = roleDAO.findByRoleName(roleReq.getRoleName());
		if (existingRole!=null) {
			Set<String> permissionSet=new HashSet<String>();
			Set<String> permissionType=new HashSet<String>();
			Set<SystemAccessTbl> accessList=null;
			for (SystemAccessRequest permission : roleReq.getAsignedPermission()) {
				
				permissionSet.add(permission.getPrivilegeName());

				permissionType.add(permission.getPrivilegeType());
			}
			if (permissionType.size()>0) {
				accessList=systemAccessDAO.findAllByPrivilegeNameInAndPrivilegeTypeIn(permissionSet, permissionType);
			}
			
			existingRole.setRoleDesc(roleReq.getRoleDesc());
			existingRole.setRoleType(roleReq.getRoleType());
			if (accessList!=null) {
				existingRole.setSystemAccess(accessList);
			}
			roleDAO.saveAndFlush(existingRole);
		}else {
			throw new DataNotFoundException("Role Does Not Exists");
			
		}
		
	}

	@Transactional
	public void createRole(SystemRoleRequest roleReq) {
		SystemRoleTbl newRole = new SystemRoleTbl();
		Set<String> permissionSet=new HashSet<String>();
		Set<String> permissionType=new HashSet<String>();
		Set<SystemAccessTbl> accessList=null;
		for (SystemAccessRequest permission : roleReq.getAsignedPermission()) {
			
			permissionSet.add(permission.getPrivilegeName());

			permissionType.add(permission.getPrivilegeType());
		}
		if (permissionType.size()>0) {
			accessList=systemAccessDAO.findAllByPrivilegeNameInAndPrivilegeTypeIn(permissionSet, permissionType);
		}
		newRole.setRoleName(roleReq.getRoleName());
		newRole.setRoleDesc(roleReq.getRoleDesc());
		newRole.setRoleType(roleReq.getRoleType());
		if (accessList!=null) {
			newRole.setSystemAccess(accessList);
		}
		roleDAO.saveAndFlush(newRole);
	}

	public  List<UserDiscomData> getUserDiscomData(String name) {
		
		return userDiscomDAO.findByUserAndStartDateBeforeAndEndDateAfter(name,LocalDateTime.now(),LocalDateTime.now());
	}

	public  void deleteRole(String role) throws DataNotFoundException {

		SystemRoleTbl existingRole = roleDAO.findByRoleName(role);
		if (existingRole!=null) {
			
			roleDAO.delete(existingRole);
		}else {
			throw new DataNotFoundException("Role Does Not Exists");
			
		}
	}
	
	
}
