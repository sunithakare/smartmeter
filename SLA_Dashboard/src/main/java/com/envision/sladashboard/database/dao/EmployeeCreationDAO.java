package com.envision.sladashboard.database.dao;


import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.envision.sladashboard.database.objects.EmployeeCreation;
import com.envision.sladashboard.database.projections.EmployeeCreationProjection;

@Repository
public interface EmployeeCreationDAO extends JpaRepository<EmployeeCreation, Long>{
	
	Optional<EmployeeCreation> findByEmployeeId (String employeeId);
	Page<EmployeeCreationProjection> findAllBy(Pageable pageable);
	
	Page<EmployeeCreationProjection> findAllByemployeeNameContainingIgnoreCaseAndEmployeeRoles_RoleNameContainingIgnoreCaseAndEmployeeIdContainingIgnoreCase(Pageable pageable,String userName, String role,String empId);
}
