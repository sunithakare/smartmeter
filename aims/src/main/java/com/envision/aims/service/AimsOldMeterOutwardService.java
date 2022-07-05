package com.envision.aims.service;

import com.envision.aims.entity.AimsOldMeterOutward;
import com.envision.aims.entity.AimsOldMeterOutwardDetail;
import com.envision.aims.model.AimsOldMeterOutwardDto;
import com.envision.aims.model.ImportFileDTO;
import com.envision.aims.repository.AimsOldMeterOutwardDetailRepository;
import com.envision.aims.repository.AimsOldMeterOutwardRepository;
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
public class AimsOldMeterOutwardService {
    @Autowired
    AimsOldMeterOutwardRepository aimsOldMeterOutwardRepository;

    @Autowired
    AimsOldMeterOutwardDetailRepository aimsOldMeterOutwardDetailRepository;

    @Autowired
    AimsCommonService commonService;

    public AimsOldMeterOutwardDto saveAimsOldMeterOutward(AimsOldMeterOutwardDto aimsOldMeterInwardDto){
        AimsOldMeterOutward aimsOldMeterInward = new AimsOldMeterOutward();
        BeanUtils.copyProperties(aimsOldMeterInwardDto, aimsOldMeterInward);
        aimsOldMeterInward = aimsOldMeterOutwardRepository.save(aimsOldMeterInward);
        BeanUtils.copyProperties(aimsOldMeterInward, aimsOldMeterInwardDto);
        return aimsOldMeterInwardDto;
    }


    public AimsOldMeterOutwardDto getAimsOldMeterOutward(String id) throws DataNotFoundException {
        AimsOldMeterOutwardDto aimsOldMeterInwardDto = new AimsOldMeterOutwardDto();
        Optional<AimsOldMeterOutward> aimsOldMeterInwardOptional = aimsOldMeterOutwardRepository.findById(id);
        if(!aimsOldMeterInwardOptional.isPresent()){
            throw new DataNotFoundException("Data not found for " + id);
        }
        BeanUtils.copyProperties(aimsOldMeterInwardOptional.get() , aimsOldMeterInwardDto);
        return aimsOldMeterInwardDto;
    }

    public AimsOldMeterOutwardDto addAimsOldMeterOutward(String data, MultipartFile dataFile) throws DataNotFoundException {
        AimsOldMeterOutward aimsOldMeterInward = convertDataToObject(data);
        aimsOldMeterInward = aimsOldMeterOutwardRepository.save(aimsOldMeterInward);
        AimsOldMeterOutwardDto aimsOldMeterInwardDto = new AimsOldMeterOutwardDto();
        BeanUtils.copyProperties(aimsOldMeterInward, aimsOldMeterInwardDto);
        List<ImportFileDTO> importFileDTOList = commonService.readDataFromExcelSheet(dataFile);
        List<AimsOldMeterOutwardDetail> aimsOldMeterInwardDetails = new ArrayList<>();
        for (ImportFileDTO importFileDTO : importFileDTOList) {
            AimsOldMeterOutwardDetail aimsOldMeterInwardDetail = new AimsOldMeterOutwardDetail();
            BeanUtils.copyProperties(importFileDTO, aimsOldMeterInwardDetail);
            aimsOldMeterInwardDetail.setOldMeterOutward(aimsOldMeterInward.getId());
            aimsOldMeterInwardDetails.add(aimsOldMeterInwardDetail);
        }
        aimsOldMeterInwardDetails = aimsOldMeterOutwardDetailRepository.saveAll(aimsOldMeterInwardDetails);
        importFileDTOList = new ArrayList<>();
        for (AimsOldMeterOutwardDetail aimsOldMeterInwardDetail : aimsOldMeterInwardDetails) {
            ImportFileDTO importFileDTO = new ImportFileDTO();
            BeanUtils.copyProperties(aimsOldMeterInwardDetail, importFileDTO);
            importFileDTOList.add(importFileDTO);
        }
        aimsOldMeterInwardDto.setItemDetails(importFileDTOList);
        return aimsOldMeterInwardDto;
    }

    public Page<AimsOldMeterOutward> getAimsOldMeterOutwardByDiscom(String discom, Pageable pageableObj){
        return aimsOldMeterOutwardRepository.findByDiscom(discom, pageableObj);
    }

    private AimsOldMeterOutward convertDataToObject(String data) throws DataNotFoundException {
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            return objectMapper.readValue(data,AimsOldMeterOutward.class);
        }catch (Exception e){
            throw new DataNotFoundException(e.getMessage());
        }
    }
}
