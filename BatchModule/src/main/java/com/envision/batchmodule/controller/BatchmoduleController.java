package com.envision.batchmodule.controller;

import java.util.Set;

import javax.batch.operations.JobOperator;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.envision.batchmodule.modal.JobStatus;

@RestController
@RequestMapping("batch")
public class BatchmoduleController {

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    Job importCiMasterDataJob;
    
    @Autowired
    JobExplorer jobExplorer;

    
	String jobName="importCiMasterDataJob";
	
	
    @GetMapping("/invoke/importCiMasterDataJob")
    @ResponseBody
    public void runCIMAsterDataBatch() throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
    	  jobLauncher.run(importCiMasterDataJob, new JobParameters());
	}
    

    @GetMapping("/getstatus/importCiMasterDataJob")
    @ResponseBody
    public JobStatus getCIMAsterDataBatchStatus() throws Exception {
    	JobStatus jobStatus=null;
    	Set<JobExecution> jobsSet= jobExplorer.findRunningJobExecutions(jobName);
    	if (jobsSet.size()>0) {
    		for (JobExecution jobExecution : jobsSet) {
    	
    			if (jobExecution.getStatus()!=BatchStatus.COMPLETED) {
    				StepExecution stepexec = jobExecution.getStepExecutions().iterator().next();
        			stepexec.getWriteCount();
        			stepexec.getProcessSkipCount();
        			jobStatus=new JobStatus(stepexec.getWriteCount(),stepexec.getProcessSkipCount());
        			return jobStatus;
//					throw new Exception("batch Still in progress");
				}
			}
		}
    	return jobStatus;
	}
}