package com.planning.apache.curator;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessLock;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.RetryNTimes;

import javax.annotation.PostConstruct;
import java.util.Objects;

/**
 * @author yxc
 * @date 2022/8/8 4:55 下午
 */
@Slf4j
//@Component
public class DistributedLockViaZk {


    private CuratorFramework client;

    private String zkLockNode = "/xxx/schedule/locks";

    @PostConstruct
    public void newClient() {
        this.client = CuratorFrameworkFactory.newClient("",
                60000, 30000, new RetryNTimes(2147483647, 1000));
        client.start();
    }

    public InterProcessLock getZkLock() {
        try {
            InterProcessLock lock = new InterProcessMutex(client, zkLockNode);
            lock.acquire();
            return lock;
        } catch (Exception e) {
            log.error("DistributedLockViaZk getZkLock error: {}", e.getMessage());
            return null;
        }
    }

    public void releaseZkLock(InterProcessLock lock) {
        if (Objects.isNull(lock)) {
            log.info("DistributedLockViaZk releaseZkLock: lock is null");
            return;
        }
        try {
            lock.release();
        } catch (Exception e) {
            log.error("DistributedLockViaZk releaseZkLock error: {}", e.getMessage());
        }
    }

}
