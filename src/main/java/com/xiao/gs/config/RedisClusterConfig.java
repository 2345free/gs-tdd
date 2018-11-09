package com.xiao.gs.config;

import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

import java.util.HashSet;
import java.util.Set;

/**
 * spring 集群配置
 *
 * @author luoxiaoxiao
 */
//@Configuration
@EnableConfigurationProperties(RedisProperties.class)
public class RedisClusterConfig {

    private final RedisProperties redisProperties;

    public RedisClusterConfig(RedisProperties redisProperties) {
        this.redisProperties = redisProperties;
    }

    @Bean
    public RedisClusterConfiguration redisClusterConfiguration() {
        RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration();
        Set<RedisNode> nodes = new HashSet<>();
        for (String hostInfoStr : redisProperties.getCluster().getNodes()) {
            String[] hostInfo = hostInfoStr.split(":");
            nodes.add(new RedisNode(hostInfo[0], Integer.parseInt(hostInfo[1])));
        }
        redisClusterConfiguration.setClusterNodes(nodes);
        return redisClusterConfiguration;
    }

    @Primary
    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        return new JedisConnectionFactory(redisClusterConfiguration());
    }

}
