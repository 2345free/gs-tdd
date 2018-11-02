package com.xiao.gs.listener;

import com.xiao.gs.model.LoginUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 登录成功的监听事件
 *
 * @author luoxiaoxiao
 */
@Slf4j
@Component
public class AuthenticationEventListener {

    @EventListener(AuthenticationSuccessEvent.class)
    public void onAuthenticationSuccess(AuthenticationSuccessEvent event) {
        User user = (User) event.getAuthentication().getPrincipal();
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert requestAttributes != null;
        HttpServletRequest request = requestAttributes.getRequest();
        HttpSession session = request.getSession();
        session.setAttribute("user", new LoginUser(0L, user.getUsername(), "password", user.getAuthorities()));
    }

    @EventListener(AuthenticationFailureBadCredentialsEvent.class)
    public void onAuthenticationFailureBadCredentials(AuthenticationSuccessEvent event) {
        User user = (User) event.getAuthentication().getPrincipal();
        log.warn("用户:{},登录信息错误", user.getUsername());
    }

}
