package com.xiao.gs.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
 * 至少添加一个IP，如果设置了"client.transport.sniff"= true 一个就够了，因为开启了自动嗅探配置
 *
 * @author luoxiaoxiao
 */
@Configuration
@EnableElasticsearchRepositories(basePackages = "com.xiao.gs.data.elasticsearch.repository")
public class ElasticsearchConfig {

}
