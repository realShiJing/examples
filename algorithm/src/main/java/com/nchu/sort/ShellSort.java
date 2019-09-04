package com.nchu.sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Decription 希尔排序
 * @Author yangsj
 * @Date 20190904 09:39
 **/
public class ShellSort {

    /**
     * @Description 移位法，希尔排序过程推演
     * @Author yangsj
     * @Date 2019/9/4 9:41
     **/
    @Test
    public void shellSortDemo(){
        int[] arry = { 8, 9, 1, 7, 2, 3, 5, 4, 6, 0 };
        //第一次分为五组，增量为5
        //{{8,3},{9,5},{1,4},{7,6},{2,0}}
        int gap = 5 ;
        //对每一组执行插入排序
        for(int i = 5 ; i < arry.length; i++){
            //待插入的元素
            int temp = arry[i];
            //插入的索引,和前面第gap个元素比较,执行插入排序，步长为5
            int index = i - gap;
            while (index >= 0 && temp < arry[index]){
                arry[index + gap] = arry[index];
                index -= gap;
            }
            arry[index + gap] = temp;
        }
        //[3, 5, 1, 6, 0, 8, 9, 4, 7, 2]
        System.out.println("第一趟希尔排序后的数组：" + Arrays.toString(arry));

        //第二次分为两组，增量为2
        //{3 , 1 , 0 , 9 , 7},{ 5, 6, 8, 4, 2}
        //{0 , 1 , 3 , 7 , 9},{ 2, 4, 5, 6, 8}
        gap = 2 ;
        //对每一组执行插入排序
        for(int i = 2 ; i < arry.length; i++){
            //待插入的元素
            int temp = arry[i];
            //插入的索引,和前面第gap个元素比较,执行插入排序，步长为5
            int index = i - gap;
            while (index >= 0 && temp < arry[index]){
                arry[index + gap] = arry[index];
                index -= gap;
            }
            arry[index + gap] = temp;
        }
        //[0, 2, 1, 4, 3, 5, 7, 6, 9, 8]
        System.out.println("第二趟希尔排序后的数组：" + Arrays.toString(arry));

        //第三次分为一组，增量为1
        //{0, 2, 1, 4, 3, 5, 7, 6, 9, 8}
        gap = 1 ;
        //对每一组执行插入排序
        for(int i = 1 ; i < arry.length; i++){
            //待插入的元素
            int temp = arry[i];
            //插入的索引,和前面第gap个元素比较,执行插入排序，步长为5
            int index = i - gap;
            while (index >= 0 && temp < arry[index]){
                arry[index + gap] = arry[index];
                index -= gap;
            }
            arry[index + gap] = temp;
        }
        //[0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
        System.out.println("第三趟希尔排序后的数组：" + Arrays.toString(arry));
    }


    /**
     * @Description 希尔排序
     * @Author yangsj
     * @Date 2019/9/4 10:44
     **/
    public void shellSort(int[] arry){
        int gap = arry.length/2 ;
        //排序的次数
        int count = 0;
        while (gap >= 1){
            //对每一组执行插入排序
            for(int i = gap ; i < arry.length; i++){
                //待插入的元素
                int temp = arry[i];
                //插入的索引,和前面第gap个元素比较,执行插入排序，步长为5
                int index = i - gap;
                while (index >= 0 && temp < arry[index]){
                    arry[index + gap] = arry[index];
                    index -= gap;
                }
                arry[index + gap] = temp;
            }
            gap = gap/2;
            System.out.println("第"+(++count)+"趟希尔排序后的数组：" + Arrays.toString(arry));
        }

    }



    /**
     * @Description 冒泡法实现 希尔排序 过程推演
     * @Author yangsj
     * @Date 2019/9/4 10:58
     **/
    @Test
    public void shellSortByBubbleDemo(){
        int[] arry = { 8, 9, 1, 7, 2, 3, 5, 4, 6, 0 };
        //临时变量，交换时用于存放数据
        int temp = 0 ;
        int gap = arry.length/2;
        //第一次排序，分为五组
        //{{8,3},{9,5},{1,4},{7,6},{2,0}}
        for(int i = gap; i < arry.length; i++){
            //每一组进行冒泡排序，将每组的最小值冒泡到最前列
            for (int j = i - gap ; j >= 0 ; j -= gap){
                if(arry[j] > arry[ j + gap]){
                    temp = arry[ j + gap ];
                    arry[j + gap] = arry[j];
                    arry[j] = temp;
                }
            }
        }
        //[3, 5, 1, 6, 0, 8, 9, 4, 7, 2]
        System.out.println("第一趟希尔排序后的数组"+ Arrays.toString(arry));

        //第二次排序，分为两组
        gap = gap/2;
        //{3 , 1 , 0 , 9 , 7},{ 5, 6, 8, 4, 2}
        //{0 , 1 , 3 , 7 , 9},{ 2, 4, 5, 6, 8}
        for(int i = gap; i < arry.length; i++){
            //每一组进行冒泡排序，将每组的最小值冒泡到最前列
            for (int j = i - gap ; j >= 0 ; j -= gap){
                if(arry[j] > arry[ j + gap]){
                    temp = arry[ j + gap ];
                    arry[j + gap] = arry[j];
                    arry[j] = temp;
                }
            }
        }
        //[0, 2, 1, 4, 3, 5, 7, 6, 9, 8]
        System.out.println("第二趟希尔排序后的数组"+ Arrays.toString(arry));

        //第三次排序，分为一组
        gap = gap/2;
        //[0, 2, 1, 4, 3, 5, 7, 6, 9, 8]
        for(int i = gap; i < arry.length; i++){
            //每一组进行冒泡排序，将每组的最小值冒泡到最前列
            for (int j = i - gap ; j >= 0 ; j -= gap){
                if(arry[j] > arry[ j + gap]){
                    temp = arry[ j + gap ];
                    arry[j + gap] = arry[j];
                    arry[j] = temp;
                }
            }
        }
        //[0, 2, 1, 4, 3, 5, 7, 6, 9, 8]
        System.out.println("第二趟希尔排序后的数组"+ Arrays.toString(arry));
    }


    /**
     * @Description 冒泡实现希尔排序核心算法
     * @Author yangsj
     * @Date 2019/9/4 16:33
     **/
    public  void shellSortByBubble(int[] arry){
        //临时变量，交换时用于存放数据
        int temp = 0 ;
        //比较次数
        int count = 0;
        int gap = arry.length/2;
        while (gap >=1 ){
            for(int i = gap; i < arry.length; i++){
                //每一组进行冒泡排序，将每组的最小值冒泡到最前列，从后往前进行冒泡
                for (int j = i - gap ; j >= 0 ; j -= gap){
                    if(arry[j] > arry[ j + gap]){
                        temp = arry[ j + gap ];
                        arry[j + gap] = arry[j];
                        arry[j] = temp;
                    }
                }
            }
            //每次排序完成后，缩小增量，下次的分组相应的也会变少
            gap = gap/2;
            System.out.println("第"+(++count)+"趟希尔排序后的数组"+ Arrays.toString(arry));
        }
    }
}
