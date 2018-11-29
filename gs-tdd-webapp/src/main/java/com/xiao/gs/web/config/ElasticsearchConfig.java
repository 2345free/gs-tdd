package com.xiao.gs.web.config;

import org.elasticsearch.client.transport.TransportClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
 * 至少添加一个IP，如果设置了"client.transport.sniff"= true 一个就够了，因为开启了自动嗅探配置
 *
 * @author luoxiaoxiao
 */
@Configuration
@EnableElasticsearchRepositories(basePackages = "com.xiao.gs.data.elasticsearch.repository")
public class ElasticsearchConfig {

    private final TransportClient transportClient;

    public ElasticsearchConfig(TransportClient transportClient) {
        this.transportClient = transportClient;
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                transportClient.close();
            }
        });
    }

    @Bean
    public ElasticsearchTemplate elasticsearchTemplate() {
        return new ElasticsearchTemplate(transportClient);
    }

}
