package com.envision.useraccessmanagement.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import com.envision.common.security.BaseSecurityConfig;

@Configuration
public class UAMWebSecurity extends BaseSecurityConfig  {
	@Override
	public void configure(HttpSecurity http) throws Exception {
		
//		http.csrf().ignoringAntMatchers("/uam/register");getid
		http.csrf().ignoringAntMatchers("/uam/register");
		http.authorizeRequests().antMatchers("/uam/register").permitAll();
		http.csrf().ignoringAntMatchers("/uam/getid/**");
		http.authorizeRequests().antMatchers("/uam/getid/**").permitAll();
		http.authorizeRequests().antMatchers("/uam/getnextapprover/**").permitAll();
	}

}
