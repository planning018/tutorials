package com.planning.apache.curator;

import lombok.Data;

/**
 * @author planning
 * @since 2020-03-17 15:37
 **/
@Data
public class HostConfig {

    private String hostname;
    private int port;

    public HostConfig(String hostname, int port) {
        this.hostname = hostname;
        this.port = port;
    }
}