package com.planning.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author yxc
 * @date 2021/4/16 14:35
 */
public class AdderBeforeAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public void beforeAdvice() throws Throwable {
        logger.info("I would be executed just before method starts");
    }
}
