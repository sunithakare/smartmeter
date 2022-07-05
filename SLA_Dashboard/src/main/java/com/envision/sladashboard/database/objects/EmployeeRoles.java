// Generated with g9.

package com.envision.sladashboard.database.objects;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="employee_roles", uniqueConstraints = { @UniqueConstraint( columnNames = { "role_name", "role_category" } )})
public class EmployeeRoles implements Serializable {

    /** Primary key. */
    protected static final String PK = "id";

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(unique=true, nullable=false, precision=10)
    private Long id;
    @Column(name="role_name", nullable=false)
    private String roleName;
    @Column(name="role_category", nullable=false)
    private String roleCategory;
    @Column(name="role_desc")
    private String roleDesc;
//    @OneToMany(mappedBy="employeeRoles")
//    private Set<EmployeeAttendance> employeeAttendance;
//    @OneToOne(mappedBy="employeeRoles")
//    private EmployeeCreation employeeCreation;

    /** Default constructor. */
    public EmployeeRoles() { 
        super();
    }


    public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getRoleName() {
		return roleName;
	}


	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}


	public String getRoleCategory() {
		return roleCategory;
	}


	public void setRoleCategory(String roleCategory) {
		this.roleCategory = roleCategory;
	}


	public String getRoleDesc() {
		return roleDesc;
	}


	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}


	/**
     * Compares the key for this instance with another EmployeeRoles.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class EmployeeRoles and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof EmployeeRoles)) {
            return false;
        }
        EmployeeRoles that = (EmployeeRoles) other;
        if (this.getId() != that.getId()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another EmployeeRoles.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof EmployeeRoles)) return false;
        return this.equalKeys(other) && ((EmployeeRoles)other).equalKeys(this);
    }
//
//    /**
//     * Returns a hash code for this instance.
//     *
//     * @return Hash code
//     */
//    @Override
//    public int hashCode() {
//        int i;
//        int result = 17;
//        i = getId().hashCode();
//        result = 37*result + i;
//        return result;
//    }

    /**
     * Returns a debug-friendly String representation of this instance.
     *
     * @return String representation of this instance
     */
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer("[EmployeeRoles |");
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
        ret.put("id", Integer.valueOf(getId().hashCode()));
        return ret;
    }

}
