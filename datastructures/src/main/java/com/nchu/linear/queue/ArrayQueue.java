package com.nchu.linear.queue;

/**
 * @Decription 数组模拟队列（只能使用一次）
 * @Author yangshijing
 * @Date 20190729 9:45
 **/
public class ArrayQueue implements Queue {
    //最大容量
    public int maxSize;
    //队列头索引
    public int front;
    //队列尾索引
    public int rear;
    //数组
    public int[] arry ;
    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        arry = new int[maxSize];
    }


    /**
     * @Description 判断队列是否满
     * @Author yangsj
     * @Date 2019/7/29 9:51
     **/
    @Override
    public boolean isFull(){
        if(rear == maxSize ){
            return true;
        }
        return false;
    }

    
    /**
     * @Description 判断队列是否为空
     * @Author yangsj
     * @Date 2019/7/29 9:53
     **/
    @Override
    public boolean isEmpty(){
        if(front == rear){
            return true;
        }
        return false;
    }
    
    /**
     * @Description 添加元素到队列，从队尾添加
     * @Author yangsj
     * @Date 2019/7/29 9:58
     **/
    @Override
    public void addQueue(int n){
        if(isFull()){//队列已满
            throw new RuntimeException("队列已满！");
        }
        arry[rear++] = n ;
    }
    

    /**
     * @Description 从队列头取出数据
     * @Author yangsj
     * @Date 2019/7/29 10:07
     **/
    @Override
    public int getQueue(){
        if(isEmpty()){
            throw new RuntimeException("队列为空！");
        }
        return arry[front++];
    }


    /**
     * @Description 查看队列头元素
     * @Author yangsj
     * @Date 2019/7/29 10:32
     **/
    @Override
    public void showHead(){
        if(isEmpty()){
           System.out.println("队列为空！");
           return;
        }
       System.out.println("队列头元素为arry["+front+"]："+arry[front]);
    }


    /**
     * @Description 查看队列全部数据
     * @Author yangsj
     * @Date 2019/7/29 10:30
     **/
    public void showQueue(){
        if(isEmpty()){
            System.out.println("队列为空！");
            return;
        }
        for(int i = front ; i < rear ; i++ ){
            System.out.println("arry["+i+"]"+arry[i]);
        }
    }
}
