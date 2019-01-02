package com.xiao.gs.web.util;


import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

public class WebUtils {

    private static final String AGENT = "User-Agent";
    private static final String REFERER = "Referer";

    public static String getIp(HttpServletRequest request) {
        if (request == null) {
            return null;
        }

        String ip = request.getHeader("X-Forwarded-For");
        if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        // 多个路由时，取第一个非unknown的ip
        final String[] arr = ip.split(",");
        for (final String str : arr) {
            if (!"unknown".equalsIgnoreCase(str)) {
                ip = str;
                break;
            }
        }
        return ip;
    }

    public static String getServerUrl(HttpServletRequest request) {
        return null == request ? null : String.format("%s://%s:%s", request.getScheme(), request.getServerName(), request.getServerPort());
    }

    public static String getAgent(HttpServletRequest request) {
        return null == request ? null : request.getHeader(AGENT);
    }

    public static String getReferer(HttpServletRequest request) {
        return null == request ? null : request.getHeader(REFERER);
    }

}
