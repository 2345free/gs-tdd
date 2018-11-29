package com.xiao.gs.data.redis.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.lang.NonNull;

import java.nio.charset.Charset;

/**
 * redis消息监听器
 *
 * @author luoxiaoxiao
 */
@Slf4j
public class TestMessageListener implements MessageListener {

    @Override
    public void onMessage(@NonNull Message message, byte[] pattern) {
        log.info("pattern: {}", new String(pattern, Charset.forName("utf-8")));
        log.info("channel: {}", message.getChannel());
        log.info("body: {}", new String(message.getBody(), Charset.forName("utf-8")));
    }

}
