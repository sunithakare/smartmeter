package com.envision.useraccessmanagement.controller;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.envision.common.exception.DataNotFoundException;
import com.envision.useraccessmanagement.database.projection.MasterDataProjection;
import com.envision.useraccessmanagement.modal.ApproverRequest;
import com.envision.useraccessmanagement.modal.NextApproverData;
import com.envision.useraccessmanagement.modal.UAMCompleteRequest;
import com.envision.useraccessmanagement.modal.UAMDetailsVO;
import com.envision.useraccessmanagement.modal.UAMRegisteredVO;
import com.envision.useraccessmanagement.modal.UAMRequest;
import com.envision.useraccessmanagement.service.UseraccessmanagementService;

@RestController
@RequestMapping("uam")
public class UseraccessmanagementController {
 
	@Autowired
	UseraccessmanagementService useraccessmanagementService; 
	
	@PostMapping("register")
	@ResponseBody
    public  UAMRegisteredVO registeruser(@RequestBody UAMRequest request) throws Exception {
		return useraccessmanagementService.uamRegistration(request);
    }
	
	@GetMapping("getid/{uuid}")
	@ResponseBody
    public  UAMDetailsVO fetchDataForLink(@PathVariable("uuid") UUID uuid) throws DataNotFoundException {
		return useraccessmanagementService.fetchUAMUserRecordByUUID(uuid);
    }

	@GetMapping("reference/{refId}")
	@ResponseBody
    public  UAMDetailsVO fetchDataForLink(@PathVariable("refId")  String refId) throws DataNotFoundException {
		return useraccessmanagementService.fetchUAMUserRecord(refId);
    }

	@GetMapping("getall")
	@ResponseBody
	@PreAuthorize("checkPermission('ANY','UAMAPPROVAL') or checkPermission('ANY','UAMVIEW') ")
    public  Page<MasterDataProjection> getAllRecords( Authentication authentication,@RequestParam(value = "listview",required = true) Boolean listView,@RequestParam(value = "page",required = true) Integer page,@RequestParam(value = "size",required = true) Integer size) {

		Pageable pageableObj = PageRequest.of(page, size,Sort.by("referenceId").ascending());
			 Page<MasterDataProjection> response = useraccessmanagementService.getAllUAMRecords(authentication,pageableObj,listView);
			 return response;
	
    }
	

	@GetMapping("getallwithfilter")
	@ResponseBody
	@PreAuthorize("checkPermission('ANY','UAMAPPROVAL') or checkPermission('ANY','UAMVIEW') ")
    public  Page<MasterDataProjection> getAllRecordsWithFilter( Authentication authentication,@RequestParam(value = "listview",required = true) Boolean listView,
    		@RequestParam(value = "refId",required = false) String refId,	
    		@RequestParam(value = "status",required = false) String status,
    		@RequestParam(value = "page",required = true) Integer page,@RequestParam(value = "size",required = true) Integer size) {

		Pageable pageableObj = PageRequest.of(page, size,Sort.by("referenceId").ascending());
			 Page<MasterDataProjection> response = useraccessmanagementService.getAllUAMRecordsWithFilter(authentication,pageableObj,listView,refId,status);
			 return response;
	
    }
	

	@GetMapping("getstatuslist")
	@ResponseBody
	@PreAuthorize("checkPermission('ANY','UAMAPPROVAL') or checkPermission('ANY','UAMVIEW') ")
    public  Set<String> getAllStatusList( Authentication authentication){

			 return useraccessmanagementService.findAllValidStatusForApprover(authentication);
	
    }
	

	@PostMapping("approval/{refId}")
	@ResponseBody
    public  void approveRequest( Authentication authentication, @RequestBody ApproverRequest approveReq,@PathVariable("refId") String refId) throws Exception {
			useraccessmanagementService.approveUAMUserRecord(authentication,approveReq,refId);
		
    }

	@PostMapping("completeapplication/{refId}")
	@ResponseBody
    public  void completeApplication( Authentication authentication, @RequestBody UAMCompleteRequest req,@PathVariable("refId") String refId) throws Exception {
			useraccessmanagementService.completeUAMProcess(authentication,req,refId);
		
    }
	

	@GetMapping("getnextapprover")
	@ResponseBody
	public NextApproverData getNextApproverData(@RequestParam(value = "status",required = true) String status,@RequestParam(value = "discom",required = false) String discom) throws DataNotFoundException {
	
		
		try {
			System.out.println(useraccessmanagementService);
			return useraccessmanagementService.fetchNextApproverData(status, discom);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}

	}
}