package com.nchu.stack;

/**
 * @Decription 链表实现栈
 * @Author yangsj
 * @Date 20190819 16:42
 **/
public class LinkedStack implements Stack {
    class Node{
        //节点数据
        int data;
        //下个节点
        Node next;
        //上个节点
        Node pre;

        public Node(int data) {
            this.data = data;
        }
    }

    //栈低
    private Node bottom;

    //栈顶
    private Node top;

    //栈中元素个数
    private int size;

    //栈的最大容量
    private int maxSize;

    public LinkedStack(int maxSize) {
        this.maxSize = maxSize;
    }

    
    /**
     * @Description 判断栈是否满
     * @Author yangsj
     * @Date 2019/8/19 16:49
     **/
    @Override
    public boolean isFull(){
        if(bottom == null){
            return false;
        }
        if(size == maxSize){
            return true;
        }
        return false;
    }


    /**
     * @Description 判断栈是否空
     * @Author yangsj
     * @Date 2019/8/19 16:57
     **/
    @Override
    public boolean isEmpty(){
        if(bottom == null){
            return true;
        }
        return false;
    }


    /**
     * @Description 入栈
     * @Author yangsj
     * @Date 2019/8/19 16:59
     **/
    @Override
    public void push(int data){
        if(isFull()){
            System.out.println("栈已满！");
            return;
        }
        //栈的大小加一
        size++;
        Node node = new Node(data);
        if(isEmpty()){//第一次入栈
            bottom = node;
            top = bottom;
            return;
        }
        //栈顶的下个节点指向新的节点
        top.next = node;
        //新的节点的前一个节点指向栈顶
        node.pre = top;
        //将栈顶更新为新的节点
        top = node;
    }


    /**
     * @Description 出栈
     * @Author yangsj
     * @Date 2019/8/19 17:10
     **/
    @Override
    public int pop(){
        if(isEmpty()){
            throw new RuntimeException("栈为空！");
        }
        int val = top.data;
        //如果出栈到栈低
        if(top == bottom){
            bottom = null;
            size = 0 ;
            return val;
        }
        //没有出栈到栈低时，栈顶的前一个节点的下个节点指向空（即删除栈顶节点）
        top.pre.next = null;
        //将栈顶指向前一个节点
        top = top.pre;
        size--;
        return val;
    }


    /**
     * @Description 展示栈中元素
     * @Author yangsj
     * @Date 2019/8/19 17:15
     **/
    @Override
    public void show(){
        if(isEmpty()){
            System.out.println("栈为空！");
            return;
        }
        //栈中只有一个节点
        if(bottom == top){
            System.out.print("Node(" + bottom.data+")->");
            return;
        }
        Node temp = bottom;
        while (temp != top){
            System.out.print("Node(" + temp.data+")->");
            temp = temp.next;
        }
        System.out.println("Node(" + temp.data+")->");
    }
}
