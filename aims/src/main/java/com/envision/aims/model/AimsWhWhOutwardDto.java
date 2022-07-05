package com.envision.aims.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AimsWhWhOutwardDto implements Serializable {
    private String id;
    private  String discomFrom;
    private  String sender;
    private  String discomTo;
    private  String receiver;
    private  String docNo;
    private Date docDate;
    private  Date dispatchDate;
    private  String itemGroup;
    private  String itemName;
    private  String itemMake;
    private  String itemType;
    private  Integer quantity;
    private  String itemStatus;
    private  String transporter;
    private  String lrNo;
    private  String vehicleNo;
    private  String driverName;
    private  String contactNo;
    private  String remark;
    private List<ImportFileDTO> itemDetails = new ArrayList<>();
}
