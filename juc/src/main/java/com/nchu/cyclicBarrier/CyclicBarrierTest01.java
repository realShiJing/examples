package com.nchu.cyclicBarrier;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @Decription  CyclicBarrier 模拟LOL英雄加载进入战场
 * @Author yangsj
 * @Date 2020/7/21 9:16
 **/
public class CyclicBarrierTest01 {

    public static void main(String[] args) {
        // 创建一个 parties 为 5 可循环使用的屏障
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5, new Runnable() {
            @Override
            public void run() {
                System.out.println("所有英雄均加载完成，进入战场准备战斗！");
            }
        });

        String[] heros = {"盖伦","寒冰","瞎子","瑞文","流浪"};
        // 创建线程池
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(8,
                10,
                100,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(100),
                r -> new Thread(r,"threadName"+r.hashCode()),
                new ThreadPoolExecutor.AbortPolicy());
        // 模拟英雄进入加载页面，都加载完成后进入战场
        for (int i = 0; i < 5; i++) {
            poolExecutor.execute(new Player(cyclicBarrier,heros[i]));
        }
    }

    static class Player implements Runnable{
        private CyclicBarrier barrier;

        private String hero;

        Player(CyclicBarrier barrier,String hero){
            this.barrier = barrier;
            this.hero = hero;
        }
        @Override
        public void run() {
            //模拟英雄随机加载时长
            try {
                System.out.println(hero + "英雄进入加载页面");
                TimeUnit.SECONDS.sleep(new Random().nextInt(10));
                System.out.println(hero + "英雄加载完成");
                // 屏障使用Condition阻塞当前线程，屏障 parties  减一，等待屏障 parties 为零时放行
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }

        }
    }
}
