package com.envision.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.envision.login.auth.UserPrincipalDetails;
import com.envision.login.model.UserPojo;

import lombok.extern.slf4j.Slf4j;

@Service("LoginUserDetailsService")
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {
static boolean uservalidation=false;
@Autowired
UserServiceImpl userService;
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
    	 log.trace("in userservice impl-->userName:"+userName);
    	 UserPojo appUser =null; ;
    	 
    	 
    	 try {
			appUser = userService.fetchUserbyName(userName);
		} catch (Exception e) {
			e.printStackTrace();
			 throw new UsernameNotFoundException("User " + userName + " was not found");
		}
        log.trace("User Logged in with Name:"+userName);
        log.trace(appUser.getPassword());

        UserPrincipalDetails userprincipal=new UserPrincipalDetails(appUser);
        return userprincipal;
    }
 
}