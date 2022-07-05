package com.envision.aims.repository;

import com.envision.aims.entity.AimsImportMiDatum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AimsImportMiDatumRepository extends JpaRepository<AimsImportMiDatum, String> {
}