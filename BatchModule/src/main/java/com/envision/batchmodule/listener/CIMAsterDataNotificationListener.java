package com.envision.batchmodule.listener;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CIMAsterDataNotificationListener extends JobExecutionListenerSupport {

	@Autowired
	public CIMAsterDataNotificationListener() {
	}

	@Override
	public void beforeJob(JobExecution jobExecution) {

		if (jobExecution.getStatus() == BatchStatus.STARTED) {
			log.info("!!! JOB Started !! Change FileNames to Process  "+jobExecution.getStatus());

			String batchFolder = "H:/Works/Envision/LTAPP/BatchFilesFolder";

			File folder = new File(batchFolder);

			if (folder.isDirectory()) {
				for (File originalFile : folder.listFiles(new FileFilter() {
					
					@Override
					public boolean accept(File pathname) {
						// TODO Auto-generated method stub
						return pathname.getName().endsWith(".csv");
					}
				})) {
					

					File batchFile = new File("H:/Works/Envision/LTAPP/BatchFilesFolder/" + jobExecution.getId());

					try {
						FileUtils.copyFileToDirectory(originalFile, batchFile);
//						FileUtils.moveFile(originalFile,
//								new File(originalFile.getAbsolutePath() + "_" + System.currentTimeMillis()));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}

		}

	}

	@Override 
	public void afterJob(JobExecution jobExecution) {
		if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
			log.info("!!! JOB FINISHED !! It's time to verify the results!!");

		}
	}
}