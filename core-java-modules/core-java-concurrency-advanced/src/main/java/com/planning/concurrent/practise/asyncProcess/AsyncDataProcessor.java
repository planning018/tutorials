package com.planning.concurrent.practise.asyncProcess;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ForkJoinPool;
import java.util.function.Function;

/**
 * 异步数据处理.
 * <p>通过线程池，对一批数据进行多线程分片处理<p/>
 *
 * @author: xiyt
 * @date: 2018/12/8
 */
public class AsyncDataProcessor<T, R> {
    /**
     * 默认的每个线程处理的数据量
     */
    public static final Integer DEFAULT_DATA_SIZE_OF_THREAD = 100;
    private static final Logger LOGGER = LoggerFactory.getLogger(AsyncDataProcessor.class);
    /**
     * 待处理的全量数据
     */
    private List<T> data;

    /**
     * 线程池对象
     */
    private Executor executor;

    /**
     * 每个线程处理的数据量
     */
    private Integer dataSizeOfThread;

    /**
     * 处理结果
     */
    private List<CompletableFuture<R>> completableFutures = new ArrayList<>();

    /**
     * 正常结果
     */
    private List<R> results = new ArrayList<>();

    /**
     * 异常信息
     */
    private List<Throwable> errors = new ArrayList<>();

    /**
     * 构造方法.
     * <p>通过SpringContextHolder自动获取TaskExecutor，默认每个线程处理100条数据</p>
     *
     * @param data 待处理的全量数据
     * @return:
     * @author: xiyt
     * @date: 2018/12/10
     */
    public AsyncDataProcessor(List<T> data) {
        this(data, DEFAULT_DATA_SIZE_OF_THREAD);
    }

    /**
     * 构造方法.
     * <p>使用默认的线程池</p>
     *
     * @param data             待处理的全量数据
     * @param dataSizeOfThread 每个线程处理的数据量，默认100
     * @return:
     * @author: xiyt
     * @date: 2018/12/10
     */
    public AsyncDataProcessor(List<T> data, int dataSizeOfThread) {
        this(ForkJoinPool.commonPool(), data, dataSizeOfThread);
    }

    /**
     * 构造方法.
     *
     * @param executor         线程池执行对象
     * @param data             待处理的全量数据
     * @param dataSizeOfThread 每个线程处理的数据量，默认100
     * @return:
     * @author: xiyt
     * @date: 2018/12/10
     */
    public AsyncDataProcessor(Executor executor, List<T> data, int dataSizeOfThread) {
        this.data = data;
        this.dataSizeOfThread = dataSizeOfThread;
        this.executor = executor;
    }

    /**
     * 分批执行data数据，并阻塞返回结果.
     *
     * @param function 对数据处理的方法
     * @return:
     * @author: xiyt
     * @date: 2018/12/8
     */
    public void run(Function<List<T>, R> function) {
        // 按照dataSizeOfThread进行数据分页
        int section = (int) ((double) Math.ceil((double) data.size() / (double) dataSizeOfThread));
        for (int i = 1; i <= section; i++) {
            int end = i * dataSizeOfThread > data.size() ? data.size() : i * dataSizeOfThread;
            List<T> subList = data.subList((i - 1) * dataSizeOfThread, end);
            // 通过supplyAsync工厂方法构造CompletableFuture，并handle处理结果
            CompletableFuture future = CompletableFuture.supplyAsync(() -> function.apply(new ArrayList<>(subList)), executor)
                    .handle((result, exception) -> {
                        if (null == exception) {
                            // 保存正常结果
                            this.results.add(result);
                        } else {
                            // 保存异常信息
                            this.errors.add(exception);
                        }
                        return result;
                    });
            completableFutures.add(future);
        }
    }

    /**
     * 阻塞获取所有结果.
     *
     * @param
     * @return:
     * @author: xiyt
     * @date: 2018/12/10
     */
    public AsyncDataProcessor join() {
        CompletableFuture.allOf(completableFutures.toArray(new CompletableFuture[completableFutures.size()])).join();
        return this;
    }

    /**
     * 是否发生有错误.
     *
     * @param
     * @return:
     * @author: xiyt
     * @date: 2018/12/10
     */
    public Boolean hasError() {
        return CollectionUtils.isNotEmpty(this.errors);
    }

    /**
     * 获取异常信息.
     *
     * @param
     * @return:
     * @author: xiyt
     * @date: 2018/12/10
     */
    public List<Throwable> getErrors() {
        return errors;
    }

    /**
     * 获取正常结果信息.
     *
     * @param
     * @return:
     * @author: xiyt
     * @date: 2018/12/10
     */
    public List<R> getResults() {
        return results;
    }


//    public static void main(String[] args) {
//        List<String> list = Arrays.asList(new String[]{"qqq", "www", "eee", "rrr", "ttt", "yyy"});
//        AsyncTaskForkJoin<String, String> asyncTaskForkJoin = new AsyncTaskForkJoin(ForkJoinPool.commonPool(), list, 4);
//        asyncTaskForkJoin.run(subData -> {
//            if (subData.size() == 4) {
//                // 这里抛出异常
//                System.out.println(subData.get(4));
//            }
//            subData.forEach(item -> {
//                System.out.println(Thread.currentThread().getId() + "=" + item + "=" + item.length());
//            });
//            return "OK";
//        });
//        List results = asyncTaskForkJoin.join().getResults();
//        List error = asyncTaskForkJoin.join().getErrors();
//        System.out.println(results.size());
//        System.out.println(error.size());
//    }
}
