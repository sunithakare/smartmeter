package com.envision.login.service;

import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

//@Component
@Slf4j
public class SecurityService extends SecurityExpressionRoot implements MethodSecurityExpressionOperations{

	public SecurityService(Authentication authentication) {
		super(authentication);
	}

	public boolean checkPermission(String role, String permission) {
		for (GrantedAuthority grantedAuthority : authentication.getAuthorities()) {
			if (role.equals("ANY")) {
				if (grantedAuthority.getAuthority().contains("_")) {
					if (grantedAuthority.getAuthority().split("_")[1].equalsIgnoreCase(permission)) {
						return true;
					}
				}
				if (grantedAuthority.getAuthority().contains(permission)) {
					return true;
				}

			} else {
				if (grantedAuthority.getAuthority().equalsIgnoreCase( role + "_" + permission)) {
					return true;
				}
			}
		}

		return false;

	}


	public boolean canSLADownloaded(String role, String permission) {
		for (GrantedAuthority grantedAuthority : authentication.getAuthorities()) {
			if (role.equals("ANY")) {

				if (grantedAuthority.getAuthority().contains(permission)) {
					return true;
				}

			} else {
				if (grantedAuthority.getAuthority().equalsIgnoreCase( role + "_" + permission)) {
					return true;
				}
			}
		}

		return false;

	}
	
	
	@Override
	public void setFilterObject(Object filterObject) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object getFilterObject() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setReturnObject(Object returnObject) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object getReturnObject() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getThis() {
		// TODO Auto-generated method stub
		return null;
	}
}
