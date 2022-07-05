package com.envision.sladashboard;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.mail.Multipart;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.SecurityContextProvider;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.envision.common.exception.DataNotFoundException;
import com.envision.login.service.UserServiceImpl;
import com.envision.sladashboard.database.projections.SlaReportResponse;
import com.envision.sladashboard.modal.ReportDownloadRequest;
import com.envision.sladashboard.modal.ReportUploadRequest;

import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

@Component
@Slf4j
public class SlaUtilities {
	final DefaultResourceLoader loader = new DefaultResourceLoader();

	@Autowired
	protected DataSource localDataSource;
	@Autowired
	UserServiceImpl userService;

	public byte[] generateReportFromJasper(ReportDownloadRequest reportRequest, String jasperPath) throws Exception {
		Connection dbConnection = localDataSource.getConnection();
		JasperPrint print;
		try {
			Resource resource = loader.getResource("classpath:" + jasperPath);
			File myFile = resource.getFile();
			InputStream stream = resource.getInputStream();
			JasperReport report = JasperCompileManager.compileReport(stream);
			Map<String, Object> parameters = new HashMap<>();
			parameters.put("Month", reportRequest.getMonth());
			parameters.put("Year", reportRequest.getYear());
			parameters.put("Discom", reportRequest.getDiscom());
			parameters.put("State", reportRequest.getState());
			parameters.put("quarter", reportRequest.getQuarter());
			print = JasperFillManager.fillReport(report, parameters, dbConnection);
		} catch (Exception e) {
			throw e;
		} finally {

			dbConnection.close();
		}
		return JasperExportManager.exportReportToPdf(print);
	}

	public List<String> getAllSLAReportsForUser() {
		List<String> reportNameList = new ArrayList<String>();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		authentication.getAuthorities().forEach(authority -> {
			if (authority.getAuthority().startsWith(SlaConstants.REPORT_ACCESS_NAME)) {
				reportNameList.add(authority.getAuthority().substring(7));
			}

		});

		return reportNameList;
	}

	public byte[] getReportFromLocal(SlaReportResponse reportObj, ReportDownloadRequest reportRequest,
			String reportPath) throws Exception {
		String path = "";
		if (reportObj.getTimeframe().equals(SlaConstants.TIME_MONTHLY)) {

			LocalDate date = LocalDate.of(Integer.valueOf(reportRequest.getYear()),
					Integer.valueOf(reportRequest.getMonth()), 1);
			String yearFolder = date.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH).toUpperCase() + "_"
					+ reportRequest.getYear();
			String reportFolderName = reportObj.getRfpNoType() + " " + reportObj.getReportName();
			path = reportPath + reportRequest.getState() + "/" + reportRequest.getDiscom() + "/"
					+ reportRequest.getYear() + "/" + yearFolder + "/" + reportFolderName;
		} else if (reportObj.getTimeframe().equals(SlaConstants.TIME_ANNUAL)) {

			LocalDate date = LocalDate.of(Integer.valueOf(reportRequest.getYear()),
					Integer.valueOf(reportRequest.getMonth()), 1);
			String yearFolder = date.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH).toUpperCase() + "_"
					+ reportRequest.getYear();
			String reportFolderName = reportObj.getRfpNoType() + " " + reportObj.getReportName();
			path = reportPath + reportRequest.getState() + "/" + reportRequest.getDiscom() + "/"
					+ reportRequest.getYear() + "/" + reportFolderName;
		} else if (reportObj.getTimeframe().equals(SlaConstants.TIME_DAILY)) {
			LocalDate date = LocalDate.of(Integer.valueOf(reportRequest.getYear()),
					Integer.valueOf(reportRequest.getMonth()), Integer.valueOf(reportRequest.getDay()));
			String yearFolder = date.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH).toUpperCase() + "_"
					+ reportRequest.getYear();
			String reportFolderName = reportObj.getRfpNoType() + " " + reportObj.getReportName();
			path = reportPath + reportRequest.getState() + "/" + reportRequest.getDiscom() + "/"
					+ reportRequest.getYear() + "/" + yearFolder + "/" + reportFolderName;

			DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd MMMM uuuu");
			String fileNameLike = date.format(formatters);
			File dir = new File(path);
			if (dir.exists()) {
				File[] filteredFiles = dir.listFiles(new FilenameFilter() {

					@Override
					public boolean accept(File dir, String name) {
						// TODO Auto-generated method stub
						return name.contains(fileNameLike);
					}
				});
				if (filteredFiles.length > 0) {
					return Files.readAllBytes(filteredFiles[0].toPath());
				} else {
					log.error("Report not Found");
					throw new DataNotFoundException("Report not Found");
				}
			} else {
				log.error("Report not Found");
				throw new DataNotFoundException("Report not Found");
			}

		} else if (reportObj.getTimeframe().equals(SlaConstants.TIME_QUARTERLY)) {

//			LocalDate date = LocalDate.of(Integer.valueOf(reportRequest.getYear()), Integer.valueOf(reportRequest.getMonth()), 1);
//			String yearFolder=date.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH).toUpperCase()+"_"+reportRequest.getYear();
			String reportFolderName = reportObj.getRfpNoType() + " " + reportObj.getReportName();
			path = reportPath + reportRequest.getState() + "/" + reportRequest.getDiscom() + "/"
					+ reportRequest.getYear() + "/" + reportRequest.getQuarter() + "/" + reportFolderName;
		} else {
			log.error("Report not Found");
			throw new DataNotFoundException("Report not Found");
		}

		File reportFolder = new File(path);
		log.debug("getReport:reportPath" + reportPath);
		log.debug("path:" + path);
		log.debug("AbsolutePath:" + reportFolder.getAbsolutePath());
		if (reportFolder.exists() && reportFolder.listFiles().length > 0) {

			return Files.readAllBytes(reportFolder.listFiles()[0].toPath());
		} else {
			log.error("Report not Found");
			throw new DataNotFoundException("Report not Found");
		}
	}

	public void uploadReportToFolder(String reportPath, String reportFolderName,
			ReportUploadRequest reportUploadRequest, MultipartFile uploadFile) throws IOException {

		String path = reportPath + reportUploadRequest.getState() + "/" + reportUploadRequest.getDiscom() + "/"
				+ reportUploadRequest.getYear() + "/" + reportUploadRequest.getQuarter() + "/" + reportFolderName;

		File reportFolder = new File(path);

		if (reportFolder.exists()) {

			for (File file : reportFolder.listFiles()) {
				file.delete();
			}
		} else {
			reportFolder.mkdirs();
		}

		path = path + "/" + uploadFile.getOriginalFilename();

		Files.write(new File(path).toPath(), uploadFile.getBytes(), StandardOpenOption.CREATE);

	}
}
