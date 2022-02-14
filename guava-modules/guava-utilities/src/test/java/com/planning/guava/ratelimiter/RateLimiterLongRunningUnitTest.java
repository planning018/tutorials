package com.planning.guava.ratelimiter;

import com.google.common.util.concurrent.RateLimiter;
import org.junit.Test;

import java.time.ZonedDateTime;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author yxc
 * @date 2022/2/10 3:36 下午
 */
public class RateLimiterLongRunningUnitTest {

    @Test
    public void givenLimitedResource_whenUseRateLimiter_thenShouldLimitPermits(){
        // given
        RateLimiter rateLimiter = RateLimiter.create(1);

        // when
        long startTime =  ZonedDateTime.now().getSecond();
        IntStream.range(0, 10).forEach(i -> {
            rateLimiter.acquire();
            doSomeLimitedOperation();
        });
        long elapsedTimeSeconds = ZonedDateTime.now().getSecond() - startTime;

        // then
        assertThat(elapsedTimeSeconds >= 10);
    }

    private void doSomeLimitedOperation() {
        // some computing
        System.out.println("test");
    }
}
