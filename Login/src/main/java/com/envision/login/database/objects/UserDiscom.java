// Generated with g9.

package com.envision.login.database.objects;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.envision.common.database.objects.DiscomDetails;

@Entity(name="user_discom")
public class UserDiscom implements Serializable {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(unique=true, nullable=false, precision=19)
    private Long id;
    @Column(length=1)
    private boolean isdefault;
    @Column(name="start_date")
    private LocalDateTime startDate;
    @Column(name="end_date")
    private LocalDateTime endDate;

    @Column(name="user_name")
    private String user;
    
    @Column(name="discom_name")
    private String discomName;
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name="discom_name",referencedColumnName="discom_code", nullable=false,insertable = false,updatable = false)
    private DiscomDetails discomDetails;

    /** Default constructor. */
    public UserDiscom() {
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
     * Access method for isdefault.
     *
     * @return true if and only if isdefault is currently true
     */
    public boolean getIsdefault() {
        return isdefault;
    }

    /**
     * Setter method for isdefault.
     *
     * @param aIsdefault the new value for isdefault
     */
    public void setIsdefault(boolean aIsdefault) {
        isdefault = aIsdefault;
    }

    /**
     * Access method for startDate.
     *
     * @return the current value of startDate
     */
    public LocalDateTime getStartDate() {
        return startDate;
    }

    /**
     * Setter method for startDate.
     *
     * @param aStartDate the new value for startDate
     */
    public void setStartDate(LocalDateTime aStartDate) {
        startDate = aStartDate;
    }

    /**
     * Access method for endDate.
     *
     * @return the current value of endDate
     */
    public LocalDateTime getEndDate() {
        return endDate;
    }


    public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getDiscomName() {
		return discomName;
	}

	public void setDiscomName(String discomName) {
		this.discomName = discomName;
	}

	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}


    /**
     * Access method for discomDetails.
     *
     * @return the current value of discomDetails
     */
    public DiscomDetails getDiscomDetails() {
        return discomDetails;
    }

    /**
     * Setter method for discomDetails.
     *
     * @param aDiscomDetails the new value for discomDetails
     */
    public void setDiscomDetails(DiscomDetails aDiscomDetails) {
        discomDetails = aDiscomDetails;
    }

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return this.discomName.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof UserDiscom) {
			if(this.discomName.equals(((UserDiscom)obj).discomName)){
				return true;
			}
		}
		return super.equals(obj);
	}

}
