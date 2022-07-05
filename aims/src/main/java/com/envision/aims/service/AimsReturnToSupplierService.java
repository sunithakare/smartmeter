package com.envision.aims.service;

import com.envision.aims.entity.AimsReturnToSupplier;
import com.envision.aims.entity.AimsReturnToSupplierDetail;
import com.envision.aims.model.AimsReturnToSupplierDto;
import com.envision.aims.model.AimsReturnToSupplierListVO;
import com.envision.aims.model.AimsReturnToSupplierVO;
import com.envision.aims.model.ImportFileDTO;
import com.envision.aims.repository.AimsReturnToSupplierDetailRepository;
import com.envision.aims.repository.AimsReturnToSupplierRepository;
import com.envision.common.exception.DataNotFoundException;
import com.envision.common.service.FileUploadService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

@Service
public class AimsReturnToSupplierService {

    @Autowired
    AimsReturnToSupplierRepository aimsReturnToSupplierRepository;

    @Autowired
    AimsReturnToSupplierDetailRepository aimsReturnToSupplierDetailRepository;

    @Autowired
    AimsCommonService commonService;
    
    @Autowired
    FileUploadService fileUploadService;

    public AimsReturnToSupplierDto saveAimsReturnToSupplier(AimsReturnToSupplierDto aimsReturnToSupplierDto){
        AimsReturnToSupplier aimsReturnToSupplier = new AimsReturnToSupplier();
        BeanUtils.copyProperties(aimsReturnToSupplierDto, aimsReturnToSupplier);
        aimsReturnToSupplier = aimsReturnToSupplierRepository.save(aimsReturnToSupplier);
        BeanUtils.copyProperties(aimsReturnToSupplier, aimsReturnToSupplierDto);
        return aimsReturnToSupplierDto;
    }


    public AimsReturnToSupplierDto getAimsReturnToSupplier(String id) throws DataNotFoundException {
        AimsReturnToSupplierDto aimsReturnToSupplierDto = new AimsReturnToSupplierDto();
        Optional<AimsReturnToSupplier> aimsReturnToSupplierOptional = aimsReturnToSupplierRepository.findById(id);
        if(!aimsReturnToSupplierOptional.isPresent()){
            throw new DataNotFoundException("Data not found for " + id);
        }
        BeanUtils.copyProperties(aimsReturnToSupplierOptional.get() , aimsReturnToSupplierDto);
        return aimsReturnToSupplierDto;
    }

    @Transactional
    public AimsReturnToSupplierDto addAimsReturnToSupplier(AimsReturnToSupplier aimsReturnToSupplier, MultipartFile dataFile) throws DataNotFoundException, IOException {
//        AimsReturnToSupplier aimsReturnToSupplier = convertDataToObject(data);
        //aimsReturnToSupplier.setDataFileId(fileUploadService.saveFile(dataFile.getBytes(), dataFile.getName()).getFileId());
        aimsReturnToSupplier = aimsReturnToSupplierRepository.save(aimsReturnToSupplier);
        AimsReturnToSupplierDto aimsReturnToSupplierDto = new AimsReturnToSupplierDto();
        BeanUtils.copyProperties(aimsReturnToSupplier, aimsReturnToSupplierDto);
        List<ImportFileDTO> importFileDTOList = commonService.readDataFromExcelSheet(dataFile);
        List<AimsReturnToSupplierDetail> aimsReturnToSupplierDetails = new ArrayList<>();
        for (ImportFileDTO importFileDTO : importFileDTOList) {
            AimsReturnToSupplierDetail aimsReturnToSupplierDetail = new AimsReturnToSupplierDetail();
            BeanUtils.copyProperties(importFileDTO, aimsReturnToSupplierDetail);
            aimsReturnToSupplierDetail.setReturnToSupplier(aimsReturnToSupplier.getId());
            aimsReturnToSupplierDetails.add(aimsReturnToSupplierDetail);
        }
        aimsReturnToSupplierDetails = aimsReturnToSupplierDetailRepository.saveAll(aimsReturnToSupplierDetails);
        importFileDTOList = new ArrayList<>();
        for (AimsReturnToSupplierDetail aimsReturnToSupplierDetail : aimsReturnToSupplierDetails) {
            ImportFileDTO importFileDTO = new ImportFileDTO();
            BeanUtils.copyProperties(aimsReturnToSupplierDetail, importFileDTO);
            importFileDTOList.add(importFileDTO);
        }
        aimsReturnToSupplierDto.setItemDetails(importFileDTOList);
        return aimsReturnToSupplierDto;
    }

