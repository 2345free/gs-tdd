package com.xiao.gs.config;

import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerUtils;
import org.springframework.cache.support.CompositeCacheManager;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import static org.springframework.data.redis.cache.RedisCacheConfiguration.defaultCacheConfig;

/**
 * @author luoxiaoxiao
 */
@EnableCaching
@Configuration
@EnableConfigurationProperties(CacheProperties.class)
public class CacheManagerConfig {

    private final CacheProperties cacheProperties;

    public CacheManagerConfig(CacheProperties cacheProperties) {
        this.cacheProperties = cacheProperties;
    }

    /**
     * 混合缓存管理
     */
    @Primary
    @Bean
    public CacheManager compositeCacheManager(RedisCacheManager redisCacheManager) {
        CompositeCacheManager cacheManager = new CompositeCacheManager();
        cacheManager.setCacheManagers(Arrays.asList(
                simpleCacheManager(),
                ehCacheCacheManager(),
                caffeineCacheManager(),
                redisCacheManager
        ));
        cacheManager.setFallbackToNoOpCache(true);
        return cacheManager;
    }

    @Bean
    public SimpleCacheManager simpleCacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        Set<Cache> caches = new HashSet<>();
        caches.add(new ConcurrentMapCache("map1", new ConcurrentHashMap<>(500), false));
        caches.add(new ConcurrentMapCache("map2", false));
        caches.add(new ConcurrentMapCache("map3"));
        cacheManager.setCaches(caches);
        return cacheManager;
    }

    @Bean
    public RedisCacheManager redisCacheManager(RedisConnectionFactory redisConnectionFactory) {
        /*
         *  使用默认配置: return RedisCacheManager.create(redisConnectionFactory)
         */
        Map<String, RedisCacheConfiguration> cacheConfigurations = new HashMap<>(1);
        return RedisCacheManager.builder(redisConnectionFactory)
                .cacheDefaults(defaultCacheConfig()
                        .entryTtl(Duration.ofSeconds(1))
                        .disableCachingNullValues())
                .withInitialCacheConfigurations(cacheConfigurations)
                .transactionAware()
                .build();
    }

    @Bean
    public EhCacheCacheManager ehCacheCacheManager() {
        Resource resource = cacheProperties.resolveConfigLocation(cacheProperties.getEhcache().getConfig());
        return new EhCacheCacheManager(EhCacheManagerUtils.buildCacheManager(resource));
    }

    @Bean
    public CaffeineCacheManager caffeineCacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        cacheManager.setCacheNames(Arrays.asList("caffeine1", "caffeine2"));
        return cacheManager;
    }

}
