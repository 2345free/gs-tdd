package com.xiao.gs.web.config;

import com.xiao.gs.web.codec.MyPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 监控端点接口安全设置
 *
 * @author luoxiaoxiao
 */
@Profile("monitor")
@Order(2)
@Configuration
public class EndpointSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String MGD_ROLE = "MGD-ADMIN";

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .requestMatcher(EndpointRequest.toAnyEndpoint().excluding("health"))
                .authorizeRequests()
                .anyRequest().hasRole(MGD_ROLE)
                .and()
                .httpBasic();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                // 保护监控端点接口(sba server调用端点接口时的用户凭证)
                .inMemoryAuthentication()
                .passwordEncoder(passwordEncoder())
                .withUser("sba").password("mgd@sba").roles(MGD_ROLE);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new MyPasswordEncoder();
    }

}
