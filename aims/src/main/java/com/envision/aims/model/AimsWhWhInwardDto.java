package com.envision.aims.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

@Data
public class AimsWhWhInwardDto implements Serializable {
    private String id;
    private  String discomFrom;
    private  String receiver;
    private  String discomTo;
    private  String sender;
    private  String dcNo;
    private  Date dcDate;
    private  Date inwardDate;
    private  String itemGroup;
    private  String itemName;
    private  String itemMake;
    private  String itemType;
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
