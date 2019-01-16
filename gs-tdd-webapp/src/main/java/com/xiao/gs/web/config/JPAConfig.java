package com.xiao.gs.web.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaAuditing
@EnableTransactionManagement
@EntityScan(basePackages = "com.xiao.gs.data.jpa.domain")
@EnableJpaRepositories(basePackages = "com.xiao.gs.data.jpa.repository")
public class JPAConfig {

}
