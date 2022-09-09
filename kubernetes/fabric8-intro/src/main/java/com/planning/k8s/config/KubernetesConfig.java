package com.planning.k8s.config;

import io.fabric8.kubernetes.client.Config;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yxc
 * @date 2022/8/30 5:17 下午
 */
@Configuration
public class KubernetesConfig {

    @Bean
    public DefaultKubernetesClient kubernetesClient(){
        String kubeConfig = "";
        return new DefaultKubernetesClient(Config.fromKubeconfig(kubeConfig));
    }
}
