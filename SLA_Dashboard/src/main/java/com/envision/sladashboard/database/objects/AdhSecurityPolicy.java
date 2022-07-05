// Generated with g9.

package com.envision.sladashboard.database.objects;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity(name="adh_security_policy")
@EntityListeners(AuditingEntityListener.class)
public class AdhSecurityPolicy implements Serializable {

    /** Primary key. */
    protected static final String PK = "id";

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(unique=true, nullable=false, precision=19)
    private Long id;
    @Column(name="section_title")
    private String sectionTitle;
    @Column(name="policy_statement")
    private String policyStatement;
    @Column(name="adherence_status")
    private String adherenceStatus;
    @Column(name="supporting_document")
    private String supportingDocument;
    @Column(name="statedetail_code", length=25)
    private String statedetailCode;
    @Column(name="discomdetail_code", length=25)
    private String discomdetailCode;

    @CreatedBy
    @Column(name="created_by", length=150)
    private String createdBy;
    @CreatedDate
    @Column(name="created_date")
    private LocalDateTime createdDate;
    @LastModifiedBy
    @Column(name="modified_by", length=150)
    private String modifiedBy;
    @LastModifiedDate
    @Column(name="modified_date")
    private LocalDateTime modifiedDate;
    @Column(length=50)
    private String quarterly;
    @Column(length=50)
    private String year;

    /** Default constructor. */
    public AdhSecurityPolicy() {
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
     * Access method for sectionTitle.
     *
     * @return the current value of sectionTitle
     */
    public String getSectionTitle() {
        return sectionTitle;
    }

    /**
     * Setter method for sectionTitle.
     *
     * @param aSectionTitle the new value for sectionTitle
     */
    public void setSectionTitle(String aSectionTitle) {
        sectionTitle = aSectionTitle;
    }

    /**
     * Access method for policyStatement.
     *
     * @return the current value of policyStatement
     */
    public String getPolicyStatement() {
        return policyStatement;
    }

    /**
     * Setter method for policyStatement.
     *
     * @param aPolicyStatement the new value for policyStatement
     */
    public void setPolicyStatement(String aPolicyStatement) {
        policyStatement = aPolicyStatement;
    }

    /**
     * Access method for adherenceStatus.
     *
     * @return the current value of adherenceStatus
     */
    public String getAdherenceStatus() {
        return adherenceStatus;
    }

    /**
     * Setter method for adherenceStatus.
     *
     * @param aAdherenceStatus the new value for adherenceStatus
     */
    public void setAdherenceStatus(String aAdherenceStatus) {
        adherenceStatus = aAdherenceStatus;
    }

    /**
     * Access method for supportingDocument.
     *
     * @return the current value of supportingDocument
     */
    public String getSupportingDocument() {
        return supportingDocument;
    }

    /**
     * Setter method for supportingDocument.
     *
     * @param aSupportingDocument the new value for supportingDocument
     */
    public void setSupportingDocument(String aSupportingDocument) {
        supportingDocument = aSupportingDocument;
    }

    /**
     * Access method for statedetailCode.
     *
     * @return the current value of statedetailCode
     */
    public String getStatedetailCode() {
        return statedetailCode;
    }

    /**
     * Setter method for statedetailCode.
     *
     * @param aStatedetailCode the new value for statedetailCode
     */
    public void setStatedetailCode(String aStatedetailCode) {
        statedetailCode = aStatedetailCode;
    }

    /**
     * Access method for discomdetailCode.
     *
     * @return the current value of discomdetailCode
     */
    public String getDiscomdetailCode() {
        return discomdetailCode;
    }

    /**
     * Setter method for discomdetailCode.
     *
     * @param aDiscomdetailCode the new value for discomdetailCode
     */
    public void setDiscomdetailCode(String aDiscomdetailCode) {
        discomdetailCode = aDiscomdetailCode;
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

    public String getQuarterly() {
		return quarterly;
	}

	public void setQuarterly(String quarterly) {
		this.quarterly = quarterly;
	}

	/**
     * Access method for year.
     *
     * @return the current value of year
     */
    public String getYear() {
        return year;
    }

    /**
     * Setter method for year.
     *
     * @param aYear the new value for year
     */
    public void setYear(String aYear) {
        year = aYear;
    }

    /**
     * Compares the key for this instance with another AdhSecurityPolicy.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class AdhSecurityPolicy and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof AdhSecurityPolicy)) {
            return false;
        }
        AdhSecurityPolicy that = (AdhSecurityPolicy) other;
        Object myId = this.getId();
        Object yourId = that.getId();
        if (myId==null ? yourId!=null : !myId.equals(yourId)) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another AdhSecurityPolicy.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof AdhSecurityPolicy)) return false;
        return this.equalKeys(other) && ((AdhSecurityPolicy)other).equalKeys(this);
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
        StringBuffer sb = new StringBuffer("[AdhSecurityPolicy |");
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
