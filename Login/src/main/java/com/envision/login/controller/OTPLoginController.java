package com.envision.login.controller;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.envision.login.JwtTokenUtil;
import com.envision.login.LoginUtilities;
import com.envision.login.model.JwtRequest;
import com.envision.login.model.JwtResponse;
import com.envision.login.model.OtpLoginRequest;
import com.envision.login.service.JWTUserDetailsServiceImpl;
import com.envision.login.service.OtpService;

@RestController
@CrossOrigin(origins = "*")
public class OTPLoginController {



	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	@Qualifier("JWTUserDetailsService")
	private JWTUserDetailsServiceImpl jwtuserDetailsService;
	
	@Autowired
	LoginUtilities loginUtilities;
	
	@Autowired
	OtpService otpService;
	

	@PostMapping("generateOtp")
	private void generateOtpForLogin(@RequestBody OtpLoginRequest otpReq) {
		final UserDetails userDetails = jwtuserDetailsService
				.loadUserByMobileNo(otpReq.getMobileNo());
		otpService.generateLoginOtp(otpReq.getMobileNo());

	}
	
	@RequestMapping(value = "/validateOtpAndLogin", method = RequestMethod.POST)
	public JwtResponse generateAuthenticationToken(@RequestBody OtpLoginRequest otpReq)
			throws Exception {

		try {
			final UserDetails userDetails = jwtuserDetailsService
					.loadUserByMobileNo(otpReq.getMobileNo());
			Authentication authentication=authenticateOtp(otpReq.getMobileNo(), otpReq.getOtp(),userDetails);

//		final UserDetails userDetails = jwtuserDetailsService
//				.loadUserByMobileNo(otpReq.getMobileNo());

			final String token = jwtTokenUtil.generateToken(userDetails,authentication);
			final String roles = jwtTokenUtil.generateRolesToken(userDetails,authentication);

			return new JwtResponse(token,roles);
		} catch (Exception e) {

throw new BadCredentialsException("Otp Invalid");
		}
	}

	private Authentication authenticateOtp(String mobileNo, String otp,UserDetails userDetails) throws Exception {
		Objects.requireNonNull(mobileNo);
		Objects.requireNonNull(otp);
		
		otpService.validateOpt(mobileNo, Long.valueOf(otp));
		
		try {
			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails,
                    null, userDetails.getAuthorities());
//			Authentication authentication= authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, null));
			return authentication;
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}

}