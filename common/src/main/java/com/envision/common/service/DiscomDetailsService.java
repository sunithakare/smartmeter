package com.envision.common.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.envision.common.database.dao.DiscomDetailsDAO;
import com.envision.common.database.projections.DiscomCityListForState;
import com.envision.common.database.projections.DiscomDetailsResponse;
import com.envision.common.database.projections.DiscomInState;
import com.envision.common.database.projections.DiscomStateListReposne;

@Service
public class DiscomDetailsService {

	@Autowired
	DiscomDetailsDAO discomDetailsDAO;
	
	public List<DiscomDetailsResponse> getDiscomDetails(String discomCode) {
		return discomDetailsDAO.findByDiscomCode(discomCode);
	}

	public List<DiscomInState> getAllDiscomInState(String state) {
		return discomDetailsDAO.findByState(state);
	}

	public List<DiscomInState> getAllDiscom() {
		return discomDetailsDAO.findDistinctBy();
	}
	public List<DiscomStateListReposne> getAllDiscomStates() {
		return discomDetailsDAO.findDistinctStateBy();
	}
	public List<DiscomCityListForState> getDiscomCitysFromState(String state) {
		return discomDetailsDAO.findDistinctCityByState(state);
	}
}
