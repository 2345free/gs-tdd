package com.xiao.gs.aop;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Aspect
@Component
public class ApiLogAspect {

    @Around("@annotation(apiOperation)")
    public Object log(ProceedingJoinPoint joinPoint, ApiOperation apiOperation) throws Throwable {

        log.info("开始执行api:" + apiOperation.value());

        Map<String, String> paramEntries = new HashMap<>(8);

        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        Assert.notNull(requestAttributes, "requestAttributes can't be null");
        HttpServletRequest request = requestAttributes.getRequest();
        Enumeration<String> parameterNames = request.getParameterNames();

        while (parameterNames.hasMoreElements()) {
            String name = parameterNames.nextElement();
            String value = request.getParameter(name);
            paramEntries.put(name, value);
        }
        paramEntries.put("ip", request.getRemoteAddr());
        ObjectMapper objectMapper = new ObjectMapper();
        String info = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(paramEntries);
        log.info("请求参数等信息:" + info);

        Object proceed = joinPoint.proceed();

        String result = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(proceed);
        log.info("接口执行结果:" + result);

        return proceed;
    }

}
