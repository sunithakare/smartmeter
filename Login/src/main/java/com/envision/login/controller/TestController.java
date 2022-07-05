//package com.envision.login.controller;
//import static org.springframework.web.servlet.function.RouterFunctions.route;
//import static org.springframework.web.servlet.function.ServerResponse.ok;
//
//import java.util.HashSet;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.http.MediaType;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.function.RouterFunction;
//import org.springframework.web.servlet.function.ServerResponse;
//
//import com.database.AccountRepository;
//import com.envision.login.database.dao.SystemRoleDAO;
//import com.envision.login.database.dao.UserDAO;
//import com.envision.login.database.objects.SystemRoleTbl;
//import com.envision.login.database.objects.User;
//@Component
//public class TestController {
////	@Autowired;
////	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
////
////		// Setting Service to find User in the database.
////		// And Setting PassswordEncoder
////	auth.inMemoryAuthentication().withUser("admin")
////		.password(passwordEncoder().encode("admin")).authorities("ADMIN").roles("ADMIN");
//////		auth.authenticationProvider(authenticationProvider());
////
////
////	}
//
//	@Bean
//	public RouterFunction<ServerResponse> productListing() {
//	    return route().POST("/home", req -> ok().body("Success"))
//	      .build();
//	}
//
//
//	@Bean
//	public RouterFunction<ServerResponse> getListing() {
//		String json=" {\"data\": [{ \"position\": 1, \"name\": \"John\", \"email\": \"john@gmail.com\" },"
//	    		+ "  { \"position\": 2, \"name\": \"Herry\", \"email\": \"herry@gmail.com\" },"
//	    		+ "  { \"position\": 3, \"name\": \"Ann\", \"email\": \"ann@gmail.com\" },"
//	    		+ "  { \"position\": 4, \"name\": \"Johnny\", \"email\": \"johnny@gmail.com\" },"
//	    		+ "  { \"position\": 5, \"name\": \"Roy\", \"email\": \"roy@gmail.com\" },"
//	    		+ "  { \"position\": 6, \"name\": \"Kia\", \"email\": \"kia@gmail.com\" },"
//	    		+ "  { \"position\": 7, \"name\": \"Merry\", \"email\": \"merry@gmail.com\" },"
//	    		+ "  { \"position\": 8, \"name\": \"William\", \"email\": \"william@gmail.com\" },"
//	    		+ "  { \"position\": 9, \"name\": \"Shia\", \"email\": \"shia@gmail.com\" },"
//	    		+ "  { \"position\": 10, \"name\": \"Kane\", \"email\": \"kane@gmail.com\" },"
//	    		+ "  { \"position\": 2, \"name\": \"Herry\", \"email\": \"herry@gmail.com\" },"
//	    		+ "  { \"position\": 3, \"name\": \"Ann\", \"email\": \"ann@gmail.com\" },"
//	    		+ "  { \"position\": 4, \"name\": \"Johnny\", \"email\": \"johnny@gmail.com\" },"
//	    		+ "  { \"position\": 5, \"name\": \"Roy\", \"email\": \"roy@gmail.com\" },"
//	    		+ "  { \"position\": 6, \"name\": \"Kia\", \"email\": \"kia@gmail.com\" },"
//	    		+ "  { \"position\": 7, \"name\": \"Merry\", \"email\": \"merry@gmail.com\" },"
//	    		+ "  { \"position\": 8, \"name\": \"William\", \"email\": \"william@gmail.com\" },"
//	    		+ "  { \"position\": 9, \"name\": \"Shia\", \"email\": \"shia@gmail.com\" },"
//	    		+ "  { \"position\": 10, \"name\": \"Kane\", \"email\": \"kane@gmail.com\" }]}";
//		
//		
//		
//	    return route().POST("/getlist", req -> ok().contentType(MediaType.APPLICATION_JSON).body(json))
//	      .build();
//	}
//	
//	@Autowired
//	AccountRepository accountRepo;
//
//	@Autowired
//	SystemRoleDAO roleDAO;
//	@Autowired
//	UserDAO userDAO;
//	
//	@Bean
//	public RouterFunction<ServerResponse> roledao() {
//
//	    return route().GET("/userdao", req -> ok().body(userDAO.findAll()))
//	      .build();
//	}
//	@Bean
//	public RouterFunction<ServerResponse> userdao() {
//	    return route().GET("/roledao", req -> ok().body(roleDAO.findAll()))
//	      .build();
//	}
//	@Bean
//	public RouterFunction<ServerResponse> getAllUser() {
//	    return route().GET("/getallUsers", req -> ok().body(accountRepo.findAllBy()))
//	      .build();
//	}
//	
//	
//
//
//	
////	 @Bean
////	 PasswordEncoder passwordEncoder() {
////			 return new DirectPasswordEnconder();
////
//////	 return new BCryptPasswordEncoder();
////	 }
////	 
//}
