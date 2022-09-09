package com.planning.k8s.service;

import com.planning.k8s.config.KubernetesConfig;
import io.fabric8.kubernetes.api.model.ListOptionsBuilder;
import io.fabric8.kubernetes.client.KubernetesClient;
import org.springframework.stereotype.Service;

/**
 * @author yxc
 * @date 2022/8/30 5:23 下午
 */
@Service
public class WatcherService {

    public WatcherService(KubernetesClient kubernetesClient, PodWatcher podWatcher) {
        kubernetesClient.pods().inNamespace("kubeflow-user-example-com").watch(new ListOptionsBuilder().withTimeoutSeconds(31536000L).build(), podWatcher);
    }
}
