package com.nchu.linear.queue;

/**
 * @Decription 数组模拟环形队列
 * @Author yangshijing
 * @Date 20190729 15:07
 **/
public class CircleQueue implements Queue {
    //最大容量
    public int maxSize;
    //队列头索引 front 的初始值 = 0
    public int front;
    //队列尾索引:指向最后一个元素的后一个位置
    //rear 的初始值 = 0
    public int rear;
    //数组
    public int[] arry ;
    public CircleQueue(int maxSize) {
        this.maxSize = maxSize;
        arry = new int[maxSize];
    }

    /**
     * @Description 队列尾的下个索引 对 队列总大小取模 等于 队列头索引时 表示队列已满
     * 尾索引的下一个为头索引时表示队列满，会空出一个位置
     * @Author yangsj
     * @Date 2019/7/29 15:09
     **/
    @Override
    public boolean isFull() {
        //队列最后一个元素的后两个位置 等于 队列头索引时，表示队列已满，其实这时还要一个空位
        if(((rear + 1) % maxSize)==front){
            return true;
        }
        return false;
    }


    /**
     * @Description 队列尾索引和头索引相等时，表示队列尾空
     * @Author yangsj
     * @Date 2019/7/29 15:15
     **/
    @Override
    public boolean  isEmpty() {
        if(rear == front){
            return true;
        }
        return false;
    }


    /**
     * @Description 向队列中添加元素
     * @Author yangsj
     * @Date 2019/7/29 15:15
     **/
    @Override
    public void addQueue(int n) {
        if(isFull()){
            throw new RuntimeException("队列已满！");
        }
        arry[rear] = n;
        rear = (rear + 1) % maxSize ;
    }


    /**
     * @Description 从队列中获取元素
     * @Author yangsj
     * @Date 2019/7/29 16:17
     **/
    @Override
    public int getQueue() {
        if(isEmpty()){
            throw new RuntimeException("队列为空！");
        }
        int value =  arry[front];
        front = (front+1)% maxSize;
        return value;
    }

    @Override
    public void showQueue() {
        if(isEmpty()){
            System.out.println("队列为空！");
            return;
        }

        for(int i = front ; i <  front  + size() ; i++){
            System.out.println("arry["+ i % maxSize+"]："+arry[i % maxSize]);
        }
    }

    @Override
    public void showHead() {
        if(isEmpty()){
            System.out.println("队列为空！");
            return;
        }
        System.out.println("队列头元素为arry["+front+"]："+arry[front]);
    }

    // 求出当前队列有效数据的个数
    public int size() {
        // rear = 3
        // front = 0
        // maxSize = 4
        return (rear + maxSize - front) % maxSize;
    }
}
