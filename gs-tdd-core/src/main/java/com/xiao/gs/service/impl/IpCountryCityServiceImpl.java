package com.xiao.gs.service.impl;

import com.xiao.gs.service.IpCountryCityService;
import com.xiao.gs.ws.client.IpCountryCityClient;
import com.xiao.gs.ws.ip.GetCountryCityByIpResponse;
import org.springframework.stereotype.Service;

/**
 * @author luoxiaoxiao
 * @date 2018-12-01 22:30
 */
@Service
public class IpCountryCityServiceImpl implements IpCountryCityService {

    private final IpCountryCityClient ipCountryCityClient;

    public IpCountryCityServiceImpl(IpCountryCityClient ipCountryCityClient) {
        this.ipCountryCityClient = ipCountryCityClient;
    }

    @Override
    public GetCountryCityByIpResponse getCountryCityByIp(String ip) {
        return ipCountryCityClient.getCountryCityByIp(ip);
    }

}
