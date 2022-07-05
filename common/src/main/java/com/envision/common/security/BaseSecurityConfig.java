package com.envision.common.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;

@Configuration
public abstract class BaseSecurityConfig implements IBaseSecurityConfig {

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        // TODO Any defaults

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // TODO Any defaults

    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        // TODO Any defaults

    }
}