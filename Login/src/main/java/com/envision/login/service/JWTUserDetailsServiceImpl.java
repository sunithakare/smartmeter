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

@Service("JWTUserDetailsService")
@Slf4j
public class JWTUserDetailsServiceImpl implements UserDetailsService {
static boolean uservalidation=false;
@Autowired
UserServiceImpl userService;
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
    	 log.trace("in userservice impl-->userName:"+userName);
    	 UserPojo appUser =null; ;
    	 
    	 
    	 try {
			appUser = userService.fetchUserbyNameforLogin(userName);
		} catch (Exception e) {
			 throw new UsernameNotFoundException("User " + userName + " was not found");
		}
        log.trace("User Logged in with Name:"+userName);

        UserPrincipalDetails userprincipal=new UserPrincipalDetails(appUser);
        return userprincipal;
    }
    

    @Transactional
	public UserDetails loadUserByMobileNo(String mobileNo) {
    	 log.trace("in userservice impl-->mobileNo:"+mobileNo);
    	 UserPojo appUser =null; ;
    	 
    	 
    	 try {
			appUser = userService.fetchUserbymobileNoforLogin(mobileNo);
		} catch (Exception e) {
			 throw new UsernameNotFoundException("User " + mobileNo + " was not found");
		}
        log.trace("User Logged in with Name:"+appUser.getUserId());

        UserPrincipalDetails userprincipal=new UserPrincipalDetails(appUser);
        return userprincipal;
	}
 
}