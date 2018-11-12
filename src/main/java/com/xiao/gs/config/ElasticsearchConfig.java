package com.xiao.gs.config;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.NodeClientFactoryBean;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.UUID;

/**
 * @author luoxiaoxiao
 */
@Configuration
@EnableElasticsearchRepositories(basePackages = "com.xiao.gs.data.elasticsearch.repository")
public class ElasticsearchConfig {

    /**
     * Embedded Node Client
     */
//    @Bean
    public NodeClientFactoryBean client() {
        NodeClientFactoryBean bean = new NodeClientFactoryBean(true);
        bean.setClusterName(UUID.randomUUID().toString());
        bean.setEnableHttp(false);
        bean.setPathData("build/elasticsearchTestData");
        bean.setPathHome("src/test/resources/test-home-dir");
        return bean;
    }

    /**
     * 至少添加一个IP，如果设置了"client.transport.sniff"= true 一个就够了，因为开启了自动嗅探配置
     */
    @Bean(destroyMethod = "close")
    public TransportClient transportClient() throws UnknownHostException {
        TransportClient client = new PreBuiltTransportClient(Settings.builder()
                .put("cluster.name", "elasticsearch")
//                .put("client.transport.sniff", true) // 适用于集群节点
                .build());
        client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
        return client;
    }

    @Bean
    public ElasticsearchTemplate elasticsearchTemplate() throws UnknownHostException {
        return new ElasticsearchTemplate(transportClient());
    }

}
