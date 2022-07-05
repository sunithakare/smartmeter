package com.envision.aims.model;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

@Data
public class AimsInwardImportDto implements Serializable {
    private String id;
    private  String discom;
    private  String warehouseName;
    private  String supplier;
    private  String invoiceNo;
    private  LocalDateTime invoiceDate;
    private  String ginNo;
    private  LocalDateTime ginDate;
    private  String itemGroup;
    private  String itemModelName;
    private  String itemSupplier;
    private  String itemCategory;
    private  Integer quantity;
    private  String itemStatus;
    private  String transporter;
    private  String lrNo;
    private  String vehicleNo;
    private  String driverName;
    private  String contactNo;
    private  String remark;
    //private  String warrantyMonth;
    private  LocalDateTime pdiDate;
    private String stockType;
    private LocalDateTime lrDate;
    private List<ImportFileDTO> itemDetails = new ArrayList<>();
}
