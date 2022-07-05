package com.envision.sladashboard.database.projections;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Value;

public interface EmployeeCreationProjection {

	String  getEmployeeName();
	String getEmployeeId();
	LocalDateTime getDoj();
	LocalDateTime getDor();
	String getState();
	Boolean getActive();
	@Value("#{target.employeeRoles.roleName}")
	String getRoleName();
	@Value("#{target.employeeRoles.roleCategory}")
	String getRoleCategory();
}
