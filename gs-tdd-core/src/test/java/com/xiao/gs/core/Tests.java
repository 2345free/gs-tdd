package com.xiao.gs.core;

import org.junit.Ignore;
import org.junit.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

@Ignore
public class Tests {

    @Test
    public void test1() {
        Instant now = Instant.now(Clock.system((ZoneId.of("Asia/Shanghai"))));
        Instant now2 = Instant.now(Clock.systemDefaultZone());
        System.out.println(now);
        System.out.println(now2);
    }

    @Test
    public void test2() {
        int i = 1;
        int j = i++;
        if ((i == (++j)) && ((i++) == j)) {
            i += j;
        }
        System.out.println(i); // 5
    }

    @Test
    public void test3() {
        int num = 50;
        num = num++ * 2;
        System.out.println(num); // 100
    }


}

