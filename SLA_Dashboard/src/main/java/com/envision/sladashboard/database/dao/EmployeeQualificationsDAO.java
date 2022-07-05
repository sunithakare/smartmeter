package com.envision.sladashboard.database.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.envision.sladashboard.database.objects.EmployeeQualifications;

@Repository
public interface EmployeeQualificationsDAO extends JpaRepository<EmployeeQualifications, Long>{


}
