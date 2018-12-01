package com.xiao.gs.web.controller.api;

import com.xiao.gs.client.model.WeatherInfo;
import com.xiao.gs.service.WeatherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author luoxiaoxiao
 * @date 2018-12-01 17:26
 */
@RestController
@RequestMapping("/api/weather")
public class ApiWeatherController {

    private final WeatherService weatherService;

    public ApiWeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/data/{id}")
    public WeatherInfo getWeatherInfo(@PathVariable("id") String id) {
        return weatherService.getWeatherInfo(id);
    }

}
