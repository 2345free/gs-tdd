package com.xiao.gs.service;

import com.xiao.gs.ws.ip.GetCountryCityByIpResponse;

/**
 * @author luoxiaoxiao
 * @date 2018-12-01 22:29
 */
public interface IpCountryCityService {

    GetCountryCityByIpResponse getCountryCityByIp(String ip);

}
