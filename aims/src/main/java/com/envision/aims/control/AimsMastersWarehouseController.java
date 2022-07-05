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

import com.envision.aims.entity.AimsWarehouse;
import com.envision.aims.model.AimsWarehouseDto;
import com.envision.aims.service.AimsWareHouseService;

@RestController
@RequestMapping("/warehouse")
public class AimsMastersWarehouseController {
	
	@Autowired
	private AimsWareHouseService aimsWareHouseService;
	
	
	@PostMapping(value = "/")
	@ResponseBody
	public AimsWarehouse saveWarehouse(@RequestBody AimsWarehouseDto aimsMasterItemDto) {
		
		return aimsWareHouseService.saveWarehouse(aimsMasterItemDto);
	}
	
	@GetMapping("/active")
	@ResponseBody
	public List<AimsWarehouse> getActiveWarehouse() {
		return aimsWareHouseService.getActiveWarehouse();
	}

	@GetMapping("/")
	@ResponseBody
	public Page<AimsWarehouse> findAllWarehouseList(@RequestParam("page") Integer page, @RequestParam("size") Integer size) {
		
		Pageable pageObj=PageRequest.of(page, size);
		
		return aimsWareHouseService.findAllBy(pageObj);
	}
	
	
	@DeleteMapping("/cityMapping/{id}")
	@ResponseBody
	public void deleteWareHouseMapping(@PathVariable Long id){
		aimsWareHouseService.deleteWarehouseMapping(id);
		//return "SUCCESS";
	}
}
