package com.envision.common.database.dao;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.envision.common.database.objects.ConfigCodes;
import com.envision.common.database.projections.ConfigCodeProjection;

@Repository
public interface ConfigCodeDAO extends JpaRepository<ConfigCodes, BigDecimal>  { 

	List<ConfigCodes> findBycodeType(String codeType);
	List<ConfigCodeProjection> findProjectedDataBycodeType(String codeType);
	List<ConfigCodeProjection> findBycodeTypeAndShortCodeIn(String codeType,List<String> shor);
	List<ConfigCodeProjection> findProjectedDataBycodeTypeAndSubType(String codeType,String subType);
}
