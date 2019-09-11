package com.nchu.search;

import java.util.ArrayList;

/**
 * @Decription 二分查找
 * @Author yangsj
 * @Date 20190910 10:09
 **/
public class BinarySearch {


    /**
     * @Description 递归实现二分查找，返回查找到值的索引，若没有返回 -1
     * @Param 有序数组（从小到大排列）
     * @Param 左索引
     * @Param 右索引
     * @Param 要查找的值
     * @Author yangsj
     * @Date 2019/9/10 10:10
     **/
    public int binarySearch(int[] arry ,int left ,int right,int value){
        if(left > right){//递归时，左右索引在不断靠近，当左索引大于右索引时，退出递归
            return  -1;
        }
        //确定子序列的中间索引
        int mid  = (left + right)/2;
        if(value > arry[mid]){//如果要查找的值大于中间索引的值，则向右递归继续查找
           return binarySearch(arry,mid + 1, right,value);
        }else if(value < arry[mid]) {//如果要查找的值小于中间索引的值，则向左递归继续查找
           return binarySearch(arry,left,mid - 1,value);
        }else{//如果要查找的值等于中间索引，代表找到该索引，并回溯返回
            return  arry[mid];
        }

    }


    /**
     * @Description 递归实现二分查找，返回查找到值的索引，若没有返回 -1
     * @Param 有序数组（从小到大排列）
     * @Param 左索引
     * @Param 右索引
     * @Param 要查找的值
     * @Author yangsj
     * @Date 2019/9/10 10:10
     **/
    public ArrayList<Integer> binarySearchList(int[] arry , int left , int right, int value){
        if(left > right){//递归时，左右索引在不断靠近，当左索引大于右索引时，退出递归
            return  new ArrayList<>();
        }
        //确定子序列的中间索引
        int mid  = (left + right)/2;
        if(value > arry[mid]){//如果要查找的值大于中间索引的值，则向右递归继续查找
            return binarySearchList(arry,mid + 1, right,value);
        }else if(value < arry[mid]) {//如果要查找的值小于中间索引的值，则向左递归继续查找
            return binarySearchList(arry,left,mid - 1,value);
        }else{//如果要查找的值等于中间索引，代表找到该索引，并回溯返回
            ArrayList<Integer> list = new ArrayList<>();
            int temp = mid - 1;//临时索引
            //将中间索引左边和 value值相等的索引加到list中
            while (temp >= left && value == arry[temp]){
                list.add(temp);
                temp--;
            }
            list.add(mid);
            //将中间索引右边和 value值相等的索引加到list中
            temp = mid + 1;
            while (temp <= right && value == arry[temp]){
                list.add(temp);
                temp++;
            }

            return  list;
        }

    }
}
