package com.xiao.gs.data.redis;

import com.xiao.gs.AbstractIntegrationTest;
import com.xiao.gs.data.jpa.domain.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

public class MessagePubSubTest extends AbstractIntegrationTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate<String, User> template;

    @Test
    public void pub() {
        stringRedisTemplate.convertAndSend("test:pub", "hello world!");
    }

    @Test
    public void pub2() {
        template.convertAndSend("test:pub", User.builder().username("luoxx").password("123").build());
    }

}
