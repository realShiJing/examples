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
    public void TestSigleLinkedList(){
        SingleLinkedList list = new SingleLinkedList();

        list.show();
        System.out.println("---------------");
        SingleLinkedList listSort = new SingleLinkedList();
        listSort.addSort(2);
        listSort.addSort(1);
        listSort.addSort(3);
        listSort.addSort(1);
        listSort.addSort(4);
        listSort.addSort(5);
        listSort.addSort(6);
        listSort.show();

    }

}
