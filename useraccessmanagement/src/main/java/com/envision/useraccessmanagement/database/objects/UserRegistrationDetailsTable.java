// Generated with g9.

package com.envision.useraccessmanagement.database.objects;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity(name="user_registration_details_table")
@EntityListeners(AuditingEntityListener.class)
public class UserRegistrationDetailsTable implements Serializable {

    /** Primary key. */
    protected static final String PK = "id";

    @Column(name="application_name", length=100)
    private String applicationName;
    @Column(name="access_validity_from")
    private LocalDateTime accessValidityFrom;
    @Column(name="access_validity_to")
    private LocalDateTime accessValidityTo;
    @Column(name="last_approval_status", length=100)
    private String lastApprovalStatus;
    @Column(name="access_type", length=50)
    private String accessType;
    @Column(length=50)
    private String function;
    @Column(name="required")
    private Boolean required;
    private LocalDate date;
    @Column(name="lnt_approve_remarks", length=1000)
    private String lntApproveRemarks;
    @Column(name="eesl_intellsmart_appr_remarks")
    private String eeslIntellsmartApprRemarks;
    @Column(length=1000)
    private String remarks;
    @CreatedDate
    @Column(name="created_date")
    private LocalDateTime createdDate;
    @CreatedBy
    @Column(name="created_by", length=25)
    private String createdBy;
    @LastModifiedBy
    @Column(name="modified_by", length=25)
    private String modifiedBy;
    @LastModifiedDate
    @Column(name="modified_date")
    private LocalDateTime modifiedDate;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(unique=true, nullable=false, precision=19)
    private Long id;
    @ManyToOne(optional=false,fetch = FetchType.LAZY)
    @JoinColumn(name="reference_id", nullable=false)
    private UserRegistrationMasterTable userRegistrationMasterTable;

    /** Default constructor. */
    public UserRegistrationDetailsTable() {
        super();
    }

  
    /**
     * Access method for applicationName.
     *
     * @return the current value of applicationName
     */
    public String getApplicationName() {
        return applicationName;
    }

    /**
     * Setter method for applicationName.
     *
     * @param aApplicationName the new value for applicationName
     */
    public void setApplicationName(String aApplicationName) {
        applicationName = aApplicationName;
    }

    /**
     * Access method for accessValidityFrom.
     *
     * @return the current value of accessValidityFrom
     */
    public LocalDateTime getAccessValidityFrom() {
        return accessValidityFrom;
    }

    /**
     * Setter method for accessValidityFrom.
     *
     * @param aAccessValidityFrom the new value for accessValidityFrom
     */
    public void setAccessValidityFrom(LocalDateTime aAccessValidityFrom) {
        accessValidityFrom = aAccessValidityFrom;
    }

    /**
     * Access method for accessValidityTo.
     *
     * @return the current value of accessValidityTo
     */
    public LocalDateTime getAccessValidityTo() {
        return accessValidityTo;
    }

    /**
     * Setter method for accessValidityTo.
     *
     * @param aAccessValidityTo the new value for accessValidityTo
     */
    public void setAccessValidityTo(LocalDateTime aAccessValidityTo) {
        accessValidityTo = aAccessValidityTo;
    }

    /**
     * Access method for lastApprovalStatus.
     *
     * @return the current value of lastApprovalStatus
     */
    public String getLastApprovalStatus() {
        return lastApprovalStatus;
    }

    /**
     * Setter method for lastApprovalStatus.
     *
     * @param aLastApprovalStatus the new value for lastApprovalStatus
     */
    public void setLastApprovalStatus(String aLastApprovalStatus) {
        lastApprovalStatus = aLastApprovalStatus;
    }



    /**
     * Access method for accessType.
     *
     * @return the current value of accessType
     */
    public String getAccessType() {
        return accessType;
    }

    /**
     * Setter method for accessType.
     *
     * @param aAccessType the new value for accessType
     */
    public void setAccessType(String aAccessType) {
        accessType = aAccessType;
    }

    /**
     * Access method for function.
     *
     * @return the current value of function
     */
    public String getFunction() {
        return function;
    }

    /**
     * Setter method for function.
     *
     * @param aFunction the new value for function
     */
    public void setFunction(String aFunction) {
        function = aFunction;
    }

    public Boolean getRequired() {
		return required;
	}


	public void setRequired(Boolean required) {
		this.required = required;
	}


	/**
     * Access method for date.
     *
     * @return the current value of date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Setter method for date.
     *
     * @param aDate the new value for date
     */
    public void setDate(LocalDate aDate) {
        date = aDate;
    }

    /**
     * Access method for lntApproveRemarks.
     *
     * @return the current value of lntApproveRemarks
     */
    public String getLntApproveRemarks() {
        return lntApproveRemarks;
    }

    /**
     * Setter method for lntApproveRemarks.
     *
     * @param aLntApproveRemarks the new value for lntApproveRemarks
     */
    public void setLntApproveRemarks(String aLntApproveRemarks) {
        lntApproveRemarks = aLntApproveRemarks;
    }

    /**
     * Access method for eeslIntellsmartApprRemarks.
     *
     * @return the current value of eeslIntellsmartApprRemarks
     */
    public String getEeslIntellsmartApprRemarks() {
        return eeslIntellsmartApprRemarks;
    }

    /**
     * Setter method for eeslIntellsmartApprRemarks.
     *
     * @param aEeslIntellsmartApprRemarks the new value for eeslIntellsmartApprRemarks
     */
    public void setEeslIntellsmartApprRemarks(String aEeslIntellsmartApprRemarks) {
        eeslIntellsmartApprRemarks = aEeslIntellsmartApprRemarks;
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
     * Access method for modifiedBy.
     *
     * @return the current value of modifiedBy
     */
    public String getModifiedBy() {
        return modifiedBy;
    }

    /**
     * Setter method for modifiedBy.
     *
     * @param aModifiedBy the new value for modifiedBy
     */
    public void setModifiedBy(String aModifiedBy) {
        modifiedBy = aModifiedBy;
    }

    /**
     * Access method for modifiedDate.
     *
     * @return the current value of modifiedDate
     */
    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    /**
     * Setter method for modifiedDate.
     *
     * @param aModifiedDate the new value for modifiedDate
     */
    public void setModifiedDate(LocalDateTime aModifiedDate) {
        modifiedDate = aModifiedDate;
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
     * Compares the key for this instance with another UserRegistrationDetailsTable.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class UserRegistrationDetailsTable and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof UserRegistrationDetailsTable)) {
            return false;
        }
        UserRegistrationDetailsTable that = (UserRegistrationDetailsTable) other;
        Object myId = this.getId();
        Object yourId = that.getId();
        if (myId==null ? yourId!=null : !myId.equals(yourId)) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another UserRegistrationDetailsTable.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof UserRegistrationDetailsTable)) return false;
        return this.equalKeys(other) && ((UserRegistrationDetailsTable)other).equalKeys(this);
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
        StringBuffer sb = new StringBuffer("[UserRegistrationDetailsTable |");
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
