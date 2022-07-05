package com.envision.approvalprocess.modal;

public interface ApprovalStepsVO {
	 	Integer getStepNo();
	    String getRecordStage() ;
	    Integer getStepOnReject();
	    Integer getStepOnApproved();
	    String getApprovedStatus();
	    String getRejectStatus();
	    String getApproverIdentity();
}
