package com.envision.common.database.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.envision.common.database.objects.DiscomDetails;
import com.envision.common.database.projections.DiscomCityListForState;
import com.envision.common.database.projections.DiscomDetailsResponse;
import com.envision.common.database.projections.DiscomInState;
import com.envision.common.database.projections.DiscomStateListReposne;


@Repository
public interface DiscomDetailsDAO extends JpaRepository<DiscomDetails, Integer>  { 

	List<DiscomDetailsResponse> findByDiscomCode(String discomCode);
	List<DiscomInState> findByState(String state);
	List<DiscomStateListReposne> findDistinctStateBy();
	List<DiscomCityListForState> findDistinctCityByState(String state);
	List<DiscomInState> findDistinctBy();
}