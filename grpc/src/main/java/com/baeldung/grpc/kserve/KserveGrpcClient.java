package com.baeldung.grpc.kserve;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

/**
 * @author yxc
 * @date 2022/5/26 3:29 下午
 */
public class KserveGrpcClient {

    public static void main(String[] args) {
        String host = "10.13.68.11";
        int port = 31507;
        String modelName = "collector-test-iris-demo";

        // create a channel
        ManagedChannel channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
        // tensorflow.serving.PredictionServiceGrpc.PredictionServiceBlockingStub stub = tensorflow.serving.PredictionServiceGrpc.newBlockingStub(channel);

    }
}
