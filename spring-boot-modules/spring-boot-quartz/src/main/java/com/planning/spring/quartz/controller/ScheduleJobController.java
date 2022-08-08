package com.planning.spring.quartz.controller;

import com.planning.spring.quartz.bean.ScheduleJobRequest;
import com.planning.spring.quartz.service.ScheduleJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yxc
 * @date 2022/5/21 2:15 下午
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleJobController {

    @Autowired
    private ScheduleJobService scheduleJobService;

    @PostMapping("/setup/job")
    public Boolean createScheduleJob(@RequestBody ScheduleJobRequest request){
        scheduleJobService.createScheduleJob(request.getJobName(), request.getJobGroup());
        return true;
    }

    @PostMapping("/cancel/job")
    public Boolean cancelScheduleJob(@RequestBody ScheduleJobRequest request){
        scheduleJobService.cancelScheduleJob(request.getJobName(), request.getJobGroup());
        return true;
    }
}
