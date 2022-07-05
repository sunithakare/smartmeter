package com.envision.aims.service;

import com.envision.aims.entity.AimsOldMeterInwardDetail;
import com.envision.aims.repository.AimsOldMeterInwardDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AimsOldMeterInwardDetailService {

    @Autowired
    AimsOldMeterInwardDetailRepository aimsOldMeterInwardDetailRepository;

    public Page<AimsOldMeterInwardDetail> getAimsOldMeterInwardDetail(String referenceId, Pageable pageableObj){

        return aimsOldMeterInwardDetailRepository.findByOldMeterInward(referenceId, pageableObj);
    }
}
