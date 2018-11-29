package com.xiao.gs.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.xiao.gs.data.jpa.repository")
public class JPAConfig {

}
