package com.envision.login.database.projections;

import org.springframework.beans.factory.annotation.Value;

public interface UserDiscomData {
	String getDiscomName();
	boolean getIsdefault() ;
	@Value("#{target.discomDetails.state}")
	String getState();
}