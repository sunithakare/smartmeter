package com.envision.aims.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.envision.aims.entity.AimsInwardImport;
import com.envision.aims.entity.AimsInwardImportDetail;
import com.envision.aims.model.AimsInwardFromSupplierListVO;
import com.envision.aims.model.AimsInwardFromSupplierVO;
import com.envision.aims.model.AimsInwardImportDto;
import com.envision.aims.model.AimsItemModelNameDto;
import com.envision.aims.model.AimsMasterSupplierNameVO;
import com.envision.aims.model.AimsMasterWarehouseVO;
import com.envision.aims.model.AimsMastersItemGroupVO;
import com.envision.aims.model.AimsSupplierCategoryLookUpDto;
import com.envision.aims.model.ImportFileDTO;
import com.envision.aims.repository.AimsInwardImportDetailRepository;
import com.envision.aims.repository.AimsInwardImportRepository;
import com.envision.aims.repository.AimsMasterItemModelNameRepository;
import com.envision.aims.repository.AimsMastersSupplierRepository;
import com.envision.aims.repository.AimsWareHouseRepository;
import com.envision.common.exception.DataNotFoundException;
import com.envision.common.service.FileUploadService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class AimsInwardImportService {

    @Autowired
    AimsInwardImportRepository aimsInwardImportRepository;

    @Autowired
    AimsInwardImportDetailRepository aimsInwardImportDetailRepository;
    
    @Autowired
    AimsMasterItemModelNameRepository aimsMasterItemModelNameRepository;
    
    @Autowired
    AimsWareHouseRepository aimsWareHouseRepository;
    
    @Autowired
    AimsMastersSupplierRepository aimsMastersSupplierRepository;
    
    @Autowired
    AimsCommonService commonService;

    @Autowired
    FileUploadService fileUploadService;
    public AimsInwardImportDto saveAimsInwardImport(AimsInwardImportDto aimsInwardImportDto){
        AimsInwardImport aimsInwardImport = new AimsInwardImport();
        BeanUtils.copyProperties(aimsInwardImportDto, aimsInwardImport);
        aimsInwardImport = aimsInwardImportRepository.save(aimsInwardImport);
        BeanUtils.copyProperties(aimsInwardImport, aimsInwardImportDto);
        return aimsInwardImportDto;
    }


    public AimsInwardImportDto getAimsInwardImport(String id) throws DataNotFoundException {
        AimsInwardImportDto aimsInwardImportDto = new AimsInwardImportDto();
        Optional<AimsInwardImport> aimsInwardImportOptional = aimsInwardImportRepository.findById(id);
        if(!aimsInwardImportOptional.isPresent()){
            throw new DataNotFoundException("Data not found for " + id);
        }
        BeanUtils.copyProperties(aimsInwardImportOptional.get() , aimsInwardImportDto);
        return aimsInwardImportDto;
    }

    public AimsInwardImportDto addAimsInwardImport(String data, MultipartFile dataFile) throws DataNotFoundException {
        AimsInwardImport aimsInwardImport = convertDataToObject(data);
        aimsInwardImport = aimsInwardImportRepository.save(aimsInwardImport);
        AimsInwardImportDto aimsInwardImportDto = new AimsInwardImportDto();
        BeanUtils.copyProperties(aimsInwardImport, aimsInwardImportDto);
        List<ImportFileDTO> importFileDTOList = commonService.readDataFromExcelSheet(dataFile);
        List<AimsInwardImportDetail> aimsInwardImportDetails = new ArrayList<>();
        for (ImportFileDTO importFileDTO : importFileDTOList) {
            AimsInwardImportDetail aimsWhWhInwardDetail = new AimsInwardImportDetail();
            BeanUtils.copyProperties(importFileDTO, aimsWhWhInwardDetail);
            aimsWhWhInwardDetail.setInwardImport(aimsInwardImport.getId());
            aimsInwardImportDetails.add(aimsWhWhInwardDetail);
        }
        aimsInwardImportDetails = aimsInwardImportDetailRepository.saveAll(aimsInwardImportDetails);
        importFileDTOList = new ArrayList<>();
        for (AimsInwardImportDetail aimsInwardImportDetail : aimsInwardImportDetails) {
            ImportFileDTO importFileDTO = new ImportFileDTO();
            BeanUtils.copyProperties(aimsInwardImportDetail, importFileDTO);
            importFileDTOList.add(importFileDTO);
        }
        aimsInwardImportDto.setItemDetails(importFileDTOList);
        return aimsInwardImportDto;
    }

    private AimsInwardImport convertDataToObject(String data) throws DataNotFoundException {
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            return objectMapper.readValue(data, AimsInwardImport.class);
        }catch (Exception e){
            throw new DataNotFoundException(e.getMessage());
        }
    }

    public Page<AimsInwardImport> getAimsInwardImportByDiscom(String discom, Pageable pageableObj) {
        return aimsInwardImportRepository.findByDiscom(discom, pageableObj);
    }
    
    public List<AimsMastersItemGroupVO> getItemGroup() {
		return aimsMasterItemModelNameRepository.findItemGroupBy();
	}
    
    public List<AimsItemModelNameDto> getItemName(String itemGroup){
		return aimsMasterItemModelNameRepository.findItemModelNameByItemGroup(itemGroup);
	}
    
    public AimsSupplierCategoryLookUpDto getLookupData(String itemGroup,String itemModelName) {
    	return aimsMasterItemModelNameRepository.findByItemGroupAndItemModelName(itemGroup, itemModelName);
    }
    
    public List<AimsMasterWarehouseVO> getWarehouseName() {
    	return aimsWareHouseRepository.findWarehouseNameBy();
    }
    
    public List<AimsMasterSupplierNameVO> getSupplier(){
    	return aimsMastersSupplierRepository.findSupplierBy();
    }
    
   public Page<AimsInwardFromSupplierListVO> findInwardSupplierList(Pageable pageable){
    	return aimsInwardImportRepository.findAllBy(pageable);
    }
    
    @Transactional
    public AimsInwardFromSupplierVO saveInwardFromSupplier(AimsInwardFromSupplierVO aimsInwardFromSupplierVO, MultipartFile dataFile, MultipartFile dataDocument) throws IOException, DataNotFoundException {
    	AimsInwardImport aimsInwardImport = new AimsInwardImport();
    	aimsInwardImport.setDiscom(aimsInwardFromSupplierVO.getDiscom());
    	aimsInwardImport.setWarehouseName(aimsInwardFromSupplierVO.getWarehouseName());
    	aimsInwardImport.setSupplier(aimsInwardFromSupplierVO.getSupplier());
    	aimsInwardImport.setInvoiceNo(aimsInwardFromSupplierVO.getInvoiceNo());
    	aimsInwardImport.setInvoiceDate(aimsInwardFromSupplierVO.getInvoiceDate());
    	aimsInwardImport.setGinNo(aimsInwardFromSupplierVO.getGinNo());
    	aimsInwardImport.setGinDate(aimsInwardFromSupplierVO.getGinDate());
    	aimsInwardImport.setItemGroup(aimsInwardFromSupplierVO.getItemGroup());
    	aimsInwardImport.setItemModelName(aimsInwardFromSupplierVO.getItemModelName());
    	aimsInwardImport.setItemSupplier(aimsInwardFromSupplierVO.getItemSupplier());
    	aimsInwardImport.setItemCategory(aimsInwardFromSupplierVO.getItemCategory());
    	aimsInwardImport.setQuantity(Integer.parseInt(aimsInwardFromSupplierVO.getQuantity()));
    	aimsInwardImport.setItemStatus(aimsInwardFromSupplierVO.getItemStatus());
    	aimsInwardImport.setTransporter(aimsInwardFromSupplierVO.getTransporter());
    	aimsInwardImport.setLrNo(aimsInwardFromSupplierVO.getLrNo());
    	aimsInwardImport.setLrDate(aimsInwardFromSupplierVO.getLrDate());
    	aimsInwardImport.setStockType(aimsInwardFromSupplierVO.getStockType());
    	aimsInwardImport.setPdiDate(aimsInwardFromSupplierVO.getPdiDate());
    	aimsInwardImport.setVehicleNo(aimsInwardFromSupplierVO.getVehicleNo());
    	aimsInwardImport.setDriverName(aimsInwardFromSupplierVO.getDriverName());
    	aimsInwardImport.setContactNo(aimsInwardFromSupplierVO.getContactNo());
    	aimsInwardImport.setRemark(aimsInwardFromSupplierVO.getRemark());
    	
    	aimsInwardImport.setDocumentId(fileUploadService.saveFile(dataDocument.getBytes(), dataDocument.getName()).getFileId());
    	aimsInwardImport.setDataFileId(fileUploadService.saveFile(dataFile.getBytes(), dataFile.getName()).getFileId());
    	
    	 List<ImportFileDTO> importFileDTOList = commonService.readDataFromExcelSheet(dataFile);
//    	 
//    	 if (!aimsInwardFromSupplierVO.getImportFileDTOList().isEmpty()) {
//    		 importFileDTOList.addAll(aimsInwardFromSupplierVO.getImportFileDTOList());
//		}
//    	 
    	 aimsInwardImport=aimsInwardImportRepository.save(aimsInwardImport);
    	 HashSet<AimsInwardImportDetail> detailRecords=new HashSet<AimsInwardImportDetail>();
    	for (ImportFileDTO importFileDTO : importFileDTOList) {
    		AimsInwardImportDetail aimsInwardImportDetail=new AimsInwardImportDetail();
             BeanUtils.copyProperties(aimsInwardImportDetail, importFileDTO);
             aimsInwardImportDetail.setAimsInwardFromSupplier(aimsInwardImport);
             detailRecords.add(aimsInwardImportDetail);
		}
    	aimsInwardImportDetailRepository.saveAll(detailRecords);
    	return aimsInwardFromSupplierVO;
    }



}
