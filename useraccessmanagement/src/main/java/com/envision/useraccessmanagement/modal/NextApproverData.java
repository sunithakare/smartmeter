package com.envision.useraccessmanagement.modal;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NextApproverData {
	String name;
	String org;
	String empId;
	String email;
	String phone;
}
