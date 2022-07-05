package com.envision.aims.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AimsWareHouseFetchAllListDto {
	String discom;
    String warehouseName;
    BigDecimal pincode;
    String address;
    String contactPerson;
    BigDecimal contactNumber;
    String emailId;
    BigDecimal space;
    String status;
    String uom;
    String city;
    String state;
    String warehouseCode;
}
