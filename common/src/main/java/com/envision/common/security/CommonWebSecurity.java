package com.envision.common.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
public class CommonWebSecurity extends BaseSecurityConfig  {
	@Override
	public void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests().antMatchers("/common/**").permitAll();
	}

}
