package com.xiao.gs.web.util;


public class ViewUtils {

    public static final String API_ROUTE_PREFIX = "/api";
    public static final String USER_ROUTE_PREFIX = "/user";
    public static final String USER_ROUTE = API_ROUTE_PREFIX + USER_ROUTE_PREFIX;
    public static final String CONSUL_ROUTE = API_ROUTE_PREFIX + "/consul";

    private ViewUtils() {
    }

    public static String redirectView(String prefix, String name) {
        return redirectView(prefix + name);
    }

    public static String redirectView(String name) {
        return "redirect:" + name;
    }
}
