package com.envision.common;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.envision.common.exception.DataException;
import com.envision.common.exception.DataNotFoundException;
import com.envision.common.exception.UserNotFoundException;
import com.envision.common.modal.ErrorResponse;
import com.envision.common.modal.RestResponse;

import io.jsonwebtoken.SignatureException;

@RestControllerAdvice
public class ExceptionHandlerControllerAdvice  {
	
	@ExceptionHandler(BadCredentialsException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public RestResponse<Object>  handleEntityNotFoundException(BadCredentialsException exception) {
	    // This method returns an empty response body
		 final RestResponse<Object> output = new RestResponse<>();
		 output.setMessage(exception.getMessage());
		 output.setHttpcode(HttpStatus.UNAUTHORIZED.value());
		 output.setStatus("Failed");
		 return output;
	}	
	@ExceptionHandler(SignatureException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public RestResponse<Object>  handleSignatureException(SignatureException exception) {
	    // This method returns an empty response body
		 final RestResponse<Object> output = new RestResponse<>();
		 output.setMessage(exception.getMessage());
		 output.setHttpcode(HttpStatus.UNAUTHORIZED.value());
		 output.setStatus("Failed");
		 return output;
	}
	@ExceptionHandler(UserNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public  RestResponse<Object> handleResourceNotFound(final UserNotFoundException exception,
			final HttpServletRequest request) {
		 final RestResponse<Object> output = new RestResponse<>();
		 output.setMessage(exception.getMessage());
		 output.setHttpcode(HttpStatus.NOT_FOUND.value());
		 output.setStatus("Failed");
		 return output;
	}

	@ExceptionHandler(DataNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public  RestResponse<Object> handleResourceNotFound(final DataNotFoundException exception,
			final HttpServletRequest request) {

		 final RestResponse<Object> output = new RestResponse<>();
		 output.setMessage(exception.getMessage());
		 output.setHttpcode(HttpStatus.NOT_FOUND.value());
		 output.setStatus("Failed");
		 return output;
	}


	@ExceptionHandler(AccessDeniedException.class)
	@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
	public  RestResponse<Object> handleAccessDenyed(final AccessDeniedException exception,
			final HttpServletRequest request) {
		 final RestResponse<Object> output = new RestResponse<>();
		 output.setMessage(exception.getMessage());
		 output.setHttpcode(HttpStatus.UNAUTHORIZED.value());
		 output.setStatus("Failed");
		 return output;
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public  RestResponse<Object> handleException(final Exception exception,
			final HttpServletRequest request) {

		 final RestResponse<Object> output = new RestResponse<>();
		 output.setMessage("Server error");
		 output.setHttpcode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		 output.setStatus("Failed");
		 return output;
	}
	

	@ExceptionHandler(DataException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public  RestResponse<Object> handleException(final DataException exception,
			final HttpServletRequest request) {

		 final RestResponse<Object> output = new RestResponse<>();
		 output.setMessage("Data Format Incorrect");
		 output.setHttpcode(HttpStatus.BAD_REQUEST.value());
		 output.setStatus("Failed");
		 return output;
	}
	
}