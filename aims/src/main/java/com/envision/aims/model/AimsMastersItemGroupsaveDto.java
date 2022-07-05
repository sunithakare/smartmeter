package com.envision.aims.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AimsMastersItemGroupsaveDto {

    private Long id;
	private String itemGroup;
    private String transactionMechnsm;
    private String errorCode;
    private String errorDescription;
    private List<AimsMastersUserErrorMsgDto> errors;

}
