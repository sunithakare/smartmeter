package com.envision.common.database.projections;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DiscomDetailsResponse {
	String discomCode;
	String discomName;
	String city;
	String state;
}
