package com.planning.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author yxc
 * @date 2021/4/16 14:34
 */
public class AdderAfterAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public void afterAdvice() throws Throwable {
        logger.info("I'm done calling the method");
    }
}
