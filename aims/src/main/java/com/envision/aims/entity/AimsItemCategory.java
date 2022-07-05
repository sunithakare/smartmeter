package com.envision.aims.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="aims_item_category")
@EntityListeners(AuditingEntityListener.class)
public class AimsItemCategory implements Serializable {

    /** Primary key. */
    protected static final String PK = "id";

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(unique=true, nullable=false, precision=19)
    private Long id;
    @Column(length=150)
    private String remark;
    @Column(nullable=false, length=20)
    private String status;
    @CreatedDate
    @Column(name="created_date")
    private LocalDateTime createdDate;
    @CreatedBy
    @Column(name="created_by", length=150)
    private String createdBy;
    @LastModifiedBy
    @Column(name="modified_by", length=150)
    private String modifiedBy;
    @LastModifiedDate
    @Column(name="modified_date")
    private LocalDateTime modifiedDate;
    @Column(name="deactivated_date")
    private LocalDateTime deactivatedDate;
    @Column(name="item_group", nullable=false, length=100)
    private String itemGroup;
    @Column(name="item_category", unique=true, nullable=false, length=100)
    private String itemCategory;

}
