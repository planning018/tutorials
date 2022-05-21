package com.planning.spring.quartz.test;

import com.planning.spring.quartz.bean.ScheduleJobRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

import java.net.URI;

/**
 * @author yxc
 * @date 2022/5/21 3:26 下午
 */
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ScheduleJobServiceTest {

/*    @LocalServerPort
    private String serverPort;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void givenJobName_runScheduleByCrontab() {
        String url = "http://localhost:" + serverPort + "/schedule/job";

        ScheduleJobRequest param = new ScheduleJobRequest();
        param.setJobName("planning-quartz");
        param.setJobGroup("planning-group");

        HttpEntity<ScheduleJobRequest> entity = new HttpEntity<>(param);
        ResponseEntity<Boolean> response = restTemplate.exchange(url, HttpMethod.POST, entity, Boolean.class);

        Assertions.assertTrue(response.getBody());
    }*/
}
