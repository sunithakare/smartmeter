package com.envision.aims.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.envision.aims.entity.AimsSupplier;
import com.envision.aims.model.AimsMasterSupplierDto;
import com.envision.aims.service.AimsMasterSupplierService;
@RestController
@RequestMapping("/supplier")
public class AimsMasterSupplierController {
	@Autowired
    AimsMasterSupplierService aimsMasterSupplierService;
	
	/*
	 * Code By Pramod 
	 * Aims Master Supplier Detail Page
	 * fetch All Data Through supplierCode
	 */
//	@GetMapping("/")
//	@ResponseBody
//	public Page<AimsSupplier> getAimsMasterSupplierDetails(
//			@RequestParam(value = "page",required = true) Integer page, @RequestParam(value = "size",required = true) Integer size){
//		Pageable pageableObj = PageRequest.of(page, size);
//		return aimsMasterSupplierService.getSupplier(pageableObj);
//	}
	
	@PostMapping("/")
	@ResponseBody
	public AimsSupplier saveMasterSupplier(@RequestBody AimsMasterSupplierDto aimsMasterSupplierDto) {
		return aimsMasterSupplierService.saveAimsSupplier(aimsMasterSupplierDto);
	}

	@GetMapping("/")
	@ResponseBody
	public List<AimsSupplier> getAimsMasterSupplierDetails(){
		return aimsMasterSupplierService.getActiveSupplier();
	}
	
	@DeleteMapping("/groupNameMapping/{id}")
	@ResponseBody
	public void deleteGroupName(@PathVariable Long id){
		aimsMasterSupplierService.deleteGroupName(id);
	}



}
