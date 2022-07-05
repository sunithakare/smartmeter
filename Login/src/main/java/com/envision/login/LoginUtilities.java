package com.envision.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.envision.login.service.UserServiceImpl;

@Service
public class LoginUtilities {
@Autowired
UserServiceImpl userService;
	
	public void removeCachedUser(String user) {
		
		userService.removeUserCache(user);
	}
}
