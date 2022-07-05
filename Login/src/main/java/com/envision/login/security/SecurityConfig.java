package com.envision.login.security;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.envision.common.security.IBaseSecurityConfig;


@Configuration

@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)

//@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
@EnableJpaAuditing
@EnableTransactionManagement
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private List<IBaseSecurityConfig> securityConfigs;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        for(IBaseSecurityConfig secConfig : securityConfigs) {
            secConfig.configure(auth);          
        }
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        for(IBaseSecurityConfig secConfig : securityConfigs) {
            secConfig.configure(web);
        }

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        for(IBaseSecurityConfig secConfig : securityConfigs) {
            secConfig.configure(http);
        }

 		http.authorizeRequests().anyRequest().authenticated();
 		
// 		http.exceptionHandling().accessDeniedHandler(new AccessDeniedHandler() {
//			
//			@Override
//			public void handle(HttpServletRequest request, HttpServletResponse response,
//					AccessDeniedException accessDeniedException) throws IOException, ServletException {
//				// TODO Auto-generated method stub
//				System.out.println("###############################################################################");
//				System.out.println("###############################################################################");
//				System.out.println("###############################################################################");
//				System.out.println("###############################################################################");
//				System.out.println("###############################################################################");
//				System.out.println("###############################################################################");
//				System.out.println("###############################################################################");
//				response.setStatus(HttpStatus.NOT_FOUND.value());
//			}
//		});
    }
    
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
}