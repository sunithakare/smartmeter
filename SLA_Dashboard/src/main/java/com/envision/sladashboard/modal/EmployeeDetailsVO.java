package com.envision.sladashboard.modal;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDetailsVO {

	Boolean active;
	LocalDateTime doj;
	LocalDateTime dor;
	String empId;
	String empName;
	String role;
	String roleCategory;
	String state;
	List<EmployeeDocDetails> documentList;
	
	
}
