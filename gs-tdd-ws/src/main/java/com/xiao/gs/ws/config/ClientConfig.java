package com.xiao.gs.ws.config;

import com.xiao.gs.ws.client.IpCountryCityClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

/**
 * @author luoxiaoxiao
 * @date 2018-12-01 22:44
 */
@Configuration
public class ClientConfig {

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        // this package must match the package in the <generatePackage> specified in
        marshaller.setContextPath("com.xiao.gs.ws.ip");
        return marshaller;
    }

    @Bean
    public IpCountryCityClient ipCountryCityClient(Jaxb2Marshaller marshaller) {
        IpCountryCityClient client = new IpCountryCityClient();
        client.setDefaultUri("http://ws.webxml.com.cn");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }

}
