package com.envision.aims.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class AimsReturnToSupplierListVO {
	     String id;
	     String discom;
	     String warehouseName;
	     String supplier;
	     String dc;
	     LocalDateTime dcDate;
	     LocalDateTime dispatchDate;
	     String itemGroup;
	     String itemModelName;
	     String itemSupplier;
	     String itemCategory;
	     Integer quantity;
	     String itemStatus;
	     String transporter;
	     String lrNo;
	     String vehicleNo;
	     String driverName;
	     String contactNo;
	     String remark;
	     LocalDateTime lrDate;

}
