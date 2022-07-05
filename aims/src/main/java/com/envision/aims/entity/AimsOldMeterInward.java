package com.envision.aims.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "aims_old_meter_inward")
@Getter
@Setter
public class AimsOldMeterInward {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reference_id", nullable = false, length = 50)
    private String id;

    @Column(name = "discom", length = 20)
    private String discom;

    @Column(name = "warehouse", length = 100)
    private String warehouse;

    @Column(name = "subcontractor_type", length = 100)
    private String subcontractorType;

    @Column(name = "subcontractor", length = 100)
    private String subcontractor;

    @Column(name = "doc_no", length = 50)
    private String docNo;

    @Column(name = "invoice_date")
    private Date invoiceDate;

    @Column(name = "gin_no", length = 50)
    private String ginNo;

    @Column(name = "grn_date")
    private Date grnDate;

    @Column(name = "item_status", length = 20)
    private String itemStatus;

    @Column(name = "quantity", length = 20)
    private String quantity;

    @Column(name = "transporter", length = 50)
    private String transporter;

    @Column(name = "lr_no", length = 50)
    private String lrNo;

    @Column(name = "vehicle_no", length = 50)
    private String vehicleNo;

    @Column(name = "driver_name", length = 50)
    private String driverName;

    @Column(name = "contact_no")
    private String contactNo;

    @Column(name = "remark", length = 500)
    private String remark;
}