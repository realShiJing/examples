package com.nchu.search;

import org.junit.Test;

import java.util.ArrayList;

/**
 * @Decription 查找算法的测试
 * @Author yangsj
 * @Date 20190910 10:20
 **/
public class App {


    /**
     * @Description 二分查找的测试
     * @Author yangsj
     * @Date 2019/9/10 10:20
     **/
    @Test
    public void testBinarySearch(){
        int[] arry = { 1, 1, 1, 4, 5, 6, 7, 8, 9, 10,11, 12, 13,14,15,16,17,18,19,20 };
        BinarySearch binarySearch = new BinarySearch();
        /*int index = binarySearch.binarySearch(arry, 0, arry.length - 1, 1);
        if(index == -1){
            System.out.println("未找到该元素！");
        }else{
            System.out.println("要查找值的索引为："+ index);
        }*/

        int[] arrylist = { 1, 1, 1, 4, 5, 6, 7, 8, 9, 10,10, 10, 13,14,15,16,17,18,19,20 };
        ArrayList<Integer> list = binarySearch.binarySearchList(arrylist, 0, arry.length - 1, 1);
        System.out.println(list);
    }

}
