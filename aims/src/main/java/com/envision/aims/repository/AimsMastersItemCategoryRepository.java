package com.envision.aims.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.envision.aims.entity.AimsItemCategory;
import com.envision.aims.model.AimsMastersItemCategoryVO;

@Repository
public interface AimsMastersItemCategoryRepository extends JpaRepository<AimsItemCategory, Long>{
	
	List<AimsItemCategory> findByStatus(String status);
	Page<AimsMastersItemCategoryVO> findAllBy(Pageable pageObj);
	Optional<AimsItemCategory> findByItemCategory(String itemCategory);
	Page<AimsMastersItemCategoryVO> findAllFilterDataByItemCategoryContainingIgnoreCaseAndStatus(Pageable pageObj, String itemCategory, String status);
	AimsMastersItemCategoryVO findAllByItemCategory(String itemCategory);
	
}
