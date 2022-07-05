package com.envision.sladashboard.database.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.envision.sladashboard.database.objects.ImpleAuditRecom;

@Repository
public interface ImpleAuditRecomDAO extends JpaRepository<ImpleAuditRecom, Long>  {

	Long  deleteByQuarterlyAndYearAndStatedetailCodeAndDiscomdetailCode(String quarter, String year, String state,String discom);

	

}
