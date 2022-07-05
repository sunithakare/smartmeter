package com.envision.approvalprocess.database.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.envision.approvalprocess.database.objects.ApprovalProcess;
import com.envision.approvalprocess.modal.ApprovalProcessVO;

@Repository
public interface ApprovalProcessDAO extends JpaRepository<ApprovalProcess, Integer>  {  
	ApprovalProcessVO findByProcessName(String processName);
}
