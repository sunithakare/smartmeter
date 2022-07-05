package com.envision.aims.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

@Data
public class AimsOutwardImportDto implements Serializable {
    private String id;
    private  String discom;
    private  String warehouseName;
    private  String subcontractorType;
    private  String subcontractor;
    private  Integer indentNo;
    private  Date indentDate;
    private  String dcNo;
    private  Date dispatchDate;
    private  String itemGroup;
    private  String itemName;
    private  String itemMake;
    private  String itemType;
    private  String itemStatus;
    private  Integer quantity;
    private  String transporter;
    private  String lrNo;
    private  String vehicleNo;
    private  String driverName;
    private  String contactNo;
    private  String remark;
    private List<ImportFileDTO> itemDetails = new ArrayList<>();
}
