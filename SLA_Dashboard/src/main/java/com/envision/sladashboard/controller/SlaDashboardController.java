package com.envision.sladashboard.controller;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URLConnection;
import java.util.List;

import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.envision.common.exception.DataNotFoundException;
import com.envision.sladashboard.SlaUtilities;
import com.envision.sladashboard.database.projections.EmployeeCreationProjection;
import com.envision.sladashboard.database.projections.SlaReportResponse;
import com.envision.sladashboard.modal.EmployeeDetailsVO;
import com.envision.sladashboard.modal.EmployeeRoleDetail;
import com.envision.sladashboard.modal.ReportDownloadRequest;
import com.envision.sladashboard.modal.ReportUploadRequest;
import com.envision.sladashboard.service.SlaReportsService;

@RestController
@RequestMapping("sladashboard")
public class SlaDashboardController {

	@Autowired
	SlaReportsService reportsService;

	@Autowired
	SlaUtilities slaUtilities;

	@PreAuthorize("@slaSecurityService.canSLADownloaded(authentication,#downloadRequest)")
	@PostMapping("download")
	public ResponseEntity<Resource> download(@RequestBody ReportDownloadRequest downloadRequest,@RequestParam(value="module",required = false) String module) throws DataNotFoundException, IOException {

		ByteArrayResource resource = null;
		try {
			byte[] fileData = reportsService.getReport(downloadRequest,module);
			resource = new ByteArrayResource(fileData);
			BufferedInputStream bis = new BufferedInputStream(new ByteArrayInputStream(fileData));
//			String mimeType = URLConnection.guessContentTypeFromStream(bis);
			Tika tika=new Tika();  
			return ResponseEntity.ok().contentLength(fileData.length).contentType(MediaType.valueOf(tika.detect(bis)))
					.body(resource);
		} catch (DataNotFoundException e) {
			throw e;
//			return ResponseEntity.notFound().build();
		}

	}

//	@GetMapping("view")
//	public ResponseEntity<Resource> viewReport(ReportDownloadRequest downloadRequest) {
//
//		ByteArrayResource resource = null;
//		try {
//			byte[] generatedReport = slaUtilities.generateReportFromJasper(downloadRequest, "");
//			resource = new ByteArrayResource(generatedReport);
//			return ResponseEntity.ok().contentLength(generatedReport.length).contentType(MediaType.APPLICATION_PDF)
////		            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+downloadRequest.getReportName()+"_"+downloadRequest.getDate()+".pdf")
//					.body(resource);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return ResponseEntity.notFound().build();
//		}
//
//	}

//	@PreAuthorize("@securityService.checkPermission('ADMIN','All SLA Link')")
	
	@PreAuthorize("( #module==null and checkPermission('ANY','All SLA Link')) or ( #module!=null and checkPermission('ANY',#module))")
	@GetMapping(path = { "fetchSlaReports/{module}", "fetchSlaReports" })
	@ResponseBody
	public List<SlaReportResponse> fetchSLAReports(@PathVariable(value = "module", required = false) String module,
			@RequestParam("page") Integer page, @RequestParam("size") Integer size) {

		Pageable pageableObj = PageRequest.of(page, size, Sort.by("module").ascending());
//		return reportsService.fetchSLAReports(pageableObj,module);
		return reportsService.fetchSLAReports(module);
	}

	@GetMapping(path = { "fetchSlaReportswithfilter/{module}", "fetchSlaReportswithfilter" })
	@ResponseBody
	public Page<SlaReportResponse> fetchSLAReportsWithFilter(
			@PathVariable(value = "module", required = false) String module, @RequestParam("page") Integer page,
			@RequestParam("size") Integer size, @RequestParam("rfpNo") String rfpNo,
			@RequestParam("reportName") String reportName) {
		if (rfpNo == null) {
			rfpNo = "";
		}
		if (reportName == null) {
			reportName = "";
		}
		Pageable pageableObj = PageRequest.of(page, size, Sort.by("module").ascending());
		return reportsService.fetchSLAReportswithFilter(pageableObj,module,rfpNo, reportName);
	
	}
//
//	@GetMapping("testsla")
//	public Page<SLAReportResponse> testAPI() {
//		Pageable firstPageWithTwoElements = PageRequest.of(0, 5, Sort.by("module").ascending());
//
////		return sladao.findByModuleAndReportName("HES", "SIM Information Availability");
////		return sladao.findAllSLAReportsBy(firstPageWithTwoElements);
//		return sladao.findAllSLAReportswithFilter(firstPageWithTwoElements, "1", "E");
//	}
	

//	@GetMapping("fetchStateAndDiscomForUser")
//	@ResponseBody
//	public List<UserDiscomData> fetchStateAndDiscomForUser(Authentication authentication) {
//		
//		return reportsService.getUserDiscomData(authentication.getName());
//	}
	

