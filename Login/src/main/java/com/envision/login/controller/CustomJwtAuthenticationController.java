package com.envision.login.controller;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.envision.login.JwtTokenUtil;
import com.envision.login.LoginUtilities;
import com.envision.login.model.JwtRequest;
import com.envision.login.model.JwtResponse;
import com.envision.login.service.JWTUserDetailsServiceImpl;

@RestController
@CrossOrigin(origins = "*")
public class CustomJwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	@Qualifier("JWTUserDetailsService")
	private JWTUserDetailsServiceImpl jwtuserDetailsService;
	

	
	@Autowired
	LoginUtilities loginUtilities;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public JwtResponse generateAuthenticationToken(@RequestBody JwtRequest authenticationRequest)
			throws Exception {

		Authentication authentication=authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		final UserDetails userDetails = jwtuserDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails,authentication);
		final String roles = jwtTokenUtil.generateRolesToken(userDetails,authentication);

		return new JwtResponse(token,roles);
	}

	private Authentication authenticate(String username, String password) throws Exception {
		Objects.requireNonNull(username);
		Objects.requireNonNull(password);
		try {
			Authentication authentication= authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			return authentication;
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}

	@GetMapping("logout")
	public void userLogout(Authentication authentication) {
	
		loginUtilities.removeCachedUser(authentication.getName());
	}
}