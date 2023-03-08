package com.planning.k8s.config;

import io.fabric8.kubernetes.client.Config;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yxc
 * @date 2022/8/30 5:17 下午
 */
@Configuration
public class KubernetesConfig {

    @Bean
    public KubernetesClient kubernetesClient(){
//        String kubeConfig = "xxx";
//        return new DefaultKubernetesClient(Config.fromKubeconfig(kubeConfig));

        String kubeConfig = "xxx";
        return new KubernetesClientBuilder().withConfig(Config.fromKubeconfig(kubeConfig)).build();
    }
}
