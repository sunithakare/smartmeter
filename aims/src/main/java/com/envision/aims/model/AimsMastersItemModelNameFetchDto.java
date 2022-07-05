package com.envision.aims.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AimsMastersItemModelNameFetchDto {

	 private String itemGroup;
	    private String itemCategory;
	    private String supplier;
	    private String itemModelName;
	    private String uom;
	    private String itemHsn;
	    private String itemDescription;
	    private String remarks;
	    private int warrantyMonth;
//	    private String status;
	    private LocalDateTime createdDate;
	    private String createdBy;
	    private String modifiedBy;
	    private LocalDateTime modifiedDate;
	    private LocalDateTime deactivatedDate;
	    private String itemModelCode;
}
