package com.xiao.gs.config;

import com.xiao.gs.codec.MyPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/h2-console", "/api/user/**").authenticated()
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .and()
                .httpBasic()
                .and()
                .csrf().disable();
        // h2-console用到了iframe
        http.headers().frameOptions().sameOrigin();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                // 自定义登录认证(认证信息在内存)
                .inMemoryAuthentication()
                .passwordEncoder(passwordEncoder())
                .withUser("luoxx").password("123").roles("ADMIN");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new MyPasswordEncoder();
    }

}
