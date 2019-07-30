package com.nchu.linear.queue;

import org.junit.Test;

import java.util.Scanner;

/**
 * @Decription 队列测试
 * @Author yangshijing
 * @Date 20190729 10:42
 **/
public class APP {


    public static void main(String[] args){
        Queue queue = new ArrayQueue(4);
        queue = new CircleQueue(4);
        TestQueue(queue);
    }

    /**
     * @Description 数组模拟队列测试
     * @Author yangsj
     * @Date 2019/7/29 11:10
     **/
    public static void TestQueue(Queue queue){
        //接受用户输入
        char key = ' ';
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop){
            System.out.println("s(showQueue):   显示队列");
            System.out.println("a(addQueue):    添加元素到队列");
            System.out.println("g(getQueue):    从队列中取出元素");
            System.out.println("h(showHead):    查看队头元素");
            System.out.println("e(exit):        退出");
            key = scanner.next().charAt(0);
            switch (key) {
                case 's':
                    queue.showQueue();
                    break;
                case 'a':
                    int n = scanner.nextInt();
                    try{
                        queue.addQueue(n);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'g':
                    try{
                        int head = queue.getQueue();
                        System.out.println("获取的队列头元素为："+ head);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    queue.showHead();
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出！");
    }
}
