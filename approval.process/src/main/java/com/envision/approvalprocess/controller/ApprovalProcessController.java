package com.envision.approvalprocess.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.envision.approvalprocess.database.objects.ApproverDetails;
import com.envision.approvalprocess.modal.ApprovalProcessVO;
import com.envision.approvalprocess.modal.ApproverCreationPOJO;
import com.envision.approvalprocess.modal.ApproverDetailsProjection;
import com.envision.approvalprocess.service.ApprovalProcessService;
import com.envision.common.exception.DataNotFoundException;

@RestController
@RequestMapping("approval")
public class ApprovalProcessController {
 
	@Autowired
	ApprovalProcessService approvalProcessService;
	
//	@GetMapping("approval")
//	@ResponseBody
//    public  ApprovalProcessVO approvalProcessSampleMethod() {
//    	return approvalProcessService.getApprovalProcessData("UAM Approval Process");
//    }

	@GetMapping("list")
	@PreAuthorize("checkPermission('ANY','APPUC')")
	@ResponseBody
    public  Page<ApproverDetailsProjection> listApprovers(@RequestParam(value = "page",required = true) Integer page,@RequestParam(value = "size",required = true) Integer size) {

		Pageable pageableObj = PageRequest.of(page, size);
    	return approvalProcessService.getAllApprovers(pageableObj);
    }
	@GetMapping("find")
	@PreAuthorize("checkPermission('ANY','APPUC')")
	@ResponseBody
    public  Page<ApproverDetailsProjection> findApprovers(@RequestParam(value = "userName",required = true) String userName,@RequestParam(value = "approverIdentity",required = true) String approverIdentity,@RequestParam(value = "page",required = true) Integer page,@RequestParam(value = "size",required = true) Integer size) {

		Pageable pageableObj = PageRequest.of(page, size);
    	return approvalProcessService.findApprovers(userName,approverIdentity,pageableObj);
    }
	
	@DeleteMapping("delete/{userName}")
	@PreAuthorize("checkPermission('ADMIN','APPUC') or checkPermission('MANAGER','APPUC')")
	@ResponseBody
    public  void deleteApprover(@PathVariable(value = "userName",required = true) String userName) {

    	approvalProcessService.deleteApprover(userName);
    }
	
	@GetMapping("getapprover")
	@PreAuthorize("checkPermission('ANY','APPUC')")
	@ResponseBody
    public  List<ApproverDetailsProjection> findApproverByName(@RequestParam(value = "userName",required = true) String userName) {

    	return approvalProcessService.findApproverByName(userName);
    }
	
	@PostMapping("createapprover")
	@PreAuthorize("checkPermission('ADMIN','APPUC') or checkPermission('MANAGER','APPUC')")
	@ResponseBody
    public  void createApprover(@RequestBody ApproverCreationPOJO approverCreationPOJO) throws DataNotFoundException {

		approvalProcessService.createApprover(approverCreationPOJO);
	}
	@PostMapping("saveapprover")
	@PreAuthorize("checkPermission('ADMIN','APPUC') or checkPermission('MANAGER','APPUC')")
	@ResponseBody
    public  void saveApprover(@RequestBody ApproverCreationPOJO approverCreationPOJO) throws DataNotFoundException {

		approvalProcessService.saveApprover(approverCreationPOJO);
	}
}