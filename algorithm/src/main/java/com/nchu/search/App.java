package com.nchu.search;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

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


    /**
     * @Description 测试插值查找，适合分布均匀的序列
     * @Author yangsj
     * @Date 2019/9/11 14:52
     **/
    @Test
    public void testInsertValueSearch(){
        /*int[] arry = new int[100];
        for(int i = 0 ; i < 100 ;i++ ){
            arry[i]=i;
        }*/
        int[] arry = {1,9,43,433,1000,3499,5900,10000,10001,29329};
        BinarySearch binarySearch = new BinarySearch();
        InsertValueSearch insertValueSearch = new InsertValueSearch();
        int index = insertValueSearch.insertValueSearch(arry, 0, arry.length - 1, 1000);
        //二分查找的时间复杂度为log(n)
        //int index = binarySearch.binarySearch(arry, 0, arry.length - 1, 1);
        if(index == -1){
            System.out.println("未找到该元素！");
        }else{
            System.out.println("要查找值的索引为："+ index);
        }
    }


    /**
     * @Description 测试斐波那契查找算法
     * @Author yangsj
     * @Date 2019/9/12 14:45
     **/
    @Test
    public void testFibonacciSearch(){
        int[] array = {1,8, 10, 89, 1000, 1234};
        FibonacciSearch fibonacciSearch = new FibonacciSearch();
      /*  int[] fibnacci = fibonacciSearch.fibonacci();
        System.out.println(Arrays.toString(fibnacci));*/
        int index = fibonacciSearch.fibonacciSearch(array, 1234);
        if(index == -1){
            System.out.println("未找到该元素！");
        }else{
            System.out.println("要查找值的索引为："+ index);
        }

    }

}
