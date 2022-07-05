package com.envision.aims.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.envision.aims.entity.AimsItemGroup;
import com.envision.aims.model.AimsMastersItemGroupDto;
import com.envision.aims.model.AimsMastersItemGroupsaveDto;
import com.envision.aims.service.AimsMastersItemGroupService;

@RestController
@RequestMapping("/itemGroup")
public class AimsMastersItemGroupController {
	
	@Autowired
	AimsMastersItemGroupService aimsMastersItemGroupService;
	
	@GetMapping("/getItemGroup")
	@ResponseBody
	public Page<AimsItemGroup> getAllItemGroup(@RequestParam(value="page", required = true)Integer page, @RequestParam(value="size",required = true)Integer size){
		Pageable pageableObj=PageRequest.of(page, size);
		return aimsMastersItemGroupService.getAllItemGroupData(pageableObj);
	}

	@GetMapping("/")
	@ResponseBody
	public List<AimsItemGroup> getAllItemGroup(){
		return aimsMastersItemGroupService.getAllItemGroupData();
	}
	
	@PostMapping("/")
	@ResponseBody
	public AimsMastersItemGroupsaveDto saveItemGroup(@RequestBody AimsMastersItemGroupsaveDto aimsMastersItemGroupsaveDto){
		return aimsMastersItemGroupService.saveAimsItemGroup(aimsMastersItemGroupsaveDto);
	}
	
	
	
	@GetMapping("getItemGroupFilterData")
    @ResponseBody
    public Page<AimsMastersItemGroupDto> getFilterData(@RequestParam("page") Integer page, @RequestParam("size") Integer size,
            @RequestParam(value="itemGroup", required = false, defaultValue = "") String itemGroup){
        Pageable pageObj = PageRequest.of(page, size, Sort.by("itemGroup").ascending());
        return aimsMastersItemGroupService.fetchFilterData(pageObj, itemGroup);
    }
	
	@GetMapping("getDetailsData")
    @ResponseBody
    public AimsMastersItemGroupDto getDetailsData(@RequestParam(value = "itemGroup", required = false) String itemGroup) {
        return aimsMastersItemGroupService.fetchDetailsData(itemGroup);
    }
	
	//delete 
	

	@DeleteMapping("/errorDetails/{id}")
	@ResponseBody
	public void deleteErrorDetails(@PathVariable Long id){
		aimsMastersItemGroupService.deleteError(id);
	}
}
