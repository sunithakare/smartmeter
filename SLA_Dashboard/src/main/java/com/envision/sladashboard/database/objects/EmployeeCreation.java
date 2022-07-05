// Generated with g9.

package com.envision.sladashboard.database.objects;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name="employee_creation", indexes={@Index(name="employee_creation_employee_id_IX", columnList="employee_id", unique=true)})
@EntityListeners(AuditingEntityListener.class)
public class EmployeeCreation implements Serializable {

    /** Primary key. */
    protected static final String PK = "id";

    @Id
    @Column(unique=true, nullable=false, precision=19)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(name="employee_name", length=150)
    private String employeeName;
    @Column(name="employee_id", unique=true, length=100)
    private String employeeId;
    private LocalDateTime doj;
    private LocalDateTime dor;
    @Column(length=100)
    private String state;
    private Boolean active;
    @CreatedBy
    @Column(name="created_by", length=25)
    private String createdBy;
    @CreatedDate
    @Column(name="created_date")
    private LocalDateTime createdDate;
    @LastModifiedBy
    @Column(name="modified_by", length=25)
    private String modifiedBy;
    @LastModifiedDate
    @Column(name="modified_date")
    private LocalDateTime modifiedDate;
    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL,optional = true)
    @JoinColumns({@JoinColumn(referencedColumnName="role_name",name="role_name"), @JoinColumn(referencedColumnName="role_category",name="role_category")})
    private EmployeeRoles employeeRoles;
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName="employee_id",name = "emp_id")
    private List<EmployeeQualifications> employeeQualifications;

    /** Default constructor. */
    public EmployeeCreation() {
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
     * Access method for employeeName.
     *
     * @return the current value of employeeName
     */
    public String getEmployeeName() {
        return employeeName;
    }

    /**
     * Setter method for employeeName.
     *
     * @param aEmployeeName the new value for employeeName
     */
    public void setEmployeeName(String aEmployeeName) {
        employeeName = aEmployeeName;
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
     * Access method for doj.
     *
     * @return the current value of doj
     */
    public LocalDateTime getDoj() {
        return doj;
    }

    /**
     * Setter method for doj.
     *
     * @param aDoj the new value for doj
     */
    public void setDoj(LocalDateTime aDoj) {
        doj = aDoj;
    }

    /**
     * Access method for dor.
     *
     * @return the current value of dor
     */
    public LocalDateTime getDor() {
        return dor;
    }

    /**
     * Setter method for dor.
     *
     * @param aDor the new value for dor
     */
    public void setDor(LocalDateTime aDor) {
        dor = aDor;
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



    public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
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
     * Access method for employeeRoles.
     *
     * @return the current value of employeeRoles
     */
    public EmployeeRoles getEmployeeRoles() {
        return employeeRoles;
    }

    /**
     * Setter method for employeeRoles.
     *
     * @param aEmployeeRoles the new value for employeeRoles
     */
    public void setEmployeeRoles(EmployeeRoles aEmployeeRoles) {
        employeeRoles = aEmployeeRoles;
    }

    public List<EmployeeQualifications> getEmployeeQualifications() {
		return employeeQualifications;
	}

	public void setEmployeeQualifications(List<EmployeeQualifications> employeeQualifications) {
		this.employeeQualifications = employeeQualifications;
	}

	/**
     * Compares the key for this instance with another EmployeeCreation.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class EmployeeCreation and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof EmployeeCreation)) {
            return false;
        }
        EmployeeCreation that = (EmployeeCreation) other;
        Object myId = this.getId();
        Object yourId = that.getId();
        if (myId==null ? yourId!=null : !myId.equals(yourId)) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another EmployeeCreation.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof EmployeeCreation)) return false;
        return this.equalKeys(other) && ((EmployeeCreation)other).equalKeys(this);
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
        StringBuffer sb = new StringBuffer("[EmployeeCreation |");
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
