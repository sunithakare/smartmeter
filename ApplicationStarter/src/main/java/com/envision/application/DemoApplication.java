package com.envision.application;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.envision.login.security.SecurityConfig;

@SpringBootApplication
//@EnableAutoConfiguration(exclude = {ErrorMvcAutoConfiguration.class})
@EnableAutoConfiguration
@EntityScan("com.envision")
@EnableJpaRepositories(basePackages="com.envision")
@ComponentScan(value="com.envision",excludeFilters = @ComponentScan.Filter(type=FilterType.ASSIGNABLE_TYPE,classes=WebSecurityConfigurerAdapter.class))
@Import(SecurityConfig.class)
@EnableBatchProcessing
public class DemoApplication extends SpringBootServletInitializer {
	 @Override
	  protected SpringApplicationBuilder configure(SpringApplicationBuilder builder){
	    return builder.sources(DemoApplication.class);
	  }

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}

