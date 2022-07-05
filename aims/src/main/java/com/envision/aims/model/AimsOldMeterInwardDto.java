package com.envision.aims.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

@Data
public class AimsOldMeterInwardDto implements Serializable {
    private String id;
    private  String discom;
    private  String warehouse;
    private  String subcontractorType;
    private  String subcontractor;
    private  String docNo;
    private  Date invoiceDate;
    private  String ginNo;
    private  Date grnDate;
    private  String itemStatus;
    private  String quantity;
    private  String transporter;
    private  String lrNo;
    private  String vehicleNo;
    private  String driverName;
    private  String contactNo;
    private  String remark;
    private List<ImportFileDTO> itemDetails = new ArrayList<>();
}
