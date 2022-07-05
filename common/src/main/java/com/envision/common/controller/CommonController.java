 package com.envision.common.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.envision.common.database.projections.ConfigCodeProjection;
import com.envision.common.database.projections.DiscomCityListForState;
import com.envision.common.database.projections.DiscomDetailsResponse;
import com.envision.common.database.projections.DiscomInState;
import com.envision.common.database.projections.DiscomStateListReposne;
import com.envision.common.service.ConfigCodeService;
import com.envision.common.service.DiscomDetailsService;

@RestController
@RequestMapping("common")
public class CommonController {

	@Autowired
	ConfigCodeService configCodeService;

	@Autowired
	DiscomDetailsService discomDetailsService;
	
	@GetMapping("code")
	@ResponseBody
	private List<ConfigCodeProjection> fetchConfigcodeList(	@RequestParam(name = "type", required = true) String codeType) {

		return configCodeService.fetchConfigCodeByCodeTypeforAPI(codeType);

	}

	@GetMapping("discomdata")
	@ResponseBody
	private List<DiscomDetailsResponse> fetchDiscomData(	@RequestParam(name = "discom", required = true) String discom) {

		return discomDetailsService.getDiscomDetails(discom);
	}

	@GetMapping("discomstatelist")
	@ResponseBody
	private List<DiscomStateListReposne> fetchDiscomStates(	) {

		return discomDetailsService.getAllDiscomStates();
	}

	@GetMapping("discominstate")
	@ResponseBody
	private List<DiscomInState> fetchDiscomInState(	@RequestParam(name = "state", required = true) String state) {

		return discomDetailsService.getAllDiscomInState(state);
	}
	@GetMapping("discomcitylist")
	@ResponseBody
	private List<DiscomCityListForState> fetchDiscomcityList(	@RequestParam(name = "state", required = true) String state) {

		return discomDetailsService.getDiscomCitysFromState(state);
	}
	

	@GetMapping("findcodewithsubtype")
	@ResponseBody
	private List<ConfigCodeProjection> fetchAllDiscoms(	@RequestParam(name = "type", required = true) String codeType ,	@RequestParam(name = "subtype", required = true) String subType) {

		return configCodeService.fetchConfigCodeByCodeTypeAndSubType(codeType, subType);
	}
	
}