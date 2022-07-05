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

@Entity(name="ci_agency_creation")
public class CiAgencyCreation implements Serializable {

    /** Primary key. */
    protected static final String PK = "agencyCode";

    @Column(name="agency_name", length=100)
    private String agencyName;
    @Column(precision=19)
    private long mobile;
    @Column(length=100)
    private String email;
    @Column(length=200)
    private String landmark;
    @Column(name="agency_manager", length=100)
    private String agencyManager;
    @Column(length=100)
    private String access;
    @Column(unique=true, nullable=false, precision=10)
    private int id;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="agency_code", unique=true, nullable=false, length=100)
    private String agencyCode;

    /** Default constructor. */
    public CiAgencyCreation() {
        super();
    }

    /**
     * Access method for agencyName.
     *
     * @return the current value of agencyName
     */
    public String getAgencyName() {
        return agencyName;
    }

    /**
     * Setter method for agencyName.
     *
     * @param aAgencyName the new value for agencyName
     */
    public void setAgencyName(String aAgencyName) {
        agencyName = aAgencyName;
    }

    /**
     * Access method for mobile.
     *
     * @return the current value of mobile
     */
    public long getMobile() {
        return mobile;
    }

    /**
     * Setter method for mobile.
     *
     * @param aMobile the new value for mobile
     */
    public void setMobile(long aMobile) {
        mobile = aMobile;
    }

    /**
     * Access method for email.
     *
     * @return the current value of email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter method for email.
     *
     * @param aEmail the new value for email
     */
    public void setEmail(String aEmail) {
        email = aEmail;
    }

    /**
     * Access method for landmark.
     *
     * @return the current value of landmark
     */
    public String getLandmark() {
        return landmark;
    }

    /**
     * Setter method for landmark.
     *
     * @param aLandmark the new value for landmark
     */
    public void setLandmark(String aLandmark) {
        landmark = aLandmark;
    }

    /**
     * Access method for agencyManager.
     *
     * @return the current value of agencyManager
     */
    public String getAgencyManager() {
        return agencyManager;
    }

    /**
     * Setter method for agencyManager.
     *
     * @param aAgencyManager the new value for agencyManager
     */
    public void setAgencyManager(String aAgencyManager) {
        agencyManager = aAgencyManager;
    }

    /**
     * Access method for access.
     *
     * @return the current value of access
     */
    public String getAccess() {
        return access;
    }

    /**
     * Setter method for access.
     *
     * @param aAccess the new value for access
     */
    public void setAccess(String aAccess) {
        access = aAccess;
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
     * Access method for agencyCode.
     *
     * @return the current value of agencyCode
     */
    public String getAgencyCode() {
        return agencyCode;
    }

    /**
     * Setter method for agencyCode.
     *
     * @param aAgencyCode the new value for agencyCode
     */
    public void setAgencyCode(String aAgencyCode) {
        agencyCode = aAgencyCode;
    }

    /**
     * Compares the key for this instance with another CiAgencyCreation.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class CiAgencyCreation and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof CiAgencyCreation)) {
            return false;
        }
        CiAgencyCreation that = (CiAgencyCreation) other;
        Object myAgencyCode = this.getAgencyCode();
        Object yourAgencyCode = that.getAgencyCode();
        if (myAgencyCode==null ? yourAgencyCode!=null : !myAgencyCode.equals(yourAgencyCode)) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another CiAgencyCreation.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof CiAgencyCreation)) return false;
        return this.equalKeys(other) && ((CiAgencyCreation)other).equalKeys(this);
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
        if (getAgencyCode() == null) {
            i = 0;
        } else {
            i = getAgencyCode().hashCode();
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
        StringBuffer sb = new StringBuffer("[CiAgencyCreation |");
        sb.append(" agencyCode=").append(getAgencyCode());
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
        ret.put("agencyCode", getAgencyCode());
        return ret;
    }

}
