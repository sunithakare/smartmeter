// Generated with g9.

package com.envision.approvalprocess.database.objects;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Table(name="approver_details", indexes={@Index(name="approverDetailsApproverDetailsUn", columnList="approver_identity", unique=true)})
public class ApproverDetails implements Serializable {

    /** Primary key. */
    protected static final String PK = "id";

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(unique=true, nullable=false, precision=10)
    private Integer id;
    @Column(name="approver_identity", nullable=false)
    private String approverIdentity;
    @Column(name="approval_for")
    private String approvalFor;
    @Column(name="approver_filter")
    private String approverFilter;
    @Column(name="user_name", nullable=false)
    private String userName;

    /** Default constructor. */
    public ApproverDetails() {
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
     * Access method for approvalFor.
     *
     * @return the current value of approvalFor
     */
    public String getApprovalFor() {
        return approvalFor;
    }

    /**
     * Setter method for approvalFor.
     *
     * @param aApprovalFor the new value for approvalFor
     */
    public void setApprovalFor(String aApprovalFor) {
        approvalFor = aApprovalFor;
    }

    /**
     * Access method for approverFilter.
     *
     * @return the current value of approverFilter
     */
    public String getApproverFilter() {
        return approverFilter;
    }

    public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
     * Setter method for approverFilter.
     *
     * @param aApproverFilter the new value for approverFilter
     */
    public void setApproverFilter(String aApproverFilter) {
        approverFilter = aApproverFilter;
    }

    /**
     * Compares the key for this instance with another ApproverDetails.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class ApproverDetails and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof ApproverDetails)) {
            return false;
        }
        ApproverDetails that = (ApproverDetails) other;
        Object myId = this.getId();
        Object yourId = that.getId();
        if (myId==null ? yourId!=null : !myId.equals(yourId)) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another ApproverDetails.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof ApproverDetails)) return false;
        return this.equalKeys(other) && ((ApproverDetails)other).equalKeys(this);
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
        StringBuffer sb = new StringBuffer("[ApproverDetails |");
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
