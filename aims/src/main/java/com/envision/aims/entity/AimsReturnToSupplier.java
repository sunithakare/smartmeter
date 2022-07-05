package com.envision.aims.entity;


import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "aims_return_to_supplier")
@Getter
@Setter
public class AimsReturnToSupplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reference_id", nullable = false, length = 50)
    private String id;

    @Column(name = "discom", length = 50)
    private String discom;

    @Column(name = "warehouse_name", length = 100)
    private String warehouseName;

    @Column(name = "supplier", length = 100)
    private String supplier;

    @Column(name = "dc", length = 50)
    private String dc;

    @Column(name = "dc_date")
    private LocalDateTime dcDate;

    @Column(name = "dispatch_date")
    private LocalDateTime dispatchDate;

    @Column(name = "item_group", length = 100)
    private String itemGroup;

    @Column(name = "item_model_name", length = 100)
    private String itemModelName;

    @Column(name = "item_supplier", length = 100)
    private String itemSupplier;

    @Column(name = "item_category", length = 100)
    private String itemCategory;

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


    @Column(name = "lr_date")
    private LocalDateTime lrDate;
    
    @Column(name="data_file_id")
    private Long  dataFileId;
    
    @OneToMany(mappedBy="aimsReturnToSupplier",fetch=FetchType.LAZY,orphanRemoval = true,cascade = CascadeType.ALL)
    private Set<AimsReturnToSupplierDetail> aimsReturnToSupplierDetails;
    
}