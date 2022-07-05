package com.envision.login.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.envision.login.database.projections.ModuleResponse;
import com.envision.login.database.projections.SystemAccessResponse;
import com.envision.login.database.projections.UserDiscomData;
import com.envision.login.service.SystemRoleService;

@RestController
@RequestMapping("access")
public class SystemAccessController {

	@Autowired
	SystemRoleService roleService;
	
	
	@GetMapping("moduleslist")
	@ResponseBody
	private List<ModuleResponse> fetchAllModules() {
		return roleService.fetchAllModules();

	}
	

	@GetMapping("modulespermission/{module}")
	@ResponseBody
	private List<SystemAccessResponse> fetchAllModuleAccess(@PathVariable(value = "module",required = true) String module) {
		return roleService.fetchAllPermissionForModule(module);

	}
	
	

	@GetMapping("fetchStateAndDiscomForUser")
	@ResponseBody
	public List<UserDiscomData> fetchStateAndDiscomForUser(Authentication authentication) {
		
		return roleService.getUserDiscomData(authentication.getName());
	}
}
