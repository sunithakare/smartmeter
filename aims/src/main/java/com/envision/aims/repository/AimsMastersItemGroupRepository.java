package com.envision.aims.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.envision.aims.entity.AimsItemGroup;
import com.envision.aims.model.AimsMastersItemGroupDto;
import com.envision.aims.model.AimsMastersItemGroupVO;
import com.envision.aims.model.AimsMastersItemModelNameFetchDto;

@Repository
public interface AimsMastersItemGroupRepository extends JpaRepository<AimsItemGroup, Long>{

	Page<AimsMastersItemGroupDto> findAllBy(Pageable pageObj);

	Page<AimsMastersItemGroupDto> findByItemGroup(String itemGroup, Pageable pageableObj);
	Page<AimsMastersItemGroupDto> findAllFilterDataByItemGroup(Pageable pageObj, String itemGroup);

	AimsItemGroup findByItemGroup(String itemGroup);


	AimsMastersItemGroupDto findAllByItemGroup(String itemGroup);

	
}
