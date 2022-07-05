package com.envision.login.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.envision.common.exception.DataNotFoundException;
import com.envision.login.database.objects.User;
import com.envision.login.database.projections.SystemUserListResponse;
import com.envision.login.model.UserCreation;
import com.envision.login.service.SystemUserService;

@RestController
@RequestMapping("user")
public class SystemUserCreationController {

	@Autowired
	SystemUserService systemUserService;
	
	@GetMapping("getuserslist")
	@ResponseBody
//	@PreAuthorize("checkPermission('ANY','SUC')")
	private Page<SystemUserListResponse> fetchUserList(@RequestParam(value = "page",required = true) Integer page,@RequestParam(value = "size",required = true) Integer size) {
		// TODO Auto-generated method stub
		Pageable pageableObj = PageRequest.of(page, size,Sort.by("id").descending ());
			return systemUserService.fetchAllusers(pageableObj);
	}
	
	@GetMapping("getuserslistwithfilter")
	@ResponseBody
//	@PreAuthorize("checkPermission('ANY','SUC')")
	private Page<SystemUserListResponse> fetchUserListWithFilter(@RequestParam(value = "page",required = true) Integer page,@RequestParam(value = "size",required = true) Integer size,
			@RequestParam(value = "firstName",required = false,defaultValue = "") String firstName,
			@RequestParam(value = "userName",required = false,defaultValue = "") String userName,
			@RequestParam(value = "employeeId",required = false,defaultValue = "") String employeeId) {
		Pageable pageableObj = PageRequest.of(page, size,Sort.by("id").descending());
			return systemUserService.fetchAllusersWithFilter(firstName,userName,employeeId,pageableObj);
	}
	
	@GetMapping("getuserdata")
	@ResponseBody
//	@PreAuthorize("checkPermission('ANY','SUC')")
	private UserCreation fetchUserData(@RequestParam(value = "user",required = true) String user) throws DataNotFoundException {
			return systemUserService.fetchUserData(user);
	}

	@GetMapping("checkusername")
	@ResponseBody
//	@PreAuthorize("checkPermission('ANY','SUC')")
	private void checkIfUserAlreadyExits(@RequestParam(value = "user",required = true) String user) throws DataNotFoundException {
			systemUserService.checkUserNameAlreadyExists(user);
	}

	@PostMapping("save")
	@ResponseBody
//	@PreAuthorize("checkPermission('ADMIN','SUC') or checkPermission('MANAGER','SUC')")
	private void saveUserData(@RequestBody UserCreation userCreation) throws DataNotFoundException {
//			return systemUserService.fetchUserData(userCreation.getUserId());
		systemUserService.saveUserData(userCreation);
	}

	@PostMapping("create")
	@ResponseBody
//	@PreAuthorize("checkPermission('ADMIN','SUC') or checkPermission('MANAGER','SUC')")
	private void createUserData(@RequestBody UserCreation userCreation) throws DataNotFoundException {
//			return systemUserService.fetchUserData(userCreation.getUserId());
		systemUserService.createUserData(userCreation);
	}
	
	
}
