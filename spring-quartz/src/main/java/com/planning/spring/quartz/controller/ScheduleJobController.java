package com.planning.spring.quartz.controller;

import com.planning.spring.quartz.bean.ScheduleJobRequest;
import com.planning.spring.quartz.service.ScheduleJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author yxc
 * @date 2022/5/21 2:15 下午
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleJobController {

    @Autowired
    private ScheduleJobService scheduleJobService;

    @PostMapping("/job")
    public Boolean createScheduleJob(@RequestBody ScheduleJobRequest request){
        scheduleJobService.createScheduleJob(request.getJobName(), request.getJobGroup());
        return true;
    }
}
