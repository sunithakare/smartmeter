package com.envision.login.database.dao;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.envision.login.database.objects.UserDiscom;
import com.envision.login.database.projections.UserDiscomData;

@Repository
public interface UserDiscomDAO extends JpaRepository<UserDiscom, Long> { 

	List<UserDiscomData> findByUserAndStartDateBeforeAndEndDateAfter(String user,LocalDateTime startDate,LocalDateTime endDate);
	
}
