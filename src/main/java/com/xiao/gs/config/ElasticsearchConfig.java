package com.xiao.gs.config;

import com.xiao.gs.util.Symbols;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
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
@EnableConfigurationProperties(ElasticsearchProperties.class)
@EnableElasticsearchRepositories(basePackages = "com.xiao.gs.data.elasticsearch.repository")
public class ElasticsearchConfig {

    private final ElasticsearchProperties esProperties;

    public ElasticsearchConfig(ElasticsearchProperties esProperties) {
        this.esProperties = esProperties;
    }

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
                .put("cluster.name", esProperties.getClusterName())
                .put(esProperties.getProperties())
                .build());
        for (String serverInfoStr : esProperties.getClusterNodes().split(Symbols.COMMA)) {
            String[] serverInfo = serverInfoStr.split(Symbols.COLON);
            client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(serverInfo[0]), Integer.parseInt(serverInfo[1])));
        }
        return client;
    }

    @Bean
    public ElasticsearchTemplate elasticsearchTemplate() throws UnknownHostException {
        return new ElasticsearchTemplate(transportClient());
    }

}
