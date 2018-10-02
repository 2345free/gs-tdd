package com.xiao.gs.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * mvc配置
 *
 * @author luoxiaoxiao
 */
@Configuration
@ComponentScan(basePackages = "com.xiao.gs.controller")
public class WebMvcConfig implements WebMvcConfigurer {

}
