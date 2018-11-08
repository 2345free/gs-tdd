package com.xiao.gs.config;

import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.HashSet;
import java.util.Set;

/**
 * spring 集成 redis配置,代码配置方式
 *
 * @author luoxiaoxiao
 */
@Configuration
@EnableConfigurationProperties(RedisProperties.class)
public class RedisConfig {

    private final RedisProperties redisProperties;

    public RedisConfig(RedisProperties redisProperties) {
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

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
//        return new JedisConnectionFactory(redisClusterConfiguration());
        // redis单机模式
        String[] hostInfo = redisProperties.getCluster().getNodes().get(0).split(":");
        return new JedisConnectionFactory(new RedisStandaloneConfiguration(hostInfo[0], Integer.parseInt(hostInfo[1])));
    }

    @Bean
    public RedisTemplate<String, ?> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, ?> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        template.setKeySerializer(keySerializer());
        template.setValueSerializer(valueSerializer());
        return template;
    }

    @Bean
    public StringRedisTemplate stringRedisTemplate(JedisConnectionFactory jedisConnectionFactory) {
        StringRedisTemplate template = new StringRedisTemplate();
        template.setConnectionFactory(jedisConnectionFactory);
        template.setKeySerializer(keySerializer());
        template.setValueSerializer(valueSerializer());
        return template;
    }

    @Bean
    public RedisSerializer keySerializer() {
        return new StringRedisSerializer();
    }

    @Bean
    public RedisSerializer valueSerializer() {
        return new GenericJackson2JsonRedisSerializer();
    }

}
