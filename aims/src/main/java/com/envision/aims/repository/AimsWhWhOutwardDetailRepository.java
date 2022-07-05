package com.envision.aims.repository;

import com.envision.aims.entity.AimsWhWhOutwardDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface AimsWhWhOutwardDetailRepository extends JpaRepository<AimsWhWhOutwardDetail, Integer> {

    Page<AimsWhWhOutwardDetail> findByWhWhOutward(String referenceId, Pageable pageableObj);
}