package com.xiao.gs.web.controller.api;

import com.xiao.gs.client.feign.GsTddClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static com.xiao.gs.web.util.ViewUtils.CONSUL_ROUTE;

//@RestController
@RequestMapping(CONSUL_ROUTE)
public class ApiConsulController {

    private final LoadBalancerClient loadBalancer;

    private final DiscoveryClient discoveryClient;

    private final GsTddClient gsTddClient;

    private final Registration registration;

    private final RestTemplate restTemplate;

    @Value("${spring.application.name}")
    private String appName;

    public ApiConsulController(LoadBalancerClient loadBalancer, DiscoveryClient discoveryClient, GsTddClient gsTddClient, Registration registration, RestTemplate restTemplate) {
        this.loadBalancer = loadBalancer;
        this.discoveryClient = discoveryClient;
        this.gsTddClient = gsTddClient;
        this.registration = registration;
        this.restTemplate = restTemplate;
    }

    @RequestMapping("/me")
    public ServiceInstance me() {
        return this.registration;
    }

    @RequestMapping("/")
    public ServiceInstance lb() {
        return loadBalancer.choose(appName);
    }

    @RequestMapping("/rest")
    public String rest() {
        return this.restTemplate.getForObject("http://" + appName + CONSUL_ROUTE + "/me", String.class);
    }

    @RequestMapping("/choose")
    public String choose() {
        return loadBalancer.choose(appName).getUri().toString();
    }

    @RequestMapping("/instances")
    public List<ServiceInstance> instances() {
        return discoveryClient.getInstances(appName);
    }

    @RequestMapping("/feign")
    public String feign() {
        return gsTddClient.choose();
    }

}
