package com.planning.k8s.controller;

import com.planning.k8s.service.DeploymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yxc
 * @date 2023/2/15 11:05 上午
 */
@RestController
public class DeploymentController {

    @Autowired
    private DeploymentService deploymentService;

    @PostMapping("/deployment/template")
    public String deploymentByTemplate() {
        deploymentService.createDeploymentByTemplate();
        return "deployment";
    }
}
