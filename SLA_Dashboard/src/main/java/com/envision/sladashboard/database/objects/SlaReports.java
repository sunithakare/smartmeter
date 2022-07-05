// Generated with g9.

package com.envision.sladashboard.database.objects;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

import org.springframework.data.annotation.CreatedDate;

@Entity(name="sla_reports")
@IdClass(SlaReportsId.class)
public class SlaReports implements Serializable {


    /**
	 * 
	 */
	private static final long serialVersionUID = -8014414188841796438L;

	/** Primary key. */
    protected static final String PK = "SLAReportsSlaReportsPk";

    @Id
    @Column(nullable=false, length=100)
    private String module;
    @Id
    @Column(name="report_name", nullable=false)
    private String reportName;
    @Column(name="rfp_no_type", length=20)
    private String rfpNoType;
    @Column(length=500)
    private String path;
    @Column(name="Timeframe", length=100)
    private String timeframe;
    @Column(name="created_by", length=25)
    private String createdBy;
    @CreatedDate
    @Column(name="created_date")
    private LocalDateTime createdDate;
    @Column(name="modified_by", length=25)
    private String modifiedBy;
    @Column(name="modified_date")
    private LocalDateTime modifiedDate;
    @Column(name="is_active", length=1)
    private boolean isActive;
    @Column(name="report_type")
    private String reportType;

    /** Default constructor. */
    public SlaReports() {
        super();
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
     * Access method for reportName.
     *
     * @return the current value of reportName
     */
    public String getReportName() {
        return reportName;
    }

    /**
     * Setter method for reportName.
     *
     * @param aReportName the new value for reportName
     */
    public void setReportName(String aReportName) {
        reportName = aReportName;
    }

    /**
     * Access method for rfpNoType.
     *
     * @return the current value of rfpNoType
     */
    public String getRfpNoType() {
        return rfpNoType;
    }

    /**
     * Setter method for rfpNoType.
     *
     * @param aRfpNoType the new value for rfpNoType
     */
    public void setRfpNoType(String aRfpNoType) {
        rfpNoType = aRfpNoType;
    }

    /**
     * Access method for path.
     *
     * @return the current value of path
     */
    public String getPath() {
        return path;
    }

    /**
     * Setter method for path.
     *
     * @param aPath the new value for path
     */
    public void setPath(String aPath) {
        path = aPath;
    }

    /**
     * Access method for timeframe.
     *
     * @return the current value of timeframe
     */
    public String getTimeframe() {
        return timeframe;
    }

    /**
     * Setter method for timeframe.
     *
     * @param aTimeframe the new value for timeframe
     */
    public void setTimeframe(String aTimeframe) {
        timeframe = aTimeframe;
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
     * Access method for isActive.
     *
     * @return true if and only if isActive is currently true
     */
    public boolean getIsActive() {
        return isActive;
    }

    /**
     * Setter method for isActive.
     *
     * @param aIsActive the new value for isActive
     */
    public void setIsActive(boolean aIsActive) {
        isActive = aIsActive;
    }

    /**
     * Access method for reportType.
     *
     * @return the current value of reportType
     */
    public String getReportType() {
        return reportType;
    }

    /**
     * Setter method for reportType.
     *
     * @param aReportType the new value for reportType
     */
    public void setReportType(String aReportType) {
        reportType = aReportType;
    }

    /**
     * Compares the key for this instance with another SLAReports.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class SLAReports and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof SlaReports)) {
            return false;
        }
        SlaReports that = (SlaReports) other;
        Object myModule = this.getModule();
        Object yourModule = that.getModule();
        if (myModule==null ? yourModule!=null : !myModule.equals(yourModule)) {
            return false;
        }
        Object myReportName = this.getReportName();
        Object yourReportName = that.getReportName();
        if (myReportName==null ? yourReportName!=null : !myReportName.equals(yourReportName)) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another SLAReports.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof SlaReports)) return false;
        return this.equalKeys(other) && ((SlaReports)other).equalKeys(this);
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
        if (getModule() == null) {
            i = 0;
        } else {
            i = getModule().hashCode();
        }
        result = 37*result + i;
        if (getReportName() == null) {
            i = 0;
        } else {
            i = getReportName().hashCode();
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
        StringBuffer sb = new StringBuffer("[SLAReports |");
        sb.append(" module=").append(getModule());
        sb.append(" reportName=").append(getReportName());
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
        ret.put("module", getModule());
        ret.put("reportName", getReportName());
        return ret;
    }

}
