package com.envision.aims.model;

import java.time.LocalDateTime;

import javax.persistence.Column;

import com.envision.aims.entity.AimsItemGroup;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AimsMastersItemCategoryDto {

     private Long id;
     private String itemCategory;
     private String remark;
     private String status;
     private String itemGroup;
	
}
