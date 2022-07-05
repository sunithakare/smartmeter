package com.envision.aims.repository;

import com.envision.aims.entity.AimsWhWhOutward;
import com.envision.aims.model.AimsWhWhOutwardDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface AimsWhWhOutwardRepository extends JpaRepository<AimsWhWhOutward, String> {

    Page<AimsWhWhOutward> findByDiscomFrom(String discom, Pageable pageableObj);
}