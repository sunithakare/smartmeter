package com.envision.aims.repository;

import com.envision.aims.entity.AimsOutwardImport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AimsOutwardImportRepository extends JpaRepository<AimsOutwardImport, String> {

    Page<AimsOutwardImport> findByDiscom(String discom, Pageable pageableObj);

}