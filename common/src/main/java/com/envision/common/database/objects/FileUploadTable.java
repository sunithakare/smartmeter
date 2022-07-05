// Generated with g9.

package com.envision.common.database.objects;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity(name="file_upload_table")
@EntityListeners(AuditingEntityListener.class)
public class FileUploadTable implements Serializable {

    /** Primary key. */
    protected static final String PK = "id";

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(unique=true, nullable=false, precision=19)
    private Long id;
    private String filename;
    private String filepath;
    @CreatedBy
    @Column(length=150)
    private String createdby;
    @CreatedDate
    private LocalDateTime createddate;

    /** Default constructor. */
    public FileUploadTable() {
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
     * Access method for filepath.
     *
     * @return the current value of filepath
     */
    public String getFilepath() {
        return filepath;
    }

    /**
     * Setter method for filepath.
     *
     * @param aFilepath the new value for filepath
     */
    public void setFilepath(String aFilepath) {
        filepath = aFilepath;
    }

    public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	/**
     * Access method for createdby.
     *
     * @return the current value of createdby
     */
    public String getCreatedby() {
        return createdby;
    }

    /**
     * Setter method for createdby.
     *
     * @param aCreatedby the new value for createdby
     */
    public void setCreatedby(String aCreatedby) {
        createdby = aCreatedby;
    }

    /**
     * Access method for createddate.
     *
     * @return the current value of createddate
     */
    public LocalDateTime getCreateddate() {
        return createddate;
    }

    /**
     * Setter method for createddate.
     *
     * @param aCreateddate the new value for createddate
     */
    public void setCreateddate(LocalDateTime aCreateddate) {
        createddate = aCreateddate;
    }

    /**
     * Compares the key for this instance with another FileUploadTable.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class FileUploadTable and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof FileUploadTable)) {
            return false;
        }
        FileUploadTable that = (FileUploadTable) other;
        Object myId = this.getId();
        Object yourId = that.getId();
        if (myId==null ? yourId!=null : !myId.equals(yourId)) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another FileUploadTable.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof FileUploadTable)) return false;
        return this.equalKeys(other) && ((FileUploadTable)other).equalKeys(this);
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
        StringBuffer sb = new StringBuffer("[FileUploadTable |");
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
