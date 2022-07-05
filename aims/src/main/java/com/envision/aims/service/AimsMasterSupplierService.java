package com.envision.aims.service;

import java.sql.SQLDataException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.envision.aims.entity.AimsSupplierGroupNameMapping;
import com.envision.aims.model.*;
import com.envision.aims.repository.AimsMastersSupplierRepository;
import com.envision.aims.repository.AimsSupplierGroupNameMappingRepository;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.poi.util.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.envision.aims.entity.AimsSupplier;
import com.envision.aims.repository.AimsMastersItemGroupRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class AimsMasterSupplierService {

	private static final String ACTIVE = "Active";
	@Autowired
	AimsMastersSupplierRepository aimsMasterSupplierRepository;
	@Autowired
	AimsSupplierGroupNameMappingRepository aimsSupplierGroupNameMappingRepository;
	
	public Page<AimsSupplier> getSupplier( Pageable pageableObj){
		return aimsMasterSupplierRepository.findAll( pageableObj);
	}

	@Transactional(rollbackFor = SQLDataException.class)
	public AimsSupplier saveAimsSupplier(AimsMasterSupplierDto aimsMasterSupplierDto) {

		AimsSupplier aimsSupplierentity = new AimsSupplier();
		BeanUtils.copyProperties(aimsMasterSupplierDto, aimsSupplierentity);
		if(ObjectUtils.isEmpty(aimsSupplierentity.getSupplierCode())){
			String supplierCode=String.valueOf(aimsMasterSupplierRepository.getNextValSequenceName());
			aimsSupplierentity.setSupplierCode(supplierCode);
		}

		aimsSupplierentity = aimsMasterSupplierRepository.save(aimsSupplierentity);
		List<AimsSupplierGroupNameMapping> aimsSupplierGroupNameMappingList = new ArrayList<>();
		for (AimsSupplierGroupNameMappingDTO groupName : aimsMasterSupplierDto.getGroups()) {
			AimsSupplierGroupNameMapping aimsSupplierGroupNameMapping = new AimsSupplierGroupNameMapping();
			BeanUtils.copyProperties(groupName, aimsSupplierGroupNameMapping);
			aimsSupplierGroupNameMapping.setSupplier(aimsSupplierentity);
			aimsSupplierGroupNameMappingList.add(aimsSupplierGroupNameMapping);
		}
		aimsSupplierGroupNameMappingList = aimsSupplierGroupNameMappingRepository.saveAll(aimsSupplierGroupNameMappingList);
		aimsSupplierentity.setGroups(aimsSupplierGroupNameMappingList);
		return aimsSupplierentity;
	}


	public List<AimsSupplier> getActiveSupplier(){
 	   return aimsMasterSupplierRepository.findByStatus(ACTIVE);
    }
	
	public void deleteGroupName(Long id) {
		aimsSupplierGroupNameMappingRepository.deleteById(id);
	}
	

}
