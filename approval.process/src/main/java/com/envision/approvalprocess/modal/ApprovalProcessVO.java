package com.envision.approvalprocess.modal;

import java.util.List;

public interface ApprovalProcessVO {
	String getProcessName();
	List<ApprovalStepsVO> getApprovalSteps();
}
