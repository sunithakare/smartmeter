package com.envision.aims.model;

import java.time.LocalDateTime;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
//@NoArgsConstructor
public class AimsInwardFromSupplierListVO {
	  String id;
      String discom;
      String warehouseName;
      String supplier;
      String invoiceNo;
      LocalDateTime invoiceDate;
      String ginNo;
      LocalDateTime ginDate;
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
      LocalDateTime pdiDate;
     String stockType;
     LocalDateTime lrDate;
     Long documentId;

}
