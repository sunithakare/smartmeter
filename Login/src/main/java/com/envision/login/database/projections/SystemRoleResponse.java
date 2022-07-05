package com.envision.login.database.projections;

import java.util.Set;


public interface SystemRoleResponse {
	String getRoleName();
	String getRoleDesc();
	String getRoleType();
	Set<SystemAccessResponse> getSystemAccess();
	
}
