package com.envision.cimodule.controller;


import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.envision.cimodule.database.objects.CiMasterData;
import com.envision.cimodule.model.MasterDataModel;
import com.envision.cimodule.service.MasterDataService;
import com.envision.common.exception.DataNotFoundException;



@RestController
@RequestMapping("/")
public class CimoduleController {
 
    @Autowired
    MasterDataService masterDataService;
    
    
    
    @PostMapping("/masterdata")
	@ResponseBody
    public MasterDataModel saveMasterData(@RequestBody MasterDataModel masterDataModel) throws DataNotFoundException {
    	return masterDataService.insertMasterData(masterDataModel);
    }
    
    @PutMapping("/masterdata")
	@ResponseBody
    public MasterDataModel updateMasterData(@RequestBody MasterDataModel masterDataModel) throws DataNotFoundException{
    	
    	return masterDataService.updateMasterData(masterDataModel);
    	
    }
    
    @GetMapping("/{consumerId}")
	@ResponseBody
    public ResponseEntity<MasterDataModel> getMasterData(@PathVariable String consumerId){
    	return null;
    }
 
}