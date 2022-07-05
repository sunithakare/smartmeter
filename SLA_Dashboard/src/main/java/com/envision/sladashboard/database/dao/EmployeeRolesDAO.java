package com.envision.sladashboard.database.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.envision.sladashboard.database.objects.EmployeeRoles;
import com.envision.sladashboard.modal.EmployeeRoleDetail;

public interface EmployeeRolesDAO extends JpaRepository<EmployeeRoles, Long> {

	List<EmployeeRoleDetail> findAllBy();
	Optional<EmployeeRoles> findByRoleNameAndRoleCategory(String roleName,String roleCategory);
	List<EmployeeRoleDetail> findAllByRoleCategory(String category);
}
