package com.xiao.gs.web.controller.api;

import com.xiao.gs.service.WeatherService;
import com.xiao.gs.web.model.JsonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.xiao.gs.web.util.ViewUtils.API_ROUTE_PREFIX;

/**
 * @author luoxiaoxiao
 * @date 2018-12-01 17:26
 */
@RestController
@RequestMapping(API_ROUTE_PREFIX + "/weather")
public class ApiWeatherController {

    private final WeatherService weatherService;

    public ApiWeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/data/{id}")
    public JsonResult getWeatherInfo(@PathVariable("id") String id) {
        return JsonResult.success(weatherService.getWeatherInfo(id));
    }

}
