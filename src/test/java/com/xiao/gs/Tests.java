package com.xiao.gs;

import org.junit.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

public class Tests {

    @Test
    public void test1() {
        Instant now = Instant.now(Clock.system((ZoneId.of("Asia/Shanghai"))));
        Instant now2 = Instant.now(Clock.systemDefaultZone());
        System.out.println(now);
        System.out.println(now2);
    }

}
