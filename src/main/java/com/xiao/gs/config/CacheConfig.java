package com.xiao.gs.config;

import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerUtils;
import org.springframework.cache.support.CompositeCacheManager;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author luoxiaoxiao
 */
@EnableCaching
@Configuration
@EnableConfigurationProperties(CacheProperties.class)
public class CacheConfig {

    private final CacheProperties cacheProperties;

    public CacheConfig(CacheProperties cacheProperties) {
        this.cacheProperties = cacheProperties;
    }

    @Primary
    @Bean
    public CacheManager compositeCacheManager() {
        CompositeCacheManager cacheManager = new CompositeCacheManager(simpleCacheManager(), ehCacheCacheManager());
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

//    @Bean
//    public RedisCacheManager redisCacheManager(RedisCacheWriter cacheWriter, RedisCacheConfiguration cacheConfiguration) {
//        RedisCacheManager cacheManager = new RedisCacheManager(cacheWriter, cacheConfiguration);
//        return cacheManager;
//    }

    @Bean
    public EhCacheCacheManager ehCacheCacheManager() {
        Resource location = cacheProperties.resolveConfigLocation(cacheProperties.getEhcache().getConfig());
        return new EhCacheCacheManager(EhCacheManagerUtils.buildCacheManager(location));
    }

}
