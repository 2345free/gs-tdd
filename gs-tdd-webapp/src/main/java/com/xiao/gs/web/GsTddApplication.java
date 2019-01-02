package com.xiao.gs.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchAutoConfiguration;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchDataAutoConfiguration;

/**
 * test-driven-development(测试驱动开发模式)
 *
 * @author luoxiaoxiao
 */
@SpringBootApplication(exclude = {ElasticsearchAutoConfiguration.class, ElasticsearchDataAutoConfiguration.class})
public class GsTddApplication {

    public static void main(String[] args) {
        SpringApplication.run(GsTddApplication.class, args);
    }

}
