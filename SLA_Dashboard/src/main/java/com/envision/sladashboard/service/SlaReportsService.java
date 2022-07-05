package com.envision.sladashboard.service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.envision.common.exception.DataException;
import com.envision.common.exception.DataNotFoundException;
import com.envision.common.service.FileUploadService;
import com.envision.sladashboard.SlaConstants;
import com.envision.sladashboard.SlaUtilities;
import com.envision.sladashboard.database.dao.AdhSecurityPolicyDAO;
import com.envision.sladashboard.database.dao.EmployeeAttendanceDAO;
import com.envision.sladashboard.database.dao.EmployeeCreationDAO;
import com.envision.sladashboard.database.dao.EmployeeQualificationsDAO;
import com.envision.sladashboard.database.dao.EmployeeRolesDAO;
import com.envision.sladashboard.database.dao.ImpleAuditRecomDAO;
import com.envision.sladashboard.database.dao.SlaReportsDAO;
import com.envision.sladashboard.database.objects.AdhSecurityPolicy;
import com.envision.sladashboard.database.objects.EmployeeAttendance;
import com.envision.sladashboard.database.objects.EmployeeCreation;
import com.envision.sladashboard.database.objects.EmployeeQualifications;
import com.envision.sladashboard.database.objects.EmployeeRoles;
import com.envision.sladashboard.database.objects.ImpleAuditRecom;
import com.envision.sladashboard.database.projections.EmployeeCreationProjection;
import com.envision.sladashboard.database.projections.SlaReportResponse;
import com.envision.sladashboard.modal.EmployeeDetailsVO;
import com.envision.sladashboard.modal.EmployeeDocDetails;
import com.envision.sladashboard.modal.EmployeeRoleDetail;
import com.envision.sladashboard.modal.ReportDownloadRequest;
import com.envision.sladashboard.modal.ReportUploadRequest;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.dataformat.csv.CsvSchema.Builder;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SlaReportsService {

	@Autowired
	SlaReportsDAO reportsDAO;
	@Autowired
	AdhSecurityPolicyDAO adhSecurityPolicyDAO;
	@Autowired
	ImpleAuditRecomDAO auditRecomDAO;

	@Autowired
	EmployeeAttendanceDAO employeeAttendanceDAO;
	@Autowired
	EmployeeCreationDAO employeeCreationDAO;

	@Autowired
	SlaUtilities slaUtilities;

	@Autowired
	EmployeeRolesDAO employeeRolesDAO;

	@Autowired
	EmployeeQualificationsDAO employeeQualificationsDAO;
//	@Autowired
//	UserDiscomDAO userDiscomDAO;

	@Value("${reportpath}")
	String reportPath;

	@Autowired
	FileUploadService fileUploadService;

	public byte[] getReport(ReportDownloadRequest reportRequest, String module) throws DataNotFoundException {

		SlaReportResponse reportObj;

		Optional<SlaReportResponse> optional = reportsDAO.findByModuleAndReportName(reportRequest.getReportModule(),
				reportRequest.getReportName());
		if (!optional.isPresent()) {
			throw new DataNotFoundException("Report not Found");
		}
		reportObj = optional.get();
		try {
			if (module == null) {
				return slaUtilities.getReportFromLocal(reportObj, reportRequest, reportPath);
			} else {
				if (reportObj.getReportType().equalsIgnoreCase(SlaConstants.REPORT_TYPE_FILE)) {
					return slaUtilities.getReportFromLocal(reportObj, reportRequest, reportPath);
				}
				return slaUtilities.generateReportFromJasper(reportRequest, reportObj.getPath());
			}
		} catch (DataNotFoundException e) {
			log.error("Report not Found", e);
			throw e;
		} catch (Exception e) {

			log.error("Report not Found", e);
			throw new DataNotFoundException("Report not Found");
		}

	}

	public List<SlaReportResponse> fetchSLAReports(String module) {
		List<String> reportList = slaUtilities.getAllSLAReportsForUser();
		if (module == null) {
			return reportsDAO.findAllSLAReportsByReportNameIn(reportList);
		} else {
			return reportsDAO.findByModuleAndReportNameIn(module, reportList);
		}
	}

	public Page<SlaReportResponse> fetchSLAReportswithFilter(Pageable pageableObj, String module, String rfpNo,
			String reportName) {

		List<String> reportList = slaUtilities.getAllSLAReportsForUser();
		if (module == null) {
			return reportsDAO.findAllSLAReportswithFilter(pageableObj, rfpNo, reportName, reportList);
		} else {
			return reportsDAO.findByModulewithFilter(module, pageableObj, rfpNo, reportName, reportList);
		}
	}

//	public  List<UserDiscomData> getUserDiscomData(String name) {
//		
//		return userDiscomDAO.findByUser(name);
//	}

	@Transactional
	public void processUploadOfRfp1819Reports(ReportUploadRequest reportUploadRequest, MultipartFile file)
			throws Exception {

		try {
			if (reportUploadRequest.getType().equals(SlaConstants.REPORT_TYPE_LOG)) {

				if (reportUploadRequest.getReport().equals(SlaConstants.REPORT_RFP18)) {
					slaUtilities.uploadReportToFolder(reportPath, SlaConstants.REPORT_RFP18_FILE_NAME,
							reportUploadRequest, file);
				} else if (reportUploadRequest.getReport().equals(SlaConstants.REPORT_RFP19)) {
					slaUtilities.uploadReportToFolder(reportPath, SlaConstants.REPORT_RFP19_FILE_NAME,
							reportUploadRequest, file);
				}

			} else if (reportUploadRequest.getType().equals(SlaConstants.REPORT_TYPE_REPORT)) {

				XSSFWorkbook workbook;
				workbook = new XSSFWorkbook(file.getInputStream());
				// Get first sheet
				XSSFSheet sheet = workbook.getSheetAt(0);
				Iterator<Row> rowIterator = sheet.iterator();
				rowIterator.next();

				List<AdhSecurityPolicy> adhSecurityPoliciesList = new ArrayList<AdhSecurityPolicy>();
				List<ImpleAuditRecom> impleAuditRecomList = new ArrayList<ImpleAuditRecom>();

				try {
					while (rowIterator.hasNext()) {
						Row row = rowIterator.next();
						row.getCell(0);
						if (reportUploadRequest.getReport().equals(SlaConstants.REPORT_RFP18)) {
							adhSecurityPoliciesList.add(mapRowToAshSecuritypolicy(row, reportUploadRequest));
						} else if (reportUploadRequest.getReport().equals(SlaConstants.REPORT_RFP19)) {
							impleAuditRecomList.add(mapRowToImpleAuditRecom(row, reportUploadRequest));
						}
					}
				} catch (Exception e) {
					log.error("Data Format Incorrect Check Input Data", e);
					throw new DataException("Data Format Incorrect Check Input Data");
				}

				if (adhSecurityPoliciesList.size() > 0) {
					adhSecurityPolicyDAO.deleteByQuarterlyAndYearAndStatedetailCodeAndDiscomdetailCode(
							reportUploadRequest.getQuarter(), reportUploadRequest.getYear(),
							reportUploadRequest.getState(), reportUploadRequest.getDiscom());
					adhSecurityPolicyDAO.saveAll(adhSecurityPoliciesList);
					adhSecurityPolicyDAO.flush();
				}

				if (impleAuditRecomList.size() > 0) {
					auditRecomDAO.deleteByQuarterlyAndYearAndStatedetailCodeAndDiscomdetailCode(
							reportUploadRequest.getQuarter(), reportUploadRequest.getYear(),
							reportUploadRequest.getState(), reportUploadRequest.getDiscom());
					auditRecomDAO.saveAll(impleAuditRecomList);
					auditRecomDAO.flush();
				}

			}

		} catch (IOException e) {
			log.error("Processing Failed", e);
			throw new Exception("Processing Failed");
		}

	}

	private AdhSecurityPolicy mapRowToAshSecuritypolicy(Row row, ReportUploadRequest reportUploadRequest) {
		AdhSecurityPolicy adhSecurityPolicy = new AdhSecurityPolicy();
		adhSecurityPolicy.setSectionTitle(row.getCell(1).getStringCellValue());
		adhSecurityPolicy.setPolicyStatement(row.getCell(2).getStringCellValue());
		adhSecurityPolicy.setAdherenceStatus(row.getCell(3).getStringCellValue());
		adhSecurityPolicy.setSupportingDocument(row.getCell(4).getStringCellValue());
		adhSecurityPolicy.setYear(reportUploadRequest.getYear());
		adhSecurityPolicy.setQuarterly(reportUploadRequest.getQuarter());
		adhSecurityPolicy.setStatedetailCode(reportUploadRequest.getState());
		adhSecurityPolicy.setDiscomdetailCode(reportUploadRequest.getDiscom());
		return adhSecurityPolicy;
	}

	private ImpleAuditRecom mapRowToImpleAuditRecom(Row row, ReportUploadRequest reportUploadRequest) {
		ImpleAuditRecom implAuditRecom = new ImpleAuditRecom();
		implAuditRecom.setObservations(row.getCell(1).getStringCellValue());
		implAuditRecom.setObservationDate(row.getCell(2).getLocalDateTimeCellValue().toLocalDate());
		implAuditRecom.setRecommendation(row.getCell(3).getStringCellValue());
		implAuditRecom.setStatusRemark(row.getCell(4).getStringCellValue());
		implAuditRecom.setClousureStatus(row.getCell(5).getStringCellValue());
		implAuditRecom.setClousureDate(row.getCell(6).getLocalDateTimeCellValue().toLocalDate());
		implAuditRecom.setYear(reportUploadRequest.getYear());
		implAuditRecom.setQuarterly(reportUploadRequest.getQuarter());
		implAuditRecom.setStatedetailCode(reportUploadRequest.getState());
		implAuditRecom.setDiscomdetailCode(reportUploadRequest.getDiscom());
		return implAuditRecom;
	}

	@Transactional
	public void processUploadOfRfp21Reports(MultipartFile file) throws IOException {
		Builder schemaBuilder = CsvSchema.builder().addColumn("STATE").addColumn("Employee_Number")
				.addColumn("Username");

		byte[] fileData=file.getBytes();
		
		String fileLines=new String(fileData);
		
		LocalDate startDate = LocalDate.parse(fileLines.split(",")[3],DateTimeFormatter.ofPattern("dd-MM-yyyy")).with(TemporalAdjusters.firstDayOfMonth());
		LocalDate endDate = startDate.with(TemporalAdjusters.lastDayOfMonth());

		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");

		
		while (startDate.isBefore(endDate) || startDate.isEqual(endDate)) {
			schemaBuilder.addColumn(format.format(startDate));
			startDate = startDate.plusDays(1);
		}
		CsvSchema schema = schemaBuilder.build();
		CsvMapper mapper = new CsvMapper();
		MappingIterator<Map<String, String>> it = mapper.readerForMapOf(String.class).with(schema)
				.readValues(fileData);

		List<EmployeeAttendance> attendenceList = new ArrayList<EmployeeAttendance>();
		it.nextValue();
		
		List<EmployeeAttendance> listToDelete=new ArrayList<EmployeeAttendance>();
		while (it.hasNext()) {
			Map<String, String> singleRecord = it.nextValue();
			Optional<EmployeeCreation> empCreationOpt = employeeCreationDAO
					.findByEmployeeId(singleRecord.get("Employee_Number"));
			startDate = LocalDate.parse(fileLines.split(",")[3],DateTimeFormatter.ofPattern("dd-MM-yyyy")).with(TemporalAdjusters.firstDayOfMonth());
			listToDelete.addAll(employeeAttendanceDAO.findByEmployeeIdAndDateGreaterThanEqualAndDateLessThanEqual(singleRecord.get("Employee_Number"),
					LocalDateTime.of(startDate, LocalTime.MIN),LocalDateTime.of(endDate, LocalTime.MIN)));
			if (!empCreationOpt.isPresent()) {
				continue;
			}
			
			EmployeeCreation empCreation = empCreationOpt.get();
//			startDate = LocalDate.parse(fileLines.split(",")[3],DateTimeFormatter.ofPattern("dd-MM-yyyy")).with(TemporalAdjusters.firstDayOfMonth());
			while (startDate.isBefore(endDate) || startDate.isEqual(endDate)) {

				EmployeeAttendance employeeAttendance = new EmployeeAttendance();
//				employeeAttendance.setEmployeeCreation(empCreation);
				employeeAttendance.setEmployeeId(empCreation.getEmployeeId());
				employeeAttendance.setStateCode(singleRecord.get("STATE"));
				employeeAttendance.setStatus(singleRecord.get(format.format(startDate)));
				employeeAttendance.setDate(LocalDateTime.of(startDate, LocalTime.MIN));

				startDate = startDate.plusDays(1);
				attendenceList.add(employeeAttendance);
			}

		}
		
		employeeAttendanceDAO.deleteAll(listToDelete);
		employeeAttendanceDAO.flush();
		employeeAttendanceDAO.saveAll(attendenceList);

	}

	public List<EmployeeRoleDetail> getEmployeeRoles() {

		return employeeRolesDAO.findAllBy();
	}

	public List<EmployeeRoleDetail> getEmployeeRolesForCategory(String category) {

		return employeeRolesDAO.findAllByRoleCategory(category);
	}

	public void saveEmployeeRole(EmployeeRoleDetail detail) {
		Optional<EmployeeRoles> employeeRoleOpt = employeeRolesDAO.findByRoleNameAndRoleCategory(detail.getRoleName(),
				detail.getRoleCategory());
		EmployeeRoles dataObj = new EmployeeRoles();
		if (employeeRoleOpt.isPresent()) {
			dataObj = employeeRoleOpt.get();
			dataObj.setRoleCategory(detail.getRoleCategory());
			dataObj.setRoleName(detail.getRoleName());
			dataObj.setRoleDesc(detail.getRoleDesc());
		} else {
			dataObj.setRoleCategory(detail.getRoleCategory());
			dataObj.setRoleName(detail.getRoleName());
			dataObj.setRoleDesc(detail.getRoleDesc());
		}
		employeeRolesDAO.saveAndFlush(dataObj);

	}

	public void deleteEmployeeRole(EmployeeRoleDetail detail) throws DataNotFoundException {
		Optional<EmployeeRoles> employeeRoleOpt = employeeRolesDAO.findByRoleNameAndRoleCategory(detail.getRoleName(),
				detail.getRoleCategory());
		EmployeeRoles dataObj = new EmployeeRoles();
		if (employeeRoleOpt.isPresent()) {
			dataObj = employeeRoleOpt.get();
			employeeRolesDAO.delete(dataObj);
			employeeRolesDAO.flush();
		} else {
			throw new DataNotFoundException("Employee Role Not Found");
		}

	}

	public Page<EmployeeCreationProjection> getEmployees(Pageable pageableObj) {

		return employeeCreationDAO.findAllBy(pageableObj);

	}

	public Page<EmployeeCreationProjection> getEmployeesWithFilter(Pageable pageableObj, String userName, String role,
			String empId) {

		return employeeCreationDAO
				.findAllByemployeeNameContainingIgnoreCaseAndEmployeeRoles_RoleNameContainingIgnoreCaseAndEmployeeIdContainingIgnoreCase(
						pageableObj, userName, role, empId);
	}

	public void saveEmployee(EmployeeDetailsVO detailsVO) {

		EmployeeCreation empobj;
		Optional<EmployeeCreation> empOpt = employeeCreationDAO.findByEmployeeId(detailsVO.getEmpId());

		if (empOpt.isPresent()) {
			empobj = empOpt.get();
		} else {
			empobj = new EmployeeCreation();
		}
		empobj.setActive(detailsVO.getActive());
		empobj.setDoj(detailsVO.getDoj());
		empobj.setDor(detailsVO.getDor());
		empobj.setEmployeeName(detailsVO.getEmpName());
		empobj.setEmployeeId(detailsVO.getEmpId());
		empobj.setState(detailsVO.getState());
		EmployeeRoles aEmployeeRoles = employeeRolesDAO
				.findByRoleNameAndRoleCategory(detailsVO.getRole(), detailsVO.getRoleCategory()).get();
		empobj.setEmployeeRoles(aEmployeeRoles);

		if (empobj.getEmployeeQualifications() != null) {
			if (empobj.getEmployeeQualifications().size() > 0) {
				employeeQualificationsDAO.deleteAll(empobj.getEmployeeQualifications());
			}
		}
		empobj.setEmployeeQualifications(new ArrayList<EmployeeQualifications>());

		for (EmployeeDocDetails docData : detailsVO.getDocumentList()) {
			EmployeeQualifications employeeQualification = new EmployeeQualifications();
			employeeQualification.setFileDocId(docData.getDocumentId());
			employeeQualification.setName(docData.getDocumentName());
			employeeQualification.setType(docData.getDocumentType());
			employeeQualification.setEmployeeId(detailsVO.getEmpId());
			empobj.getEmployeeQualifications().add(employeeQualification);
		}

		employeeCreationDAO.save(empobj);
	}

	public EmployeeDetailsVO getEmployee(String empId) throws DataNotFoundException {

		EmployeeCreation empobj;

		Optional<EmployeeCreation> empOpt = employeeCreationDAO.findByEmployeeId(empId);

		if (empOpt.isPresent()) {
			empobj = empOpt.get();
		} else {
			throw new DataNotFoundException("Employee Data Not Available");
		}
		List<EmployeeDocDetails> docDetailsLsit = new ArrayList<EmployeeDocDetails>();
		for (EmployeeQualifications docData : empobj.getEmployeeQualifications()) {
			EmployeeDocDetails docDetails = new EmployeeDocDetails(docData.getType(), docData.getName(),
					docData.getFileDocId());

			docDetailsLsit.add(docDetails);
		}

		EmployeeDetailsVO detailsVO = new EmployeeDetailsVO(empobj.getActive(), empobj.getDoj(), empobj.getDor(),
				empobj.getEmployeeId(), empobj.getEmployeeName(), empobj.getEmployeeRoles().getRoleName(),
				empobj.getEmployeeRoles().getRoleCategory(), empobj.getState(), docDetailsLsit);

		return detailsVO;
	}

}
