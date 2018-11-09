package com.xiao.gs.config;

import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

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
    public RedisConnectionFactory redisConnectionFactory() {
        // redis单机模式
        return new JedisConnectionFactory(new RedisStandaloneConfiguration(redisProperties.getHost(), redisProperties.getPort()));
    }

    /**
     * @param connectionFactory 作为参数传进来而不是调用方法是为了方便切换到redis集群模式(启用RedisClusterConfig即可)
     */
    @Bean
    public RedisTemplate<String, ?> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, ?> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        template.setKeySerializer(keySerializer());
        template.setValueSerializer(valueSerializer());
        return template;
    }

    @Bean
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory connectionFactory) {
        StringRedisTemplate template = new StringRedisTemplate();
        template.setConnectionFactory(connectionFactory);
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
        return new JdkSerializationRedisSerializer();
    }

}
