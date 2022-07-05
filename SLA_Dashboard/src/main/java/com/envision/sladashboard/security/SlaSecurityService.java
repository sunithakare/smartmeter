package com.envision.sladashboard.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.envision.sladashboard.SlaConstants;
import com.envision.sladashboard.modal.ReportDownloadRequest;

import lombok.extern.slf4j.Slf4j;
@Component
@Slf4j
public class SlaSecurityService {


		public boolean canSLADownloaded(Authentication authentication,ReportDownloadRequest downloadRequest) {
			log.debug("inside SlaSecurityService:");
			for (GrantedAuthority grantedAuthority : authentication.getAuthorities()) {
				log.debug("inside SlaSecurityService:grantedAuthority");
					if (grantedAuthority.getAuthority().equalsIgnoreCase(SlaConstants.REPORT_ACCESS_NAME+"_"+downloadRequest.getReportName())) {

						log.debug("inside SlaSecurityService:grantedAuthority"+grantedAuthority.getAuthority());
						return true;
					}
		
			}

			return false;

		}
		
}
