package com.planning.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author yxc
 * @date 2021/4/16 14:34
 */
public class AdderAfterThrowAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public void afterThrow(final Exception exception) throws Throwable {
        logger.info("Exception thrown was {}",  exception.getMessage());
    }
}
