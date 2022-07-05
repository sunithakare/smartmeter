package com.envision.aims.service;

import com.envision.aims.entity.AimsWhWhOutwardDetail;
import com.envision.aims.repository.AimsWhWhOutwardDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;



@Service
public class AimsWhWhOutwardDetailsService {

    @Autowired
    AimsWhWhOutwardDetailRepository aimsWhWhOutwardDetailRepository;

    public Page<AimsWhWhOutwardDetail> getAimsWhWhOutwardDetail(String referenceId, Pageable pageableObj){

        return aimsWhWhOutwardDetailRepository.findByWhWhOutward(referenceId, pageableObj);
    }
}
