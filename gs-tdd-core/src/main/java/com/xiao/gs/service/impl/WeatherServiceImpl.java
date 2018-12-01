package com.xiao.gs.service.impl;

import com.xiao.gs.client.feign.WeatherClient;
import com.xiao.gs.client.model.WeatherInfo;
import com.xiao.gs.service.WeatherService;
import org.springframework.stereotype.Service;

/**
 * @author luoxiaoxiao
 * @date 2018-12-01 17:24
 */
@Service
public class WeatherServiceImpl implements WeatherService {

    private final WeatherClient weatherClient;

    public WeatherServiceImpl(WeatherClient weatherClient) {
        this.weatherClient = weatherClient;
    }

    @Override
    public WeatherInfo getWeatherInfo(String id) {
        return weatherClient.getWeatherInfo(id);
    }

}
