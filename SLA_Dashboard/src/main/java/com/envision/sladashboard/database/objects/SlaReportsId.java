package com.envision.sladashboard.database.objects;

import java.io.Serializable;

public class SlaReportsId implements Serializable {
    String module;
    String reportName;
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	public String getReportName() {
		return reportName;
	}
	public void setReportName(String reportName) {
		this.reportName = reportName;
	}
}