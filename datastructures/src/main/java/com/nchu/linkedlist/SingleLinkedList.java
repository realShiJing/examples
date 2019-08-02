package com.nchu.linkedlist;

/**
 * @Decription 单链表
 * @Author yangsj
 * @Date 20190801 9:17
 **/
public class SingleLinkedList {
    //节点
    class Node{
        //节点数据
        public  int data;
        //下个节点的引用
        public  Node next;

        Node(int data){
            this.data = data;
        }

        @Override
        public String toString() {
            return "Node{ data=" + data + " }";
        }
    }
    //头结点
    public Node headNode = new Node(0);

    //添加节点到单链表的尾部
    public void add(int data){
        Node node = new Node(data);
        //辅助节点，用于保存单链表最后一个节点
        Node temp = headNode;
        while (true){
            //如果辅助节点没有下个节点，代表已经遍历到最后一个节点
            if(temp.next == null){
                break;
            }
            temp = temp.next;
        }
        //添加节点到最后一个节点的下个节点
       temp.next = node;
    }

    //遍历列表
    public void show(){
        Node temp = headNode;
        if(temp.next == null){
            System.out.println("链表为空！");
            return;
        }

        while (true){
            temp = temp.next;
            if(temp == null){
                break;
            }
            System.out.println(temp);
        }
    }

    //有序添加
    public void addSort(int data){
        Node node = new Node(data);
        Node temp = headNode;
        while(true){
            //如果临时节点的下个节点为空，代表已经遍历到了最后，都没有符合条件的
            //这时直接将节点加到链表的最后
            if (temp.next == null){
                temp.next = node;
                return;
            }
            int tempData = temp.data;
            int nextdata = temp.next.data;
            if(tempData <= data && data < nextdata){
                break;
            }
            temp = temp.next;
        }
        node.next = temp.next;
        temp.next = node;
    }
}
