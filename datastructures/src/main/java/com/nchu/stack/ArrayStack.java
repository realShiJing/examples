package com.nchu.stack;

/**
 * @Decription 数组实现栈
 * @Author yangsj
 * @Date 20190819 15:21
 **/
public class ArrayStack implements Stack {
    //数组用于存放栈数据
    private int[] elements;
    //数组的最大容量
    private int maxSize;
    //数组的初始容量
    private int initSize = 10;
    //顶指针,初始为-1,表示栈中无数据
    private int top = -1;



    //构造函数，初始化数组
    public ArrayStack() {
        elements = new int[initSize];
        this.maxSize = initSize;
    }

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        elements = new int[maxSize];
    }
    //判断栈是否满
    @Override
    public boolean isFull(){
        if(top == -1){
            return false;
        }
        if(top == maxSize -1){
            return true;
        }
        return false;
    }
    //判断栈是否空
    @Override
    public boolean isEmpty(){
        if(top == -1){
            return true;
        }
        return false;
    }
    //入栈
    @Override
    public void pop(int data){
        if(isFull()){
            System.out.println("栈已满！");
            return;
        }
        top++;
        elements[top] = data;
    }

    //出栈
    @Override
    public int push(){
        if(isEmpty()){
           throw new RuntimeException("栈已空！");
        }
        int data = elements[top];
        top--;
        return data;
    }

    //展示栈
    @Override
    public void show(){
        if(isEmpty()){
            System.out.println("栈为空！");
            return;
        }
        int index = top;
        while (index != -1){
            System.out.println("elements["+index+"]:"+ elements[index]);
            index--;
        }
    }
}
