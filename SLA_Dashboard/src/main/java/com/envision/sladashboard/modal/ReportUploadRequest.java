package com.envision.sladashboard.modal;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ReportUploadRequest {
	
	String quarter;
	String year;
	String type;
	String report;
	String state;
	String discom;
	
//	MultipartFile file;
	
}
