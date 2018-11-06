package com.xiao.gs.controller.api;

import com.xiao.gs.data.domain.User;
import com.xiao.gs.model.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author luoxiaoxiao
 */
@Slf4j
@RestController
@RequestMapping("/api/cache")
public class ApiCacheController {

    private final CacheManager cacheManager;

    public ApiCacheController(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    @GetMapping("/user")
    public ResponseEntity<JsonResult> ehcache(String key) {
        Cache cache = cacheManager.getCache("user");
        assert cache != null;
        return ResponseEntity.ok(JsonResult.success(cache.get(key, User.class)));
    }

}
