package com.envision.aims.service;

import com.envision.aims.entity.AimsInwardImportDetail;
import com.envision.aims.repository.AimsInwardImportDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AimsInwardImportDetailService {

    @Autowired
    AimsInwardImportDetailRepository aimsInwardImportDetailRepository;

    public Page<AimsInwardImportDetail> getAimsInwardImportDetail(String referenceId, Pageable pageableObj){

        return aimsInwardImportDetailRepository.findByInwardImport(referenceId, pageableObj);
    }
}
