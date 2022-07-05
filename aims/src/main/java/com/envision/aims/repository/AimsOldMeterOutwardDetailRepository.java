package com.envision.aims.repository;

import com.envision.aims.entity.AimsOldMeterOutwardDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AimsOldMeterOutwardDetailRepository extends JpaRepository<AimsOldMeterOutwardDetail, Integer> {
    Page<AimsOldMeterOutwardDetail> findByOldMeterOutward(String referenceId, Pageable pageableObj);
}