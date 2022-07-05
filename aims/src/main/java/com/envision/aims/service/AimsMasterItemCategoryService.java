package com.envision.aims.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.envision.aims.entity.AimsItemCategory;
import com.envision.aims.model.AimsMastersItemCategoryDto;
import com.envision.aims.model.AimsMastersItemCategoryVO;
import com.envision.aims.repository.AimsMastersItemCategoryRepository;
import com.envision.aims.repository.AimsMastersItemGroupRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AimsMasterItemCategoryService {
	
	@Autowired
	private AimsMastersItemCategoryRepository aimsMastersItemCategoryRepository;
	@Autowired
	private AimsMastersItemGroupRepository aimsMastersItemGroupRepository;

	private static final String ACTIVE = "Active";
	

	
	
	public AimsItemCategory saveMasterItemCategory(AimsMastersItemCategoryDto aimsMastersItemCategoryDto) {
		
		AimsItemCategory aimsItem=new AimsItemCategory();
		BeanUtils.copyProperties(aimsMastersItemCategoryDto, aimsItem);

		aimsItem = aimsMastersItemCategoryRepository.save(aimsItem);
		
		return aimsItem;
	}


	public Page<AimsItemCategory> getItemCategory(Pageable pageObj) {
		return aimsMastersItemCategoryRepository.findAll(pageObj);
	}

	public List<AimsItemCategory> getItemCategory() {
		return aimsMastersItemCategoryRepository.findByStatus(ACTIVE);
	}
}
