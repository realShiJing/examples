package com.nchu.callable;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * @Decription Callable 测试
 * @Author yangsj
 * @Date 2020/1/6 16:36
 **/
public class App {

    @Test
    public void test() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Task task = new Task();
        Future<Integer> future = executorService.submit(task);
        // 注意调用get方法会阻塞当前线程，直到得到结果。
        // 所以实际编码中建议使用可以设置超时时间的重载get方法。
        System.out.println(future.get());
    }



    /**
     * @Description  FutureTask 测试
     * @Author yangsj
     * @Date 2020/1/6 16:54
     **/
    @Test
    public void test1() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Task task = new Task();
        FutureTask<Integer> futureTask = new FutureTask<>(task);
        executorService.submit(futureTask);
        // 注意调用get方法会阻塞当前线程，直到得到结果。
        // 所以实际编码中建议使用可以设置超时时间的重载get方法。
        System.out.println(futureTask.get());
    }
}
