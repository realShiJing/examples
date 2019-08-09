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
}
