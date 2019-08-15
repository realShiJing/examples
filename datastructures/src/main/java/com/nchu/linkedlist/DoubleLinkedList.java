package com.nchu.linkedlist;

/**
 * @Decription 双向链表
 * @Author yangsj
 * @Date 20190815 14:50
 **/
public class DoubleLinkedList {
    //双向链表属性：头结点
    private  Node headNode = new Node(0);
    class Node{
        int data;
        //节点的前一个节点
        Node pre;
        //节点的下个节点
        Node next;
        Node(int data){
            this.data = data;
        }

        @Override
        public String toString() {
            return "Node{ data = " + data + "}";
        }
    }


    /**
     * @Description 向链表中添加元素
     * @Author yangsj
     * @Date 2019/8/15 14:58
     **/
    public void add(int data){
        Node temp = headNode;
        //遍历出链表的最后一个节点
        while (temp.next != null){
            temp = temp.next;
        }
        Node node = new Node(data);
        temp.next = node;
        node.pre = temp;
    }


    /**
     * @Description 遍历并打印双向链表
     * @Author yangsj
     * @Date 2019/8/15 15:10
     **/
    public void show(){
        Node temp = headNode.next;
        if(temp == null){
            System.out.println("链表为空！");
        }
        while(temp != null){
            System.out.println(temp);
            temp = temp.next;
        }
    }


    /**
     * @Description 有序添加
     * @Author yangsj
     * @Date 2019/8/15 15:18
     **/
    public void addSort(int data){
        Node temp = headNode;
        Node node = new Node(data);
        while (true){
            //遍历到最后一个节点
            if(temp.next == null){
                break;
            }
            //遍历到node有序位置
            if(temp.data <= data && data < temp.next.data){
                break;
            }
            temp = temp.next;
        }
        if(temp.next == null){
            temp.next = node;
            node.pre = temp;
        }else{
            node.next = temp.next;
            temp.next.pre = node;
            temp.next = node;
            node.pre = temp;
        }
    }


    /**
     * @Description 删除节点
     * @Author yangsj
     * @Date 2019/8/15 16:47
     **/
    public void delete(int data){
        Node temp = headNode.next;
        while (true){
         if(temp == null){
             System.out.println("链表中不存在该节点");
             return;
         }
         if(temp.data ==  data){
             break;
         }
         temp = temp.next;
        }

        if(temp.next  == null){//如果是最后一个节点
            temp.pre.next = null;
            temp.pre = null;
        }else{//如果是中间节点
            temp.pre.next = temp.next;
            temp.next.pre = temp.pre;
        }
    }


    /**
     * @Description 链表节点的个数
     * @Author yangsj
     * @Date 2019/8/15 17:26
     **/
    public int size(){
        int count = 0 ;
        Node temp = headNode.next;
        while (temp != null){
            count ++ ;
            temp = temp.next;
        }
        return count;
    }
}
