package com.envision.login.security;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;

import com.envision.login.service.SecurityService;

public class CustomMethodSecurityExpressionHandler 
  extends DefaultMethodSecurityExpressionHandler {

    @Override
    protected MethodSecurityExpressionOperations createSecurityExpressionRoot(
      Authentication authentication, MethodInvocation invocation) {
    	SecurityService root = 
          new SecurityService(authentication);
        root.setPermissionEvaluator(getPermissionEvaluator());
        root.setTrustResolver(getTrustResolver() );
        root.setRoleHierarchy(getRoleHierarchy());
        return root;
    }
}