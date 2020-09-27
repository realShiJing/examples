package com.example.boot.redislock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Decription
 * @Author yangsj
 * @Date 2020/5/27 10:46 下午
 **/
@RestController
public class RedisLock {

    @Autowired
    private RedisTemplate redisTemplate;


    @RequestMapping("/decy")
    public void decyCount(){
        // 方案 一
        //synchronized (this){ 单机同步锁，在分布式集群环境下会失效

        //}
        // 方案 二，存在问题，加锁和设置过期时间不是原子操作
        String lockKey = "lockKey";
        String id = UUID.randomUUID().toString();// 需保证id全局唯一，此时应该使用雪花算法
        /*Boolean aBoolean = redisTemplate.opsForValue().setIfAbsent(lockKey, id);
        redisTemplate.expire(lockKey, 30, TimeUnit.SECONDS);*/

        // 方案 三 原子操作加锁和设置锁的过期时间
        // 存在问题：业务代码的执行时间超过了锁的过期时间，有可能出现释放别人锁的情况，导致锁的永久失效
        Boolean aBoolean = redisTemplate.opsForValue().setIfAbsent(lockKey, id, 30, TimeUnit.SECONDS);
        // 方案 四 开启一个子线程，10秒钟定时判断业务是否执行完毕，对锁的持有者进行续zhi命
        new Thread(()->{
            for (;;){
                if(String.valueOf(redisTemplate.opsForValue().get(lockKey)).equals(id)){
                    redisTemplate.expire(lockKey,30,TimeUnit.SECONDS);//判断id和设置过期时间不是原子操作，同样有可能会有并发问题
                }else {
                    return;
                }
                try {
                    Thread.sleep(1000);// 定时任务多长时间执行一次需要考量
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        if(!aBoolean){ // 加锁失败，直接返回
            return;
        }
        try{
            // 需要加锁保护的业务代码
            int stock = Integer.parseInt((String) redisTemplate.opsForValue().get("stock"));
            if(stock > 0){
                stock--;
                redisTemplate.opsForValue().set("stock",stock);
                System.out.println("扣减成功！剩余库存："+stock);
            }else {
                System.out.println("扣减失败，库存不足！");
            }
        }finally {// 加锁成功，业务代码执行完成后必须手工释放锁
            // 仍然存在问题，保证不了查询判断和解锁的原子性，如果在执行判断为true后，还未释放锁之前，锁失效了，就有可能释放了别人的锁
            // 这时必须使用 lua 脚本 释放锁
            if(String.valueOf(redisTemplate.opsForValue().get(lockKey)).equals(id)){
                redisTemplate.delete(lockKey);
            }
        }




    }

}
