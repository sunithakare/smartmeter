package com.envision.login.database.projections;



public interface SystemAccessResponse {
	String getPrivilegeName();
	String getDesc() ;
	String getPrivilegeType();
	String getModule();
}

