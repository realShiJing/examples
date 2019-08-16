package com.nchu.linkedlist;

/**
 * @Decription 单向循环链表
 * @Author yangsj
 * @Date 210190816 11:11
 **/
public class CycleSingleLinkedList {
    //节点
    class Node{
        //数据
        public int data;
        //下个节点
        public Node next;

        public Node(int data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "Node{ data = " + data +"}";
        }
    }

    //头指针，指向添加到链表中的第一个元素
    private Node first = null;
    //尾指针，指向最新添加的一个节点
    private Node end = null ;


    /**
     * @Description 向链表中添加元素
     * @Author yangsj
     * @Date 2019/8/16 11:19
     **/
    public void add(int data){
        Node node = new Node(data);
        if(first == null){//第一次添加节点,这时头指针和尾指针都指向该节点
            first = node;
            end = node;
        }else{
            //最后一个指针指向新添加的节点
            end.next = node;
            //新添加的节点的下个节点指向第一个节点
            node.next = first;
            end = node;
        }
    }


    /**
     * @Description 遍历循环单链表
     * @Author yangsj
     * @Date 2019/8/16 11:40
     **/
    public void show(){
        if(first == null){
            System.out.println("链表为空！");
            return;
        }
        //如果头指针和尾指针指向同一个节点，表示链表中只有一个节点
        if(first == end ){
            System.out.println(first);
            return;
        }

        Node temp = first;
        while (temp.next != first){
            System.out.println(temp);
            temp = temp.next;
        }
        //打印尾节点
        System.out.println(temp);
    }


    /**
     * @Description  循环链表有效元素的个数
     * @Author yangsj
     * @Date 2019/8/16 14:10
     **/
    public int  size(){
        int count = 0 ;
        if(first == null){
            return count;
        }
        if(first == end){
            return count  + 1 ;
        }
        Node temp = first;
        while (temp != end){
            count ++;
            temp = temp.next;
        }
        return ++count;
    }


    /**
     * @Description 约瑟夫环问题
     * @Author yangsj
     * @Date 2019/8/16 15:39
     **/
    public void josephu(int begin , int count){
        if(first == null){
            System.out.println("链表为空！");
        }
        if(first == end ){//链表只有一个节点
            System.out.print(first.data + "->" );
            return;
        }
        //先定位开始的节点，头指针和尾指针同时向后移动begin-1的位置
        for(int i = 1 ; i < begin ; i++ ){
            first = first.next;
            end = end.next;
        }
        while (first != end){
            //遍历count个节点，并打印和删除该节点
            for(int i = 1 ;i < count ; i++){
                first = first.next;
                end = end.next;
            }
            //打印节点
            System.out.print(first.data + "->");
            //删除节点
            end.next = first.next;
            first = first.next;
        }
        System.out.print(first.data + "->");
    }
}
