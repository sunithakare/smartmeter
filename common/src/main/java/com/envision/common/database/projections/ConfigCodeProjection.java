package com.envision.common.database.projections;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ConfigCodeProjection {
	String codeType;
	String shortCode;
	String shortDescription;
}
