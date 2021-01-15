package com.nchu.blockqueue;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ClassName: MiniArrayBrokingQueue
 * Description:
 * date: 2020/4/6 20:56
 */
public class MiniArrayBrokingQueue implements BlockingQueue {
    //线程并发控制
    private Lock lock = new ReentrantLock();
    /**
     * 当生产者线程生产数据时，它会先检查当前queues是否已经满了，如果已经满了，需要将当前生产者线程 调用notFull.await()
     * 进入到notFull条件队列挂起。等待消费者线程消费数据时唤醒。
     */
    private Condition notFull = lock.newCondition();

    /**
     * 当消费者线程消费数据时，它会先检查当前queues中是否有数据，如果没有数据,需要将当前消费者线程 调用notEmpty.await()
     * 进入到notEmpty条件队列挂起。等待生产者线程生产数据时唤醒。
     */
    private Condition notEmpty = lock.newCondition();


    //底层存储元素的数组
    private Object[] queues;
    //数组长度
    private int size;

    /**
     * count:当前队列中可以被消费的数据量
     * putptr:记录生产者存放数据的下一次位置。每个生产者生产完一个数据后，会将 putptr ++
     * takeptr:记录消费者消费数据的下一次的位置。每个消费者消费完一个数据后，将将takeptr ++
     */
    private int count,putptr, takeptr;


    public MiniArrayBrokingQueue(int size) {
        this.size = size;
        this.queues = new Object[size];
    }



    @Override
    public void put(Object element) throws InterruptedException {
        lock.lock();
        try {
            //第一件事？ 判断一下当前queues是否已经满了...
            if(count == this.size) {
                notFull.await();
            }

            //执行到这里，说明队列未满，可以向队列中存放数据了..
            this.queues[putptr] = element;

            putptr ++;

            if(putptr == this.size) putptr = 0;
            //生产数据 自增count
            count ++;

            //当向队列中成功放入一个元素之后，需要做什么呢？
            //需要给notEmpty一个唤醒信号
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public Object take() throws InterruptedException {
        lock.lock();
        try {
            //第一件事？判断一下当前队列是否有数据可以被消费...
            if(count == 0) {
                notEmpty.await();
            }

            //执行到这里，说明队列有数据可以被消费了..
            Object element = this.queues[takeptr];

            takeptr ++;
            if(takeptr == this.size) takeptr = 0;
            //生产数据 自减count
            count --;

            //当向队列中成功消费一个元素之后，需要做什么呢？
            //需要给notFull一个唤醒信号
            notFull.signal();

            return element;
        }finally {
            lock.unlock();
        }
    }


    public static void main(String[] args) {
        BlockingQueue<Integer> queue = new MiniArrayBrokingQueue(10);

        Thread producer = new Thread(() -> {
            int i = 0;
            while(true) {
                i ++;
                if(i == 10) i = 0;

                try {
                    System.out.println("生产数据：" + i);
                    queue.put(Integer.valueOf(i));
                    TimeUnit.MILLISECONDS.sleep(200);
                } catch (InterruptedException e) {
                }
            }
        });
        producer.start();


        Thread consumer = new Thread(() -> {
            while(true) {
                try {
                    Integer result = queue.take();
                    System.out.println("消费者消费：" + result);
                    TimeUnit.MILLISECONDS.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        consumer.start();
    }
}
