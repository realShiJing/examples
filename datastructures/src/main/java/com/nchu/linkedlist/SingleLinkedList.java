package com.nchu.linkedlist;

import java.util.Stack;

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

    /**
     * @Description 修改节点
     * @Author yangsj
     * @Date 2019/8/8 9:41
     **/
    public void update(Node headNode, int data){
        Node newNode = new Node(data);
        Node temp = headNode;
        if(temp.next == null){
           System.out.println("链表为空！");
        }
        while(true){
            if(temp.data == newNode.data){
                break;
            }
            temp = temp.next;
        }

        temp.data = newNode.data;
    }
    
    
    /**
     * @Description 删除节点
     * @Author yangsj
     * @Date 2019/8/9 15:07
     **/
    public void delete(Node headNode, int data){
        Node temp = headNode;
        if(temp.next == null){
            System.out.println("链表为空！");
            return;
        }
        while(true){
            if(temp.next == null){
                System.out.println("链表中不存在该数据");
                return;
            }
            //找到要删除节点的前一个节点
            if(temp.next.data == data){
                break;
            }
            temp = temp.next;
        }
        temp.next = temp.next.next;
    }


    /**
     * @Description 链表节点个数
     * @Author yangsj
     * @Date 2019/8/9 16:47
     **/
    public int  size(){
        Node temp = headNode.next;
        int length = 0 ;
        if(temp == null){
            return length;
        }
        while (true){
            length++;
            if(temp.next == null){
                break;
            }
            temp = temp.next;
        }
        return length;
    }


    /**
     * @Description 获取链表倒数第n个节点
     * @Author yangsj
     * @Date 2019/8/9 16:54
     **/
    public int getReciprocal(int n){
        Node temp = headNode;
        int length = this.size();
        if(n>length){
          throw new RuntimeException("索引越界");
        }
        //length 7  - n 2 +1 6
        int index = length - n + 1;
        for(int i = 1 ; i <= index ;i++){
            temp = temp.next;
        }
        return temp.data;
    }


    /**
     * @Description 单链表的反转
     * @Author yangsj
     * @Date 2019/8/13 9:51
     **/
    public void reversal(){
        //翻转链表的临时头结点
        Node reversalNode = new Node(0);
        Node temp = headNode.next;
        //链表为空或者链表只有一个节点，不需要进行翻转处理
        if(temp == null || temp.next == null){
           return;
        }
        //遍历节点，并翻转
        while (true){
            if(temp == null){
                break;
            }
            //保存遍历出节点的下个节点，防止断链
            Node next = temp.next;
            //将原链表遍历出的节点 ，添加到翻转头结点的下个节点
            temp.next = reversalNode.next;
            reversalNode.next = temp;
            temp = next;
        }
        headNode.next = reversalNode.next;
    }

    /**
     * @Description 从尾到头打印单链表
     * 方式1：反向遍历  破坏了原有链表结构
     * 方式2：Stack栈  保留了原有链表结构
     * @Author yangsj
     * @Date 2019/8/14 14:53
     **/
    public void reversalPrint(){
        Stack<Node> stack = new Stack<>();
        Node temp = headNode.next;
        if(temp == null){
            System.out.println("链表为空！");
            return;
        }

        while (true){
            if(temp == null){
                break;
            }
            stack.push(temp);
            temp = temp.next;
        }

        while (!stack.empty()){
            System.out.println(stack.pop());
        }
    }


    /**
     * @Description 合并两个单链表，并保持合并后的链表有序
     * 思路：将两个链表的元素入栈，并出栈有序添加
     * @Author yangsj
     * @Date 2019/8/14 15:26
     **/
    public SingleLinkedList mergeSort(SingleLinkedList list2){
        Stack<Node> stack = new Stack<>();
        //链表一元素入栈
        Node temp1 = headNode.next;
        while (temp1 != null){
            stack.push(temp1);
            temp1 = temp1.next;
        }
        //链表二元素入栈
        Node temp2 = list2.headNode.next;
        while (temp2 != null){
            stack.push(temp2);
            temp2 = temp2.next;
        }
        //声明新链表，并初始头结点
        SingleLinkedList list = new SingleLinkedList();
        list.headNode = new Node(0);
        while(!stack.empty()){
           list.addSort(stack.pop().data);
        }
        return list;
    }
}
