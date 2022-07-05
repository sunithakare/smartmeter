package com.envision.aims.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AimsMastersItemCategoryVO {
	Long id;
    String itemCategory;
    String remark;
    String status;
    String itemGroup;
}
