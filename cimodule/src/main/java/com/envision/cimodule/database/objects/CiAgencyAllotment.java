// Generated with g9.

package com.envision.cimodule.database.objects;

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
@Table(name="ci_agency_allotment", indexes={@Index(name="ci_agency_allotment_agency_IX", columnList="agency", unique=true), @Index(name="ciAgencyAllotmentCiAgencyDivisionCodePk", columnList="agency,division", unique=true)})
public class CiAgencyAllotment implements Serializable {

    /** Primary key. */
    protected static final String PK = "id";

    @Column(length=100)
    private String state;
    @Column(unique=true, length=100)
    private String agency;
    @Column(length=100)
    private String city;
    @Column(length=100)
    private String division;
    @Column(name="division_counts", precision=10)
    private int divisionCounts;
    @Column(length=50)
    private String allocation;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(unique=true, nullable=false, precision=10)
    private int id;
    @Column(name="allocation_data", length=20)
    private String allocationData;

    /** Default constructor. */
    public CiAgencyAllotment() {
        super();
    }

    /**
     * Access method for state.
     *
     * @return the current value of state
     */
    public String getState() {
        return state;
    }

    /**
     * Setter method for state.
     *
     * @param aState the new value for state
     */
    public void setState(String aState) {
        state = aState;
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
     * Access method for division.
     *
     * @return the current value of division
     */
    public String getDivision() {
        return division;
    }

    /**
     * Setter method for division.
     *
     * @param aDivision the new value for division
     */
    public void setDivision(String aDivision) {
        division = aDivision;
    }

    /**
     * Access method for divisionCounts.
     *
     * @return the current value of divisionCounts
     */
    public int getDivisionCounts() {
        return divisionCounts;
    }

    /**
     * Setter method for divisionCounts.
     *
     * @param aDivisionCounts the new value for divisionCounts
     */
    public void setDivisionCounts(int aDivisionCounts) {
        divisionCounts = aDivisionCounts;
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
     * Access method for id.
     *
     * @return the current value of id
     */
    public int getId() {
        return id;
    }

    /**
     * Setter method for id.
     *
     * @param aId the new value for id
     */
    public void setId(int aId) {
        id = aId;
    }

    /**
     * Access method for allocationData.
     *
     * @return the current value of allocationData
     */
    public String getAllocationData() {
        return allocationData;
    }

    /**
     * Setter method for allocationData.
     *
     * @param aAllocationData the new value for allocationData
     */
    public void setAllocationData(String aAllocationData) {
        allocationData = aAllocationData;
    }

    /**
     * Compares the key for this instance with another CiAgencyAllotment.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class CiAgencyAllotment and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof CiAgencyAllotment)) {
            return false;
        }
        CiAgencyAllotment that = (CiAgencyAllotment) other;
        if (this.getId() != that.getId()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another CiAgencyAllotment.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof CiAgencyAllotment)) return false;
        return this.equalKeys(other) && ((CiAgencyAllotment)other).equalKeys(this);
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
        i = getId();
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
        StringBuffer sb = new StringBuffer("[CiAgencyAllotment |");
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
        ret.put("id", Integer.valueOf(getId()));
        return ret;
    }

}
