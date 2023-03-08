package com.planning.k8s.service;

import com.planning.k8s.FabricIntroApplication;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author yxc
 * @date 2022/9/9 5:30 下午
 */
@SpringBootTest(classes = FabricIntroApplication.class)
public class DeploymentTest {

    @Autowired
    private DeploymentService deploymentService;

    @Test
    public void testCreateByYaml(){
        deploymentService.createCustomDeploymentByYaml();
    }

    @Test
    public void testCreateByTemplate(){
        deploymentService.createDeploymentByTemplate();
    }
}
