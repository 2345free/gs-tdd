package com.xiao.gs.bind.support;

import com.xiao.gs.bind.annotation.CurrentUser;
import com.xiao.gs.data.domain.User;
import com.xiao.gs.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.security.Principal;

/**
 * 解析自动以注解CurentUser,并赋值
 *
 * @author luoxiaoxiao
 */
public class CurentUserArgumentResolver implements HandlerMethodArgumentResolver {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean supportsParameter(@NonNull MethodParameter parameter) {
        return parameter.hasParameterAnnotation(CurrentUser.class) && parameter.getParameterType().equals(User.class);
    }

    @Override
    public Object resolveArgument(@NonNull MethodParameter parameter, ModelAndViewContainer mavContainer, @NonNull NativeWebRequest webRequest, @Nullable WebDataBinderFactory binderFactory) throws Exception {

        Principal principal = webRequest.getUserPrincipal();
        assert principal != null;
        UserDetails userDetails = (UserDetails) ((Authentication) principal).getPrincipal();
        if (userDetails != null) {
            return userRepository.findByUsername(userDetails.getUsername());
        }
        return userRepository.findById(1L);
    }
}
