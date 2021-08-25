package com.planning.apache.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;

/**
 * @author planning
 * @since 2020-03-17 16:24
 **/
public abstract class BaseManualTest {

    private final String zkServerUrl = "localhost:2181";

    protected CuratorFramework newClient() {
        int sleepMsBetweenRetries = 100;
        int maxRetries = 3;
        RetryPolicy retryPolicy = new RetryNTimes(maxRetries, sleepMsBetweenRetries);
        return CuratorFrameworkFactory.newClient(zkServerUrl, retryPolicy);
    }
}