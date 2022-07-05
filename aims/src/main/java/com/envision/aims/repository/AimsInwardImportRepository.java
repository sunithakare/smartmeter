package com.envision.aims.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.envision.aims.entity.AimsInwardImport;
import com.envision.aims.model.AimsInwardFromSupplierListVO;

@Repository
public interface AimsInwardImportRepository extends JpaRepository<AimsInwardImport, String> {

    Page<AimsInwardImport> findByDiscom(String discom, Pageable pageableObj);
    Page<AimsInwardFromSupplierListVO> findAllBy(Pageable pageable);
}