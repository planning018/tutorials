package com.planning.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author yxc
 * @date 2021/4/16 14:34
 */
public class AdderAfterReturnAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public void afterReturn(final Object returnValue) throws Throwable {
        logger.info("value return was {}",  returnValue);
    }
}
