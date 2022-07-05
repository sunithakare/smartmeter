// Generated with g9.

package com.envision.useraccessmanagement.database.objects;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="user_registration_master_table", indexes={@Index(name="userRegistrationMasterTableUserRegDetailsRefIdCirlDivisionEmailIdx", columnList="reference_id,division,city,email,mobile_no,approver_details_id,subdivision,department")})
@EntityListeners(AuditingEntityListener.class)
public class UserRegistrationMasterTable implements Serializable {

    /** Primary key. */
    protected static final String PK = "referenceId";

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="reference_id", unique=true, nullable=false, length=100)
    private String referenceId;
    @Column(length=50)
    private String salutation;
    @Column(name="first_name", length=100)
    private String firstName;
    @Column(name="last_name", length=100)
    private String lastName;
    @Column(name="employee_id", length=50)
    private String employeeId;
    @Column(length=100)
    private String designation;
    @Column(length=100)
    private String department;
    @Column(name ="state")
    private String state;
    @Column(name ="discom_name", length=10)
    private String discomName;
    @Column(name="mobile_no", length=100)
    private String mobileNo;
    @Column(length=50)
    private String email;
    @Column(length=100)
    private String city;
    @Column(length=100)
    private String zone;
    @Column(length=100)
    private String circle;
    @Column(length=100)
    private String division;
    @Column(length=100)
    private String subdivision;

    @Column(name="user_type", length=100)
    private String userType;
    @Column(name="existing_vpn_username", length=100)
    private String existingVpnUsername;
    @Column(name="request_for", length=100)
    private String requestFor;
    
    
    @Column(name="lan_mac_address", length=100)
    private String lanMacAddress;
    @Column(name="wifi_mac_address", length=100)
    private String wifiMacAddress;
    @Column(name="vpn_access_from_india", length=100)
    private String vpnAccessFromIndia;
    @Column(name="country_name_outside_india", length=100)
    private String countryNameOutsideIndia;
    @Column(name="status")
    private String status;
    @Column(name="discom")
    private String discom;

    @Column(name="user_remarks")
    private String userRemarks;

    @Column(name="spoc_remarks")
    private String spocRemarks;
    @Column(name="approver_details_id", precision=10)
    private Integer approverDetailsId;
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
    @JsonIgnore
    @OneToMany(mappedBy="userRegistrationMasterTable",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<UserRegistrationDetailsTable> userRegistrationDetailsTable;
    @JsonIgnore
    @OneToMany(mappedBy="userRegistrationMasterTable",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<UserRegistrationResourceTable> userRegistrationResourceTable;

    @JsonIgnore
    @OneToMany(mappedBy="userRegistrationMasterTable",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @OrderBy( "approverLevel asc")
    private List<UserRegistrationApproverRemarks> userRegistrationApproverRemarks;
    
    @Column(unique=true, nullable=false)
    private UUID uuid;
    
    /** Default constructor. */
    public UserRegistrationMasterTable() {
        super();
    }

    /**
     * Access method for referenceId.
     *
     * @return the current value of referenceId
     */
    public String getReferenceId() {
        return referenceId;
    }

    /**
     * Setter method for referenceId.
     *
     * @param aReferenceId the new value for referenceId
     */
    public void setReferenceId(String aReferenceId) {
        referenceId = aReferenceId;
    }

    /**
     * Access method for salutation.
     *
     * @return the current value of salutation
     */
    public String getSalutation() {
        return salutation;
    }

    public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	/**
     * Setter method for salutation.
     *
     * @param aSalutation the new value for salutation
     */
    public void setSalutation(String aSalutation) {
        salutation = aSalutation;
    }

    
    
    public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	/**
     * Access method for firstName.
     *
     * @return the current value of firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Setter method for firstName.
     *
     * @param aFirstName the new value for firstName
     */
    public void setFirstName(String aFirstName) {
        firstName = aFirstName;
    }

    /**
     * Access method for lastName.
     *
     * @return the current value of lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Setter method for lastName.
     *
     * @param aLastName the new value for lastName
     */
    public void setLastName(String aLastName) {
        lastName = aLastName;
    }

    /**
     * Access method for employeeId.
     *
     * @return the current value of employeeId
     */
    public String getEmployeeId() {
        return employeeId;
    }

    /**
     * Setter method for employeeId.
     *
     * @param aEmployeeId the new value for employeeId
     */
    public void setEmployeeId(String aEmployeeId) {
        employeeId = aEmployeeId;
    }

    public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getDiscom() {
		return discom;
	}

	public void setDiscom(String discom) {
		this.discom = discom;
	}

	public String getDiscomName() {
		return discomName;
	}

	public void setDiscomName(String discomName) {
		this.discomName = discomName;
	}

	/**
     * Access method for designation.
     *
     * @return the current value of designation
     */
    public String getDesignation() {
        return designation;
    }

    public String getUserRemarks() {
		return userRemarks;
	}

	public void setUserRemarks(String userRemarks) {
		this.userRemarks = userRemarks;
	}

	public String getSpocRemarks() {
		return spocRemarks;
	}

	public void setSpocRemarks(String spocRemarks) {
		this.spocRemarks = spocRemarks;
	}

	/**
     * Setter method for designation.
     *
     * @param aDesignation the new value for designation
     */
    public void setDesignation(String aDesignation) {
        designation = aDesignation;
    }

    /**
     * Access method for department.
     *
     * @return the current value of department
     */
    public String getDepartment() {
        return department;
    }

    /**
     * Setter method for department.
     *
     * @param aDepartment the new value for department
     */
    public void setDepartment(String aDepartment) {
        department = aDepartment;
    }

    /**
     * Access method for mobileNo.
     *
     * @return the current value of mobileNo
     */
    public String getMobileNo() {
        return mobileNo;
    }

    /**
     * Setter method for mobileNo.
     *
     * @param aMobileNo the new value for mobileNo
     */
    public void setMobileNo(String aMobileNo) {
        mobileNo = aMobileNo;
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
     * Access method for zone.
     *
     * @return the current value of zone
     */
    public String getZone() {
        return zone;
    }

    /**
     * Setter method for zone.
     *
     * @param aZone the new value for zone
     */
    public void setZone(String aZone) {
        zone = aZone;
    }

    /**
     * Access method for circle.
     *
     * @return the current value of circle
     */
    public String getCircle() {
        return circle;
    }

    /**
     * Setter method for circle.
     *
     * @param aCircle the new value for circle
     */
    public void setCircle(String aCircle) {
        circle = aCircle;
    }

    public List<UserRegistrationApproverRemarks> getUserRegistrationApproverRemarks() {
		return userRegistrationApproverRemarks;
	}

	public void setUserRegistrationApproverRemarks(List<UserRegistrationApproverRemarks> userRegistrationApproverRemarks) {
		this.userRegistrationApproverRemarks = userRegistrationApproverRemarks;
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
     * Access method for subdivision.
     *
     * @return the current value of subdivision
     */
    public String getSubdivision() {
        return subdivision;
    }

    /**
     * Setter method for subdivision.
     *
     * @param aSubdivision the new value for subdivision
     */
    public void setSubdivision(String aSubdivision) {
        subdivision = aSubdivision;
    }
    /**
     * Access method for userType.
     *
     * @return the current value of userType
     */
    public String getUserType() {
        return userType;
    }

    /**
     * Setter method for userType.
     *
     * @param aUserType the new value for userType
     */
    public void setUserType(String aUserType) {
        userType = aUserType;
    }

    /**
     * Access method for existingVpnUsername.
     *
     * @return the current value of existingVpnUsername
     */
    public String getExistingVpnUsername() {
        return existingVpnUsername;
    }

    /**
     * Setter method for existingVpnUsername.
     *
     * @param aExistingVpnUsername the new value for existingVpnUsername
     */
    public void setExistingVpnUsername(String aExistingVpnUsername) {
        existingVpnUsername = aExistingVpnUsername;
    }

    /**
     * Access method for requestFor.
     *
     * @return the current value of requestFor
     */
    public String getRequestFor() {
        return requestFor;
    }

    /**
     * Setter method for requestFor.
     *
     * @param aRequestFor the new value for requestFor
     */
    public void setRequestFor(String aRequestFor) {
        requestFor = aRequestFor;
    }


    public String getLanMacAddress() {
		return lanMacAddress;
	}

	public void setLanMacAddress(String lanMacAddress) {
		this.lanMacAddress = lanMacAddress;
	}

	public String getWifiMacAddress() {
		return wifiMacAddress;
	}

	public void setWifiMacAddress(String wifiMacAddress) {
		this.wifiMacAddress = wifiMacAddress;
	}

	/**
     * Access method for vpnAccessFromIndia.
     *
     * @return the current value of vpnAccessFromIndia
     */
    public String getVpnAccessFromIndia() {
        return vpnAccessFromIndia;
    }

    /**
     * Setter method for vpnAccessFromIndia.
     *
     * @param aVpnAccessFromIndia the new value for vpnAccessFromIndia
     */
    public void setVpnAccessFromIndia(String aVpnAccessFromIndia) {
        vpnAccessFromIndia = aVpnAccessFromIndia;
    }

    /**
     * Access method for countryNameOutsideIndia.
     *
     * @return the current value of countryNameOutsideIndia
     */
    public String getCountryNameOutsideIndia() {
        return countryNameOutsideIndia;
    }

    /**
     * Setter method for countryNameOutsideIndia.
     *
     * @param aCountryNameOutsideIndia the new value for countryNameOutsideIndia
     */
    public void setCountryNameOutsideIndia(String aCountryNameOutsideIndia) {
        countryNameOutsideIndia = aCountryNameOutsideIndia;
    }

    /**
     * Access method for approverDetailsId.
     *
     * @return the current value of approverDetailsId
     */
    public Integer getApproverDetailsId() {
        return approverDetailsId;
    }

    /**
     * Setter method for approverDetailsId.
     *
     * @param aApproverDetailsId the new value for approverDetailsId
     */
    public void setApproverDetailsId(Integer aApproverDetailsId) {
        approverDetailsId = aApproverDetailsId;
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
     * Access method for userRegistrationDetailsTable.
     *
     * @return the current value of userRegistrationDetailsTable
     */
    public List<UserRegistrationDetailsTable> getUserRegistrationDetailsTable() {
        return userRegistrationDetailsTable;
    }

    /**
     * Setter method for userRegistrationDetailsTable.
     *
     * @param aUserRegistrationDetailsTable the new value for userRegistrationDetailsTable
     */
    public void setUserRegistrationDetailsTable(List<UserRegistrationDetailsTable> aUserRegistrationDetailsTable) {
        userRegistrationDetailsTable = aUserRegistrationDetailsTable;
    }

    /**
     * Access method for userRegistrationResourceTable.
     *
     * @return the current value of userRegistrationResourceTable
     */
    public List<UserRegistrationResourceTable> getUserRegistrationResourceTable() {
        return userRegistrationResourceTable;
    }

    /**
     * Setter method for userRegistrationResourceTable.
     *
     * @param aUserRegistrationResourceTable the new value for userRegistrationResourceTable
     */
    public void setUserRegistrationResourceTable(List<UserRegistrationResourceTable> aUserRegistrationResourceTable) {
        userRegistrationResourceTable = aUserRegistrationResourceTable;
    }

    /**
     * Compares the key for this instance with another UserRegistrationMasterTable.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class UserRegistrationMasterTable and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof UserRegistrationMasterTable)) {
            return false;
        }
        UserRegistrationMasterTable that = (UserRegistrationMasterTable) other;
        Object myReferenceId = this.getReferenceId();
        Object yourReferenceId = that.getReferenceId();
        if (myReferenceId==null ? yourReferenceId!=null : !myReferenceId.equals(yourReferenceId)) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another UserRegistrationMasterTable.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof UserRegistrationMasterTable)) return false;
        return this.equalKeys(other) && ((UserRegistrationMasterTable)other).equalKeys(this);
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
        if (getReferenceId() == null) {
            i = 0;
        } else {
            i = getReferenceId().hashCode();
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
        StringBuffer sb = new StringBuffer("[UserRegistrationMasterTable |");
        sb.append(" referenceId=").append(getReferenceId());
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
        ret.put("referenceId", getReferenceId());
        return ret;
    }

}
