package com.planning.apache.curator.configuration;

import com.planning.apache.curator.BaseManualTest;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.x.async.AsyncCuratorFramework;
import org.assertj.core.util.Lists;
import org.junit.Test;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.jayway.awaitility.Awaitility.await;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author planning
 * @since 2020-03-17 16:23
 **/
public class ConfigurationManagementManualTest extends BaseManualTest {

    private static final String KEY_FORMAT = "/%s";

    @Test
    public void givenPath_whenCreateKey_thenValueIsStored() throws Exception {
        try (CuratorFramework client = newClient()) {
            client.start();
            AsyncCuratorFramework async = AsyncCuratorFramework.wrap(client);
            String key = getKey();
            String expected = "planning_set_value";

            // create key nodes structure
            client.create().forPath(key);

            // set date value for our key
            async.setData().forPath(key, expected.getBytes());

            // get data value
            AtomicBoolean isEquals = new AtomicBoolean(false);
            async.getData().forPath(key)
                    .thenAccept(
                            data -> isEquals.set(new String(data).equals(expected))
                    );

            await().until(() -> assertThat(isEquals.get()).isTrue());
        }
    }

    @Test
    public void givenPath_whenWatchAKeyAndStoreAValue_thenWatchIsTriggered() {
        try (final CuratorFramework client = newClient()) {
            client.start();
            AsyncCuratorFramework async = AsyncCuratorFramework.wrap(client);
            String key = getKey();
            String expected = "planning_set_value_20200317_1659";

            // create key structure
            async.create().forPath(key);

            List<String> changes = Lists.newArrayList();

            // watch data value
            async.watched().getData()
                    .forPath(key).event()
                    .thenAccept(watchedEvent -> {
                        try {
                            changes.add(new String(client.getData().forPath(watchedEvent.getPath())));
                        } catch (Exception e) {
                            // fail ...
                            e.printStackTrace();
                        }
                    });

            // set data value for our key
            async.setData().forPath(key, expected.getBytes());

            await().until(() -> assertThat(changes.size() > 0).isTrue());
        }
    }


    private String getKey() {
        return String.format(KEY_FORMAT, UUID.randomUUID().toString());
    }
}