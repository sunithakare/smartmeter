package com.envision.login.database.projections;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SystemUserListResponse {
	 Long id;
	 String userName;
	 String employeeId;
	 String firstName;
	 
}
