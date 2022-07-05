// Generated with g9.

package com.envision.sladashboard.database.objects;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="employee_attendance", uniqueConstraints = { @UniqueConstraint( columnNames = { "employee_id", "date" } )})
public class EmployeeAttendance implements Serializable {

    /** Primary key. */
    protected static final String PK = "attendanceId";

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="attendance_id", unique=true, nullable=false, precision=19)
    private Long attendanceId;
    @Column(name="employee_id", length=100)
    private String employeeId;
    private LocalDateTime date;
    @Column(length=5)
    private String status;
    @Column(name="total_days", precision=10)
    private int totalDays;
    @Column(name="eligible_shift_days", precision=10)
    private int eligibleShiftDays;
    @Column(name="present_shift_days", precision=10)
    private int presentShiftDays;
    @Column(name="state_code", length=25)
    private String stateCode;
    @Column(name="discomdetail_code", length=25)
    private String discomdetailCode;
    @Column(name="created_by", length=25)
    private String createdBy;
    @Column(name="created_date")
    private LocalDateTime createdDate;
    /** Default constructor. */
    public EmployeeAttendance() {
        super();
    }

    /**
     * Access method for attendanceId.
     *
     * @return the current value of attendanceId
     */
    public Long getAttendanceId() {
        return attendanceId;
    }

    /**
     * Setter method for attendanceId.
     *
     * @param aAttendanceId the new value for attendanceId
     */
    public void setAttendanceId(Long aAttendanceId) {
        attendanceId = aAttendanceId;
    }

    public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	/**
     * Access method for status.
     *
     * @return the current value of status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Setter method for status.
     *
     * @param aStatus the new value for status
     */
    public void setStatus(String aStatus) {
        status = aStatus;
    }

    /**
     * Access method for totalDays.
     *
     * @return the current value of totalDays
     */
    public int getTotalDays() {
        return totalDays;
    }

    /**
     * Setter method for totalDays.
     *
     * @param aTotalDays the new value for totalDays
     */
    public void setTotalDays(int aTotalDays) {
        totalDays = aTotalDays;
    }

    /**
     * Access method for eligibleShiftDays.
     *
     * @return the current value of eligibleShiftDays
     */
    public int getEligibleShiftDays() {
        return eligibleShiftDays;
    }

    /**
     * Setter method for eligibleShiftDays.
     *
     * @param aEligibleShiftDays the new value for eligibleShiftDays
     */
    public void setEligibleShiftDays(int aEligibleShiftDays) {
        eligibleShiftDays = aEligibleShiftDays;
    }

    /**
     * Access method for presentShiftDays.
     *
     * @return the current value of presentShiftDays
     */
    public int getPresentShiftDays() {
        return presentShiftDays;
    }

    /**
     * Setter method for presentShiftDays.
     *
     * @param aPresentShiftDays the new value for presentShiftDays
     */
    public void setPresentShiftDays(int aPresentShiftDays) {
        presentShiftDays = aPresentShiftDays;
    }

    /**
     * Access method for stateCode.
     *
     * @return the current value of stateCode
     */
    public String getStateCode() {
        return stateCode;
    }

    /**
     * Setter method for stateCode.
     *
     * @param aStateCode the new value for stateCode
     */
    public void setStateCode(String aStateCode) {
        stateCode = aStateCode;
    }

    /**
     * Access method for discomdetailCode.
     *
     * @return the current value of discomdetailCode
     */
    public String getDiscomdetailCode() {
        return discomdetailCode;
    }

    /**
     * Setter method for discomdetailCode.
     *
     * @param aDiscomdetailCode the new value for discomdetailCode
     */
    public void setDiscomdetailCode(String aDiscomdetailCode) {
        discomdetailCode = aDiscomdetailCode;
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
     * Compares the key for this instance with another EmployeeAttendance.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class EmployeeAttendance and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof EmployeeAttendance)) {
            return false;
        }
        EmployeeAttendance that = (EmployeeAttendance) other;
        Object myAttendanceId = this.getAttendanceId();
        Object yourAttendanceId = that.getAttendanceId();
        if (myAttendanceId==null ? yourAttendanceId!=null : !myAttendanceId.equals(yourAttendanceId)) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another EmployeeAttendance.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof EmployeeAttendance)) return false;
        return this.equalKeys(other) && ((EmployeeAttendance)other).equalKeys(this);
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
        if (getAttendanceId() == null) {
            i = 0;
        } else {
            i = getAttendanceId().hashCode();
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
        StringBuffer sb = new StringBuffer("[EmployeeAttendance |");
        sb.append(" attendanceId=").append(getAttendanceId());
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
        ret.put("attendanceId", getAttendanceId());
        return ret;
    }

}
