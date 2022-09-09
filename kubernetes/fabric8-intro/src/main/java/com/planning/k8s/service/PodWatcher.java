package com.planning.k8s.service;

import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.client.Watcher;
import io.fabric8.kubernetes.client.WatcherException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author yxc
 * @date 2022/8/30 5:25 下午
 */
@Slf4j
@Service
public class PodWatcher implements Watcher<Pod> {

    @Override
    public void eventReceived(Action action, Pod pod) {
        String name = pod.getMetadata().getName();
        String phase = pod.getStatus().getPhase();
        //log.info("PodWatcher: action is {}, pod name is {} and status is {}", action.name(), name, phase);
    }

    @Override
    public void onClose(WatcherException e) {

    }
}
