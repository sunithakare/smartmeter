package com.envision.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserNotFoundException extends UsernameNotFoundException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8126152977410658320L;

	public UserNotFoundException(String msg) {
		super(msg);
	}

	public UserNotFoundException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
