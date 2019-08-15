package com.nchu.linkedlist;

import org.junit.Test;

/**
 * @Decription 链表测试
 * @Author yangsj
 * @Date 20190801 10:07
 **/
public class APP {

    /**
     * @Description 测试单链表
     * @Author yangsj
     * @Date 2019/8/1 10:12
     **/
    @Test
    public void testSigleLinkedList(){
        SingleLinkedList list = new SingleLinkedList();

        /*list.show();
        System.out.println("---------------");*/
        SingleLinkedList listSort = new SingleLinkedList();
        listSort.addSort(2);
        listSort.addSort(1);
        listSort.addSort(3);
        listSort.addSort(1);
        listSort.addSort(4);
        listSort.addSort(5);
        listSort.addSort(6);

        //listSort.show();

        // 链表的更新
//        listSort.update(listSort.headNode,2);
//        listSort.show();

        //链表的节点删除
//        listSort.delete(listSort.headNode,7);
//        listSort.show();

       //链表的长度
//        int size = listSort.size();
//        System.out.println(size);
//        listSort.show();

        //链表的倒数第 n 个元素
//        int data = listSort.getReciprocal(8);
//        System.out.println(data);

        //链表的翻转
       /*listSort.reversal();
       listSort.show();*/
       //链表的翻转打印
//        listSort.reversalPrint();
        //链表的有序合并（有序）
        SingleLinkedList list2 = new SingleLinkedList();
        list2.add(11);
        list2.add(23);
        list2.add(19);
        list2.add(12);
        list2.add(33);
        list2.add(10);
        list2.add(9);

        SingleLinkedList mergeList = listSort.mergeSort(list2);
        mergeList.show();
    }


    /**
     * @Description 测试双向链表
     * @Author yangsj
     * @Date 2019/8/15 15:07
     **/
    @Test
    public void testDoubleLinkedList(){
        DoubleLinkedList list = new DoubleLinkedList();

        //无序添加
       /* list.add(2);
        list.add(9);
        list.add(3);
        list.add(6);
        list.add(4);
        list.add(1);
        list.add(5);
        list.add(7);*/
        //遍历双向链表并打印
        //list.show();
        //有序添加
        list.addSort(4);
        list.addSort(2);
        list.addSort(9);
        list.addSort(1);
        list.addSort(3);
        list.addSort(4);
        list.addSort(1);
        list.addSort(5);

      /*  list.show();
        //删除
        list.delete(9);
        System.out.println("------------------");
        list.show();*/
      System.out.println(list.size());
    }

}
