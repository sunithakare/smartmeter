// Generated with g9.

package com.envision.login.database.objects;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="user_role_mapping", indexes={@Index(name="userRoleMappingUserRoleMappingUn", columnList="user_name,role_name", unique=true)})
public class UserRoleMapping implements Serializable {

    /** Primary key. */
    protected static final String PK = "id";

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(unique=true, nullable=false, precision=19)
    private Long id;
    

    @Column(name="user_name", nullable=false)
    private String userName;
    @Column(name="role_name", nullable=false)
    private String roleName;
    
    @ManyToOne(optional=false)
    @JoinColumn(referencedColumnName="user_name", nullable=false,insertable = false,updatable = false)
    private User user;
    @ManyToOne(optional=false)
    @JoinColumn(referencedColumnName = "role_name", nullable=false,insertable = false,updatable = false)
    private SystemRoleTbl systemRoleTbl;

    /** Default constructor. */
    public UserRoleMapping() {
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
     * Access method for user.
     *
     * @return the current value of user
     */
    public User getUser() {
        return user;
    }

    /**
     * Setter method for user.
     *
     * @param aUser the new value for user
     */
    public void setUser(User aUser) {
        user = aUser;
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
     * Gets the group fragment id for member user.
     *
     * @return Current value of the group fragment
     * @see User
     */
    public Long getUserId() {
        return (getUser() == null ? null : getUser().getId());
    }

    /**
     * Sets the group fragment id from member user.
     *
     * @param aId New value for the group fragment
     * @see User
     */
    public void setUserId(Long aId) {
        if (getUser() != null) {
            getUser().setId(aId);
        }
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

    public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	/**
     * Compares the key for this instance with another UserRoleMapping.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class UserRoleMapping and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof UserRoleMapping)) {
            return false;
        }
        UserRoleMapping that = (UserRoleMapping) other;
        if (this.getId() != that.getId()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another UserRoleMapping.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof UserRoleMapping)) return false;
        return this.equalKeys(other) && ((UserRoleMapping)other).equalKeys(this);
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
        StringBuffer sb = new StringBuffer("[UserRoleMapping |");
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
