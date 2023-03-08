package com.planning.k8s.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import io.fabric8.kubernetes.api.model.EnvVar;
import io.fabric8.kubernetes.api.model.LabelSelectorBuilder;
import io.fabric8.kubernetes.api.model.Quantity;
import io.fabric8.kubernetes.api.model.ResourceRequirements;
import io.fabric8.kubernetes.api.model.ResourceRequirementsBuilder;
import io.fabric8.kubernetes.api.model.Volume;
import io.fabric8.kubernetes.api.model.VolumeBuilder;
import io.fabric8.kubernetes.api.model.VolumeMount;
import io.fabric8.kubernetes.api.model.VolumeMountBuilder;
import io.fabric8.kubernetes.api.model.apps.Deployment;
import io.fabric8.kubernetes.api.model.apps.DeploymentBuilder;
import io.fabric8.kubernetes.client.KubernetesClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

/**
 * Deployment 管理
 *
 * @author yxc
 * @date 2022/8/31 2:12 下午
 */
@Component
public class DeploymentService {

    @Autowired
    private KubernetesClient kubernetesClient;

    /**
     * 根据 yaml 创建 Deployment
     */
    public void createCustomDeploymentByYaml(){
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

    /**
     * 使用 DeploymentBuilder 创建对象
     */
    public void createDeploymentByTemplate(){
        Deployment deployment = new DeploymentBuilder()
                .withNewMetadata()
                .withName("train-20220214-01")
                .endMetadata()
                .withNewSpec()
                .withReplicas(1)
                .withSelector(new LabelSelectorBuilder().withMatchLabels(getSelector()).build())
                .withNewTemplate()
                .withNewMetadata()
                .addToLabels("app", "ol")
                .endMetadata()
                .withNewSpec()
                .addNewContainer()
                .withName("main")
                .withImage("harbor.ymmoa.com/matrix/kflearn/tf-2.3.0-gpu-jupyter:latest")
                .withArgs(Lists.newArrayList("mkdir -p /data_path/yxc/test/20230214; cd /data_path/test/20230214;" +
                        " git clone -b master git@code.amh-group.com:matrix-group-default/search-online-learning.git; sh" +
                        " search-online-learning/online_aliyun_zhou/boot_aliyun.sh; /bin/bash"))
                .withCommand(Lists.newArrayList("bash","-c"))
                .withEnv(getEnvList())
                .withResources(getResources())
                .withVolumeMounts(getVolumeMounts())
                .withStdin(true)
                .withStdinOnce(true)
                .endContainer()
                .withVolumes(getVolumes())
                .endSpec()
                .endTemplate()
                .endSpec()
                .build();

        Deployment deploymentResult = kubernetesClient.apps().deployments().inNamespace("default").resource(deployment).createOrReplace();
        System.out.println("Deployment result is" + deploymentResult.toString());
    }

    private List<Volume> getVolumes() {
        Volume volume = new VolumeBuilder()
                .withName("cnfs-nas-pvc")
                .withNewPersistentVolumeClaim()
                .withClaimName("cnfs-nas-pvc")
                .endPersistentVolumeClaim()
                .build();
        return Lists.newArrayList(volume);
    }

    protected List<VolumeMount> getVolumeMounts() {
        VolumeMount volumeMount = new VolumeMountBuilder()
                .withName("cnfs-nas-pvc")
                .withMountPath("/data_path")
                .build();
        return Lists.newArrayList(volumeMount);
    }

    private List<EnvVar> getEnvList() {
        List<EnvVar> envVarList = Lists.newArrayList();
        EnvVar env1 = new EnvVar("key1", "value1", null);
        EnvVar env2 = new EnvVar("key2", "value2", null);
        envVarList.add(env1);
        envVarList.add(env2);
        return envVarList;
    }

    private ResourceRequirements getResources() {
        Map<String, Quantity> requests = Maps.newHashMap();
        requests.put("cpu", new Quantity("1"));
        requests.put("memory", new Quantity("2Gi"));


        Map<String, Quantity> limits = Maps.newHashMap();
        limits.put("cpu", new Quantity("1"));
        limits.put("memory", new Quantity("2Gi"));

        return new ResourceRequirementsBuilder().withRequests(requests).withLimits(limits).build();
    }

    private Map<String, String> getSelector() {
        Map<String, String> selectorMap = Maps.newHashMap();
        selectorMap.put("app", "ol");
        return selectorMap;
    }
}
