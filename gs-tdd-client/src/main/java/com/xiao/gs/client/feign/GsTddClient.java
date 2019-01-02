package com.xiao.gs.client.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("gs-tdd-app")
public interface GsTddClient {

    @GetMapping(value = "/api/consul/choose")
    String choose();

}
