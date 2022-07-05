package com.envision.aims.repository;

import com.envision.aims.entity.AimsReturnToSupplier;
import com.envision.aims.model.AimsReturnToSupplierListVO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AimsReturnToSupplierRepository extends JpaRepository<AimsReturnToSupplier, String> {

    Page<AimsReturnToSupplierListVO> findAllBy(Pageable pageableObj);
}