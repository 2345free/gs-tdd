package com.xiao.gs.web.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

/**
 * @author luoxiaoxiao
 * @date 2018-12-01 16:59
 */
@Configuration
@EnableFeignClients(basePackages = "com.xiao.gs.client.feign")
public class FeignConfig {

}
