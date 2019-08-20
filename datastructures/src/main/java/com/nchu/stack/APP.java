package com.nchu.stack;

import java.util.Scanner;

/**
 * @Decription 栈的测试
 * @Author yangsj
 * @Date 20190819 15:49
 **/
public class APP {


    public static void main(String[] args){
        //测试数组实现栈
        Stack stack = new ArrayStack(3);
        //测试双链表实现栈
        stack = new LinkedStack(3);
        testStack(stack);

    }
    /**
     * @Description 测试自定义栈
     * @Author yangsj
     * @Date 2019/8/19 15:50
     **/
    public static void testStack(Stack stack){
        //控制台输入
        Scanner scanner = new Scanner(System.in);
        //循环标志
        boolean loop = true;
        char key = ' ';
        while (loop){
            System.out.println("show：");
            System.out.println("push：");
            System.out.println("pop：");
            System.out.println("exit：");
            key = scanner.next().charAt(0);
            switch(key){
                case 's':
                    stack.show();
                    break;
                case 'p':
                    int data = scanner.nextInt();
                    stack.push(data);
                    break;
                case 'o':
                    try{
                        int value = stack.pop();
                        System.out.println(value);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("退出！");
    }
}
