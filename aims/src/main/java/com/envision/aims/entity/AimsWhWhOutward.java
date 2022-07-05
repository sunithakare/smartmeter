package com.envision.aims.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "aims_wh_wh_outward")
@Getter
@Setter
public class AimsWhWhOutward {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reference_id", nullable = false, length = 50)
    private String id;

    @Column(name = "discom_from", length = 50)
    private String discomFrom;

    @Column(name = "sender", length = 100)
    private String sender;

    @Column(name = "discom_to", length = 100)
    private String discomTo;

    @Column(name = "receiver", length = 100)
    private String receiver;

    @Column(name = "doc_no", length = 100)
    private String docNo;

    @Column(name = "doc_date")
    private Date docDate;

    @Column(name = "dispatch_date")
    private Date dispatchDate;

    @Column(name = "item_group", length = 100)
    private String itemGroup;

    @Column(name = "item_name", length = 100)
    private String itemName;

    @Column(name = "item_make", length = 100)
    private String itemMake;

    @Column(name = "item_type", length = 100)
    private String itemType;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "item_status", length = 100)
    private String itemStatus;

    @Column(name = "transporter", length = 100)
    private String transporter;

    @Column(name = "lr_no", length = 50)
    private String lrNo;

    @Column(name = "vehicle_no", length = 50)
    private String vehicleNo;

    @Column(name = "driver_name", length = 100)
    private String driverName;

    @Column(name = "contact_no")
    private String contactNo;

    @Column(name = "remark", length = 500)
    private String remark;

}