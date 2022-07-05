package com.envision.login.database.dao;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.envision.login.database.objects.SystemAccessTbl;
import com.envision.login.database.projections.ModuleResponse;
import com.envision.login.database.projections.SystemAccessResponse;
@Repository
public interface SystemAccessDAO extends JpaRepository<SystemAccessTbl, Long> { 


	Set<SystemAccessTbl> findAllByPrivilegeNameInAndPrivilegeTypeIn(Set<String> privilegeNameList,Set<String> privilegeTypeList);
	
	List<ModuleResponse> findDistinctModuleBy();
	List<SystemAccessResponse> findByModule(String module);
}
