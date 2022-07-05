package com.envision.aims.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "aims_outward_import")
@Getter
@Setter
public class AimsOutwardImport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reference_id", nullable = false, length = 50)
    private String id;

    @Column(name = "discom", length = 50)
    private String discom;

    @Column(name = "warehouse_name", length = 100)
    private String warehouseName;

    @Column(name = "subcontractor_type", length = 50)
    private String subcontractorType;

    @Column(name = "subcontractor", length = 50)
    private String subcontractor;

    @Column(name = "indent_no")
    private Integer indentNo;

    @Column(name = "indent_date")
    private Date indentDate;

    @Column(name = "dc_no", length = 50)
    private String dcNo;

    @Column(name = "dispatch_date")
    private Date dispatchDate;

    @Column(name = "item_group", length = 50)
    private String itemGroup;

    @Column(name = "item_name", length = 100)
    private String itemName;

    @Column(name = "item_make", length = 100)
    private String itemMake;

    @Column(name = "item_type", length = 100)
    private String itemType;

    @Column(name = "item_status", length = 100)
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