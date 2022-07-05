package com.envision.aims.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.Instant;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "aims_city_warehouse_mapping")
public class AimsCityWarehouseMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "discom", length = 100)
    private String discom;

    @Column(name = "city", length = 100)
    private String city;

    @Column(name = "state", nullable = false, length = 100)
    private String state;

    @Column(name = "created_date")
    private Instant createdDate;

    @Column(name = "created_by", length = 150)
    private String createdBy;

    @Column(name = "modified_by", length = 150)
    private String modifiedBy;

    @Column(name = "modified_date")
    private Instant modifiedDate;

    @Column(name = "deactivated_date")
    private Instant deactivatedDate;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "warehouse_id", nullable = false)
    private AimsWarehouse warehouse;

}