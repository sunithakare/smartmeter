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
@Table(name="ci_field_user_creation", indexes={@Index(name="ciFieldUserCreationCiFieldUserAgencyNamePk", columnList="agency_code,field_user_code", unique=true)})
public class CiFieldUserCreation implements Serializable {

    /** Primary key. */
    protected static final String PK = "fieldUserCode";

    @Column(name="state_id", length=100)
    private String stateId;
    @Column(name="discom_id", length=100)
    private String discomId;
    @Column(name="login_id", length=100)
    private String loginId;
    @Column(name="agency_code", length=100)
    private String agencyCode;
    @Id
    @Column(name="field_user_code", unique=true, nullable=false, length=100)
    private String fieldUserCode;
    @Column(name="field_user_name", length=200)
    private String fieldUserName;
    @Column(precision=10)
    private int moble;
    @Column(length=250)
    private String email;
    @Column(length=20)
    private String access;
    @Column(length=200)
    private String password;
    @Column(length=100)
    private String city;
    @Column(precision=10)
    private int id;

    /** Default constructor. */
    public CiFieldUserCreation() {
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
     * Access method for loginId.
     *
     * @return the current value of loginId
     */
    public String getLoginId() {
        return loginId;
    }

    /**
     * Setter method for loginId.
     *
     * @param aLoginId the new value for loginId
     */
    public void setLoginId(String aLoginId) {
        loginId = aLoginId;
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
     * Access method for fieldUserCode.
     *
     * @return the current value of fieldUserCode
     */
    public String getFieldUserCode() {
        return fieldUserCode;
    }

    /**
     * Setter method for fieldUserCode.
     *
     * @param aFieldUserCode the new value for fieldUserCode
     */
    public void setFieldUserCode(String aFieldUserCode) {
        fieldUserCode = aFieldUserCode;
    }

    /**
     * Access method for fieldUserName.
     *
     * @return the current value of fieldUserName
     */
    public String getFieldUserName() {
        return fieldUserName;
    }

    /**
     * Setter method for fieldUserName.
     *
     * @param aFieldUserName the new value for fieldUserName
     */
    public void setFieldUserName(String aFieldUserName) {
        fieldUserName = aFieldUserName;
    }

    /**
     * Access method for moble.
     *
     * @return the current value of moble
     */
    public int getMoble() {
        return moble;
    }

    /**
     * Setter method for moble.
     *
     * @param aMoble the new value for moble
     */
    public void setMoble(int aMoble) {
        moble = aMoble;
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
     * Access method for password.
     *
     * @return the current value of password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter method for password.
     *
     * @param aPassword the new value for password
     */
    public void setPassword(String aPassword) {
        password = aPassword;
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
     * Compares the key for this instance with another CiFieldUserCreation.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class CiFieldUserCreation and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof CiFieldUserCreation)) {
            return false;
        }
        CiFieldUserCreation that = (CiFieldUserCreation) other;
        Object myFieldUserCode = this.getFieldUserCode();
        Object yourFieldUserCode = that.getFieldUserCode();
        if (myFieldUserCode==null ? yourFieldUserCode!=null : !myFieldUserCode.equals(yourFieldUserCode)) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another CiFieldUserCreation.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof CiFieldUserCreation)) return false;
        return this.equalKeys(other) && ((CiFieldUserCreation)other).equalKeys(this);
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
        if (getFieldUserCode() == null) {
            i = 0;
        } else {
            i = getFieldUserCode().hashCode();
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
        StringBuffer sb = new StringBuffer("[CiFieldUserCreation |");
        sb.append(" fieldUserCode=").append(getFieldUserCode());
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
        ret.put("fieldUserCode", getFieldUserCode());
        return ret;
    }

}
