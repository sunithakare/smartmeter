package com.envision.aims.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.envision.aims.entity.AimsItemModelName;
import com.envision.aims.model.AimsMastersItemGroupVO;
import com.envision.aims.model.AimsMastersItemModelNameFetchDto;
import com.envision.aims.model.AimsMasterItemModelNameDto;
import com.envision.aims.repository.AimsMasterItemModelNameRepository;
import com.envision.aims.repository.AimsMastersItemGroupRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AimsMastersItemModelNameService {
	
	@Autowired
	private AimsMasterItemModelNameRepository aimsMasterItemModelNameRepository;
	@Autowired
	AimsMastersItemGroupRepository aimsMastersItemGroupRepository;
	
	
	public Page<AimsItemModelName> getItemModelName(Pageable pageObj) {
		
		return aimsMasterItemModelNameRepository.findAll(pageObj);
	}
	

	
	public List<AimsMastersItemGroupVO> getItemGroup(){
		return null;//aimsMastersItemGroupRepository.findItemGroupBy();
	}
	
	public AimsItemModelName saveMasterItem(AimsMasterItemModelNameDto aimsMasterItemsDto) {

		AimsItemModelName aimsItemModelName = new AimsItemModelName();
		BeanUtils.copyProperties(aimsMasterItemsDto, aimsItemModelName);

		aimsItemModelName = aimsMasterItemModelNameRepository.save(aimsItemModelName);
		
		
		return aimsItemModelName;
	}
		
		public AimsMasterItemModelNameDto updateMasterItemModelName(AimsMasterItemModelNameDto aimsMasterItemModelNameDto, String itemModelName) throws Exception {
	        Optional<AimsItemModelName> AimsItemModelNameEntity = aimsMasterItemModelNameRepository.findByItemModelName(itemModelName);
	        if(!AimsItemModelNameEntity.isPresent()) {
	        throw new Exception("Item Model Name is Not Found");
	        }
	        AimsItemModelName aimsItemModelName = AimsItemModelNameEntity.get();
	        
	        
    if(Objects.nonNull(aimsMasterItemModelNameDto.getItemModelName()) && aimsMasterItemModelNameDto.getItemModelName() !="") {
		aimsItemModelName.setItemModelCode(aimsMasterItemModelNameDto.getItemModelName());
	        }else {
	            throw new Exception("Item Model Name is Not Found");
	        }
    
if(Objects.nonNull(aimsMasterItemModelNameDto.getSupplier()) && aimsMasterItemModelNameDto.getSupplier() !="") {
	aimsItemModelName.setSupplier(aimsMasterItemModelNameDto.getSupplier());
        }else {
            throw new Exception("Supplier is Not Found");
        }

	if(Objects.nonNull(aimsMasterItemModelNameDto.getItemCategory()) && aimsMasterItemModelNameDto.getItemCategory() !="") {
		aimsItemModelName.setItemCategory(aimsMasterItemModelNameDto.getItemCategory());
	        }else {
	            throw new Exception("Item Category is Not Found");
	        }
	if(Objects.nonNull(aimsMasterItemModelNameDto.getItemGroup()) && aimsMasterItemModelNameDto.getItemGroup() != "") {
		aimsItemModelName.setItemGroup(aimsMasterItemModelNameDto.getItemGroup());
	        }else {
	            throw new Exception("Item Group is Not Found");
	        }
	        if(Objects.nonNull(aimsMasterItemModelNameDto.getUom()) && aimsMasterItemModelNameDto.getUom() !="") {
	        	aimsItemModelName.setUom(aimsMasterItemModelNameDto.getUom());
	        }else {
	            throw new Exception("UOM is Not Found");
	        }
	        if(Objects.nonNull(aimsMasterItemModelNameDto.getItemHsn()) && aimsMasterItemModelNameDto.getItemHsn() !="") {
	        	aimsItemModelName.setItemHsn(aimsMasterItemModelNameDto.getItemHsn());
	        }else {
	            throw new Exception("Item Hsn is Not Found");
	        }
	        if(Objects.nonNull(aimsMasterItemModelNameDto.getItemDescription()) && aimsMasterItemModelNameDto.getItemDescription() !="") {
	        	aimsItemModelName.setItemDescription(aimsMasterItemModelNameDto.getItemDescription());
	        }else {
	            throw new Exception("Item Description is Not Found");
	        }
	        if(Objects.nonNull(aimsMasterItemModelNameDto.getRemarks()) && aimsMasterItemModelNameDto.getRemarks() !="") {
	        	aimsItemModelName.setRemarks(aimsMasterItemModelNameDto.getRemarks());
	        }else {
	            throw new Exception("Remark is Not Found");
	        }



	        aimsItemModelName = aimsMasterItemModelNameRepository.save(aimsItemModelName);
	        aimsMasterItemModelNameDto = new AimsMasterItemModelNameDto();
	        return aimsMasterItemModelNameDto;

	    }

		public AimsMastersItemModelNameFetchDto fetchDetailsData(String itemModelName) {
		    return aimsMasterItemModelNameRepository.findAllByItemModelName(itemModelName);
		} 
	
		//Filter method
				public Page<AimsMastersItemModelNameFetchDto> fetchFilterData(Pageable pageObj, String itemModelName){
				    return aimsMasterItemModelNameRepository.findAllFilterDataByItemModelName(pageObj, itemModelName);
				}
	
	
}
