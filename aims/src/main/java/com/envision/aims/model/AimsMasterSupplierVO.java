package com.envision.aims.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AimsMasterSupplierVO {
	private String supplier;
	private String itemGroup;
	private String contactPerson;
	private String state;
	private String supplierCode;
	private String city;
	private String address;
	private BigDecimal mobileNumber;
	private String email;
	private String status;
	private String gstinNo;
	private String remark;
}
