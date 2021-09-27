package com.planning.kubernetes.intro;

import io.kubernetes.client.openapi.ApiCallback;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import okhttp3.Call;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * @author yxc
 * @date 2021/9/27 4:24 下午
 */
public class AsyncHelper<R> implements ApiCallback<R> {

    private static final Logger logger = LoggerFactory.getLogger(AsyncHelper.class);

    private CoreV1Api api;
    private CompletableFuture<R> callResult;

    public AsyncHelper(CoreV1Api api) {
        this.api = api;
    }

    public static <T> CompletableFuture<T> doAsync(CoreV1Api api, ApiInvoker<T> invoker) {
        AsyncHelper<T> helper = new AsyncHelper<>(api);
        return helper.execute(invoker);
    }

    private CompletableFuture<R> execute(ApiInvoker<R> invoker) {
        try {
            callResult = new CompletableFuture<>();
            logger.info("[I38] Calling API...");
            Call call = invoker.apply(api, this);
            logger.info("[I41] API Successfully invoked: method={}, url={}",
                    call.request().method(),
                    call.request().url());
            return callResult;
        } catch (ApiException e) {
            callResult.completeExceptionally(e);
            return callResult;
        }
    }

    @Override
    public void onFailure(ApiException e, int statusCode, Map<String, List<String>> responseHeaders) {
        logger.error("[E53] onFailure", e);
        callResult.completeExceptionally(e);
    }

    @Override
    public void onSuccess(R result, int statusCode, Map<String, List<String>> map) {
        logger.error("[E61] onSuccess: statusCode={}", statusCode);
        callResult.complete(result);
    }

    @Override
    public void onUploadProgress(long bytesWritten, long contentLength, boolean done) {
        logger.info("[E61] onUploadProgress: bytesWritten={}, contentLength={}, done={}", bytesWritten, contentLength, done);
    }

    @Override
    public void onDownloadProgress(long bytesRead, long contentLength, boolean done) {
        logger.info("[E75] onDownloadProgress: bytesRead={}, contentLength={}, done={}", bytesRead, contentLength, done);
    }
}
