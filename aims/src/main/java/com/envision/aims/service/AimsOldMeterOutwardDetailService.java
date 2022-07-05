package com.envision.aims.service;

import com.envision.aims.entity.AimsOldMeterOutwardDetail;
import com.envision.aims.entity.AimsWhWhInwardDetail;
import com.envision.aims.repository.AimsOldMeterOutwardDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AimsOldMeterOutwardDetailService {

    @Autowired
    AimsOldMeterOutwardDetailRepository aimsOldMeterOutwardDetailRepository;

    public Page<AimsOldMeterOutwardDetail> getAimsOldMeterOutwardDetail(String referenceId, Pageable pageableObj){

        return aimsOldMeterOutwardDetailRepository.findByOldMeterOutward(referenceId, pageableObj);
    }
}
