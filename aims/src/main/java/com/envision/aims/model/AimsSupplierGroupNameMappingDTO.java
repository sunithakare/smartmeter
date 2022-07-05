package com.envision.aims.model;

import com.envision.aims.entity.AimsSupplier;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class AimsSupplierGroupNameMappingDTO {

    private Long id;

    private String groupName;


}