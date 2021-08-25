package com.planning.apache.curator.ymmTools;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.apache.curator.retry.RetryOneTime;
import org.apache.curator.x.async.AsyncCuratorFramework;
import org.junit.Test;

import java.nio.charset.Charset;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 读取 zk 上指定节点的值
 *
 * 官网 demo 学习：https://github.com/apache/curator
 *
 * @author yxc
 * @date 2021/4/9 15:29
 */
public class FindLionValueUtils {

    private final String ZK_SERVER_URL = "xxxx:2181";
    private final String CONNECT_STRING = "localhost:2181";

    private final int SESSION_TIMEOUT_MS = 60 * 1000;
    private final int CONNECTION_TIMEOUT_MS = 15 * 1000;
    private final RetryPolicy RETRY_POLICY = new RetryOneTime(1000);

    private static String basicPath = "/DP111/CONFIG111";

    /**
     * 获取某个指定 node 的值
     */
    @Test
    public void findZkNode() {
        int sleepMsBetweenRetries = 100;
        int maxRetries = 3;
        RetryPolicy retryPolicy = new RetryNTimes(maxRetries, sleepMsBetweenRetries);

        try (CuratorFramework client = CuratorFrameworkFactory.newClient(ZK_SERVER_URL, retryPolicy)) {
            client.start();

            AsyncCuratorFramework async = AsyncCuratorFramework.wrap(client);
            async.getData().forPath(basicPath + "/1111").thenAccept(
                    data -> System.out.println("value is " + new String(data))
            );
        }

    }

    /**
     * 列举 Node 的 children 子路经
     *
     * @throws Exception
     */
    @Test
    public void listPath() throws Exception {
        // -- CuratorFrameworkFactory::newClient
        CuratorFramework curatorFramework = CuratorFrameworkFactory.newClient(ZK_SERVER_URL, SESSION_TIMEOUT_MS, CONNECTION_TIMEOUT_MS, RETRY_POLICY);

        // -- CuratorFramework::start
        curatorFramework.start();

        // -- CuratorFramework::getChildren
        List<String> children = curatorFramework.getChildren().forPath(basicPath);

        children.stream().limit(100).forEach(System.out::println);

        // -- CuratorFramework::close
        curatorFramework.close();
    }

    @Test
    public void listPathAndValue() throws Exception {
        CuratorFramework curatorFramework = CuratorFrameworkFactory.newClient(ZK_SERVER_URL, SESSION_TIMEOUT_MS, CONNECTION_TIMEOUT_MS, RETRY_POLICY);
        curatorFramework.start();

        List<String> children = curatorFramework.getChildren().forPath(basicPath);

        children.stream().limit(50).forEach(path -> {
            try {
                String newPath = basicPath + path;
                byte[] data = curatorFramework.getData().watched().forPath(newPath);

                System.out.println("key - value :" + newPath + " - " + new String(data, Charset.forName("UTF-8")));
            } catch (Exception e) {
                e.printStackTrace();
            }

        });


        // 查询特定的值
/*        String path = "xxx";
        byte[] data = curatorFramework.getData().watched().forPath(path);
        System.out.println( new String(data, Charset.forName("UTF-8")));*/

        TimeUnit.SECONDS.sleep(10);

        curatorFramework.close();
    }

}
