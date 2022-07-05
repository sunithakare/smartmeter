package com.envision.aims.service;

import com.envision.aims.entity.AimsOutwardImport;
import com.envision.aims.entity.AimsOutwardImportDetail;
import com.envision.aims.model.AimsOutwardImportDto;
import com.envision.aims.model.ImportFileDTO;
import com.envision.aims.repository.AimsOutwardImportDetailRepository;
import com.envision.aims.repository.AimsOutwardImportRepository;
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
public class AimsOutwardImportService {

    @Autowired
    AimsOutwardImportRepository aimsOutwardImportRepository;

    @Autowired
    AimsOutwardImportDetailRepository aimsOutwardImportDetailRepository;

    @Autowired
    AimsCommonService commonService;

    public AimsOutwardImportDto saveAimsOutwardImport(AimsOutwardImportDto aimsOutwardImportDto){
        AimsOutwardImport aimsOutwardImport = new AimsOutwardImport();
        BeanUtils.copyProperties(aimsOutwardImportDto, aimsOutwardImport);
        aimsOutwardImport = aimsOutwardImportRepository.save(aimsOutwardImport);
        BeanUtils.copyProperties(aimsOutwardImport, aimsOutwardImportDto);
        return aimsOutwardImportDto;
    }


    public AimsOutwardImportDto getAimsOutwardImport(String id) throws DataNotFoundException {
        AimsOutwardImportDto aimsOutwardImportDto = new AimsOutwardImportDto();
        Optional<AimsOutwardImport> aimsOutwardImportOptional = aimsOutwardImportRepository.findById(id);
        if(!aimsOutwardImportOptional.isPresent()){
            throw new DataNotFoundException("Data not found for " + id);
        }
        BeanUtils.copyProperties(aimsOutwardImportOptional.get() , aimsOutwardImportDto);
        return aimsOutwardImportDto;
    }

    public AimsOutwardImportDto addAimsOutwardImport(String data, MultipartFile dataFile) throws DataNotFoundException {
        AimsOutwardImport aimsOutwardImport = convertDataToObject(data);
        aimsOutwardImport = aimsOutwardImportRepository.save(aimsOutwardImport);
        AimsOutwardImportDto aimsOutwardImportDto = new AimsOutwardImportDto();
        BeanUtils.copyProperties(aimsOutwardImport, aimsOutwardImportDto);
        List<ImportFileDTO> importFileDTOList = commonService.readDataFromExcelSheet(dataFile);
        List<AimsOutwardImportDetail> aimsOutwardImportDetails = new ArrayList<>();
        for (ImportFileDTO importFileDTO : importFileDTOList) {
            AimsOutwardImportDetail aimsOutwardImportDetail = new AimsOutwardImportDetail();
            BeanUtils.copyProperties(importFileDTO, aimsOutwardImportDetail);
            aimsOutwardImportDetail.setOutwardImport(aimsOutwardImport.getId());
            aimsOutwardImportDetails.add(aimsOutwardImportDetail);
        }
        aimsOutwardImportDetails = aimsOutwardImportDetailRepository.saveAll(aimsOutwardImportDetails);
        importFileDTOList = new ArrayList<>();
        for (AimsOutwardImportDetail aimsOutwardImportDetail : aimsOutwardImportDetails) {
            ImportFileDTO importFileDTO = new ImportFileDTO();
            BeanUtils.copyProperties(aimsOutwardImportDetail, importFileDTO);
            importFileDTOList.add(importFileDTO);
        }
        aimsOutwardImportDto.setItemDetails(importFileDTOList);
        return aimsOutwardImportDto;
    }

    public Page<AimsOutwardImport> getAimsOutwardImportByDiscom(String discom, Pageable pageableObj){
        return aimsOutwardImportRepository.findByDiscom(discom, pageableObj);
    }

    private AimsOutwardImport convertDataToObject(String data) throws DataNotFoundException {
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            return objectMapper.readValue(data, AimsOutwardImport.class);
        }catch (Exception e){
            throw new DataNotFoundException(e.getMessage());
        }
    }
}
