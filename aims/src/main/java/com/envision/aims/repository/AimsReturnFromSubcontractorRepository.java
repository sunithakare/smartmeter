package com.envision.aims.repository;

import com.envision.aims.entity.AimsReturnFromSubcontractor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AimsReturnFromSubcontractorRepository extends JpaRepository<AimsReturnFromSubcontractor, String> {

    Page<AimsReturnFromSubcontractor> findByDiscom(String discom, Pageable pageableObj);
}