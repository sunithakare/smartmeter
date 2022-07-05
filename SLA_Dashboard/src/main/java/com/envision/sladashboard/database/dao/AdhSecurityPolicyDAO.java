package com.envision.sladashboard.database.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.envision.sladashboard.database.objects.AdhSecurityPolicy;

@Repository
public interface AdhSecurityPolicyDAO extends JpaRepository<AdhSecurityPolicy, Long>  {
	
	Long deleteByQuarterlyAndYearAndStatedetailCodeAndDiscomdetailCode(String quater,String year,String state,String discom);

}
