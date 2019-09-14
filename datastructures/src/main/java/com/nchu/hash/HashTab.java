package com.nchu.hash;

import com.nchu.linkedlist.SingleLinkedList;

import java.util.Scanner;

/**
 * @Decription 自定义hash表
 * @Author yangsj
 * @Date 2019-09-14 15:28
 **/
public class HashTab {

    //初始容量
    public  int size = 16;

    //用于存放链表的数组
    public SingleLinkedList[] tab ;

    /**
     * @Description  hashtab构造方法，用于指定数组的初始容量和初始化链表
     * @Author yangsj
     * @Date 2019-09-14 15:53
     **/
    public HashTab(int size) {
        this.size = size;
        tab = new SingleLinkedList[size];
        for(int i = 0 ; i < size ; i++){
            tab[i] = new SingleLinkedList();
        }
    }

    /**
     * @Description  计算hash值，用于定位Node节点在hash表中存放的位置
     * @Author yangsj
     * @Date 2019-09-14 15:30
     **/
    public int hash(int data){
        return  data % size;
    }

    /**
     * @Description  向hash表中添加数据
     * @Author yangsj
     * @Date 2019-09-14 15:54
     **/
    public void add(int data){
        tab[hash(data)].add(data);
    }

    /**
     * @Description  遍历hash表
     * @Author yangsj
     * @Date 2019-09-14 15:59
     **/
    public void list(){
        for(int i =0 ; i < size ; i++){
            System.out.print("第"+(i+1)+"个链表");
            tab[i].show();
        }
    }
    /**
     * @Description  查找元素在hash表中的位置
     * @Author yangsj
     * @Date 2019-09-14 16:04
     **/
    public void find(int data){
        int hash = hash(data);
        boolean b = tab[hash].find(data);
        if (b){
            System.out.println("该元素存放在第"+(hash + 1)+"个链表中");
        }else {
            System.out.println("该hash表中不存在该元素");
        }
    }
    /**
     * @Description  删除节点
     * @Author yangsj
     * @Date 2019-09-14 16:36
     **/
    public  void delete(int data){
        int hash = hash(data);
        tab[hash].delete(data);
    }

    /**
     * @Description  测试验证
     * @Author yangsj
     * @Date 2019-09-14 17:25
     **/
    public static void main(String[] args) {
        //创建哈希表
        HashTab hashTab = new HashTab(7);

        //写一个简单的菜单
        String key = "";
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.println("add:  添加节点");
            System.out.println("list: 显示节点");
            System.out.println("find: 查找节点");
            System.out.println("delete: 删除节点");
            System.out.println("exit: 退出系统");

            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.println("输入data");
                    int data = scanner.nextInt();
                    hashTab.add(data);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "find":
                    System.out.println("请输入要查找的data");
                    data = scanner.nextInt();
                    hashTab.find(data);
                    break;
                case "delete":
                    System.out.println("请输入要删除的data");
                    data = scanner.nextInt();
                    hashTab.delete(data);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                default:
                    break;
            }
        }
    }
}
