// Generated with g9.

package com.envision.useraccessmanagement.database.objects;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@Table(name="user_registration_approver_remarks", indexes={@Index(name="userRegistrationApproverRemarksUserRegistrationApproverRemarksUn", columnList="reference_id,approver_level", unique=true)})
@EntityListeners(AuditingEntityListener.class)
public class UserRegistrationApproverRemarks implements Serializable {

    /** Primary key. */
    protected static final String PK = "id";

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(unique=true, nullable=false, precision=19)
    private Long id;
    @Column(name="approver_level", nullable=false, precision=10)
    private Integer approverLevel;
    private String remarks;
    @CreatedBy
    @Column(name="created_by", nullable=false)
    private String createdBy;
    @CreatedDate
    @Column(name="created_date")
    private LocalDateTime createdDate;
    @ManyToOne(optional=false,cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name="reference_id", nullable=false)
    private UserRegistrationMasterTable userRegistrationMasterTable;

    /** Default constructor. */
    public UserRegistrationApproverRemarks() {
        super();
    }

    /**
     * Access method for id.
     *
     * @return the current value of id
     */
    public Long getId() {
        return id;
    }

    /**
     * Setter method for id.
     *
     * @param aId the new value for id
     */
    public void setId(Long aId) {
        id = aId;
    }

    /**
     * Access method for approverLevel.
     *
     * @return the current value of approverLevel
     */
    public Integer getApproverLevel() {
        return approverLevel;
    }

    /**
     * Setter method for approverLevel.
     *
     * @param aApproverLevel the new value for approverLevel
     */
    public void setApproverLevel(Integer aApproverLevel) {
        approverLevel = aApproverLevel;
    }

    /**
     * Access method for remarks.
     *
     * @return the current value of remarks
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * Setter method for remarks.
     *
     * @param aRemarks the new value for remarks
     */
    public void setRemarks(String aRemarks) {
        remarks = aRemarks;
    }

    /**
     * Access method for createdBy.
     *
     * @return the current value of createdBy
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * Setter method for createdBy.
     *
     * @param aCreatedBy the new value for createdBy
     */
    public void setCreatedBy(String aCreatedBy) {
        createdBy = aCreatedBy;
    }

    /**
     * Access method for createdDate.
     *
     * @return the current value of createdDate
     */
    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    /**
     * Setter method for createdDate.
     *
     * @param aCreatedDate the new value for createdDate
     */
    public void setCreatedDate(LocalDateTime aCreatedDate) {
        createdDate = aCreatedDate;
    }

    /**
     * Access method for userRegistrationMasterTable.
     *
     * @return the current value of userRegistrationMasterTable
     */
    public UserRegistrationMasterTable getUserRegistrationMasterTable() {
        return userRegistrationMasterTable;
    }

    /**
     * Setter method for userRegistrationMasterTable.
     *
     * @param aUserRegistrationMasterTable the new value for userRegistrationMasterTable
     */
    public void setUserRegistrationMasterTable(UserRegistrationMasterTable aUserRegistrationMasterTable) {
        userRegistrationMasterTable = aUserRegistrationMasterTable;
    }

    /**
     * Gets the group fragment referenceId for member userRegistrationMasterTable.
     *
     * @return Current value of the group fragment
     * @see UserRegistrationMasterTable
     */
    public String getUserRegistrationMasterTableReferenceId() {
        return (getUserRegistrationMasterTable() == null ? null : getUserRegistrationMasterTable().getReferenceId());
    }

    /**
     * Sets the group fragment referenceId from member userRegistrationMasterTable.
     *
     * @param aReferenceId New value for the group fragment
     * @see UserRegistrationMasterTable
     */
    public void setUserRegistrationMasterTableReferenceId(String aReferenceId) {
        if (getUserRegistrationMasterTable() != null) {
            getUserRegistrationMasterTable().setReferenceId(aReferenceId);
        }
    }

    /**
     * Compares the key for this instance with another UserRegistrationApproverRemarks.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class UserRegistrationApproverRemarks and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof UserRegistrationApproverRemarks)) {
            return false;
        }
        UserRegistrationApproverRemarks that = (UserRegistrationApproverRemarks) other;
        Object myId = this.getId();
        Object yourId = that.getId();
        if (myId==null ? yourId!=null : !myId.equals(yourId)) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another UserRegistrationApproverRemarks.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof UserRegistrationApproverRemarks)) return false;
        return this.equalKeys(other) && ((UserRegistrationApproverRemarks)other).equalKeys(this);
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
        StringBuffer sb = new StringBuffer("[UserRegistrationApproverRemarks |");
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
