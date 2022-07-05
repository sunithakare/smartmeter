package com.envision.sladashboard.modal;

import lombok.Data;

@Data
public class ReportDownloadRequest {

	String discom;
	String state;
	String month;
	String year;
	String day;
	String reportName;
	String reportModule;
	String quarter;
}
