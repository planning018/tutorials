package com.planning.springcloudgateway.customfilters.gatewayapp.utils;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yxc
 * @date 2021/9/16 10:25 上午
 */
public class LoggerListAppender extends AppenderBase<ILoggingEvent> {

    static private List<ILoggingEvent> events = new ArrayList<>();

    @Override
    protected void append(ILoggingEvent eventObject) {
        events.add(eventObject);
    }

    public static List<ILoggingEvent> getEvents() {
        return events;
    }

    public static void clearEventList(){
        events.clear();
    }
}
