package com.envision.sladashboard.database.projections;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SlaReportResponse {
	String module;
	String reportName;
	String rfpNoType;
	String timeframe;
	@JsonIgnore
	String path;
    boolean isActive;
    String reportType;
}
