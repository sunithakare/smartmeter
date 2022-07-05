package com.envision.aims.repository;

import com.envision.aims.entity.AimsReturnSubcntrctDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AimsReturnSubcntrctDetailRepository extends JpaRepository<AimsReturnSubcntrctDetail, Integer> {
    Page<AimsReturnSubcntrctDetail> findByReturnSubcontractor(String referenceId, Pageable pageableObj);
}