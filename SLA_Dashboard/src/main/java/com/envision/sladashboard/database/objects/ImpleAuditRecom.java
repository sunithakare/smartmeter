// Generated with g9.

package com.envision.sladashboard.database.objects;

import java.io.Serializable;
import java.time.LocalDate;
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

@Entity(name="imple_audit_recom")
@EntityListeners(AuditingEntityListener.class)
public class ImpleAuditRecom implements Serializable {

    /** Primary key. */
    protected static final String PK = "id";

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(unique=true, nullable=false, precision=19)
    private Long id;
    private String observations;
    @Column(name="observation_date")
    private LocalDate observationDate;
    private String recommendation;
    @Column(name="status_remark")
    private String statusRemark;
    @Column(name="clousure_status")
    private String clousureStatus;
    @Column(name="clousure_date")
    private LocalDate clousureDate;

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
    private String quarterly;
    @Column(length=10)
    private String year;
    @Column(name="discomdetail_code", length=255)
    private String discomdetailCode;
    @Column(name="statedetail_code", length=255)
    private String statedetailCode;

    /** Default constructor. */
    public ImpleAuditRecom() {
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
     * Access method for observations.
     *
     * @return the current value of observations
     */
    public String getObservations() {
        return observations;
    }

    /**
     * Setter method for observations.
     *
     * @param aObservations the new value for observations
     */
    public void setObservations(String aObservations) {
        observations = aObservations;
    }

    /**
     * Access method for observationDate.
     *
     * @return the current value of observationDate
     */
    public LocalDate getObservationDate() {
        return observationDate;
    }

    /**
     * Setter method for observationDate.
     *
     * @param aObservationDate the new value for observationDate
     */
    public void setObservationDate(LocalDate aObservationDate) {
        observationDate = aObservationDate;
    }

    /**
     * Access method for recommendation.
     *
     * @return the current value of recommendation
     */
    public String getRecommendation() {
        return recommendation;
    }

    /**
     * Setter method for recommendation.
     *
     * @param aRecommendation the new value for recommendation
     */
    public void setRecommendation(String aRecommendation) {
        recommendation = aRecommendation;
    }

    /**
     * Access method for statusRemark.
     *
     * @return the current value of statusRemark
     */
    public String getStatusRemark() {
        return statusRemark;
    }

    /**
     * Setter method for statusRemark.
     *
     * @param aStatusRemark the new value for statusRemark
     */
    public void setStatusRemark(String aStatusRemark) {
        statusRemark = aStatusRemark;
    }

    /**
     * Access method for clousureStatus.
     *
     * @return the current value of clousureStatus
     */
    public String getClousureStatus() {
        return clousureStatus;
    }

    /**
     * Setter method for clousureStatus.
     *
     * @param aClousureStatus the new value for clousureStatus
     */
    public void setClousureStatus(String aClousureStatus) {
        clousureStatus = aClousureStatus;
    }

    /**
     * Access method for clousureDate.
     *
     * @return the current value of clousureDate
     */
    public LocalDate getClousureDate() {
        return clousureDate;
    }

    /**
     * Setter method for clousureDate.
     *
     * @param aClousureDate the new value for clousureDate
     */
    public void setClousureDate(LocalDate aClousureDate) {
        clousureDate = aClousureDate;
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

    /**
     * Access method for quarterly.
     *
     * @return the current value of quarterly
     */
    public String getQuarterly() {
        return quarterly;
    }

    /**
     * Setter method for quarterly.
     *
     * @param aQuarterly the new value for quarterly
     */
    public void setQuarterly(String aQuarterly) {
        quarterly = aQuarterly;
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
     * Compares the key for this instance with another ImpleAuditRecom.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class ImpleAuditRecom and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof ImpleAuditRecom)) {
            return false;
        }
        ImpleAuditRecom that = (ImpleAuditRecom) other;
        Object myId = this.getId();
        Object yourId = that.getId();
        if (myId==null ? yourId!=null : !myId.equals(yourId)) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another ImpleAuditRecom.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof ImpleAuditRecom)) return false;
        return this.equalKeys(other) && ((ImpleAuditRecom)other).equalKeys(this);
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
        StringBuffer sb = new StringBuffer("[ImpleAuditRecom |");
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
