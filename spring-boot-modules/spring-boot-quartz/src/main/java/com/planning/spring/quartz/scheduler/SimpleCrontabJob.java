package com.planning.spring.quartz.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

/**
 * @author yxc
 * @date 2022/5/21 10:57 上午
 */
@Slf4j
@Component
public class SimpleCrontabJob extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext jobContext) {
        JobDataMap mergedJobDataMap = jobContext.getMergedJobDataMap();
        System.out.println(mergedJobDataMap.get("jobData"));
    }
}
