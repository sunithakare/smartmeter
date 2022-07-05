package com.envision.login.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.envision.common.exception.DataNotFoundException;
import com.envision.login.database.projections.SystemRoleListResponse;
import com.envision.login.database.projections.SystemRoleResponse;
import com.envision.login.model.SystemRoleRequest;
import com.envision.login.service.SystemRoleService;

@RestController
@RequestMapping("roles")
public class RoleController {

	
	@Autowired
	SystemRoleService roleService;
	
	@GetMapping(path={"fetchAllRoles/{roleType}"})
	@ResponseBody
	@PreAuthorize("checkPermission('ANY','SRC')")
	public List<SystemRoleResponse> fetchAllRoles(@PathVariable(value = "roleType",required = true) String roleType) {

			return roleService.fetchAllRoles(roleType);
	}
	@GetMapping(path={"fetchRoles/{roleType}","fetchRoles"})
	@ResponseBody
	@PreAuthorize("checkPermission('ANY','SRC')")
	public Page<SystemRoleListResponse> fetchAllRolesWithPagination(@PathVariable(value = "roleType",required = false) String roleType,@RequestParam(value = "page",required = true) Integer page,@RequestParam(value = "size",required = true) Integer size) {

		Pageable pageableObj = PageRequest.of(page, size,Sort.by("roleType").ascending());
		if (roleType==null) {
			return roleService.fetchAllRoles(pageableObj);
		}else {
			return roleService.fetchByRoleType(roleType,pageableObj);
		}
	}
	@GetMapping("fetchRoleswithfilter")
	@ResponseBody
	@PreAuthorize("checkPermission('ANY','SRC')")
	public Page<SystemRoleListResponse> fetchAllRolesWithFilter(@RequestParam(value = "roleType",required = true) String roleType,@RequestParam(value="roleName",required=false,defaultValue = "") String roleName,@RequestParam(value = "page",required = true) Integer page,@RequestParam(value = "size",required = true) Integer size) {

		Pageable pageableObj = PageRequest.of(page, size,Sort.by("roleType").ascending());
		return roleService.fetchAllRolesFilterWithRoleName(roleType,roleName,pageableObj);
		
	}
	

	@GetMapping("roledata")
	@ResponseBody
	@PreAuthorize("checkPermission('ANY','SRC')")
	public SystemRoleResponse fetchRoleData(@RequestParam(value = "roleType",required = true) String roleType,@RequestParam(value="roleName",required=false) String roleName) throws DataNotFoundException {
		SystemRoleResponse roledata = roleService.findRoleDetails(roleName, roleType);
		if (roledata==null) {
			throw new DataNotFoundException("Data Not Found");
		}
			return roledata;
	}

	@PostMapping("save")
	@ResponseBody
	@PreAuthorize("checkPermission('ADMIN','SRC') or checkPermission('MANAGER','SRC')")
	public void saveRole(@RequestBody SystemRoleRequest roleReq) throws DataNotFoundException {
		roleService.saveRole(roleReq);
	}

	@PostMapping("create")
	@ResponseBody
	@PreAuthorize("checkPermission('ADMIN','SRC') or checkPermission('MANAGER','SRC')")
	public void createRole(@RequestBody SystemRoleRequest roleReq){
		roleService.createRole(roleReq);
	}

	
	@DeleteMapping("delete/{role}")
	@ResponseBody
	@PreAuthorize("checkPermission('ADMIN','SRC') or checkPermission('MANAGER','SRC')")
	public void  deleteRole(@PathVariable(value = "role",required = true) String role) throws DataNotFoundException{
		roleService.deleteRole(role);
//		return authentication.getAuthorities();
	}
	
	@GetMapping("getallpermissionofuser")
	@ResponseBody
	@PreAuthorize("checkPermission('ANY','SRC')")
	public Collection<? extends GrantedAuthority> getAllRolesofCurrentUser(Authentication authentication){
		return authentication.getAuthorities();
	}
	
}
