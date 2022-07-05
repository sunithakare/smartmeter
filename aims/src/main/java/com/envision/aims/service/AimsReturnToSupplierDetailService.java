package com.envision.aims.service;

import com.envision.aims.entity.AimsReturnToSupplierDetail;
import com.envision.aims.entity.AimsWhWhInwardDetail;
import com.envision.aims.repository.AimsReturnToSupplierDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AimsReturnToSupplierDetailService {

    @Autowired
    AimsReturnToSupplierDetailRepository aimsReturnToSupplierDetailRepository;

    public Page<AimsReturnToSupplierDetail> getAimsReturnToSupplierDetail(String referenceId, Pageable pageableObj){

        return aimsReturnToSupplierDetailRepository.findByReturnToSupplier(referenceId, pageableObj);
    }
}
