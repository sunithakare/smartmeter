package com.envision.aims.model;

import com.envision.aims.entity.AimsWarehouse;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.Instant;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class AimsCityWarehouseMappingDTO {

    private Long id;

    private String discom;

    private String city;

    private String state;

    private Instant createdDate;

    private String createdBy;

    private String modifiedBy;

    private Instant modifiedDate;

    private Instant deactivatedDate;

}