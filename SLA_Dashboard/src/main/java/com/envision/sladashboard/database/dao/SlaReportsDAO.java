package com.envision.sladashboard.database.dao;

import java.util.List;
import java.util.Optional;

import org.checkerframework.checker.nullness.Opt;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.envision.sladashboard.database.objects.SlaReports;
import com.envision.sladashboard.database.projections.SlaReportResponse;


@Repository
@Qualifier("userRepo")
public interface SlaReportsDAO extends JpaRepository<SlaReports, String>  {

	Page<SlaReportResponse> findByModuleAndReportNameIn(String module,List<String> reportList,Pageable pageable);
	List<SlaReportResponse> findByModuleAndReportNameIn(String module,List<String> reportList);
	Optional<SlaReportResponse>  findByModuleAndReportName(String module,String reportName);
	List<SlaReportResponse> findAllSLAReportsByReportNameIn(List<String> reportList);

	Page<SlaReportResponse> findAllSLAReportsByReportNameIn(List<String> reportList,Pageable pageable);
//	
//	@Query("Select new com.envision.sladashboard.database.projections.SLAReportResponse(s.module, s.reportName, s.rfpNoType, s.timeframe, s.path, s.isActive, s.reportType) from sla_reports s where s.rfpNoType like %:rfpNo% and s.reportName like %:reportName%")
//	Page<SLAReportResponse> findAllSLAReportswithFilter(Pageable pageable,@Param("rfpNo")String rfpNo,@Param("reportName")String ReportName);
//
//	@Query("Select new com.envision.sladashboard.database.projections.SLAReportResponse(s.module, s.reportName, s.rfpNoType, s.timeframe, s.path, s.isActive, s.reportType) from sla_reports s where s.module=:module and ( s.rfpNoType like %:rfpNo% and s.reportName like %:reportName%)")
//	Page<SLAReportResponse> findByModulewithFilter(@Param("module")String module,Pageable pageable,@Param("rfpNo")String rfpNo,@Param("reportName")String ReportName);
	
	@Query("Select new com.envision.sladashboard.database.projections.SlaReportResponse(s.module, s.reportName, s.rfpNoType, s.timeframe, s.path, s.isActive, s.reportType) from sla_reports s where s.rfpNoType like %:rfpNo% and s.reportName like %:reportName%  and s.reportName in :reportList " )
	Page<SlaReportResponse> findAllSLAReportswithFilter(Pageable pageable,@Param("rfpNo")String rfpNo,@Param("reportName")String ReportName,@Param("reportList") List<String> reportList);

	@Query("Select new com.envision.sladashboard.database.projections.SlaReportResponse(s.module, s.reportName, s.rfpNoType, s.timeframe, s.path, s.isActive, s.reportType) from sla_reports s where s.module=:module and ( s.rfpNoType like %:rfpNo% and s.reportName like %:reportName%)  and s.reportName in :reportList ")
	Page<SlaReportResponse> findByModulewithFilter(@Param("module")String module,Pageable pageable,@Param("rfpNo")String rfpNo,@Param("reportName")String ReportName,@Param("reportList")List<String> reportList);

}
