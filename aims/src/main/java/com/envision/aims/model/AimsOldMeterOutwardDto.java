package com.envision.aims.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

@Data
public class AimsOldMeterOutwardDto implements Serializable {
    private String id;
    private  String discom;
    private  String warehouse;
    private  String docNo;
    private  Date docDate;
    private  String dcNo;
    private  Date dcDate;
    private  String itemStatus;
    private  String quantity;
    private  String lrNo;
    private  String transporter;
    private  String vehicleNo;
    private  String driverName;
    private  String contactNo;
    private  String remark;
    private List<ImportFileDTO> itemDetails = new ArrayList<>();
}
