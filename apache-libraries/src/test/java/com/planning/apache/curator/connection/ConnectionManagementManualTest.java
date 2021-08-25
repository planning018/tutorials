package com.planning.apache.curator.connection;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.apache.curator.x.async.AsyncCuratorFramework;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicBoolean;

import static com.jayway.awaitility.Awaitility.await;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * 测试连接 ZK
 *
 * @author planning
 * @since 2020-03-17 15:38
 **/
public class ConnectionManagementManualTest {

    private final String zkServerUrl = "localhost:2181";

    @Test
    public void givenRunningZookeeper_whenOpenConnection_thenClientOpened() throws Exception {
        int sleepMsBetweenRetries = 100;
        int maxRetries = 3;
        RetryPolicy retryPolicy = new RetryNTimes(maxRetries, sleepMsBetweenRetries);

        try (CuratorFramework client = CuratorFrameworkFactory.newClient(zkServerUrl, retryPolicy)) {
            client.start();

            assertThat(client.checkExists().forPath("/")).isNotNull();
        }
    }

    @Test
    public void givenRunningZookeeper_whenOpenConnectionUsingAsyncNotBlock_thenClientIsOpened() {
        int sleepMsBetweenRetries = 100;
        int maxRetries = 3;
        RetryPolicy retryPolicy = new RetryNTimes(maxRetries, sleepMsBetweenRetries);

        try (CuratorFramework client = CuratorFrameworkFactory.newClient(zkServerUrl, retryPolicy)) {
            client.start();
            AsyncCuratorFramework asyncCuratorFramework = AsyncCuratorFramework.wrap(client);

            AtomicBoolean exists = new AtomicBoolean(false);

            asyncCuratorFramework.checkExists().forPath("/")
                    .thenAcceptAsync(s -> exists.set(s != null));

            await().until(() -> assertThat(exists.get()).isTrue());
        }
    }

    @Test
    public void givenRunningZookeeper_whenOpenConnectionUsingAsyncBlocking_thenClientIsOpened() {
        int sleepMsBetweenRetries = 100;
        int maxRetries = 3;
        RetryPolicy retryPolicy = new RetryNTimes(maxRetries, sleepMsBetweenRetries);

        try (CuratorFramework client = CuratorFrameworkFactory.newClient(zkServerUrl, retryPolicy)) {
            client.start();
            AsyncCuratorFramework asyncCuratorFramework = AsyncCuratorFramework.wrap(client);

            AtomicBoolean exists = new AtomicBoolean(false);

            asyncCuratorFramework.checkExists().forPath("/")
                    .thenAccept(s -> exists.set(s != null));

            await().until(() -> assertThat(exists.get()).isTrue());
        }
    }

}