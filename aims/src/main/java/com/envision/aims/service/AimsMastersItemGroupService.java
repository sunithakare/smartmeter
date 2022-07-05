package com.envision.aims.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import com.envision.aims.model.AimsMastersUserErrorMsgDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.envision.aims.entity.AimsItemGroup;
import com.envision.aims.entity.AimsUserErrorMsg;
import com.envision.aims.model.AimsMastersItemGroupDto;
import com.envision.aims.model.AimsMastersItemGroupsaveDto;
import com.envision.aims.repository.AimsMastersItemGroupRepository;
import com.envision.aims.repository.AimsMastersUserErrorMsgRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AimsMastersItemGroupService {

	@Autowired
	AimsMastersItemGroupRepository aimsMastersItemGroupRepository;
	
	@Autowired
	AimsMastersUserErrorMsgRepository aimsMastersUserErrorMsgRepository;
	


	
	//save the itemGroup data
	@Transactional
	public AimsMastersItemGroupsaveDto saveAimsItemGroup(AimsMastersItemGroupsaveDto aimsMastersItemGroupsaveDto){

		AimsItemGroup aimsItemGroup= new AimsItemGroup();
		BeanUtils.copyProperties(aimsMastersItemGroupsaveDto, aimsItemGroup);

		aimsItemGroup = aimsMastersItemGroupRepository.save(aimsItemGroup);
		List<AimsUserErrorMsg> aimsUserErrorMsgList = new ArrayList<>();
		for(AimsMastersUserErrorMsgDto aimsMastersUserErrorMsgDto : aimsMastersItemGroupsaveDto.getErrors()) {
			AimsUserErrorMsg aimsUserErrorMsg = new AimsUserErrorMsg();
			BeanUtils.copyProperties(aimsMastersUserErrorMsgDto, aimsUserErrorMsg);
			aimsUserErrorMsg.setGroup(aimsItemGroup);
			aimsUserErrorMsgList.add(aimsUserErrorMsg);
		}
		aimsMastersUserErrorMsgRepository.saveAll(aimsUserErrorMsgList);

		return aimsMastersItemGroupsaveDto;
	}
			

			
		       
	//fetch all data of itemgroup
	public Page<AimsItemGroup> getAllItemGroupData(Pageable pageableObj){
		
		return aimsMastersItemGroupRepository.findAll(pageableObj);
	}

	public List<AimsItemGroup> getAllItemGroupData(){

		return aimsMastersItemGroupRepository.findAll();
	}
	
	//Filter method
	public Page<AimsMastersItemGroupDto> fetchFilterData(Pageable pageObj, String itemGroup){
	    return aimsMastersItemGroupRepository.findAllFilterDataByItemGroup(pageObj, itemGroup);
	}
	//NavToDetails
	public AimsMastersItemGroupDto fetchDetailsData(String itemGroup) {
	    return aimsMastersItemGroupRepository.findAllByItemGroup(itemGroup);
	} 
	
	//Delete Method
	public void deleteError(Long id) {
		aimsMastersUserErrorMsgRepository.deleteById(id);
	}
	
}

	
