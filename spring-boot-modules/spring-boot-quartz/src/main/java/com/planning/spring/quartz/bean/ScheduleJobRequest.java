package com.planning.spring.quartz.bean;

import lombok.Data;

/**
 * @author yxc
 * @date 2022/5/21 2:19 下午
 */
@Data
public class ScheduleJobRequest {

    private String jobName;

    private String jobGroup;

}
