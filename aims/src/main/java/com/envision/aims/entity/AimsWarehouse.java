// Generated with g9.

package com.envision.aims.entity;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="aims_warehouse")
public class AimsWarehouse implements Serializable {
   

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(unique=true, nullable=false, precision=19)
    private Long id;
    @Column(length=100)
    private String discom;
    @Column(name="warehouse_name", nullable=false, length=100)
    private String warehouseName;
    @Column(length=100)
    private String city;
    @Column(nullable=false, precision=10)
    private BigDecimal pincode;
    @Column(nullable=false, length=200)
    private String address;
    @Column(name="contact_person", nullable=false, length=100)
    private String contactPerson;
    @Column(name="contact_number", nullable=false, precision=10)
    private BigDecimal contactNumber;
    @Column(name="email_id", nullable=false, length=50)
    private String emailId;
    @Column(nullable=false, precision=10)
    private BigDecimal space;
    @Column(nullable=false, length=20)
    private String status;
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
    @Column(length=100)
    private String state;
    @Column(length=20)
    private String uom;
    @Column(name="warehouse_code", unique=true, length=200)
    private String warehouseCode;

    @OneToMany( mappedBy = "warehouse", fetch = FetchType.LAZY)
    private List<AimsCityWarehouseMapping> discomDetails;
}
