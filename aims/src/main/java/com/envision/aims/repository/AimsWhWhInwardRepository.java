package com.envision.aims.repository;

import com.envision.aims.entity.AimsWhWhInward;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AimsWhWhInwardRepository extends JpaRepository<AimsWhWhInward, String> {

    Page<AimsWhWhInward> findByDiscomTo(String discom, Pageable pageableObj);

}