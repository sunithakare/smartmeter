// Generated with g9.

package com.envision.aims.entity;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="aims_item_model_name")
public class AimsItemModelName implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(unique=true, nullable=false, precision=19)
    private long id;
    @Column(name="item_group", nullable=false, length=100)
    private String itemGroup;
    @Column(name="item_category", nullable=false, length=100)
    private String itemCategory;
    @Column(nullable=false, length=100)
    private String supplier;
    @Column(name="item_model_name", unique=true, nullable=false, length=100)
    private String itemModelName;
    @Column(nullable=false, length=100)
    private String uom;
    @Column(name="item_hsn", nullable=false, length=100)
    private String itemHsn;
    @Column(name="item_description", nullable=false, length=200)
    private String itemDescription;
    @Column(length=200)
    private String remarks;
    @Column(name="warranty_month", nullable=false, precision=10)
    private int warrantyMonth;
//    @Column(nullable=false, length=20)
//    private String status;
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
    @Column(name="item_model_code", length=200)
    private String itemModelCode;

}
