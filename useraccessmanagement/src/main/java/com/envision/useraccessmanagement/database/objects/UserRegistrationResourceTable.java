// Generated with g9.

package com.envision.useraccessmanagement.database.objects;

import java.io.Serializable;
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

@Entity(name="user_registration_resource_table")
@EntityListeners(AuditingEntityListener.class)
public class UserRegistrationResourceTable implements Serializable {

    /** Primary key. */
    protected static final String PK = "id";

    @Column(length=100)
    private String username;
    @Column(name="app_urls")
    private String appUrls;
    @Column(name="ou_name", length=100)
    private String ouName;
    @Column(name="security_groups", length=100)
    private String securityGroups;
    @Column(name="system_destination_ip_subnets", length=100)
    private String systemDestinationIpSubnets;
    @Column(name="service_ssh_https", length=100)
    private String serviceSshHttps;
    @Column(name="custom_ports", length=100)
    private String customPorts;
    @Column(name="details_services_ports_urls_remarks", length=1000)
    private String detailsServicesPortsUrlsRemarks;
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
    public UserRegistrationResourceTable() {
        super();
    }

    /**
     * Access method for username.
     *
     * @return the current value of username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Setter method for username.
     *
     * @param aUsername the new value for username
     */
    public void setUsername(String aUsername) {
        username = aUsername;
    }

    /**
     * Access method for appUrls.
     *
     * @return the current value of appUrls
     */
    public String getAppUrls() {
        return appUrls;
    }

    /**
     * Setter method for appUrls.
     *
     * @param aAppUrls the new value for appUrls
     */
    public void setAppUrls(String aAppUrls) {
        appUrls = aAppUrls;
    }

    /**
     * Access method for ouName.
     *
     * @return the current value of ouName
     */
    public String getOuName() {
        return ouName;
    }

    /**
     * Setter method for ouName.
     *
     * @param aOuName the new value for ouName
     */
    public void setOuName(String aOuName) {
        ouName = aOuName;
    }

    /**
     * Access method for securityGroups.
     *
     * @return the current value of securityGroups
     */
    public String getSecurityGroups() {
        return securityGroups;
    }

    /**
     * Setter method for securityGroups.
     *
     * @param aSecurityGroups the new value for securityGroups
     */
    public void setSecurityGroups(String aSecurityGroups) {
        securityGroups = aSecurityGroups;
    }

    /**
     * Access method for systemDestinationIpSubnets.
     *
     * @return the current value of systemDestinationIpSubnets
     */
    public String getSystemDestinationIpSubnets() {
        return systemDestinationIpSubnets;
    }

    /**
     * Setter method for systemDestinationIpSubnets.
     *
     * @param aSystemDestinationIpSubnets the new value for systemDestinationIpSubnets
     */
    public void setSystemDestinationIpSubnets(String aSystemDestinationIpSubnets) {
        systemDestinationIpSubnets = aSystemDestinationIpSubnets;
    }

    /**
     * Access method for serviceSshHttps.
     *
     * @return the current value of serviceSshHttps
     */
    public String getServiceSshHttps() {
        return serviceSshHttps;
    }

    /**
     * Setter method for serviceSshHttps.
     *
     * @param aServiceSshHttps the new value for serviceSshHttps
     */
    public void setServiceSshHttps(String aServiceSshHttps) {
        serviceSshHttps = aServiceSshHttps;
    }

    /**
     * Access method for customPorts.
     *
     * @return the current value of customPorts
     */
    public String getCustomPorts() {
        return customPorts;
    }

    /**
     * Setter method for customPorts.
     *
     * @param aCustomPorts the new value for customPorts
     */
    public void setCustomPorts(String aCustomPorts) {
        customPorts = aCustomPorts;
    }

    /**
     * Access method for detailsServicesPortsUrlsRemarks.
     *
     * @return the current value of detailsServicesPortsUrlsRemarks
     */
    public String getDetailsServicesPortsUrlsRemarks() {
        return detailsServicesPortsUrlsRemarks;
    }

    /**
     * Setter method for detailsServicesPortsUrlsRemarks.
     *
     * @param aDetailsServicesPortsUrlsRemarks the new value for detailsServicesPortsUrlsRemarks
     */
    public void setDetailsServicesPortsUrlsRemarks(String aDetailsServicesPortsUrlsRemarks) {
        detailsServicesPortsUrlsRemarks = aDetailsServicesPortsUrlsRemarks;
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
     * Compares the key for this instance with another UserRegistrationResourceTable.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class UserRegistrationResourceTable and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof UserRegistrationResourceTable)) {
            return false;
        }
        UserRegistrationResourceTable that = (UserRegistrationResourceTable) other;
        Object myId = this.getId();
        Object yourId = that.getId();
        if (myId==null ? yourId!=null : !myId.equals(yourId)) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another UserRegistrationResourceTable.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof UserRegistrationResourceTable)) return false;
        return this.equalKeys(other) && ((UserRegistrationResourceTable)other).equalKeys(this);
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
        StringBuffer sb = new StringBuffer("[UserRegistrationResourceTable |");
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
