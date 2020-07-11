package com.nchu.future;

import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * @Decription
 * @Author yangsj
 * @Date 2020/2/3 8:34 下午
 **/
public class App {
    /**
     * @Description  将 FutureTask 对象提交给 ThreadPoolExecutor 去执行
     * @Author yangsj
     * @Date 2020/2/3 8:35 下午
     **/
    @Test
    public void test() throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask<>(() -> 1 + 2);
        ExecutorService executorService = Executors.newCachedThreadPool();

        executorService.submit(futureTask);

        System.out.println(futureTask.get());
    }
    
    /**
     * @Description  FutureTask 对象直接被 Thread 执行
     * @Author yangsj
     * @Date 2020/2/3 8:39 下午
     **/
    @Test
    public void test1() throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask<>(() -> 1 + 2);

        Thread thread = new Thread(futureTask);
        thread.start();

        System.out.println(futureTask.get());
    }
}
