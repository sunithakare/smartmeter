package com.envision.login.security;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.envision.common.security.BaseSecurityConfig;
import com.envision.login.auth.DirectPasswordEnconder;

@Configuration
/**
 * @author abishek.chandran
 *
 */
public class LoginSecurityConfig extends BaseSecurityConfig  {
	@Autowired
	@Qualifier("LoginUserDetailsService")
	private UserDetailsService userDetailsService;

	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	
	@Autowired
	private JwtRequestFilter jwtRequestFilter;

//	public CustomWebSecurityConfig(UserDetailsService userDetailsService) {
//		super();
//		this.userDetailsService = userDetailsService;
//	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
		// Setting Service to find User in the database.
		// And Setting PassswordEncoder
//		auth.inMemoryAuthentication().withUser("admin")
//			.password(passwordEncoder().encode("admin")).authorities("ADMIN").roles("ADMIN");
//			auth.authenticationProvider(authenticationProvider());

//		UserDetails userDetails = User.withUsername("ADMIN").password(passwordEncoder().encode("ADMIN")).roles("ADMIN")
//				.build();
//		auth.inMemoryAuthentication().withUser(userDetails);
		
		
	}
//
//	@Bean
//	public CustomAuthenticationSuccessHandler myAuthenticationSuccessHandler() {
//		return new CustomAuthenticationSuccessHandler();
//	}
//
//	@Bean
//	public CustomAuthenticationFailureHandler myAuthenticationFailureHandler() {
//		return new CustomAuthenticationFailureHandler();
//	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
//		http.csrf().disable();
		http.csrf();
		http.csrf().ignoringAntMatchers("/authenticate");
		http.csrf().ignoringAntMatchers("/generateOtp");
		http.csrf().ignoringAntMatchers("/validateOtpAndLogin");
		http.cors().configurationSource(corsConfigurationSource());
		
//		http.cors().configurationSource(new CorsConfigurationSource() {
//
//		    @Override
//		    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
//		        CorsConfiguration config = new CorsConfiguration();
//		        config.setAllowedHeaders(Collections.singletonList("*"));
//		        config.setAllowedMethods(Collections.singletonList("*"));
//		        config.addAllowedOrigin("localhost");
//		        config.setAllowCredentials(true);
//		        return config;
//		    }
//		});
		 http.authorizeRequests().requestMatchers(CorsUtils::isPreFlightRequest).permitAll();
		http.authorizeRequests().antMatchers("/","/**.js","/**.html", "/**.ico","/**.css", "/error").permitAll();

//		http.authorizeRequests().antMatchers("/getlist").denyAll();

		http.authorizeRequests().antMatchers("/authenticate").permitAll();
		http.authorizeRequests().antMatchers("/generateOtp").permitAll();
		http.authorizeRequests().antMatchers("/validateOtpAndLogin").permitAll()
		.and().exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http.httpBasic().disable();

		

		// Add a filter to validate the tokens with every request
		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
		CookieCsrfTokenRepository csrfRepo = CookieCsrfTokenRepository.withHttpOnlyFalse();
		csrfRepo.setSecure(false);
		csrfRepo.setCookiePath("/");
		http.csrf()
        .csrfTokenRepository(csrfRepo);

	}

	@Bean
	public SessionRegistry sessionRegistry() {

		return new SessionRegistryImpl();
	}

	@Bean
	public ServletListenerRegistrationBean<HttpSessionEventPublisher> httpSessionEventPublisher() {
		return new ServletListenerRegistrationBean<HttpSessionEventPublisher>(new HttpSessionEventPublisher());
	}

	@Bean
	DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setPasswordEncoder(passwordEncoder());
//		authenticationProvider.setPasswordEncoder(new DirectPasswordEnconder());
		authenticationProvider.setUserDetailsService(userDetailsService);

		return authenticationProvider;
	}

	@Bean
	PasswordEncoder passwordEncoder() {
//		return new DirectPasswordEnconder();

	 return new BCryptPasswordEncoder();
	}

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
//        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200","https://amidashboard1.eeslsmartmeter.in/"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type", "X-XSRF-TOKEN", "X-CSRF-TOKEN"));
        configuration.setExposedHeaders(Arrays.asList("X-XSRF-TOKEN","X-CSRF-TOKEN"));
        configuration.setAllowedOriginPatterns(Arrays.asList("*"));      
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
    
    @Bean
    public AuditorAware<String> auditorProvider() {
        return new AuditorAware<String>() {
            @Override
            public Optional<String> getCurrentAuditor() {
                if (SecurityContextHolder.getContext().getAuthentication() != null) {
                    return  Optional.of( SecurityContextHolder.getContext().getAuthentication().getName());
                } else {
                    return  Optional.of("PUBLIC USER");
                }
            }
        };
    }
    
}