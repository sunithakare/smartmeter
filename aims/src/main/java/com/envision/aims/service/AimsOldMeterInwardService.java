package com.envision.aims.service;

import com.envision.aims.entity.AimsOldMeterInward;
import com.envision.aims.entity.AimsOldMeterInwardDetail;
import com.envision.aims.model.AimsOldMeterInwardDto;
import com.envision.aims.model.ImportFileDTO;
import com.envision.aims.repository.AimsOldMeterInwardRepository;
import com.envision.aims.repository.AimsOldMeterInwardDetailRepository;
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
public class AimsOldMeterInwardService {

    @Autowired
    AimsOldMeterInwardRepository aimsOldMeterInwardRepository;

    @Autowired
    AimsOldMeterInwardDetailRepository aimsOldMeterInwardDetailRepository;

    @Autowired
    AimsCommonService commonService;

    public AimsOldMeterInwardDto saveAimsOldMeterInward(AimsOldMeterInwardDto aimsOldMeterInwardDto){
       AimsOldMeterInward aimsOldMeterInward = new AimsOldMeterInward();
        BeanUtils.copyProperties(aimsOldMeterInwardDto, aimsOldMeterInward);
        aimsOldMeterInward = aimsOldMeterInwardRepository.save(aimsOldMeterInward);
        BeanUtils.copyProperties(aimsOldMeterInward, aimsOldMeterInwardDto);
        return aimsOldMeterInwardDto;
    }


    public AimsOldMeterInwardDto getAimsOldMeterInward(String id) throws DataNotFoundException {
       AimsOldMeterInwardDto aimsOldMeterInwardDto = new AimsOldMeterInwardDto();
        Optional<AimsOldMeterInward> aimsOldMeterInwardOptional = aimsOldMeterInwardRepository.findById(id);
        if(!aimsOldMeterInwardOptional.isPresent()){
            throw new DataNotFoundException("Data not found for " + id);
        }
        BeanUtils.copyProperties(aimsOldMeterInwardOptional.get() , aimsOldMeterInwardDto);
        return aimsOldMeterInwardDto;
    }

    public AimsOldMeterInwardDto addAimsOldMeterInward(String data, MultipartFile dataFile) throws DataNotFoundException {
       AimsOldMeterInward aimsOldMeterInward = convertDataToObject(data);
        aimsOldMeterInward = aimsOldMeterInwardRepository.save(aimsOldMeterInward);
       AimsOldMeterInwardDto aimsOldMeterInwardDto = new AimsOldMeterInwardDto();
        BeanUtils.copyProperties(aimsOldMeterInward, aimsOldMeterInwardDto);
        List<ImportFileDTO> importFileDTOList = commonService.readDataFromExcelSheet(dataFile);
        List<AimsOldMeterInwardDetail> aimsOldMeterInwardDetails = new ArrayList<>();
        for (ImportFileDTO importFileDTO : importFileDTOList) {
           AimsOldMeterInwardDetail aimsOldMeterInwardDetail = new AimsOldMeterInwardDetail();
            BeanUtils.copyProperties(importFileDTO, aimsOldMeterInwardDetail);
            aimsOldMeterInwardDetail.setOldMeterInward(aimsOldMeterInward.getId());
            aimsOldMeterInwardDetails.add(aimsOldMeterInwardDetail);
        }
        aimsOldMeterInwardDetails = aimsOldMeterInwardDetailRepository.saveAll(aimsOldMeterInwardDetails);
        importFileDTOList = new ArrayList<>();
        for (AimsOldMeterInwardDetail aimsOldMeterInwardDetail : aimsOldMeterInwardDetails) {
            ImportFileDTO importFileDTO = new ImportFileDTO();
            BeanUtils.copyProperties(aimsOldMeterInwardDetail, importFileDTO);
            importFileDTOList.add(importFileDTO);
        }
        aimsOldMeterInwardDto.setItemDetails(importFileDTOList);
        return aimsOldMeterInwardDto;
    }

    public Page<AimsOldMeterInward> getAimsOldMeterInwardByDiscom(String discom, Pageable pageableObj){
        return aimsOldMeterInwardRepository.findByDiscom(discom, pageableObj);
    }

    private AimsOldMeterInward convertDataToObject(String data) throws DataNotFoundException {
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            return objectMapper.readValue(data,AimsOldMeterInward.class);
        }catch (Exception e){
            throw new DataNotFoundException(e.getMessage());
        }
    }
}
