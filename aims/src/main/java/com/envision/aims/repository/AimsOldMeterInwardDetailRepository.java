package com.envision.aims.repository;

import com.envision.aims.entity.AimsOldMeterInwardDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AimsOldMeterInwardDetailRepository extends JpaRepository<AimsOldMeterInwardDetail, Integer> {
    Page<AimsOldMeterInwardDetail> findByOldMeterInward(String referenceId, Pageable pageableObj);
}