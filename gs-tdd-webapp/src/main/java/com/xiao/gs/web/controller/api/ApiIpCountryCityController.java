package com.xiao.gs.web.controller.api;

import com.xiao.gs.service.IpCountryCityService;
import com.xiao.gs.web.model.JsonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.xiao.gs.web.util.ViewUtils.API_ROUTE_PREFIX;

/**
 * @author luoxiaoxiao
 * @date 2018-12-01 22:33
 */
@RestController
@RequestMapping(API_ROUTE_PREFIX + "/ip")
public class ApiIpCountryCityController {

    private final IpCountryCityService ipCountryCityService;

    public ApiIpCountryCityController(IpCountryCityService ipCountryCityService) {
        this.ipCountryCityService = ipCountryCityService;
    }

    @GetMapping("/city-info")
    public JsonResult getCountryCityByIp(String ip) {
        return JsonResult.success(ipCountryCityService.getCountryCityByIp(ip));
    }

}
