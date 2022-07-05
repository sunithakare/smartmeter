package com.envision.common.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.envision.common.database.dao.ConfigCodeDAO;
import com.envision.common.database.objects.ConfigCodes;
import com.envision.common.database.projections.ConfigCodeProjection;

@Service
public class ConfigCodeService {
	@Autowired
	ConfigCodeDAO configCodeDAO;

	public List<ConfigCodeProjection> fetchConfigCodeByCodeTypeforAPI(String codeType) {
		return configCodeDAO.findProjectedDataBycodeType(codeType);

	}

	public List<ConfigCodes> fetchConfigCodeByCodeType(String codeType) {
		return configCodeDAO.findBycodeType(codeType);

	}
	public List<ConfigCodeProjection> fetchConfigCodeByCodeTypeAndSubType(String codeType,String subType) {
		return configCodeDAO.findProjectedDataBycodeTypeAndSubType(codeType, subType);

	}
}
