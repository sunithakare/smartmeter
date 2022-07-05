// Generated with g9.

package com.envision.login.database.objects;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

@Entity
@Table(name="system_role_access_mapping_tbl", indexes={@Index(name="systemRoleAccessMappingTblSystemRolePrivilegeMappingTblUn", columnList="role_name,privilege_name,privilege_type", unique=true)})
public class SystemRoleAccessMappingTbl implements Serializable {

    /** Primary key. */
    protected static final String PK = "id";

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(unique=true, nullable=false, precision=19)
    private Long id;
    @NaturalId
    @ManyToOne(optional=false)
    @JoinColumn(name="role_name", nullable=false)
    private SystemRoleTbl systemRoleTbl;
    @NaturalId
    @Column(name="privilege_name", nullable=false)
    private String privilegeName;

    @Column(name="privilege_type", nullable=false)
    private String privilegeType;
    

    /** Default constructor. */
    public SystemRoleAccessMappingTbl() {
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
     * Access method for systemRoleTbl.
     *
     * @return the current value of systemRoleTbl
     */
    public SystemRoleTbl getSystemRoleTbl() {
        return systemRoleTbl;
    }

    /**
     * Setter method for systemRoleTbl.
     *
     * @param aSystemRoleTbl the new value for systemRoleTbl
     */
    public void setSystemRoleTbl(SystemRoleTbl aSystemRoleTbl) {
        systemRoleTbl = aSystemRoleTbl;
    }


    /**
     * Gets the group fragment id for member systemRoleTbl.
     *
     * @return Current value of the group fragment
     * @see SystemRoleTbl
     */
    public Long getSystemRoleTblId() {
        return (getSystemRoleTbl() == null ? null : getSystemRoleTbl().getId());
    }

    /**
     * Sets the group fragment id from member systemRoleTbl.
     *
     * @param aId New value for the group fragment
     * @see SystemRoleTbl
     */
    public void setSystemRoleTblId(Long aId) {
        if (getSystemRoleTbl() != null) {
            getSystemRoleTbl().setId(aId);
        }
    }




    public String getPrivilegeName() {
		return privilegeName;
	}

	public void setPrivilegeName(String privilegeName) {
		this.privilegeName = privilegeName;
	}

	public String getPrivilegeType() {
		return privilegeType;
	}

	public void setPrivilegeType(String privilegeType) {
		this.privilegeType = privilegeType;
	}

	/**
     * Compares the key for this instance with another SystemRoleAccessMappingTbl.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class SystemRoleAccessMappingTbl and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof SystemRoleAccessMappingTbl)) {
            return false;
        }
        SystemRoleAccessMappingTbl that = (SystemRoleAccessMappingTbl) other;
        if (this.getId() != that.getId()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another SystemRoleAccessMappingTbl.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof SystemRoleAccessMappingTbl)) return false;
        return this.equalKeys(other) && ((SystemRoleAccessMappingTbl)other).equalKeys(this);
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
        StringBuffer sb = new StringBuffer("[SystemRoleAccessMappingTbl |");
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
