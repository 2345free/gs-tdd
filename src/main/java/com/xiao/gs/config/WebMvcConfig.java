package com.xiao.gs.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author luoxiaoxiao
 */
@EnableWebMvc
@Configuration
@ComponentScan(basePackages = "com.xiao.gs.controller")
public class WebMvcConfig extends WebMvcConfigurationSupport {

}
