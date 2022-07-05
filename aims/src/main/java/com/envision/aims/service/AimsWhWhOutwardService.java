package com.envision.aims.service;

import com.envision.aims.entity.AimsWhWhOutward;
import com.envision.aims.entity.AimsWhWhOutwardDetail;
import com.envision.aims.model.AimsWhWhOutwardDto;
import com.envision.aims.model.ImportFileDTO;
import com.envision.aims.repository.AimsWhWhOutwardDetailRepository;
import com.envision.aims.repository.AimsWhWhOutwardRepository;
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
public class AimsWhWhOutwardService {

    @Autowired
    AimsWhWhOutwardRepository aimsWhWhOutwardRepository;

    @Autowired
    AimsWhWhOutwardDetailRepository aimsWhWhOutwardDetailRepository;

    @Autowired
    AimsCommonService commonService;

    public AimsWhWhOutwardDto saveAimsWhWhOutward(AimsWhWhOutwardDto aimsWhWhOutwardDto){
        AimsWhWhOutward aimsWhWhOutward = new AimsWhWhOutward();
        BeanUtils.copyProperties(aimsWhWhOutwardDto, aimsWhWhOutward);
        aimsWhWhOutward = aimsWhWhOutwardRepository.save(aimsWhWhOutward);
        BeanUtils.copyProperties(aimsWhWhOutward, aimsWhWhOutwardDto);
        return aimsWhWhOutwardDto;
    }


    public Page<AimsWhWhOutward> getAimsWhWhOutwardByDiscom(String discom, Pageable pageableObj) throws DataNotFoundException {
        Page<AimsWhWhOutward> aimsWhWhOutwards = aimsWhWhOutwardRepository.findByDiscomFrom(discom, pageableObj);
        return aimsWhWhOutwards;
    }

    public AimsWhWhOutwardDto getAimsWhWhOutward(String id) throws DataNotFoundException {
        AimsWhWhOutwardDto aimsWhWhOutwardDto = new AimsWhWhOutwardDto();
        Optional<AimsWhWhOutward> aimsWhWhOutwardOptional = aimsWhWhOutwardRepository.findById(id);
        if(!aimsWhWhOutwardOptional.isPresent()){
            throw new DataNotFoundException("Data not found for " + id);
        }
        BeanUtils.copyProperties(aimsWhWhOutwardOptional.get() , aimsWhWhOutwardDto);
        return aimsWhWhOutwardDto;
    }

    public AimsWhWhOutwardDto addAimsWhWhOutward(String data, MultipartFile dataFile) throws DataNotFoundException {
        AimsWhWhOutward aimsWhWhOutward = convertDataToObject(data);
        aimsWhWhOutward = aimsWhWhOutwardRepository.save(aimsWhWhOutward);
        AimsWhWhOutwardDto aimsWhWhOutwardDto = new AimsWhWhOutwardDto();
        BeanUtils.copyProperties(aimsWhWhOutward, aimsWhWhOutwardDto);
        List<ImportFileDTO> importFileDTOList = commonService.readDataFromExcelSheet(dataFile);
        List<AimsWhWhOutwardDetail> aimsWhWhOutwardDetails = new ArrayList<>();
        for (ImportFileDTO importFileDTO : importFileDTOList) {
            AimsWhWhOutwardDetail aimsWhWhOutwardDetail = new AimsWhWhOutwardDetail();
            BeanUtils.copyProperties(importFileDTO, aimsWhWhOutwardDetail);
            aimsWhWhOutwardDetail.setWhWhOutward(aimsWhWhOutward.getId());
            aimsWhWhOutwardDetails.add(aimsWhWhOutwardDetail);
        }
        aimsWhWhOutwardDetails = aimsWhWhOutwardDetailRepository.saveAll(aimsWhWhOutwardDetails);
        importFileDTOList = new ArrayList<>();
        for (AimsWhWhOutwardDetail aimsWhWhOutwardDetail : aimsWhWhOutwardDetails) {
            ImportFileDTO importFileDTO = new ImportFileDTO();
            BeanUtils.copyProperties(aimsWhWhOutwardDetail, importFileDTO);
            importFileDTOList.add(importFileDTO);
        }
        aimsWhWhOutwardDto.setItemDetails(importFileDTOList);
        return aimsWhWhOutwardDto;
    }

    private AimsWhWhOutward convertDataToObject(String data) throws DataNotFoundException {
        ObjectMapper objectMapper = new ObjectMapper();
        try{
           return objectMapper.readValue(data, AimsWhWhOutward.class);
        }catch (Exception e){
            throw new DataNotFoundException(e.getMessage());
        }
    }
}
