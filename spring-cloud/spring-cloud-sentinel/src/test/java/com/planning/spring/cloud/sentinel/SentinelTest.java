package com.planning.spring.cloud.sentinel;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author yxc
 * @date 2021/12/21 11:01 上午
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class SentinelTest {

    @Test
    public void numberTest(){
        double nextDouble = ThreadLocalRandom.current().nextDouble(100);
        System.out.println(nextDouble);
        System.out.println(nextDouble < 0.5);
    }

    @Test
    public void test(){
        for (int i = 0; i < 20; i++) {
            numberTest();
        }
    }
}
