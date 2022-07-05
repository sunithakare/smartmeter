package com.envision.aims.control;

import java.util.List;

import com.envision.aims.entity.AimsItemCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.envision.aims.model.AimsMastersItemCategoryDto;
import com.envision.aims.model.AimsMastersItemCategoryVO;
import com.envision.aims.service.AimsMasterItemCategoryService;

@RestController
@RequestMapping("/itemCategory")
public class AimsMastersItemCategoryController {

	@Autowired
	AimsMasterItemCategoryService aimsMasterItemCategoryService; 
	
	@GetMapping("/findAllItemCategory")
	@ResponseBody
	public Page<AimsItemCategory> findAllItemType(@RequestParam("page") Integer page,@RequestParam("size") Integer size) {
		
		Pageable pageObj=PageRequest.of(page, size);
		
		return aimsMasterItemCategoryService.getItemCategory(pageObj);
	}
	


	@GetMapping("/active")
	@ResponseBody
	public List<AimsItemCategory> getItemCategory(){
		return aimsMasterItemCategoryService.getItemCategory();
	}
	
	@PostMapping("/")
	@ResponseBody
	public AimsItemCategory saveItemType(@RequestBody AimsMastersItemCategoryDto aimsMastersItemCategoryDto) {
		
		return aimsMasterItemCategoryService.saveMasterItemCategory(aimsMastersItemCategoryDto);
	}
}
