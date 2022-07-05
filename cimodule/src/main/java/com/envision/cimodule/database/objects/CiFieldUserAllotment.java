// Generated with g9.

package com.envision.cimodule.database.objects;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Table(name="ci_field_user_allotment", indexes={@Index(name="ciFieldUserAllotmentCiFieldUseridAgencyDivisionIdUn", columnList="agency,user_id,division_id", unique=true)})
public class CiFieldUserAllotment implements Serializable {

    /** Primary key. */
    protected static final String PK = "userId";

    @Column(name="state_id", length=100)
    private String stateId;
    @Column(name="discom_id", length=100)
    private String discomId;
    @Column(name="allocation_datatype", length=100)
    private String allocationDatatype;
    @Column(length=100)
    private String agency;
    @Id
    @Column(name="user_id", unique=true, nullable=false, length=100)
    private String userId;
    @Column(name="mobile_nbr", precision=19)
    private long mobileNbr;
    @Column(name="division_id", length=100)
    private String divisionId;
    @Column(length=50)
    private String allocation;
    @Column(length=100)
    private String city;
    @Column(unique=true, precision=19)
    private long id;
    @Column(name="allocation_id", length=255)
    private String allocationId;

    /** Default constructor. */
    public CiFieldUserAllotment() {
        super();
    }

    /**
     * Access method for stateId.
     *
     * @return the current value of stateId
     */
    public String getStateId() {
        return stateId;
    }

    /**
     * Setter method for stateId.
     *
     * @param aStateId the new value for stateId
     */
    public void setStateId(String aStateId) {
        stateId = aStateId;
    }

    /**
     * Access method for discomId.
     *
     * @return the current value of discomId
     */
    public String getDiscomId() {
        return discomId;
    }

    /**
     * Setter method for discomId.
     *
     * @param aDiscomId the new value for discomId
     */
    public void setDiscomId(String aDiscomId) {
        discomId = aDiscomId;
    }

    /**
     * Access method for allocationDatatype.
     *
     * @return the current value of allocationDatatype
     */
    public String getAllocationDatatype() {
        return allocationDatatype;
    }

    /**
     * Setter method for allocationDatatype.
     *
     * @param aAllocationDatatype the new value for allocationDatatype
     */
    public void setAllocationDatatype(String aAllocationDatatype) {
        allocationDatatype = aAllocationDatatype;
    }

    /**
     * Access method for agency.
     *
     * @return the current value of agency
     */
    public String getAgency() {
        return agency;
    }

    /**
     * Setter method for agency.
     *
     * @param aAgency the new value for agency
     */
    public void setAgency(String aAgency) {
        agency = aAgency;
    }

    /**
     * Access method for userId.
     *
     * @return the current value of userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Setter method for userId.
     *
     * @param aUserId the new value for userId
     */
    public void setUserId(String aUserId) {
        userId = aUserId;
    }

    /**
     * Access method for mobileNbr.
     *
     * @return the current value of mobileNbr
     */
    public long getMobileNbr() {
        return mobileNbr;
    }

    /**
     * Setter method for mobileNbr.
     *
     * @param aMobileNbr the new value for mobileNbr
     */
    public void setMobileNbr(long aMobileNbr) {
        mobileNbr = aMobileNbr;
    }

    /**
     * Access method for divisionId.
     *
     * @return the current value of divisionId
     */
    public String getDivisionId() {
        return divisionId;
    }

    /**
     * Setter method for divisionId.
     *
     * @param aDivisionId the new value for divisionId
     */
    public void setDivisionId(String aDivisionId) {
        divisionId = aDivisionId;
    }

    /**
     * Access method for allocation.
     *
     * @return the current value of allocation
     */
    public String getAllocation() {
        return allocation;
    }

    /**
     * Setter method for allocation.
     *
     * @param aAllocation the new value for allocation
     */
    public void setAllocation(String aAllocation) {
        allocation = aAllocation;
    }

    /**
     * Access method for city.
     *
     * @return the current value of city
     */
    public String getCity() {
        return city;
    }

    /**
     * Setter method for city.
     *
     * @param aCity the new value for city
     */
    public void setCity(String aCity) {
        city = aCity;
    }

    /**
     * Access method for id.
     *
     * @return the current value of id
     */
    public long getId() {
        return id;
    }

    /**
     * Setter method for id.
     *
     * @param aId the new value for id
     */
    public void setId(long aId) {
        id = aId;
    }

    /**
     * Access method for allocationId.
     *
     * @return the current value of allocationId
     */
    public String getAllocationId() {
        return allocationId;
    }

    /**
     * Setter method for allocationId.
     *
     * @param aAllocationId the new value for allocationId
     */
    public void setAllocationId(String aAllocationId) {
        allocationId = aAllocationId;
    }

    /**
     * Compares the key for this instance with another CiFieldUserAllotment.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class CiFieldUserAllotment and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof CiFieldUserAllotment)) {
            return false;
        }
        CiFieldUserAllotment that = (CiFieldUserAllotment) other;
        Object myUserId = this.getUserId();
        Object yourUserId = that.getUserId();
        if (myUserId==null ? yourUserId!=null : !myUserId.equals(yourUserId)) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another CiFieldUserAllotment.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof CiFieldUserAllotment)) return false;
        return this.equalKeys(other) && ((CiFieldUserAllotment)other).equalKeys(this);
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
        if (getUserId() == null) {
            i = 0;
        } else {
            i = getUserId().hashCode();
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
        StringBuffer sb = new StringBuffer("[CiFieldUserAllotment |");
        sb.append(" userId=").append(getUserId());
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
        ret.put("userId", getUserId());
        return ret;
    }

}
