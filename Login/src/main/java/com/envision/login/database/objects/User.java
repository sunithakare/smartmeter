// Generated with g9.

package com.envision.login.database.objects;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.envision.common.database.objects.DiscomDetails;

@Entity
@Table(name="user", indexes={@Index(name="user_email_IX", columnList="email", unique=true), @Index(name="user_primary_mobile_number_IX", columnList="primary_mobile_number", unique=true), @Index(name="user_user_name_IX", columnList="user_name", unique=true)})
public class User implements Serializable {

 /** Primary key. */
 protected static final String PK = "id";

 @Id
 @GeneratedValue(strategy=GenerationType.IDENTITY)
 @Column(unique=true, nullable=false, precision=19)
 private Long id;
 @Column(name="created_by", length=25)
 private String createdBy;
 @Column(name="created_date")
 private LocalDateTime createdDate;
 @Column(unique=true, length=50)
 private String email;
 @Column(name="employee_id", length=50)
 private String employeeId;
 @Column(name="first_name", length=150)
 private String firstName;
 @Column(name="last_name", length=150)
 private String lastName;
 @Column(name="middle_name", length=150)
 private String middleName;
 @Column(name="modified_by", length=25)
 private String modifiedBy;
 @Column(name="modified_date")
 private LocalDateTime modifiedDate;
 @Column(length=100)
 private String password;
 @Column(name="primary_mobile_number", unique=true, length=15)
 private String primaryMobileNumber;
 @Column(name="user_group", length=25)
 private String userGroup;
 @Column(name="org_name")
 private String orgName;
 @Column(name="user_name", unique=true, length=150)
 private String userName;
 @Column(length=1)
 private boolean active;
 @ManyToMany(targetEntity = SystemRoleTbl.class,cascade = CascadeType.ALL,fetch = FetchType.LAZY)
 @JoinTable(name="user_role_mapping",joinColumns =@JoinColumn(name ="user_name",referencedColumnName  ="user_name"),
 inverseJoinColumns = {@JoinColumn(name="role_name",referencedColumnName="role_name")})
 private Set<SystemRoleTbl> userRole;

 @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
 @JoinColumn(name ="user_name",referencedColumnName  ="user_name")
 private Set<UserDiscom> userDiscom;

 /** Default constructor. */
 public User() {
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
  * Access method for middleName.
  *
  * @return the current value of middleName
  */
 public String getMiddleName() {
     return middleName;
 }

 /**
  * Setter method for middleName.
  *
  * @param aMiddleName the new value for middleName
  */
 public void setMiddleName(String aMiddleName) {
     middleName = aMiddleName;
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

 public String getOrgName() {
	return orgName;
}

public void setOrgName(String orgName) {
	this.orgName = orgName;
}

/**
  * Access method for primaryMobileNumber.
  *
  * @return the current value of primaryMobileNumber
  */
 public String getPrimaryMobileNumber() {
     return primaryMobileNumber;
 }

 /**
  * Setter method for primaryMobileNumber.
  *
  * @param aPrimaryMobileNumber the new value for primaryMobileNumber
  */
 public void setPrimaryMobileNumber(String aPrimaryMobileNumber) {
     primaryMobileNumber = aPrimaryMobileNumber;
 }

 /**
  * Access method for userGroup.
  *
  * @return the current value of userGroup
  */
 public String getUserGroup() {
     return userGroup;
 }

 /**
  * Setter method for userGroup.
  *
  * @param aUserGroup the new value for userGroup
  */
 public void setUserGroup(String aUserGroup) {
     userGroup = aUserGroup;
 }

 /**
  * Access method for userName.
  *
  * @return the current value of userName
  */
 public String getUserName() {
     return userName;
 }

 /**
  * Setter method for userName.
  *
  * @param aUserName the new value for userName
  */
 public void setUserName(String aUserName) {
     userName = aUserName;
 }

 /**
  * Access method for active.
  *
  * @return true if and only if active is currently true
  */
 public boolean getActive() {
     return active;
 }

 /**
  * Setter method for active.
  *
  * @param aActive the new value for active
  */
 public void setActive(boolean aActive) {
     active = aActive;
 }

 public Set<SystemRoleTbl> getUserRole() {
	return userRole;
}

public void setUserRole(Set<SystemRoleTbl> userRole) {
	this.userRole = userRole;
}


public Set<UserDiscom> getUserDiscom() {
	return userDiscom;
}

public void setUserDiscom(Set<UserDiscom> userDiscom) {
	this.userDiscom = userDiscom;
}

/**
  * Compares the key for this instance with another User.
  *
  * @param other The object to compare to
  * @return True if other object is instance of class User and the key objects are equal
  */
 private boolean equalKeys(Object other) {
     if (this==other) {
         return true;
     }
     if (!(other instanceof User)) {
         return false;
     }
     User that = (User) other;
     Object myId = this.getId();
     Object yourId = that.getId();
     if (myId==null ? yourId!=null : !myId.equals(yourId)) {
         return false;
     }
     return true;
 }

 /**
  * Compares this instance with another User.
  *
  * @param other The object to compare to
  * @return True if the objects are the same
  */
 @Override
 public boolean equals(Object other) {
     if (!(other instanceof User)) return false;
     return this.equalKeys(other) && ((User)other).equalKeys(this);
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
     StringBuffer sb = new StringBuffer("[User |");
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
