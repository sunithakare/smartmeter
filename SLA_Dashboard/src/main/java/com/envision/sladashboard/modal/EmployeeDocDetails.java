package com.envision.sladashboard.modal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDocDetails {

	String documentType;
	String documentName;
	Long documentId;
}
