package com.envision.aims.repository;

import com.envision.aims.entity.AimsImportMiDataDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AimsImportMiDataDetailRepository extends JpaRepository<AimsImportMiDataDetail, Integer> {
}