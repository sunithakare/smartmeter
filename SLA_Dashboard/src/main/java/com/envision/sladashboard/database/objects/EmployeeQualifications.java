// Generated with g9.

package com.envision.sladashboard.database.objects;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity(name="employee_qualifications")
public class EmployeeQualifications implements Serializable {

    /** Primary key. */
    protected static final String PK = "id";

    @Id
    @Column(unique=true, nullable=false, precision=10)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(length=100)
    private String name;
    @Column(length=50)
    private String type;
    @Column(name="file_doc_id", length=500)
    private Long fileDocId;
    @Column(name="emp_id", length=100)
    private String employeeId;
//    @OneToOne(mappedBy="employeeQualifications")
//    @JoinColumn(name="emp_id")
//    private EmployeeCreation employeeCreation;

    /** Default constructor. */
    public EmployeeQualifications() {
        super();
    }


    public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	/**
     * Access method for name.
     *
     * @return the current value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter method for name.
     *
     * @param aName the new value for name
     */
    public void setName(String aName) {
        name = aName;
    }

    /**
     * Access method for type.
     *
     * @return the current value of type
     */
    public String getType() {
        return type;
    }

    public String getEmployeeId() {
		return employeeId;
	}


	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}


	/**
     * Setter method for type.
     *
     * @param aType the new value for type
     */
    public void setType(String aType) {
        type = aType;
    }

    /**
     * Access method for filePath.
     *
     * @return the current value of filePath
     */


//    /**
//     * Access method for employeeCreation.
//     *
//     * @return the current value of employeeCreation
//     */
//    public EmployeeCreation getEmployeeCreation() {
//        return employeeCreation;
//    }
//
//    /**
//     * Setter method for employeeCreation.
//     *
//     * @param aEmployeeCreation the new value for employeeCreation
//     */
//    public void setEmployeeCreation(EmployeeCreation aEmployeeCreation) {
//        employeeCreation = aEmployeeCreation;
//    }

    /**
     * Compares the key for this instance with another EmployeeQualifications.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class EmployeeQualifications and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof EmployeeQualifications)) {
            return false;
        }
        EmployeeQualifications that = (EmployeeQualifications) other;
        if (this.getId() != that.getId()) {
            return false;
        }
        return true;
    }

    public Long getFileDocId() {
		return fileDocId;
	}


	public void setFileDocId(Long fileDocId) {
		this.fileDocId = fileDocId;
	}


	/**
     * Compares this instance with another EmployeeQualifications.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof EmployeeQualifications)) return false;
        return this.equalKeys(other) && ((EmployeeQualifications)other).equalKeys(this);
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
        i = getId().hashCode();
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
        StringBuffer sb = new StringBuffer("[EmployeeQualifications |");
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
