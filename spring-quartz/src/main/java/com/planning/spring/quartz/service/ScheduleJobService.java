package com.planning.spring.quartz.service;

import com.planning.spring.quartz.scheduler.SimpleCrontabJob;
import lombok.extern.slf4j.Slf4j;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author yxc
 * @date 2022/5/21 10:54 上午
 */
@Slf4j
@Component
public class ScheduleJobService {

    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    public void createScheduleJob(String jobName, String jobGroup) {
//        String jobName = "planning-quartz-demo";
//        String jobGroup = "planning-group";

        try {
            // 1. 获取 Scheduler
            Scheduler scheduler = schedulerFactoryBean.getScheduler();

            // 2. 构建 JobDetail
            JobDetail jobDetail = JobBuilder
                    .newJob(SimpleCrontabJob.class)
                    .withIdentity(jobName, jobGroup)
                    .build();

            if (!scheduler.checkExists(jobDetail.getKey())) {
                jobDetail = JobBuilder.newJob(SimpleCrontabJob.class)
                        .withIdentity(jobName, jobGroup)
                        .usingJobData("jobData", "Hello quartz!")
                        .build();

                // 3. 构造 Trigger
                Trigger trigger = TriggerBuilder
                        .newTrigger()
                        .withIdentity(jobName, jobGroup)
                        .startAt(new Date())
                        .withSchedule(CronScheduleBuilder.cronSchedule("0 * * ? * *")
                                .withMisfireHandlingInstructionDoNothing())
                        .build();

                scheduler.scheduleJob(jobDetail, trigger);

                log.info(">>>>> jobName = [" + jobName + "]" + " scheduled.");
            } else {
                log.error("ScheduleJobService createScheduleJob jobName {} already exist", jobName);
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }

    }
}
