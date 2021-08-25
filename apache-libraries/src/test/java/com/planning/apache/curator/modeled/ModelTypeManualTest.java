package com.planning.apache.curator.modeled;

import com.planning.apache.curator.BaseManualTest;
import com.planning.apache.curator.HostConfig;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.x.async.AsyncCuratorFramework;
import org.apache.curator.x.async.modeled.JacksonModelSerializer;
import org.apache.curator.x.async.modeled.ModelSpec;
import org.apache.curator.x.async.modeled.ModeledFramework;
import org.apache.curator.x.async.modeled.ZPath;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

/**
 * @author planning
 * @since 2020-03-17 17:15
 **/
public class ModelTypeManualTest extends BaseManualTest {

    @Test
    public void givenPath_whenStoreModel_thenNodesAreCreated() {

        ModelSpec<HostConfig> mySpec = ModelSpec.builder(ZPath.parseWithIds("/config/dev"),
                JacksonModelSerializer.build(HostConfig.class)).build();

        try (CuratorFramework client = newClient()) {
            client.start();
            AsyncCuratorFramework async = AsyncCuratorFramework.wrap(client);
            ModeledFramework<HostConfig> modelClient = ModeledFramework.wrap(async, mySpec);

            modelClient.set(new HostConfig("host-name", 8080));

            modelClient.read()
                    .whenComplete((value, e) -> {
                        if (e != null) {
                            fail("Cannot read host config", e);
                        } else {
                            assertThat(value).isNotNull();
                            assertThat(value.getHostname()).isEqualTo("host-name");
                            assertThat(value.getPort()).isEqualTo(8080);
                        }
                    });
        }
    }
}