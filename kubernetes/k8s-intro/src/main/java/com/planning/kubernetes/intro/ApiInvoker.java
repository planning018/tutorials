package com.planning.kubernetes.intro;

import io.kubernetes.client.openapi.ApiCallback;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.apis.CoreApi;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import okhttp3.Call;

/**
 * @author yxc
 * @date 2021/9/27 4:07 下午
 */
@FunctionalInterface
public interface ApiInvoker<R> {

    Call apply(CoreV1Api api, ApiCallback<R> callback) throws ApiException;
}
