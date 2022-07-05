package com.envision.aims.service;

import com.envision.aims.entity.AimsOutwardImportDetail;
import com.envision.aims.repository.AimsOutwardImportDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AimsOutwardImportDetailService {

    @Autowired
    AimsOutwardImportDetailRepository aimsOutwardImportDetailRepository;

    public Page<AimsOutwardImportDetail> getAimsOutwardImportDetail(String referenceId, Pageable pageableObj){

        return aimsOutwardImportDetailRepository.findByOutwardImport(referenceId, pageableObj);
    }
}
