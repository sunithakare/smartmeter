package com.envision.aims.repository;

import com.envision.aims.entity.AimsWhWhInwardDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AimsWhWhInwardDetailRepository extends JpaRepository<AimsWhWhInwardDetail, Integer> {
    Page<AimsWhWhInwardDetail> findByWhWhInward(String referenceId, Pageable pageableObj);
}