package com.xiao.gs.service;

import com.xiao.gs.client.model.WeatherInfo;

/**
 * @author luoxiaoxiao
 * @date 2018-12-01 17:24
 */
public interface WeatherService {

    WeatherInfo getWeatherInfo(String id);

}
