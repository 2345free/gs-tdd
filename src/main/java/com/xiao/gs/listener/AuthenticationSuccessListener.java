package com.xiao.gs.listener;

import com.xiao.gs.model.LoginUser;
import org.springframework.context.event.EventListener;
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
@Component
public class AuthenticationSuccessListener {

    @EventListener(AuthenticationSuccessEvent.class)
    public void onAuthenticationSuccess(AuthenticationSuccessEvent event) {
        User user = (User) event.getAuthentication().getPrincipal();
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert requestAttributes != null;
        HttpServletRequest request = requestAttributes.getRequest();
        HttpSession session = request.getSession();
        session.setAttribute("user", new LoginUser(0L, user.getUsername(), "password", user.getAuthorities()));
    }

}
