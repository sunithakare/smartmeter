package com.envision.aims.service;

import com.envision.aims.entity.AimsWhWhInwardDetail;
import com.envision.aims.repository.AimsWhWhInwardDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AimsWhWhInwardDetailService {

    @Autowired
    AimsWhWhInwardDetailRepository aimsWhWhInwardDetailRepository;

    public Page<AimsWhWhInwardDetail> getAimsWhWhInwardDetail(String referenceId, Pageable pageableObj){

        return aimsWhWhInwardDetailRepository.findByWhWhInward(referenceId, pageableObj);
    }
}
