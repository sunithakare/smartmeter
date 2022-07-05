package com.envision.aims.repository;

import com.envision.aims.entity.AimsOldMeterInward;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AimsOldMeterInwardRepository extends JpaRepository<AimsOldMeterInward, String> {

    Page<AimsOldMeterInward> findByDiscom(String discom, Pageable pageableObj);
}