package com.xiao.gs.web.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xiao.gs.web.bind.support.CurentUserArgumentResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * mvc配置
 *
 * @author luoxiaoxiao
 */
@Configuration
@EnableSpringDataWebSupport
@EnableAspectJAutoProxy(proxyTargetClass = true)
@ComponentScan(basePackages = "com.xiao.gs.web.controller")
public class WebMvcConfig implements WebMvcConfigurer {

    @Bean
    public HttpMessageConverter httpMessageConverter() {
        MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
        messageConverter.setObjectMapper(objectMapper);
        return messageConverter;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(curentUserArgumentResolver());
    }

    @Bean
    public CurentUserArgumentResolver curentUserArgumentResolver() {
        return new CurentUserArgumentResolver();
    }

}
