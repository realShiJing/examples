package com.nchu.api.redis.lock;

import com.nchu.api.redis.JedisUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.params.SetParams;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Decription Redis 实现分布式锁
 * @Author yangsj
 * @Date 2020/5/26 22:39
 **/
public class RedisLock {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    // 锁键
    private String lockKey = "redis_lock";

    // 锁过期时间单位 毫秒
    private long lockLeaseTime = 200;

    // SET lock_key redis_lock NX PX 5000
    // NX 代表只在键不存在时，才对键进行设置操作。 PX 设置键的过期时间。
    SetParams params = SetParams.setParams().nx().px(lockLeaseTime);

    // redis 连接池
    private JedisPool pool = JedisUtils.getPool();

    /**
     * @Description 加锁，需保证加锁和设置过期时间的原子性
     * @Author yangsj
     * @Date 2020/5/28 22:53
     **/
    public void lock(String value){
         Jedis jedis = pool.getResource();
         try{
             for(;;){
                 // SET 命令返回 ok ，则证明获取锁成功
                 String lock = jedis.set(lockKey, value, params);
                 if("OK".equals(lock)){
                     // 加锁成功后，立即对锁进行校验判断是否需要进行延时
                     this.delay(value);
                 }
                 // 如果加锁失败，轮询进行加锁，直至加锁成功
             }
         }finally {
             jedis.close();
         }
    }

    /**
     * @Description  解锁 ，需保证判断锁的持有者和删除锁的原子性，不能释放别人的锁
     * @Author yangsj
     * @Date 2020/5/28 22:52
     **/
    public void unlock(String value){
        Jedis jedis = pool.getResource();
        // 判断锁是否由当前线程持有，如果当前线程持有锁，释放该锁
        String script =
                "if redis.call('get',KEYS[1]) == ARGV[1] then " +
                    " return redis.call('del',KEYS[1]) " +
                 " else " +
                 "  return 0 " +
                 " end ";
        try{
            Object result = jedis.eval(script, Collections.singletonList(lockKey), Collections.singletonList(value));
            if ("1".equals(result.toString())) {
                System.out.println(Thread.currentThread().getName()+">>线程锁释放成功！");
            }
        }finally {
            jedis.close();
        }
    }


    /**
     * @Description 锁延时，防止业务执行时间超过锁的默认过期时间导致锁的永久失效
     * @Author yangsj
     * @Date 2020/5/28 22:54
     **/
    public void delay(String value){
        new Thread(()->{
            Jedis jedis = pool.getResource();
            try{
                List<String> argvs = new ArrayList<>();
                // 锁的持有者 value 值
                argvs.add(value);
                // 锁的延时时间，默认为锁的初始过期时间
                argvs.add(String.valueOf(lockLeaseTime));
                for (;;){
                    // 判断业务代码是否执行完（是否还持有锁）
                    String script =
                            "if redis.call('get',KEYS[1]) == ARGV[1] then " +
                                    " return redis.call('EXPIRE',KEYS[1],ARGV[2]) " +
                                    " else " +
                                    "  return 0 " +
                                    " end ";
                    String flag = String.valueOf(jedis.eval(script, Collections.singletonList(lockKey), argvs));
                    if("0".equals(flag)){//延时失败，说明业务代码执行完，结束该子线程
                        return;
                    }
                    System.out.println(Thread.currentThread().getName()+">>延时成功标识" + flag);

                    try {
                        // 每隔 1/3 的 初始过期时间进行检查
                        Thread.sleep(lockLeaseTime/3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                 }
            }finally {
                jedis.close();
            }

        }).start();

    }




        /**
         * @Description 模拟高并发场景下数据的正确性
         * @Author yangsj
         * @Date 2020/5/28 23:43
         **/
        int count = 0;
        @Test
        public void test() throws InterruptedException {
            //并发执行线程数
            int threadNum = 5;

            RedisLock redisLock = new RedisLock();

            CountDownLatch countDownLatch = new CountDownLatch(threadNum);

            //ExecutorService executorService = Executors.newFixedThreadPool(threadNum);
            ThreadPoolExecutor executorService = new ThreadPoolExecutor(10,
                    20,
                    120L,
                    TimeUnit.SECONDS,
                    new LinkedBlockingQueue<>(100));

            long start = System.currentTimeMillis();

            for (int i = 0;i<threadNum;i++){
                executorService.execute(() -> {
                    //如果是集群可以通过Snowflake算法获取唯一的ID字符串，这里我采用UUID模拟唯一id值
                    String value = UUID.randomUUID().toString();
                    try {
                       redisLock.lock(value);

                        for (int j = 0; j < 100; j++) {
                            count++;
                        }
                        // 模拟业务执行代码大于锁的默认超时时间
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                       redisLock.unlock(value);
                    }
                    countDownLatch.countDown();
                });
            }
            // 等待所有线程执行完毕
            countDownLatch.await();
            long end = System.currentTimeMillis();
            System.out.println("执行线程数:{"+threadNum+"},总耗时:{"+(end-start)+"},count数为:{"+count+"}");
        }

 }
