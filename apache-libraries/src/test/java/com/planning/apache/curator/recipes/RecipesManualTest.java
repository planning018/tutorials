package com.planning.apache.curator.recipes;

import com.planning.apache.curator.BaseManualTest;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListener;
import org.apache.curator.framework.recipes.locks.InterProcessSemaphoreMutex;
import org.apache.curator.framework.recipes.shared.SharedCount;
import org.apache.curator.framework.state.ConnectionState;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author planning
 * @since 2020-03-17 19:00
 **/
public class RecipesManualTest extends BaseManualTest {

    @Test
    public void givenRunningZookeeper_whenUsingLeaderElection_thenNoErrors() {
        try (final CuratorFramework client = newClient()) {
            client.start();
            LeaderSelector leaderSelector = new LeaderSelector(client, "/mutex/select/leader/for/job/A", new LeaderSelectorListener() {
                @Override
                public void takeLeadership(CuratorFramework client) throws Exception {
                    // I'm the leader of job A
                }

                @Override
                public void stateChanged(CuratorFramework curatorFramework, ConnectionState connectionState) {

                }
            });

            leaderSelector.start();

            // Wait until the job A is done among all the members

            leaderSelector.close();

        }
    }

    @Test
    public void givenRunningZookeeper_whenUsingShardLock_thenNoErrors() throws Exception {
        try (CuratorFramework client = newClient()) {
            client.start();
            InterProcessSemaphoreMutex sharedLock = new InterProcessSemaphoreMutex(client, "/mutex/process/A");

            sharedLock.acquire();

            // Do process A

            sharedLock.release();
        }
    }

    @Test
    public void givenRunningZooKeeper_whenUsingShardCounter_thenCounterIsIncrement() throws Exception {
        try (CuratorFramework client = newClient()) {
            client.start();

            try (SharedCount counter = new SharedCount(client, "/counter/A", 0)) {
                counter.start();

                counter.setCount(0);
                counter.setCount(counter.getCount() + 1);

                assertThat(counter.getCount()).isEqualTo(1);
            }
        }
    }

}