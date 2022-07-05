package com.envision.aims.service;

import com.envision.aims.entity.AimsWhWhInward;
import com.envision.aims.entity.AimsWhWhInwardDetail;
import com.envision.aims.entity.AimsWhWhOutward;
import com.envision.aims.model.AimsWhWhInwardDto;
import com.envision.aims.model.ImportFileDTO;
import com.envision.aims.repository.AimsWhWhInwardDetailRepository;
import com.envision.aims.repository.AimsWhWhInwardRepository;
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
public class AimsWhWhInwardService {

    @Autowired
    AimsWhWhInwardRepository aimsWhWhInwardRepository;

    @Autowired
    AimsWhWhInwardDetailRepository aimsWhWhInwardDetailRepository;

    @Autowired
    AimsCommonService commonService;

    public AimsWhWhInwardDto saveAimsWhWhInward(AimsWhWhInwardDto aimsWhWhInwardDto){
        AimsWhWhInward aimsWhWhInward = new AimsWhWhInward();
        BeanUtils.copyProperties(aimsWhWhInwardDto, aimsWhWhInward);
        aimsWhWhInward = aimsWhWhInwardRepository.save(aimsWhWhInward);
        BeanUtils.copyProperties(aimsWhWhInward, aimsWhWhInwardDto);
        return aimsWhWhInwardDto;
    }


    public AimsWhWhInwardDto getAimsWhWhInward(String id) throws DataNotFoundException {
        AimsWhWhInwardDto aimsWhWhInwardDto = new AimsWhWhInwardDto();
        Optional<AimsWhWhInward> aimsWhWhInwardOptional = aimsWhWhInwardRepository.findById(id);
        if(!aimsWhWhInwardOptional.isPresent()){
            throw new DataNotFoundException("Data not found for " + id);
        }
        BeanUtils.copyProperties(aimsWhWhInwardOptional.get() , aimsWhWhInwardDto);
        return aimsWhWhInwardDto;
    }

    public AimsWhWhInwardDto addAimsWhWhInward(String data, MultipartFile dataFile) throws DataNotFoundException {
        AimsWhWhInward aimsWhWhInward = convertDataToObject(data);
        aimsWhWhInward = aimsWhWhInwardRepository.save(aimsWhWhInward);
        AimsWhWhInwardDto aimsWhWhInwardDto = new AimsWhWhInwardDto();
        BeanUtils.copyProperties(aimsWhWhInward, aimsWhWhInwardDto);
        List<ImportFileDTO> importFileDTOList = commonService.readDataFromExcelSheet(dataFile);
        List<AimsWhWhInwardDetail> aimsWhWhInwardDetails = new ArrayList<>();
        for (ImportFileDTO importFileDTO : importFileDTOList) {
            AimsWhWhInwardDetail aimsWhWhInwardDetail = new AimsWhWhInwardDetail();
            BeanUtils.copyProperties(importFileDTO, aimsWhWhInwardDetail);
            aimsWhWhInwardDetail.setWhWhInward(aimsWhWhInward.getId());
            aimsWhWhInwardDetails.add(aimsWhWhInwardDetail);
        }
        aimsWhWhInwardDetails = aimsWhWhInwardDetailRepository.saveAll(aimsWhWhInwardDetails);
        importFileDTOList = new ArrayList<>();
        for (AimsWhWhInwardDetail aimsWhWhInwardDetail : aimsWhWhInwardDetails) {
            ImportFileDTO importFileDTO = new ImportFileDTO();
            BeanUtils.copyProperties(aimsWhWhInwardDetail, importFileDTO);
            importFileDTOList.add(importFileDTO);
        }
        aimsWhWhInwardDto.setItemDetails(importFileDTOList);
        return aimsWhWhInwardDto;
    }

    public Page<AimsWhWhInward> getAimsWhWhInwardByDiscom(String discom, Pageable pageableObj) throws DataNotFoundException {
        Page<AimsWhWhInward> aimsWhWhOutwards = aimsWhWhInwardRepository.findByDiscomTo(discom, pageableObj);
        return aimsWhWhOutwards;
    }

    private AimsWhWhInward convertDataToObject(String data) throws DataNotFoundException {
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            return objectMapper.readValue(data, AimsWhWhInward.class);
        }catch (Exception e){
            throw new DataNotFoundException(e.getMessage());
        }
    }
}
