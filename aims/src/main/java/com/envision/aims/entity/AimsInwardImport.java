package com.envision.aims.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "aims_inward_from_supplier")
@Getter
@Setter
public class AimsInwardImport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reference_id", nullable = false, length = 50)
    private String id;

    @Column(name = "discom", length = 20)
    private String discom;

    @Column(name = "warehouse_name", length = 100)
    private String warehouseName;

    @Column(name = "supplier", length = 100)
    private String supplier;

    @Column(name = "invoice_no", length = 100)
    private String invoiceNo;

    @Column(name = "invoice_date")
    private LocalDateTime invoiceDate;

    @Column(name = "gin_no", length = 50)
    private String ginNo;

    @Column(name = "gin_date")
    private LocalDateTime ginDate;

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

    @Column(name = "item_status", length = 50)
    private String itemStatus;

    @Column(name = "transporter", length = 100)
    private String transporter;

    @Column(name = "lr_no", length = 50)
    private String lrNo;

    @Column(name = "vehicle_no", length = 100)
    private String vehicleNo;

    @Column(name = "driver_name", length = 100)
    private String driverName;

    @Column(name = "contact_no")
    private String contactNo;

    @Column(name = "remark", length = 500)
    private String remark;

//    @Column(name = "warranty_month", length = 50)
//    private String warrantyMonth;

    @Column(name="stock_type")
    private String stockType;

    @Column(name = "pdi_date")
    private LocalDateTime pdiDate;
    
    @Column(name="lr_date")
    private LocalDateTime lrDate;
    

    @Column(name="data_file_id")
    private Long  dataFileId;
    @Column(name="document_id")
    private Long documentId;

    @OneToMany(mappedBy="aimsInwardFromSupplier",fetch = FetchType.LAZY,orphanRemoval = true,cascade = CascadeType.ALL)
    private Set<AimsInwardImportDetail> aimsInwardSupplierDetails;

}