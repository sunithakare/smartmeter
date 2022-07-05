// Generated with g9.

package com.envision.approvalprocess.database.objects;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="approval_steps", indexes={@Index(name="approvalStepsApprovalStepsUn", columnList="approval_process,step_no", unique=true)})
public class ApprovalSteps implements Serializable {

    /** Primary key. */
    protected static final String PK = "id";

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(unique=true, nullable=false, precision=10)
    private Integer id;
    @Column(name="step_no", nullable=false, precision=10)
    private Integer stepNo;
    @Column(name="record_stage", nullable=false)
    private String recordStage;
    @Column(name="step_on_reject", precision=10)
    private Integer stepOnReject;
    @Column(name="step_on_approved", precision=10)
    private Integer stepOnApproved;
    @Column(name="approved_status", nullable=false)
    private String approvedStatus;
    @Column(name="reject_status", nullable=false)
    private String rejectStatus;
    private String desc;
    @Column(name="approver_identity")
    private String approverIdentity;

    @Column(name="approval_process")
    private String approvalProcessName;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="approval_process",referencedColumnName = "process_name",insertable = false,updatable = false)
    private ApprovalProcess approvalProcessList;

    /** Default constructor. */
    public ApprovalSteps() {
        super();
    }

    /**
     * Access method for id.
     *
     * @return the current value of id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Setter method for id.
     *
     * @param aId the new value for id
     */
    public void setId(Integer aId) {
        id = aId;
    }

    /**
     * Access method for stepNo.
     *
     * @return the current value of stepNo
     */
    public Integer getStepNo() {
        return stepNo;
    }

    /**
     * Setter method for stepNo.
     *
     * @param aStepNo the new value for stepNo
     */
    public void setStepNo(Integer aStepNo) {
        stepNo = aStepNo;
    }

    /**
     * Access method for recordStage.
     *
     * @return the current value of recordStage
     */
    public String getRecordStage() {
        return recordStage;
    }

    /**
     * Setter method for recordStage.
     *
     * @param aRecordStage the new value for recordStage
     */
    public void setRecordStage(String aRecordStage) {
        recordStage = aRecordStage;
    }

    /**
     * Access method for stepOnReject.
     *
     * @return the current value of stepOnReject
     */
    public Integer getStepOnReject() {
        return stepOnReject;
    }

    /**
     * Setter method for stepOnReject.
     *
     * @param aStepOnReject the new value for stepOnReject
     */
    public void setStepOnReject(Integer aStepOnReject) {
        stepOnReject = aStepOnReject;
    }

    /**
     * Access method for stepOnApproved.
     *
     * @return the current value of stepOnApproved
     */
    public Integer getStepOnApproved() {
        return stepOnApproved;
    }

    /**
     * Setter method for stepOnApproved.
     *
     * @param aStepOnApproved the new value for stepOnApproved
     */
    public void setStepOnApproved(Integer aStepOnApproved) {
        stepOnApproved = aStepOnApproved;
    }

    /**
     * Access method for approvedStatus.
     *
     * @return the current value of approvedStatus
     */
    public String getApprovedStatus() {
        return approvedStatus;
    }

    /**
     * Setter method for approvedStatus.
     *
     * @param aApprovedStatus the new value for approvedStatus
     */
    public void setApprovedStatus(String aApprovedStatus) {
        approvedStatus = aApprovedStatus;
    }

    /**
     * Access method for rejectStatus.
     *
     * @return the current value of rejectStatus
     */
    public String getRejectStatus() {
        return rejectStatus;
    }

    /**
     * Setter method for rejectStatus.
     *
     * @param aRejectStatus the new value for rejectStatus
     */
    public void setRejectStatus(String aRejectStatus) {
        rejectStatus = aRejectStatus;
    }

    /**
     * Access method for desc.
     *
     * @return the current value of desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Setter method for desc.
     *
     * @param aDesc the new value for desc
     */
    public void setDesc(String aDesc) {
        desc = aDesc;
    }

    public String getApprovalProcessName() {
		return approvalProcessName;
	}

	public void setApprovalProcessName(String approvalProcessName) {
		this.approvalProcessName = approvalProcessName;
	}

	/**
     * Access method for approverIdentity.
     *
     * @return the current value of approverIdentity
     */
    public String getApproverIdentity() {
        return approverIdentity;
    }

    /**
     * Setter method for approverIdentity.
     *
     * @param aApproverIdentity the new value for approverIdentity
     */
    public void setApproverIdentity(String aApproverIdentity) {
        approverIdentity = aApproverIdentity;
    }

    /**
     * Access method for approvalProcess.
     *
     * @return the current value of approvalProcess
     */
    public ApprovalProcess getApprovalProcess() {
        return approvalProcessList;
    }

    /**
     * Setter method for approvalProcess.
     *
     * @param aApprovalProcess the new value for approvalProcess
     */
    public void setApprovalProcess(ApprovalProcess aApprovalProcess) {
    	approvalProcessList = aApprovalProcess;
    }

    /**
     * Gets the group fragment id for member approvalProcess.
     *
     * @return Current value of the group fragment
     * @see ApprovalProcess
     */
    public Integer getApprovalProcessId() {
        return (getApprovalProcess() == null ? null : getApprovalProcess().getId());
    }

    /**
     * Sets the group fragment id from member approvalProcess.
     *
     * @param aId New value for the group fragment
     * @see ApprovalProcess
     */
    public void setApprovalProcessId(Integer aId) {
        if (getApprovalProcess() != null) {
            getApprovalProcess().setId(aId);
        }
    }

    /**
     * Compares the key for this instance with another ApprovalSteps.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class ApprovalSteps and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof ApprovalSteps)) {
            return false;
        }
        ApprovalSteps that = (ApprovalSteps) other;
        Object myId = this.getId();
        Object yourId = that.getId();
        if (myId==null ? yourId!=null : !myId.equals(yourId)) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another ApprovalSteps.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof ApprovalSteps)) return false;
        return this.equalKeys(other) && ((ApprovalSteps)other).equalKeys(this);
    }

    /**
     * Returns a hash code for this instance.
     *
     * @return Hash code
     */
    @Override
    public int hashCode() {
        int i;
        int result = 17;
        if (getId() == null) {
            i = 0;
        } else {
            i = getId().hashCode();
        }
        result = 37*result + i;
        return result;
    }

    /**
     * Returns a debug-friendly String representation of this instance.
     *
     * @return String representation of this instance
     */
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer("[ApprovalSteps |");
        sb.append(" id=").append(getId());
        sb.append("]");
        return sb.toString();
    }

    /**
     * Return all elements of the primary key.
     *
     * @return Map of key names to values
     */
    public Map<String, Object> getPrimaryKey() {
        Map<String, Object> ret = new LinkedHashMap<String, Object>(6);
        ret.put("id", getId());
        return ret;
    }

}
