package com.nchu.sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Decription 基数排序，只支持整数
 * @Author yangsj
 * @Date 20190909 9:19
 **/
public class RadixSort {


    /**
     * @Description 基数排序过程推演
     * @Author yangsj
     * @Date 2019/9/9 9:22
     **/
    /*@Test
    public void radixSortDemo(){

        int[] arry = {53, 3, 542, 748, 14, 214};

        //第一次排序

        //声明十个桶，用于临时存放数据，用一个二维数组来实现
        int[][] bucket = new int[10][arry.length];
        //声明一个数组，用于存放每个桶中存放数据的个数
        int[] counts = new int[bucket.length];
        //入桶
        for(int i = 0 ; i < arry.length; i++){
            //对10取模，取出元素的个位数
            int item = arry[i] % 10;
            //根据元素的个位数，将该元素放到相应的桶中的相应位置，
            //每个桶中放过数据后，用于记录该桶中元素个数的值加一
            bucket[item][counts[item]++] = arry[i];
        }
        //从桶中取出元素
        int index = 0 ;
        for(int i = 0 ;i < bucket.length ; i++){
            //如果第 i 个桶中没有数据，就进行下次循环遍历
            if(counts[i] == 0){
                    continue;
            }
            //否则，取出同种数据，放到原始数组中
            for(int j = 0 ; j < counts[i] ;j++){
                arry[index++] = bucket[i][j];
            }
            //每次取出数据后，桶中数据个数的记录清空
            counts[i] = 0;
        }
        System.out.println("第一次排序后的数组：" + Arrays.toString(arry));

        //第二次排序
        //入桶
        for(int i = 0 ; i < arry.length; i++){
            //对10取模，取出元素的个位数
            int item = arry[i] / 10 % 10;
            //根据元素的个位数，将该元素放到相应的桶中的相应位置，
            //每个桶中放过数据后，用于记录该桶中元素个数的值加一
            bucket[item][counts[item]++] = arry[i];
        }
        //从桶中取出元素
        index = 0 ;
        for(int i = 0 ;i < bucket.length ; i++){
            //如果第 i 个桶中没有数据，就进行下次循环遍历
            if(counts[i] == 0){
                continue;
            }
            //否则，取出同种数据，放到原始数组中
            for(int j = 0 ; j < counts[i] ;j++){
                arry[index++] = bucket[i][j];
            }
            counts[i] = 0;
        }
        System.out.println("第二次排序后的数组：" + Arrays.toString(arry));

        //第二次排序
        //入桶
        for(int i = 0 ; i < arry.length; i++){
            //对10取模，取出元素的个位数
            int item = arry[i] / 100 % 10;
            //根据元素的个位数，将该元素放到相应的桶中的相应位置，
            //每个桶中放过数据后，用于记录该桶中元素个数的值加一
            bucket[item][counts[item]++] = arry[i];
        }
        //从桶中取出元素
        index = 0 ;
        for(int i = 0 ;i < bucket.length ; i++){
            //如果第 i 个桶中没有数据，就进行下次循环遍历
            if(counts[i] == 0){
                continue;
            }
            //否则，取出同种数据，放到原始数组中
            for(int j = 0 ; j < counts[i] ;j++){
                arry[index++] = bucket[i][j];
            }
            counts[i] = 0;
        }
        System.out.println("第三次排序后的数组：" + Arrays.toString(arry));

    }*/


    /**
     * @Description 基数排序
     * @Author yangsj
     * @Date 2019/9/9 10:49
     **/
    public void radixSort(int[] arry){
        //取出数组中最长的位数
        int maxLength = String.valueOf(arry[0]).length();
        for(int i = 1 ; i < arry.length ; i++){
            if(maxLength < String.valueOf(arry[i]).length()){
                maxLength = String.valueOf(arry[i]).length();
            }
        }
        for(int k = 0 ; k < maxLength ;k++){
            //声明十个桶，用于临时存放数据，用一个二维数组来实现
            //如果原始数组的数量太大，则桶的长度也会很大，会占用很多内存空间
            //以空间换时间的算法，不适合大量数据的处理
            int[][] bucket = new int[10][arry.length];
            //声明一个数组，用于存放每个桶中存放数据的个数
            int[] counts = new int[bucket.length];
            //入桶
            for(int i = 0 ; i < arry.length; i++){
                //对10取模，取出元素的个位数
                int item = arry[i] / (int)Math.pow(10,k) % 10;
                //根据元素的个位数，将该元素放到相应的桶中的相应位置，
                //每个桶中放过数据后，用于记录该桶中元素个数的值加一
                bucket[item][counts[item]++] = arry[i];
            }
            //从桶中取出元素
            int index = 0 ;
            for(int i = 0 ;i < bucket.length ; i++){
                //如果第 i 个桶中没有数据，就进行下次循环遍历
                if(counts[i] == 0){
                    continue;
                }
                //否则，取出同种数据，放到原始数组中
                for(int j = 0 ; j < counts[i] ;j++){
                    arry[index++] = bucket[i][j];
                }
                //每次取出数据后，桶中数据个数的记录清空
                counts[i] = 0;
            }
            //System.out.println("第"+(k+1)+"次排序后的数组：" + Arrays.toString(arry));
        }

    }
}
