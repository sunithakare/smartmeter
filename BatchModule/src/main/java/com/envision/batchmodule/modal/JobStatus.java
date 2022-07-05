package com.envision.batchmodule.modal;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JobStatus {
	
	Integer writeCount;
	Integer skipCount;
}
