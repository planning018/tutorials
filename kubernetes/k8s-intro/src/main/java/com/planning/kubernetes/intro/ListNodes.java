package com.planning.kubernetes.intro;

import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1Node;
import io.kubernetes.client.openapi.models.V1NodeList;
import io.kubernetes.client.util.Config;

import java.util.function.Consumer;

/**
 * @author yxc
 * @date 2021/9/27 4:09 下午
 */
public class ListNodes {

    public static void main(String[] args) throws Exception{
        ApiClient client = Config.defaultClient();
        CoreV1Api api = new CoreV1Api(client);
        V1NodeList nodeList = api.listNode(null, null, null, null, null, null,
                null, null, 10, false);
        nodeList.getItems()
                .forEach(new Consumer<V1Node>() {
                    @Override
                    public void accept(V1Node node) {
                        System.out.println(node.getMetadata());
                    }
                });
    }
}
