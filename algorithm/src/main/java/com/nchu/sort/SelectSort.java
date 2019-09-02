package com.nchu.sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Decription 选择排序
 * @Author yangsj
 * @Date 20190902 09:47
 **/
public class SelectSort {
    /**
     * @Description 选择排序推演
     * @Author yangsj
     * @Date 2019/9/2 9:49
     **/
    @Test
    public void selectSortDemo(){
        int[] arry = {2,4,5,1};
        //最小数
        int min = arry[0];
        //最小数索引
        int minIndex = 0;

        for(int i = 1+0 ;i < arry.length ; i++ ){
            if(min > arry[i]){
                min = arry[i];
                minIndex = i;
            }
        }
        arry[minIndex] = arry[0];
        arry[0] = min;
        System.out.println("第一趟排序："+Arrays.toString(arry));

        //最小数
        min = arry[1];
        //最小数索引
        minIndex = 1;

        for(int i = 1+1 ;i < arry.length ; i++ ){
            if(min > arry[i]){
                min = arry[i];
                minIndex = i;
            }
        }
        arry[minIndex] = arry[1];
        arry[1] = min;
        System.out.println("第二趟排序："+Arrays.toString(arry));

        //最小数
        min = arry[2];
        //最小数索引
        minIndex = 2;

        for(int i = 1+2 ;i < arry.length ; i++ ){
            if(min > arry[i]){
                min = arry[i];
                minIndex = i;
            }
        }
        arry[minIndex] = arry[2];
        arry[2] = min;
        System.out.println("第三趟排序："+Arrays.toString(arry));
    }
    /**
     * @Description 选择排序核心算法
     * @Author yangsj
     * @Date 2019/9/2 9:49
     **/
    public void selectSort(int[] arry){
        for(int j = 0 ; j < arry.length -1 ;j++){
            //最小数,初始值为每趟排序需替换元素的值
            int min = arry[j];
            //最小数索引
            int minIndex = j;
            //将定位元素索引后的值，依次与最小值比较，直至遍历到集合末尾
            //确认出此趟排序中的最小值和最小值的索引
            for(int i = 1+j ;i < arry.length ; i++ ){
                if(min > arry[i]){ //说明假定的最小值，并不是最小
                    min = arry[i];//重置最小值
                    minIndex = i;//重置最小值索引
                }
            }
            //优化，如果经过以上遍历最小值索引没有发生变化，表示此趟排序中
            //最小值就是 arry[j],此时不用进行交换
            if(minIndex != j){
                //将此趟排序的最小值和定位元素交换
                arry[minIndex] = arry[j];
                arry[j] = min;
            }
            System.out.println("第"+(j+1)+"趟排序："+Arrays.toString(arry));
        }
    }
}
