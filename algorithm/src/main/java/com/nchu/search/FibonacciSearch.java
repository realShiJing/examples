package com.nchu.search;

import java.util.Arrays;

/**
 * @Decription 斐波那契查找
 * @Author yangsj
 * @Date 20190912 14:36
 **/
public class FibonacciSearch {
    //斐波那契数列的长度，可支持6765个元素的查找
    //[1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, 2584, 4181, 6765]
    public static int size = 20;


    /**
     * @Description 返回斐波那契数列
     * @Author yangsj
     * @Date 2019/9/12 14:41
     **/
    public int[] fibonacci(){
        //{1, 1, 2, 3, 5, 8, 13, 21, 34, 55 ......}
        int[] f = new int[size];
        f[0] = 1;
        f[1] = 1;
        for(int i = 2;i < f.length ;i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }

    /**
     * @Description 斐波那契查找算法
     * @Author yangsj
     * @Date 2019/9/12 14:51
     **/
    public int fibonacciSearch(int[] arry ,int value){
        int left = 0;//初始左索引
        int right = arry.length - 1;//初始右索引
        int mid;//初始分割点
        int[] f = fibonacci();//斐波那契数列
        int k = 0;//斐波那契数列的初始索引
        //首先扩充原始序列，扩充原始序列长度到刚大于原始数组长度的斐波那契数列的值
        while(arry.length > f[k] ){
            k++;
        }
        int[] temp = Arrays.copyOf(arry, f[k]);//扩充后的临时数组,长度为f[k]
        //将扩充后的增加的几位用原数组的末尾值补齐
        for(int i = right + 1 ; i < temp.length ; i++){
            temp[i] = temp[right];
        }
        //如果左索引小于等于右索引，就一直进行循环分割查找
        while (left <= right){
            //长度为f[k]的数组的中间索引，此分割索引为f[k-1]子序列的末尾索引
            mid = left + f[k - 1] - 1;//将原始数组分割为 f[k-1] 和 f[k-2]两部分
            if(value < temp[mid]){ //如果关键字小于中间值，表示关键字在黄金分割点的左侧，即f[k-1]
                right = mid -1;//将子序列的右索引置为分割索引的前一个索引
                //继续讲f[k-1]进行分割
                k--;
            }else if(value > temp[mid]){ //如果关键字大于中间值，表示关键字在黄金分割点的右侧，即f[k-2]
                left = mid + 1;//将子序列的左索引置为分割索引的后一个索引
                //继续将f[k-2]进行分割
                k-=2;
            }else {//此时 value = temp[mid],找到关键字，并返回
                //有可能查找到的是补全值，此时需要返回right的索引
                if(mid >= right){
                    return right;
                }else {
                    return mid;
                }
            }
        }
        //如果循环查找都没有找到返回，表示该数组中不存在该元素，此时返回 -1，代表没找到
        return -1;
    }
}
