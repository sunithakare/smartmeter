package com.envision.approvalprocess.database.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.envision.approvalprocess.database.objects.ApprovalSteps;

@Repository
public interface ApprovalStepsDAO extends JpaRepository<ApprovalSteps, Integer>  {  
	List<ApprovalSteps> findByApprovalProcessNameAndRecordStage(String processName,String recordStage);
}
