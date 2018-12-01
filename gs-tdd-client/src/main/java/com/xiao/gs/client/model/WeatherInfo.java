package com.xiao.gs.client.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author luoxiaoxiao
 * @date 2018-12-01 17:15
 */
@Data
@NoArgsConstructor
public class WeatherInfo {


    /**
     * weatherinfo : {"city":"西安","cityid":"101110101","temp":"23.3","WD":"西南风","WS":"小于3级","SD":"52%","AP":"962.7hPa","njd":"暂无实况","WSE":"<3","time":"18:00","sm":"1.2","isRadar":"1","Radar":"JC_RADAR_AZ9290_JB"}
     */

    private Weatherinfo weatherinfo;

    @NoArgsConstructor
    @Data
    public static class Weatherinfo {
        /**
         * city : 西安
         * cityid : 101110101
         * temp : 23.3
         * WD : 西南风
         * WS : 小于3级
         * SD : 52%
         * AP : 962.7hPa
         * njd : 暂无实况
         * WSE : <3
         * time : 18:00
         * sm : 1.2
         * isRadar : 1
         * Radar : JC_RADAR_AZ9290_JB
         */

        private String city;
        private String cityid;
        private String temp;
        private String WD;
        private String WS;
        private String SD;
        private String AP;
        private String njd;
        private String WSE;
        private String time;
        private String sm;
        private String isRadar;
        private String Radar;
    }
}
