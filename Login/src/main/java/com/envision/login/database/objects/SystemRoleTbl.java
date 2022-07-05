// Generated with g9.

package com.envision.login.database.objects;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="system_role_tbl", indexes={@Index(name="system_role__tbl_role_name_IX", columnList="role_name", unique=true)})
public class SystemRoleTbl implements Serializable {

    /** Primary key. */
    protected static final String PK = "id";

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(unique=true, nullable=false, precision=19)
    private Long id;
    @Column(name="role_name", unique=true, nullable=false)
    private String roleName;
    @Column(name="role_desc", nullable=false)
    private String roleDesc;
    @Column(name="role_type", nullable=false)
    private String roleType;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name="system_role_access_mapping_tbl",
    		joinColumns = @JoinColumn(name = "role_name",referencedColumnName = "role_name", nullable=false,insertable = false,updatable = false)
    		,
    		inverseJoinColumns = {
        		    @JoinColumn(name = "privilege_name",referencedColumnName = "privilege_name", nullable=false,insertable = false,updatable = false),
        		    @JoinColumn(name="privilege_type",referencedColumnName="privilege_type", nullable=false,insertable = false,updatable = false)
        		}
		)
    private Set<SystemAccessTbl> systemAccess;
    
    /** Default constructor. */
    public SystemRoleTbl() {
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
     * Access method for roleName.
     *
     * @return the current value of roleName
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * Setter method for roleName.
     *
     * @param aRoleName the new value for roleName
     */
    public void setRoleName(String aRoleName) {
        roleName = aRoleName;
    }

    /**
     * Access method for roleDesc.
     *
     * @return the current value of roleDesc
     */
    public String getRoleDesc() {
        return roleDesc;
    }

    /**
     * Setter method for roleDesc.
     *
     * @param aRoleDesc the new value for roleDesc
     */
    public void setRoleDesc(String aRoleDesc) {
        roleDesc = aRoleDesc;
    }




    public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	public Set<SystemAccessTbl> getSystemAccess() {
		return systemAccess;
	}

	public void setSystemAccess(Set<SystemAccessTbl> systemAccess) {
		this.systemAccess = systemAccess;
	}

	/**
     * Compares the key for this instance with another SystemRoleTbl.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class SystemRoleTbl and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof SystemRoleTbl)) {
            return false;
        }
        SystemRoleTbl that = (SystemRoleTbl) other;
        if (this.getId() != that.getId()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another SystemRoleTbl.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof SystemRoleTbl)) return false;
        return this.equalKeys(other) && ((SystemRoleTbl)other).equalKeys(this);
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
        i = (int)(getId() ^ (getId()>>>32));
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
        StringBuffer sb = new StringBuffer("[SystemRoleTbl |");
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
        ret.put("id", Long.valueOf(getId()));
        return ret;
    }

}
