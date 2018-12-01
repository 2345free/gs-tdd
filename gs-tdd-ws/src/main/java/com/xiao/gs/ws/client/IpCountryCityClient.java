package com.xiao.gs.ws.client;

import com.xiao.gs.ws.ip.GetCountryCityByIp;
import com.xiao.gs.ws.ip.GetCountryCityByIpResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

/**
 * @author luoxiaoxiao
 * @date 2018-12-01 22:20
 */
@Slf4j
public class IpCountryCityClient extends WebServiceGatewaySupport {

    public GetCountryCityByIpResponse getCountryCityByIp(String ip) {
        GetCountryCityByIp request = new GetCountryCityByIp();
        request.setTheIpAddress(ip);
        log.info("Requesting ip:{}", ip);

        GetCountryCityByIpResponse response = (GetCountryCityByIpResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://ws.webxml.com.cn/WebServices/IpAddressSearchWebService.asmx", request,
                        new SoapActionCallback("http://WebXml.com.cn/getCountryCityByIp"));
        return response;
    }

}
