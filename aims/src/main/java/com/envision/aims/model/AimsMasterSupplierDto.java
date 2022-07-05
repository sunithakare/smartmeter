package com.envision.aims.model;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AimsMasterSupplierDto {
	private Long id;
	private String supplier;
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
	List<AimsSupplierGroupNameMappingDTO> groups;

}
