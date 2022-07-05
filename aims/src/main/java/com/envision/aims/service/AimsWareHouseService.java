package com.envision.aims.service;

import java.util.ArrayList;
import java.util.List;

import com.envision.aims.entity.AimsCityWarehouseMapping;
import com.envision.aims.model.*;
import com.envision.aims.repository.AimsCityWarehouseMappingRepository;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.envision.aims.entity.AimsWarehouse;
import com.envision.aims.repository.AimsWareHouseRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AimsWareHouseService {


	private static final String ACTIVE = "Active";

	@Autowired
	AimsWareHouseRepository aimsWareHouseRepo;

	@Autowired
	AimsCityWarehouseMappingRepository aimsCityWarehouseMappingRepository;
	
	
	public Page<AimsWarehouse> findAllBy(Pageable pageObj){
		
		return aimsWareHouseRepo.findAll(pageObj);
	}
	
	public List<AimsWarehouse> getActiveWarehouse() {
		return aimsWareHouseRepo.findByStatus(ACTIVE);
	}

	public AimsWarehouse saveWarehouse(AimsWarehouseDto aimsWarehouseDto) {


		AimsWarehouse aimsWarehouse = new AimsWarehouse();
		BeanUtils.copyProperties(aimsWarehouseDto, aimsWarehouse);
		if(ObjectUtils.isEmpty(aimsWarehouse.getWarehouseCode())){
			String warehouseCode=String.valueOf(aimsWareHouseRepo.getNextValSequenceName());
			aimsWarehouse.setWarehouseCode(warehouseCode);
		}
		aimsWarehouse = aimsWareHouseRepo.save(aimsWarehouse);

		List<AimsCityWarehouseMapping> aimsCityWarehouseMappings = new ArrayList<>();
		for(AimsCityWarehouseMappingDTO aimsCityWarehouseMappingDTO:aimsWarehouseDto.getDiscomDetails()) {
			AimsCityWarehouseMapping aimsCityWarehouseMapping = new AimsCityWarehouseMapping();
			BeanUtils.copyProperties(aimsCityWarehouseMappingDTO, aimsCityWarehouseMapping);
			aimsCityWarehouseMapping.setWarehouse(aimsWarehouse);
			aimsCityWarehouseMappings.add(aimsCityWarehouseMapping);

		}

		aimsCityWarehouseMappings = aimsCityWarehouseMappingRepository.saveAll(aimsCityWarehouseMappings);
		aimsWarehouse.setDiscomDetails(aimsCityWarehouseMappings);
		return aimsWarehouse;
	}
	
	public void deleteWarehouseMapping(Long id) {
		aimsCityWarehouseMappingRepository.deleteById(id);
	}
	
}
