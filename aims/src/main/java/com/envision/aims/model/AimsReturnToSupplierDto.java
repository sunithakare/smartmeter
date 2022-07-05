package com.envision.aims.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class AimsReturnToSupplierDto implements Serializable {
    //String id;
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
   // LocalDateTime warrantyDate;
      LocalDateTime lrDate;
    private List<ImportFileDTO> itemDetails = new ArrayList<>();
}
