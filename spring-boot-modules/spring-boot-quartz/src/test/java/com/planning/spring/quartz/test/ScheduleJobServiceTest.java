package com.planning.spring.quartz.test;

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