	@PutMapping(path="uploadrfp1819", consumes = { "multipart/form-data" })
	@ResponseBody
	@PreAuthorize("checkPermission('ANY','RFP 18/19 UPLOAD')")
	public void uploadFile1819(@RequestPart("formdata") ReportUploadRequest reportUploadRequest,@RequestPart("uploadFile") MultipartFile file) throws Exception {

		reportsService.processUploadOfRfp1819Reports(reportUploadRequest, file);

	}
	

	@PutMapping(path="uploadrfp21", consumes = { "multipart/form-data" })
	@ResponseBody
	@PreAuthorize("checkPermission('ANY','RFP 21 UPLOAD')")
	public void uploadFile21(@RequestPart("uploadFile") MultipartFile file) throws Exception {
try {
	reportsService.processUploadOfRfp21Reports( file);
} catch (Exception e) {
	e.printStackTrace();
	throw e;
}

	}
	

	@GetMapping("fetchEmployeeRoles")
	@ResponseBody
	@PreAuthorize("checkPermission('ANY','EMPLOYEE_ROLE')")
	public List<EmployeeRoleDetail> fetchEmployeeRoles(Authentication authentication) {
		
		return reportsService.getEmployeeRoles();
	}

	@GetMapping("fetchEmployeeRoleForCategory")
	@ResponseBody
	@PreAuthorize("checkPermission('ANY','EMPLOYEE_ROLE')")
	public List<EmployeeRoleDetail> fetchEmployeeRoleList(@RequestParam(value="category",required = false,defaultValue = "") String category) {
		
		return reportsService.getEmployeeRolesForCategory(category);
	}
	
	@PostMapping("saveEmployeeRole")
	@ResponseBody
	@PreAuthorize("checkPermission('ADMIN','EMPLOYEE_ROLE')")
	public void saveEmployeeRole(@RequestBody EmployeeRoleDetail detail) {
		
		 reportsService.saveEmployeeRole(detail);
	}
	

	@DeleteMapping("deleteEmployeeRole")
	@ResponseBody
	@PreAuthorize("checkPermission('ADMIN','EMPLOYEE_ROLE')")
	public void deleteEmployeeRole(@RequestBody EmployeeRoleDetail detail) throws DataNotFoundException {
		
		 reportsService.deleteEmployeeRole(detail);
	}
	

	@GetMapping("fetchEmployees")
	@ResponseBody
	@PreAuthorize("checkPermission('ANY','EMPLOYEE_CREATION')")
	public Page<EmployeeCreationProjection> fetchEmployees(@RequestParam("page") Integer page,
			@RequestParam("size") Integer size) {

		try {
			Pageable pageableObj = PageRequest.of(page, size, Sort.by("employeeId").ascending());
			
			return reportsService.getEmployees(pageableObj);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}
	


	@GetMapping("fetchEmployeeswithfilter")
	@ResponseBody
	@PreAuthorize("checkPermission('ANY','EMPLOYEE_CREATION')")
	public Page<EmployeeCreationProjection> fetchEmployeeswithfilter(@RequestParam("page") Integer page,
			@RequestParam("size") Integer size,
			@RequestParam(value="userName",required = false,defaultValue = "") String userName,
			@RequestParam(value="role",required = false,defaultValue = "") String role,
			@RequestParam(value="empId",required = false,defaultValue = "") String empId
			) {

		try {
			Pageable pageableObj = PageRequest.of(page, size, Sort.by("employeeId").ascending());
			
			return reportsService.getEmployeesWithFilter(pageableObj,userName,role,empId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}
	

	@PostMapping("saveEmployee")
	@ResponseBody
	@PreAuthorize("checkPermission('ANY','EMPLOYEE_CREATION')")
	public void saveEmployee(@RequestBody EmployeeDetailsVO detailsVO) {
		reportsService.saveEmployee(detailsVO);

	}

	@GetMapping("getEmployeeData")
	@ResponseBody
	@PreAuthorize("checkPermission('ANY','EMPLOYEE_CREATION')")
	public EmployeeDetailsVO getEmployee(@RequestParam(value="empId",required = false,defaultValue = "") String empId) throws DataNotFoundException {
		return reportsService.getEmployee(empId);
	}
	
//	@PutMapping("uploadrfp")
//	@ResponseBody
//	public void uploadRfpReport(@RequestParam MultipartFile uploadFile, @RequestParam String quater) {
//		
////		return reportsService.getUserDiscomData(authentication.getName());
//	}
	
	
}
