package com.envision.aims.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.envision.aims.entity.AimsItemModelName;
import com.envision.aims.model.AimsMastersItemGroupVO;
import com.envision.aims.model.AimsMastersItemModelNameFetchDto;
import com.envision.aims.model.AimsSupplierCategoryLookUpDto;
import com.envision.aims.model.AimsItemModelNameDto;
import com.envision.aims.model.AimsItemModelNameLookUpDto;
import com.envision.aims.model.AimsMasterItemModelNameDto;
import com.envision.aims.model.AimsMastersItemGroupDto;

@Repository
public interface AimsMasterItemModelNameRepository extends JpaRepository<AimsItemModelName, Integer>{
	

	
	Optional<AimsItemModelName> findById(Long id);
	Page<AimsMastersItemModelNameFetchDto> findAllBy(Pageable pageObj);
	Optional<AimsItemModelName> findByItemModelName(String itemModelName);
	AimsMastersItemModelNameFetchDto findAllByItemModelName(String itemModelName);
	Page<AimsMastersItemModelNameFetchDto> findAllFilterDataByItemModelName(Pageable pageObj, String itemModelName);
	
	List<AimsMastersItemGroupVO> findItemGroupBy();
	List<AimsItemModelNameDto> findItemModelNameByItemGroup(String itemGroup);
	AimsSupplierCategoryLookUpDto findByItemGroupAndItemModelName(String itemGroup,String itemModelName);
	
}
