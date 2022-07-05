// Generated with g9.

package com.envision.aims.entity;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="aims_supplier")
public class AimsSupplier implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(unique=true, nullable=false, precision=19)
    private Long id;
    @Column(nullable=false, length=100)
    private String supplier;
    @Column(name="contact_person", nullable=false, length=50)
    private String contactPerson;
    @Column(nullable=false, length=20)
    private String state;
    @Column(length=20)
    private String city;
    @Column(nullable=false, length=200)
    private String address;
    @Column(name="gstin_no", nullable=false, length=50)
    private String gstinNo;
    @Column(name="mobile_number", nullable=false, precision=10)
    private BigDecimal mobileNumber;
    @Column(nullable=false, length=50)
    private String email;
    @Column(length=200)
    private String remark;
    @Column(name="created_date")
    private LocalDateTime createdDate;
    @Column(name="created_by", length=150)
    private String createdBy;
    @Column(name="modified_by", length=150)
    private String modifiedBy;
    @Column(name="modified_date")
    private LocalDateTime modifiedDate;
    @Column(name="deactivated_date")
    private LocalDateTime deactivatedDate;
    @Column(nullable=false, length=20)
    private String status;
    @Column(name="supplier_code", length=200)
    private String supplierCode;

    @OneToMany( mappedBy = "supplier", fetch = FetchType.LAZY)
    private List<AimsSupplierGroupNameMapping> groups = new ArrayList();


}
