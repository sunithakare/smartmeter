

package com.envision.common.database.objects;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Table(name="config_codes", indexes={@Index(name="configCodesConfigCodesIndx4", columnList="short_description,long_description"), @Index(name="configCodesConfigCodesUq1", columnList="code_type,short_code", unique=true)})
public class ConfigCodes implements Serializable {

    /** Primary key. */
    protected static final String PK = "codeId";

    @Id
    @Column(name="code_id", unique=true, nullable=false, precision=15)
    private BigDecimal codeId;
    @Column(name="code_type")
    private String codeType;
    @Column(name="sub_type")
    private String subType;
    @Column(name="short_code")
    private String shortCode;
    @Column(name="long_code")
    private String longCode;
    @Column(name="short_description", length=500)
    private String shortDescription;
    @Column(name="long_description")
    private String longDescription;
    @Column(name="code_group")
    private String codeGroup;
    @Column(name="numeric_value", precision=15, scale=2)
    private BigDecimal numericValue;
    private String remarks;
    @Column(name="valid_from")
    private LocalDateTime validFrom;
    @Column(name="valid_to")
    private LocalDateTime validTo;
    @Column(name="is_active", precision=1)
    private BigDecimal isActive;
    @Column(name="created_by", length=150)
    private String createdBy;
    @Column(name="created_at")
    private LocalDateTime createdAt;
    @Column(name="modified_by", length=150)
    private String modifiedBy;
    @Column(name="modified_at")
    private LocalDateTime modifiedAt;

    /** Default constructor. */
    public ConfigCodes() {
        super();
    }

    /**
     * Access method for codeId.
     *
     * @return the current value of codeId
     */
    public BigDecimal getCodeId() {
        return codeId;
    }

    /**
     * Setter method for codeId.
     *
     * @param aCodeId the new value for codeId
     */
    public void setCodeId(BigDecimal aCodeId) {
        codeId = aCodeId;
    }

    /**
     * Access method for codeType.
     *
     * @return the current value of codeType
     */
    public String getCodeType() {
        return codeType;
    }

    /**
     * Setter method for codeType.
     *
     * @param aCodeType the new value for codeType
     */
    public void setCodeType(String aCodeType) {
        codeType = aCodeType;
    }

    public String getSubType() {
		return subType;
	}

	public void setSubType(String subType) {
		this.subType = subType;
	}

	/**
     * Access method for shortCode.
     *
     * @return the current value of shortCode
     */
    public String getShortCode() {
        return shortCode;
    }

    /**
     * Setter method for shortCode.
     *
     * @param aShortCode the new value for shortCode
     */
    public void setShortCode(String aShortCode) {
        shortCode = aShortCode;
    }

    /**
     * Access method for longCode.
     *
     * @return the current value of longCode
     */
    public String getLongCode() {
        return longCode;
    }

    /**
     * Setter method for longCode.
     *
     * @param aLongCode the new value for longCode
     */
    public void setLongCode(String aLongCode) {
        longCode = aLongCode;
    }

    /**
     * Access method for shortDescription.
     *
     * @return the current value of shortDescription
     */
    public String getShortDescription() {
        return shortDescription;
    }

    /**
     * Setter method for shortDescription.
     *
     * @param aShortDescription the new value for shortDescription
     */
    public void setShortDescription(String aShortDescription) {
        shortDescription = aShortDescription;
    }

    /**
     * Access method for longDescription.
     *
     * @return the current value of longDescription
     */
    public String getLongDescription() {
        return longDescription;
    }

    /**
     * Setter method for longDescription.
     *
     * @param aLongDescription the new value for longDescription
     */
    public void setLongDescription(String aLongDescription) {
        longDescription = aLongDescription;
    }

    /**
     * Access method for codeGroup.
     *
     * @return the current value of codeGroup
     */
    public String getCodeGroup() {
        return codeGroup;
    }

    /**
     * Setter method for codeGroup.
     *
     * @param aCodeGroup the new value for codeGroup
     */
    public void setCodeGroup(String aCodeGroup) {
        codeGroup = aCodeGroup;
    }

    /**
     * Access method for numericValue.
     *
     * @return the current value of numericValue
     */
    public BigDecimal getNumericValue() {
        return numericValue;
    }

    /**
     * Setter method for numericValue.
     *
     * @param aNumericValue the new value for numericValue
     */
    public void setNumericValue(BigDecimal aNumericValue) {
        numericValue = aNumericValue;
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
     * Access method for validFrom.
     *
     * @return the current value of validFrom
     */
    public LocalDateTime getValidFrom() {
        return validFrom;
    }

    /**
     * Setter method for validFrom.
     *
     * @param aValidFrom the new value for validFrom
     */
    public void setValidFrom(LocalDateTime aValidFrom) {
        validFrom = aValidFrom;
    }

    /**
     * Access method for validTo.
     *
     * @return the current value of validTo
     */
    public LocalDateTime getValidTo() {
        return validTo;
    }

    /**
     * Setter method for validTo.
     *
     * @param aValidTo the new value for validTo
     */
    public void setValidTo(LocalDateTime aValidTo) {
        validTo = aValidTo;
    }

    /**
     * Access method for isActive.
     *
     * @return the current value of isActive
     */
    public BigDecimal getIsActive() {
        return isActive;
    }

    /**
     * Setter method for isActive.
     *
     * @param aIsActive the new value for isActive
     */
    public void setIsActive(BigDecimal aIsActive) {
        isActive = aIsActive;
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
     * Access method for createdAt.
     *
     * @return the current value of createdAt
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * Setter method for createdAt.
     *
     * @param aCreatedAt the new value for createdAt
     */
    public void setCreatedAt(LocalDateTime aCreatedAt) {
        createdAt = aCreatedAt;
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
     * Access method for modifiedAt.
     *
     * @return the current value of modifiedAt
     */
    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    /**
     * Setter method for modifiedAt.
     *
     * @param aModifiedAt the new value for modifiedAt
     */
    public void setModifiedAt(LocalDateTime aModifiedAt) {
        modifiedAt = aModifiedAt;
    }

    /**
     * Compares the key for this instance with another ConfigCodes.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class ConfigCodes and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof ConfigCodes)) {
            return false;
        }
        ConfigCodes that = (ConfigCodes) other;
        Object myCodeId = this.getCodeId();
        Object yourCodeId = that.getCodeId();
        if (myCodeId==null ? yourCodeId!=null : !myCodeId.equals(yourCodeId)) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another ConfigCodes.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof ConfigCodes)) return false;
        return this.equalKeys(other) && ((ConfigCodes)other).equalKeys(this);
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
        if (getCodeId() == null) {
            i = 0;
        } else {
            i = getCodeId().hashCode();
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
        StringBuffer sb = new StringBuffer("[ConfigCodes |");
        sb.append(" codeId=").append(getCodeId());
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
        ret.put("codeId", getCodeId());
        return ret;
    }

}
