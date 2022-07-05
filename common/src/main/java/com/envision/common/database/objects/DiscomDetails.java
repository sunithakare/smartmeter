// Generated with g9.

package com.envision.common.database.objects;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="discom_details", indexes={@Index(name="discom_details_discom_code_IX", columnList="discom_code", unique=true), @Index(name="discom_details_discom_name_IX", columnList="discom_name", unique=true)})
public class DiscomDetails implements Serializable {

    /** Primary key. */
    protected static final String PK = "discomDetailsId";

    @Id
    @Column(name="discom_details_id", unique=true, nullable=false, precision=19)
    private Long discomDetailsId;
    @Column(name="discom_code", unique=true, nullable=false, length=25)
    private String discomCode;
    @Column(name="discom_name", unique=true, nullable=false, length=150)
    private String discomName;
    @Column(name="discom_location", length=100)
    private String discomLocation;
    @Column(name="discom_authority", length=100)
    private String discomAuthority;
    @Column(length=500)
    private String address;
    @Column(name="room_floor", length=100)
    private String roomFloor;
    @Column(name="block_building", length=100)
    private String blockBuilding;
    @Column(name="street_po_box", length=100)
    private String streetPoBox;
    @Column(length=150)
    private String city;
    @Column(length=150)
    private String state;
    @Column(length=150)
    private String country;
    @Column(name="zip_code", precision=10)
    private Integer zipCode;
    @Column(length=100)
    private String website;
    @Column(length=100)
    private String email;
    @Column(length=15)
    private String phone1;
    @Column(length=15)
    private String phone2;
    @Column(length=15)
    private String mobile;
    @Column(length=15)
    private String fax;
    @Column(name="state_code", length=25)
    private String stateCode;
    @Column(name="created_by", length=25)
    private String createdBy;
    @Column(name="created_date")
    private LocalDateTime createdDate;
    @Column(name="modified_by", length=25)
    private String modifiedBy;
    @Column(name="modified_date")
    private LocalDateTime modifiedDate;
    @Column(name="company_detail_id", precision=19)
    private Long companyDetailId;
    @Column(name="rounding_off", length=15)
    private String roundingOff;
    @Column(name="service_tax_reg_code", length=25)
    private String serviceTaxRegCode;

    /** Default constructor. */
    public DiscomDetails() {
        super();
    }

    /**
     * Access method for discomDetailsId.
     *
     * @return the current value of discomDetailsId
     */
    public Long getDiscomDetailsId() {
        return discomDetailsId;
    }

    /**
     * Setter method for discomDetailsId.
     *
     * @param aDiscomDetailsId the new value for discomDetailsId
     */
    public void setDiscomDetailsId(Long aDiscomDetailsId) {
        discomDetailsId = aDiscomDetailsId;
    }

    /**
     * Access method for discomCode.
     *
     * @return the current value of discomCode
     */
    public String getDiscomCode() {
        return discomCode;
    }

    /**
     * Setter method for discomCode.
     *
     * @param aDiscomCode the new value for discomCode
     */
    public void setDiscomCode(String aDiscomCode) {
        discomCode = aDiscomCode;
    }

    /**
     * Access method for discomName.
     *
     * @return the current value of discomName
     */
    public String getDiscomName() {
        return discomName;
    }

    /**
     * Setter method for discomName.
     *
     * @param aDiscomName the new value for discomName
     */
    public void setDiscomName(String aDiscomName) {
        discomName = aDiscomName;
    }

    /**
     * Access method for discomLocation.
     *
     * @return the current value of discomLocation
     */
    public String getDiscomLocation() {
        return discomLocation;
    }

    /**
     * Setter method for discomLocation.
     *
     * @param aDiscomLocation the new value for discomLocation
     */
    public void setDiscomLocation(String aDiscomLocation) {
        discomLocation = aDiscomLocation;
    }

    /**
     * Access method for discomAuthority.
     *
     * @return the current value of discomAuthority
     */
    public String getDiscomAuthority() {
        return discomAuthority;
    }

    /**
     * Setter method for discomAuthority.
     *
     * @param aDiscomAuthority the new value for discomAuthority
     */
    public void setDiscomAuthority(String aDiscomAuthority) {
        discomAuthority = aDiscomAuthority;
    }

    /**
     * Access method for address.
     *
     * @return the current value of address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Setter method for address.
     *
     * @param aAddress the new value for address
     */
    public void setAddress(String aAddress) {
        address = aAddress;
    }

    /**
     * Access method for roomFloor.
     *
     * @return the current value of roomFloor
     */
    public String getRoomFloor() {
        return roomFloor;
    }

    /**
     * Setter method for roomFloor.
     *
     * @param aRoomFloor the new value for roomFloor
     */
    public void setRoomFloor(String aRoomFloor) {
        roomFloor = aRoomFloor;
    }

    /**
     * Access method for blockBuilding.
     *
     * @return the current value of blockBuilding
     */
    public String getBlockBuilding() {
        return blockBuilding;
    }

    /**
     * Setter method for blockBuilding.
     *
     * @param aBlockBuilding the new value for blockBuilding
     */
    public void setBlockBuilding(String aBlockBuilding) {
        blockBuilding = aBlockBuilding;
    }

