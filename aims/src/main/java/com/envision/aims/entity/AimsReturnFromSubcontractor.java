package com.envision.aims.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "aims_return_from_subcontractor")
@Getter
@Setter
public class AimsReturnFromSubcontractor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "referene_id", nullable = false, length = 50)
    private String id;

    @Column(name = "discom", length = 50)
    private String discom;

    @Column(name = "warehouse_name", length = 50)
    private String warehouseName;

    @Column(name = "subcontractor_type", length = 100)
    private String subcontractorType;

    @Column(name = "subcontractor", length = 100)
    private String subcontractor;

    @Column(name = "doc_no")
    private Integer docNo;

    @Column(name = "date")
    private Date date;

    @Column(name = "srn_no", length = 100)
    private String srnNo;

    @Column(name = "srn_date")
    private Date srnDate;

    @Column(name = "item_group", length = 50)
    private String itemGroup;

    @Column(name = "item_name", length = 100)
    private String itemName;

    @Column(name = "item_make", length = 100)
    private String itemMake;

    @Column(name = "item_type", length = 50)
    private String itemType;

    @Column(name = "item_status", length = 50)
    private String itemStatus;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "transporter", length = 100)
    private String transporter;

    @Column(name = "lr_no", length = 100)
    private String lrNo;

    @Column(name = "vehicle_no", length = 100)
    private String vehicleNo;

    @Column(name = "driver_name", length = 100)
    private String driverName;

    @Column(name = "contact_no")
    private String contactNo;

    @Column(name = "remark", length = 500)
    private String remark;
}