    public Page<AimsReturnToSupplierListVO> getAllAimsReturnToSupplier(Pageable pageableObj){
        return aimsReturnToSupplierRepository.findAllBy(pageableObj);
    }
    
    @Transactional
    public AimsReturnToSupplierVO saveReturnToSupplier(AimsReturnToSupplierVO aimsReturnToSupplierVO, MultipartFile dataFile) throws DataNotFoundException, IOException {
    	AimsReturnToSupplier aimsReturnToSupplier = new AimsReturnToSupplier();
    	aimsReturnToSupplier.setDiscom(aimsReturnToSupplierVO.getDiscom());
    	aimsReturnToSupplier.setWarehouseName(aimsReturnToSupplierVO.getWarehouseName());
    	aimsReturnToSupplier.setSupplier(aimsReturnToSupplierVO.getSupplier());
    	aimsReturnToSupplier.setDc(aimsReturnToSupplierVO.getDc());
    	aimsReturnToSupplier.setDcDate(aimsReturnToSupplierVO.getDcDate());
    	aimsReturnToSupplier.setDispatchDate(aimsReturnToSupplierVO.getDispatchDate());
    	aimsReturnToSupplier.setItemGroup(aimsReturnToSupplierVO.getItemGroup());
    	aimsReturnToSupplier.setItemModelName(aimsReturnToSupplierVO.getItemModelName());
    	aimsReturnToSupplier.setItemSupplier(aimsReturnToSupplierVO.getItemSupplier());
    	aimsReturnToSupplier.setItemCategory(aimsReturnToSupplierVO.getItemCategory());
    	aimsReturnToSupplier.setQuantity(aimsReturnToSupplierVO.getQuantity());
    	aimsReturnToSupplier.setItemStatus(aimsReturnToSupplierVO.getItemStatus());
    	aimsReturnToSupplier.setTransporter(aimsReturnToSupplierVO.getTransporter());
    	aimsReturnToSupplier.setLrNo(aimsReturnToSupplierVO.getLrNo());
    	aimsReturnToSupplier.setVehicleNo(aimsReturnToSupplierVO.getVehicleNo());
    	aimsReturnToSupplier.setDriverName(aimsReturnToSupplierVO.getDriverName());
    	aimsReturnToSupplier.setContactNo(aimsReturnToSupplierVO.getContactNo());
    	aimsReturnToSupplier.setRemark(aimsReturnToSupplierVO.getRemark());
    	aimsReturnToSupplier.setLrDate(aimsReturnToSupplierVO.getLrDate());
    	
    	aimsReturnToSupplier.setDataFileId(fileUploadService.saveFile(dataFile.getBytes(), dataFile.getName()).getFileId());
    	
    	List<ImportFileDTO> importFileDTOList = commonService.readDataFromExcelSheet(dataFile);
    	
    	aimsReturnToSupplier = aimsReturnToSupplierRepository.save(aimsReturnToSupplier);
    	HashSet<AimsReturnToSupplierDetail> detailRecords = new HashSet<AimsReturnToSupplierDetail>();
    	 for (ImportFileDTO importFileDTO : importFileDTOList) {
    		 AimsReturnToSupplierDetail aimsReturnToSupplierDetail = new AimsReturnToSupplierDetail();
             BeanUtils.copyProperties(aimsReturnToSupplierDetail, importFileDTO);
             aimsReturnToSupplierDetail.setAimsReturnToSupplier(aimsReturnToSupplier);
             detailRecords.add(aimsReturnToSupplierDetail);
         }
    	 
    	 aimsReturnToSupplierDetailRepository.saveAll(detailRecords);
    	 return aimsReturnToSupplierVO;
    	
    }

//    private AimsReturnToSupplier convertDataToObject(String data) throws DataNotFoundException {
//        ObjectMapper objectMapper = new ObjectMapper();
//        try{
//            return objectMapper.readValue(data, AimsReturnToSupplier.class);
//        }catch (Exception e){
//            throw new DataNotFoundException(e.getMessage());
//        }
//    }
}