    /**
     * Access method for streetPoBox.
     *
     * @return the current value of streetPoBox
     */
    public String getStreetPoBox() {
        return streetPoBox;
    }

    /**
     * Setter method for streetPoBox.
     *
     * @param aStreetPoBox the new value for streetPoBox
     */
    public void setStreetPoBox(String aStreetPoBox) {
        streetPoBox = aStreetPoBox;
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
     * Access method for country.
     *
     * @return the current value of country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Setter method for country.
     *
     * @param aCountry the new value for country
     */
    public void setCountry(String aCountry) {
        country = aCountry;
    }

    /**
     * Access method for zipCode.
     *
     * @return the current value of zipCode
     */
    public Integer getZipCode() {
        return zipCode;
    }

    /**
     * Setter method for zipCode.
     *
     * @param aZipCode the new value for zipCode
     */
    public void setZipCode(Integer aZipCode) {
        zipCode = aZipCode;
    }

    /**
     * Access method for website.
     *
     * @return the current value of website
     */
    public String getWebsite() {
        return website;
    }

    /**
     * Setter method for website.
     *
     * @param aWebsite the new value for website
     */
    public void setWebsite(String aWebsite) {
        website = aWebsite;
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
     * Access method for phone1.
     *
     * @return the current value of phone1
     */
    public String getPhone1() {
        return phone1;
    }

    /**
     * Setter method for phone1.
     *
     * @param aPhone1 the new value for phone1
     */
    public void setPhone1(String aPhone1) {
        phone1 = aPhone1;
    }

    /**
     * Access method for phone2.
     *
     * @return the current value of phone2
     */
    public String getPhone2() {
        return phone2;
    }

    /**
     * Setter method for phone2.
     *
     * @param aPhone2 the new value for phone2
     */
    public void setPhone2(String aPhone2) {
        phone2 = aPhone2;
    }

    /**
     * Access method for mobile.
     *
     * @return the current value of mobile
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * Setter method for mobile.
     *
     * @param aMobile the new value for mobile
     */
    public void setMobile(String aMobile) {
        mobile = aMobile;
    }

    /**
     * Access method for fax.
     *
     * @return the current value of fax
     */
    public String getFax() {
        return fax;
    }

    /**
     * Setter method for fax.
     *
     * @param aFax the new value for fax
     */
    public void setFax(String aFax) {
        fax = aFax;
    }

    /**
     * Access method for stateCode.
     *
     * @return the current value of stateCode
     */
    public String getStateCode() {
        return stateCode;
    }

    /**
     * Setter method for stateCode.
     *
     * @param aStateCode the new value for stateCode
     */
    public void setStateCode(String aStateCode) {
        stateCode = aStateCode;
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
     * Access method for companyDetailId.
     *
     * @return the current value of companyDetailId
     */
    public Long getCompanyDetailId() {
        return companyDetailId;
    }

    /**
     * Setter method for companyDetailId.
     *
     * @param aCompanyDetailId the new value for companyDetailId
     */
    public void setCompanyDetailId(Long aCompanyDetailId) {
        companyDetailId = aCompanyDetailId;
    }

    /**
     * Access method for roundingOff.
     *
     * @return the current value of roundingOff
     */
    public String getRoundingOff() {
        return roundingOff;
    }

    /**
     * Setter method for roundingOff.
     *
     * @param aRoundingOff the new value for roundingOff
     */
    public void setRoundingOff(String aRoundingOff) {
        roundingOff = aRoundingOff;
    }

    /**
     * Access method for serviceTaxRegCode.
     *
     * @return the current value of serviceTaxRegCode
     */
    public String getServiceTaxRegCode() {
        return serviceTaxRegCode;
    }

    /**
     * Setter method for serviceTaxRegCode.
     *
     * @param aServiceTaxRegCode the new value for serviceTaxRegCode
     */
    public void setServiceTaxRegCode(String aServiceTaxRegCode) {
        serviceTaxRegCode = aServiceTaxRegCode;
    }

    /**
     * Compares the key for this instance with another DiscomDetails.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class DiscomDetails and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof DiscomDetails)) {
            return false;
        }
        DiscomDetails that = (DiscomDetails) other;
        Object myDiscomDetailsId = this.getDiscomDetailsId();
        Object yourDiscomDetailsId = that.getDiscomDetailsId();
        if (myDiscomDetailsId==null ? yourDiscomDetailsId!=null : !myDiscomDetailsId.equals(yourDiscomDetailsId)) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another DiscomDetails.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof DiscomDetails)) return false;
        return this.equalKeys(other) && ((DiscomDetails)other).equalKeys(this);
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
        if (getDiscomDetailsId() == null) {
            i = 0;
        } else {
            i = getDiscomDetailsId().hashCode();
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
        StringBuffer sb = new StringBuffer("[DiscomDetails |");
        sb.append(" discomDetailsId=").append(getDiscomDetailsId());
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
        ret.put("discomDetailsId", getDiscomDetailsId());
        return ret;
    }

}
