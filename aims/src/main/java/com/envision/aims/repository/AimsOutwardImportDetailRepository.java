package com.envision.aims.repository;

import com.envision.aims.entity.AimsOutwardImportDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AimsOutwardImportDetailRepository extends JpaRepository<AimsOutwardImportDetail, Integer> {
    Page<AimsOutwardImportDetail> findByOutwardImport(String referenceId, Pageable pageableObj);
}