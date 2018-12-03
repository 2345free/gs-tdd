package com.xiao.gs.web.codec;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * boot2.x 使用自定义登录认证时需要添加密码编解码器
 *
 * @author luoxiaoxiao
 */
public class MyPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence rawPassword) {
        return rawPassword.toString();
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return rawPassword.toString().equals(encodedPassword);
    }
}
