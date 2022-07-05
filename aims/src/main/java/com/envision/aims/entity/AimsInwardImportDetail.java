package com.envision.aims.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name = "aims_inward_supplier_details")
@Getter
@Setter
@NoArgsConstructor
public class AimsInwardImportDetail {


    @Column(name = "sr_no", length = 50)
    private String srNo;

    @Column(name = "box_number", length = 50)
    private String boxNumber;

    @Column(name = "internal_sr_no", length = 50)
    private String internalSrNo;

    @Column(name = "latest_sr_no", length = 50)
    private String latestSrNo;

    @Column(name = "remarks", length = 4000)
    private String remarks;

    @Column(name = "inward_supplier_id", nullable = false,insertable = false,updatable = false)
    private String inwardImport;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    
   
    @ManyToOne(optional=false,fetch = FetchType.LAZY)
    @JoinColumn(name="inward_supplier_id", nullable=false)
    private AimsInwardImport aimsInwardFromSupplier;

}