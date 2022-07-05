// Generated with g9.

package com.envision.approvalprocess.database.objects;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="approval_process", indexes={@Index(name="approval_process_process_name_IX", columnList="process_name", unique=true)})
public class ApprovalProcess implements Serializable {

    /** Primary key. */
    protected static final String PK = "id";

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(unique=true, nullable=false, precision=10)
    private Integer id;
    @Column(name="process_name", unique=true, nullable=false)
    private String processName;
    @Column(nullable=false, length=1)
    private boolean active;
    private String desc;
    @OneToMany(mappedBy="approvalProcessList")
    private Set<ApprovalSteps> approvalSteps;

    /** Default constructor. */
    public ApprovalProcess() {
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
     * Access method for processName.
     *
     * @return the current value of processName
     */
    public String getProcessName() {
        return processName;
    }

    /**
     * Setter method for processName.
     *
     * @param aProcessName the new value for processName
     */
    public void setProcessName(String aProcessName) {
        processName = aProcessName;
    }

    /**
     * Access method for active.
     *
     * @return true if and only if active is currently true
     */
    public boolean getActive() {
        return active;
    }

    /**
     * Setter method for active.
     *
     * @param aActive the new value for active
     */
    public void setActive(boolean aActive) {
        active = aActive;
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

    /**
     * Access method for approvalSteps.
     *
     * @return the current value of approvalSteps
     */
    public Set<ApprovalSteps> getApprovalSteps() {
        return approvalSteps;
    }

    /**
     * Setter method for approvalSteps.
     *
     * @param aApprovalSteps the new value for approvalSteps
     */
    public void setApprovalSteps(Set<ApprovalSteps> aApprovalSteps) {
        approvalSteps = aApprovalSteps;
    }

    /**
     * Compares the key for this instance with another ApprovalProcess.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class ApprovalProcess and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof ApprovalProcess)) {
            return false;
        }
        ApprovalProcess that = (ApprovalProcess) other;
        Object myId = this.getId();
        Object yourId = that.getId();
        if (myId==null ? yourId!=null : !myId.equals(yourId)) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another ApprovalProcess.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof ApprovalProcess)) return false;
        return this.equalKeys(other) && ((ApprovalProcess)other).equalKeys(this);
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
        StringBuffer sb = new StringBuffer("[ApprovalProcess |");
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
