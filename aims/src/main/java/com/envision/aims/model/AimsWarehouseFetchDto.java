package com.envision.aims.model;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AimsWarehouseFetchDto {
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
//    List<AimsWarehouseFieldsDto> warehouseItemList;
    
}
