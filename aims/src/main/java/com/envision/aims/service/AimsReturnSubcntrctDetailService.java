package com.envision.aims.service;

import com.envision.aims.entity.AimsReturnSubcntrctDetail;
import com.envision.aims.repository.AimsReturnSubcntrctDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AimsReturnSubcntrctDetailService {

    @Autowired
    AimsReturnSubcntrctDetailRepository aimsReturnSubcntrctDetailRepository;

    public Page<AimsReturnSubcntrctDetail> getAimsReturnSubcntrctDetail(String referenceId, Pageable pageableObj){

        return aimsReturnSubcntrctDetailRepository.findByReturnSubcontractor(referenceId, pageableObj);
    }
}
