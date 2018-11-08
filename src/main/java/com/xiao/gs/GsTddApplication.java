package com.xiao.gs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * test-driven-development(测试驱动开发模式)
 *
 * @author luoxiaoxiao
 */
@SpringBootApplication
public class GsTddApplication {

    public static void main(String[] args) {
        SpringApplication.run(GsTddApplication.class, args);
    }
}
