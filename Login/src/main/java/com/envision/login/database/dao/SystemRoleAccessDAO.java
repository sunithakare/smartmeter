package com.envision.login.database.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.envision.login.database.objects.SystemRoleAccessMappingTbl;

@Repository
public interface SystemRoleAccessDAO extends JpaRepository<SystemRoleAccessMappingTbl, Long> { 

}
