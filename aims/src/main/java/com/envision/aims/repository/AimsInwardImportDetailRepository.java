package com.envision.aims.repository;

import com.envision.aims.entity.AimsInwardImportDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AimsInwardImportDetailRepository extends JpaRepository<AimsInwardImportDetail, Integer> {
    Page<AimsInwardImportDetail> findByInwardImport(String referenceId, Pageable pageableObj);
}