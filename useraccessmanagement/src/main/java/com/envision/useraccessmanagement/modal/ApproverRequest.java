package com.envision.useraccessmanagement.modal;

import java.util.List;

import lombok.Data;

@Data
public class ApproverRequest {
	
		String approveReject;
		String remarks;
		List<ApplicationRequested> masterData;
}
