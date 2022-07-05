package com.envision.aims.service;

import com.envision.aims.entity.AimsReturnFromSubcontractor;
import com.envision.aims.entity.AimsReturnSubcntrctDetail;
import com.envision.aims.model.AimsReturnFromSubcontractorDto;
import com.envision.aims.model.ImportFileDTO;
import com.envision.aims.repository.AimsReturnSubcntrctDetailRepository;
import com.envision.aims.repository.AimsReturnFromSubcontractorRepository;
import com.envision.common.exception.DataNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AimsReturnFromSubcontractorService {

    @Autowired
    AimsReturnFromSubcontractorRepository aimsReturnFromSubcontractorRepository;

    @Autowired
    AimsReturnSubcntrctDetailRepository aimsReturnSubcntrctDetailRepository;

    @Autowired
    AimsCommonService commonService;

    public AimsReturnFromSubcontractorDto saveAimsReturnFromSubcontractor(AimsReturnFromSubcontractorDto aimsReturnFromSubcontractorDto){
        AimsReturnFromSubcontractor aimsReturnFromSubcontractor = new AimsReturnFromSubcontractor();
        BeanUtils.copyProperties(aimsReturnFromSubcontractorDto, aimsReturnFromSubcontractor);
        aimsReturnFromSubcontractor = aimsReturnFromSubcontractorRepository.save(aimsReturnFromSubcontractor);
        BeanUtils.copyProperties(aimsReturnFromSubcontractor, aimsReturnFromSubcontractorDto);
        return aimsReturnFromSubcontractorDto;
    }


    public AimsReturnFromSubcontractorDto getAimsReturnFromSubcontractor(String id) throws DataNotFoundException {
        AimsReturnFromSubcontractorDto aimsReturnFromSubcontractorDto = new AimsReturnFromSubcontractorDto();
        Optional<AimsReturnFromSubcontractor> aimsReturnFromSubcontractorOptional = aimsReturnFromSubcontractorRepository.findById(id);
        if(!aimsReturnFromSubcontractorOptional.isPresent()){
            throw new DataNotFoundException("Data not found for " + id);
        }
        BeanUtils.copyProperties(aimsReturnFromSubcontractorOptional.get() , aimsReturnFromSubcontractorDto);
        return aimsReturnFromSubcontractorDto;
    }

    public AimsReturnFromSubcontractorDto addAimsReturnFromSubcontractor(String data, MultipartFile dataFile) throws DataNotFoundException {
        AimsReturnFromSubcontractor aimsReturnFromSubcontractor = convertDataToObject(data);
        aimsReturnFromSubcontractor = aimsReturnFromSubcontractorRepository.save(aimsReturnFromSubcontractor);
        AimsReturnFromSubcontractorDto aimsReturnFromSubcontractorDto = new AimsReturnFromSubcontractorDto();
        BeanUtils.copyProperties(aimsReturnFromSubcontractor, aimsReturnFromSubcontractorDto);
        List<ImportFileDTO> importFileDTOList = commonService.readDataFromExcelSheet(dataFile);
        List<AimsReturnSubcntrctDetail> aimsReturnSubcntrctDetails = new ArrayList<>();
        for (ImportFileDTO importFileDTO : importFileDTOList) {
            AimsReturnSubcntrctDetail aimsReturnSubcntrctDetail = new AimsReturnSubcntrctDetail();
            BeanUtils.copyProperties(importFileDTO, aimsReturnSubcntrctDetail);
            aimsReturnSubcntrctDetail.setReturnSubcontractor(aimsReturnFromSubcontractor.getId());
            aimsReturnSubcntrctDetails.add(aimsReturnSubcntrctDetail);
        }
        aimsReturnSubcntrctDetails = aimsReturnSubcntrctDetailRepository.saveAll(aimsReturnSubcntrctDetails);
        importFileDTOList = new ArrayList<>();
        for (AimsReturnSubcntrctDetail aimsReturnSubcntrctDetail : aimsReturnSubcntrctDetails) {
            ImportFileDTO importFileDTO = new ImportFileDTO();
            BeanUtils.copyProperties(aimsReturnSubcntrctDetail, importFileDTO);
            importFileDTOList.add(importFileDTO);
        }
        aimsReturnFromSubcontractorDto.setItemDetails(importFileDTOList);
        return aimsReturnFromSubcontractorDto;
    }

    public Page<AimsReturnFromSubcontractor> getAimsReturnFromSubcontractorByDiscom(String discom, Pageable pageableObj){
        return aimsReturnFromSubcontractorRepository.findByDiscom(discom, pageableObj);
    }

    private AimsReturnFromSubcontractor convertDataToObject(String data) throws DataNotFoundException {
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            return objectMapper.readValue(data, AimsReturnFromSubcontractor.class);
        }catch (Exception e){
            throw new DataNotFoundException(e.getMessage());
        }
    }
}
