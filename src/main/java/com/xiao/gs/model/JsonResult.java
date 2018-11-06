package com.xiao.gs.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * @author luoxiaoxiao
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JsonResult {

    private Integer code;
    private String msg;
    private Object payload;

    public static JsonResult success(Object payload) {
        return new JsonResult(HttpStatus.OK.value(), null, payload);
    }

    public static JsonResult error(String msg) {
        return new JsonResult(HttpStatus.OK.value(), msg, null);
    }

}
