package com.envision.aims.control;

import com.envision.aims.entity.AimsItemModelName;
import com.envision.aims.model.AimsMasterItemModelNameDto;
import com.envision.aims.service.AimsMastersItemModelNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/itemModelName")
public class AimsMastersItemModelNameController {

	  @Autowired
		private AimsMastersItemModelNameService aimsMastersItemModelNameService;
		
		@GetMapping("/")
		@ResponseBody
		public Page<AimsItemModelName> getItemModelName(@RequestParam("page") Integer page, @RequestParam("size") Integer size) {
			
			Pageable pageObj=PageRequest.of(page, size);
			
			return aimsMastersItemModelNameService.getItemModelName(pageObj);
		}
		
		@PostMapping("/")
		@ResponseBody
		public AimsItemModelName saveMasterItem(@RequestBody AimsMasterItemModelNameDto aimsMasterItemDto) {
			
			return aimsMastersItemModelNameService.saveMasterItem(aimsMasterItemDto);
		}
		


}
