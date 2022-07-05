package com.envision.common.security;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;

public interface IBaseSecurityConfig {

    void configure(AuthenticationManagerBuilder auth) throws Exception;
    void configure(WebSecurity web) throws Exception;
    void configure(HttpSecurity http) throws Exception;

}
