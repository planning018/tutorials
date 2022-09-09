package com.planning.k8s.service;

import io.fabric8.kubernetes.client.KubernetesClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

/**
 * Deployment 管理
 *
 * @author yxc
 * @date 2022/8/31 2:12 下午
 */
@Service
public class DeploymentService {

    @Autowired
    private KubernetesClient kubernetesClient;

    /**
     * 根据 yaml 创建 Deployment
     */
    public void createCustomDeployment(){
        String yaml = "apiVersion: apps/v1\n" +
                "kind: Deployment\n" +
                "metadata:\n" +
                "  name: pc-deployment\n" +
                "  namespace: dev\n" +
                "spec:\n" +
                "  replicas: 3\n" +
                "  selector:\n" +
                "    matchLabels:\n" +
                "      run: nginx\n" +
                "      app: nginx-pod\n" +
                "  template:\n" +
                "    metadata:\n" +
                "      labels:\n" +
                "        run: nginx\n" +
                "        app: nginx-pod\n" +
                "    spec:\n" +
                "      containers:\n" +
                "      - image: nginx:1.17.1\n" +
                "        name: nginx\n" +
                "        ports:\n" +
                "        - containerPort: 80\n" +
                "          protocol: TCP";
        kubernetesClient.load(new ByteArrayInputStream(yaml.getBytes(StandardCharsets.UTF_8)))
                .createOrReplace();
    }
}
