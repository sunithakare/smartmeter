package com.envision.sladashboard.database.dao;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.envision.sladashboard.database.objects.EmployeeAttendance;


@Repository
public interface EmployeeAttendanceDAO extends JpaRepository<EmployeeAttendance, Long>{
	void deleteByEmployeeIdAndDateGreaterThanEqualAndDateLessThanEqual(String employeeId, LocalDateTime startDate, LocalDateTime endDate);
	List<EmployeeAttendance> findByEmployeeIdAndDateGreaterThanEqualAndDateLessThanEqual(String employeeId, LocalDateTime startDate, LocalDateTime endDate);

}
