package com.envision.aims.repository;

import com.envision.aims.entity.AimsOldMeterOutward;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AimsOldMeterOutwardRepository extends JpaRepository<AimsOldMeterOutward, String> {
    Page<AimsOldMeterOutward> findByDiscom(String discom, Pageable pageableObj);
}