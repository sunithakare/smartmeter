package com.envision.common;

import javax.servlet.http.HttpServletResponse;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.envision.common.modal.RestResponse;

@ControllerAdvice
public class CustomResponseAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter methodParam, Class<? extends HttpMessageConverter<?>> converterType) {
    	
        return methodParam.hasMethodAnnotation(ResponseBody.class);
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
            Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
        ServerHttpResponse response) {
    	
    	HttpServletResponse servletResponse = ((ServletServerHttpResponse) response).getServletResponse();

    	
        // TODO Auto-generated method stub
        final RestResponse<Object> output = new RestResponse<>();
        output.setData(body);
        output.setHttpcode(servletResponse.getStatus());
        output.setStatus(servletResponse.getStatus()==200?"Success":"Failed");
        return output;
    }
}