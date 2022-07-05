package com.envision.aims.model;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AimsInwardFromSupplierVO {
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
     String quantity;
     String itemStatus;
     String transporter;
     String lrNo;
     String vehicleNo;
     String driverName;
     String contactNo;
     String remark;
     String stockType;
     LocalDateTime pdiDate;
     LocalDateTime lrDate;
//     List<ImportFileDTO> importFileDTOList;

}
