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
import javax.persistence.Table;

@Entity
@Table(name="system_access_tbl", indexes={@Index(name="systemAccessTblSystemAccessTblUn", columnList="privilege_name,privilege_type", unique=true)})
public class SystemAccessTbl implements Serializable {

    /** Primary key. */
    protected static final String PK = "id";

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(unique=true, nullable=false, precision=19)
    private Long id;
    @Column(name="privilege_name", nullable=false)
    private String privilegeName;
    @Column(name="privilege_type", nullable=false)
    private String privilegeType;
    @Column(length=1)
    private boolean isactive;
    private String desc;
    @Column(name="module", nullable=false)
    private String module;

    /** Default constructor. */
    public SystemAccessTbl() {
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
     * Access method for privilegeName.
     *
     * @return the current value of privilegeName
     */
    public String getPrivilegeName() {
        return privilegeName;
    }

    /**
     * Setter method for privilegeName.
     *
     * @param aPrivilegeName the new value for privilegeName
     */
    public void setPrivilegeName(String aPrivilegeName) {
        privilegeName = aPrivilegeName;
    }

    /**
     * Access method for privilegeType.
     *
     * @return the current value of privilegeType
     */
    public String getPrivilegeType() {
        return privilegeType;
    }

    /**
     * Setter method for privilegeType.
     *
     * @param aPrivilegeType the new value for privilegeType
     */
    public void setPrivilegeType(String aPrivilegeType) {
        privilegeType = aPrivilegeType;
    }

    /**
     * Access method for isactive.
     *
     * @return true if and only if isactive is currently true
     */
    public boolean getIsactive() {
        return isactive;
    }

    /**
     * Setter method for isactive.
     *
     * @param aIsactive the new value for isactive
     */
    public void setIsactive(boolean aIsactive) {
        isactive = aIsactive;
    }

    /**
     * Access method for desc.
     *
     * @return the current value of desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Setter method for desc.
     *
     * @param aDesc the new value for desc
     */
    public void setDesc(String aDesc) {
        desc = aDesc;
    }

    /**
     * Access method for module.
     *
     * @return the current value of module
     */
    public String getModule() {
        return module;
    }

    /**
     * Setter method for module.
     *
     * @param aModule the new value for module
     */
    public void setModule(String aModule) {
        module = aModule;
    }

    /**
     * Compares the key for this instance with another SystemAccessTbl.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class SystemAccessTbl and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof SystemAccessTbl)) {
            return false;
        }
        SystemAccessTbl that = (SystemAccessTbl) other;
        Object myId = this.getId();
        Object yourId = that.getId();
        if (myId==null ? yourId!=null : !myId.equals(yourId)) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another SystemAccessTbl.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof SystemAccessTbl)) return false;
        return this.equalKeys(other) && ((SystemAccessTbl)other).equalKeys(this);
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
        StringBuffer sb = new StringBuffer("[SystemAccessTbl |");
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
