package com.envision.login.model;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SystemAccessRequest {
	 String module;
	 String desc;
	 String privilegeName;
	 String privilegeType;
}
