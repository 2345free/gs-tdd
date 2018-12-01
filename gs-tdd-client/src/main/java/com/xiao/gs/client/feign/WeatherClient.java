package com.xiao.gs.client.feign;

import com.google.common.collect.Lists;
import com.xiao.gs.client.model.WeatherInfo;
import feign.codec.Decoder;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author luoxiaoxiao
 * @date 2018-12-01 16:58
 */
@FeignClient(name = "weather-service",
        configuration = WeatherClient.Config.class,
        fallbackFactory = WeatherClient.WeatherClientFallbackFactory.class)
public interface WeatherClient {

    @GetMapping(value = "/data/sk/{id}.html")
    WeatherInfo getWeatherInfo(@PathVariable("id") String id);

    @Slf4j
    class WeatherClientFallbackFactory implements FallbackFactory<WeatherClient> {
        @Override
        public WeatherClient create(Throwable cause) {
            return new WeatherClient() {
                @Override
                public WeatherInfo getWeatherInfo(String id) {
                    return new WeatherInfo();
                }
            };
        }
    }

    @Configuration
    class Config {
        @Bean
        public WeatherClientFallbackFactory fallbackFactory() {
            return new WeatherClientFallbackFactory();
        }

        @Bean
        public Decoder feignDecoder() {
            MessageConverter messageConverter = new MessageConverter();
            ObjectFactory<HttpMessageConverters> objectFactory = () -> new HttpMessageConverters(messageConverter);
            return new SpringDecoder(objectFactory);
        }

        class MessageConverter extends MappingJackson2HttpMessageConverter {
            MessageConverter() {
                setSupportedMediaTypes(Lists.newArrayList(MediaType.TEXT_HTML));
            }
        }
    }

}
