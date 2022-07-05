package com.envision.login.service;

import javax.security.auth.login.CredentialExpiredException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class OtpService {

	@Autowired
	CacheManager cacheManager;
	
	@Cacheable(value="otpCache", key = "'Login:'+#mobileNo")
	public Long generateLoginOtp(String mobileNo) {
		
		return 123456L;
	}
	

	@CacheEvict(value="otpCache", key = "'Login:'+#mobileNo")
	public void validateOpt(String mobileNo,Long otp) throws CredentialExpiredException {
		// TODO Auto-generated method stub
		Cache cache = cacheManager.getCache("otpCache");
//		cache.get("Login:"+mobileNo).get()
		Long cacheValue=(Long) cache.get("Login:"+mobileNo).get();
		if (!cacheValue.equals(otp)) {
			throw new CredentialExpiredException("Otp Invalid");
		}
		
	}
}
