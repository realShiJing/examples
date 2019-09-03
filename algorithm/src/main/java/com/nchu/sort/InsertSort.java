package com.nchu.sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Decription 插入排序
 * @Author yangsj
 * @Date 20190903 11:10
 **/
public class InsertSort {

    /**
     * @Description while循环实现插入排序 过程推演
     * @Author yangsj
     * @Date 2019/9/3 11:14
     **/
    @Test
    public void insertSortDemo(){
        int[] arry = {5,4,2,1};
        //第一趟插入排序，有序列表为前一个元素
        int insertIndex = 0 ;
        //需要插入到有序列表的元素
        int insertValue = arry[0+1];
        //找到待插入元素的插入位置
        //如果待插入的元素小于有序列表中的元素，将该元素后移，直至找到待插入元素大于有序列表中元素的位置
        while(insertIndex >=0 && insertValue < arry[insertIndex]){
            arry[insertIndex + 1] = arry[insertIndex];//后移
            //遍历有序列表的下一个元素
            insertIndex --;
        }
        //循环遍历结束，即找到待插入的索引
        arry[insertIndex + 1] = insertValue;
        System.out.println("第一趟排序后："+ Arrays.toString(arry));


        //第二趟插入排序，有序列表为前两个元素
        insertIndex = 1 ;
        //需要插入到有序列表的元素
        insertValue = arry[1+1];
        //找到待插入元素的插入位置
        //如果待插入的元素小于有序列表中的元素，将该元素后移，直至找到待插入元素大于有序列表中元素的位置
        while(insertIndex >=0 && insertValue < arry[insertIndex]){
            arry[insertIndex + 1] = arry[insertIndex];//后移
            //遍历有序列表的下一个元素
            insertIndex --;
        }
        //循环遍历结束，即找到待插入的索引
        arry[insertIndex + 1] = insertValue;
        System.out.println("第二趟排序后："+ Arrays.toString(arry));

        //第三趟插入排序，有序列表为前三个元素
        insertIndex = 2 ;
        //需要插入到有序列表的元素
        insertValue = arry[2+1];
        //找到待插入元素的插入位置
        //如果待插入的元素小于有序列表中的元素，将该元素后移，直至找到待插入元素大于有序列表中元素的位置
        while(insertIndex >=0 && insertValue < arry[insertIndex]){
            arry[insertIndex + 1] = arry[insertIndex];//后移
            //遍历有序列表的下一个元素
            insertIndex --;
        }
        //循环遍历结束，即找到待插入的索引
        arry[insertIndex + 1] = insertValue;
        System.out.println("第三趟排序后："+ Arrays.toString(arry));
    }


    /**
     * @Description while循环实现插入排序算法
     * @Author yangsj
     * @Date 2019/9/3 16:02
     **/
    public void insertSort(int[] arry){
        for(int i = 0 ; i < arry.length - 1;i++){
            //第i+1趟插入排序，有序列表为前i+1个元素
            int insertIndex = i ;
            //待插入的元素
            int insertValue = arry[i+1];
            //找到待插入元素的插入位置
            //如果待插入的元素小于有序列表中的元素，将该元素后移，直至找到待插入元素大于有序列表中元素的位置
            while(insertIndex >=0 && insertValue < arry[insertIndex]){
                arry[insertIndex + 1] = arry[insertIndex];//后移
                //遍历有序列表的下一个元素
                insertIndex --;
            }
            //循环遍历结束，即找到待插入的索引
            arry[insertIndex + 1] = insertValue;
            System.out.println("第"+(i+1)+"趟排序后："+ Arrays.toString(arry));
        }
    }
    /**
     * @Description For循环实现插入排序 过程推演
     * @Author yangsj
     * @Date 2019/9/3 11:14
     **/
    @Test
    public void insertSortByForDemo(){
        int[] arry = {2,4,5,1};
        //第一趟插入排序，有序列表为前一个元素
        int insertIndex = 0 ;
        //需要插入到有序列表的元素
        int insertValue = arry[0+1];

        for(int i = 0 ; i >= 0 ; i-- ){
            //如果需要的元素 小于 有序列表中的元素，此时将有序类表中所有大于该元素的值进行后移
            if(insertValue < arry[i]){
                arry[i + 1] = arry[i];
                if(i == 0 ){
                    insertIndex = i;
                    break;
                }
            }else {
                insertIndex = i + 1;
                break;
            }
        }
        arry[insertIndex] = insertValue;
        System.out.println("第一趟排序后："+ Arrays.toString(arry));

        //第二趟插入排序，有序列表为前两个元素
        insertIndex = 1 ;
        //需要插入到有序列表的元素
        insertValue = arry[1+1];

        for(int i = 1 ; i >= 0 ; i-- ){
            if(insertValue < arry[i]){
                arry[i + 1] = arry[i];
                if(i == 0 ){
                    insertIndex = i;
                    break;
                }
            }else {
                insertIndex = i + 1;
                break;
            }
        }
        arry[insertIndex] = insertValue;
        System.out.println("第二趟排序后："+ Arrays.toString(arry));

        //第三趟插入排序，有序列表为前三个元素
        insertIndex = 2 ;
        //需要插入到有序列表的元素
        insertValue = arry[2+1];

        for(int i = 2 ; i >= 0 ; i-- ){
            if(insertValue < arry[i]){
                arry[i + 1] = arry[i];
                if(i == 0 ){
                    insertIndex = i;
                    break;
                }
            }else {
                insertIndex = i + 1;
                break;
            }
        }
        arry[insertIndex] = insertValue;
        System.out.println("第三趟排序后："+ Arrays.toString(arry));

    }


    /**
     * @Description for循环实现插入排序
     * @Author yangsj
     * @Date 2019/9/3 15:11
     **/
    public void insertSortByFor(int[] arry){
        //第j+1趟插入排序
       for(int j = 0 ; j < arry.length-1 ; j++){
           //插入索引
           int insertIndex =  j ;
           //待插入的元素
           int insertValue = arry[j+1];
            //遍历有序列表，随着每个元素的插入，有序列表的长度也在变大
           for(int i = j ; i >= 0 ; i-- ){
               //如果需要的元素 小于 有序列表中的元素，此时将有序类表中所有大于该元素的值进行后移
               if(insertValue < arry[i]){
                   arry[i + 1] = arry[i];
                   //如果遍历到有序列表头的元素 依然 大于该元素，则将待插入的索引置为 0
                   if(i == 0 ){
                       insertIndex = i;
                       break;
                   }
               }else {//当遍历到有序列表中的元素大于等于待插入的元素时，说明找到了待插入元素的位置
                   insertIndex = i + 1;
                   break;
               }
           }
           //将待插入的元素插入到待插入的位置上
           arry[insertIndex] = insertValue;
           System.out.println("第"+(j+1)+"趟排序后："+ Arrays.toString(arry));
       }
    }

}
