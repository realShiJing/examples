package com.nchu.search;

/**
 * @Decription 插值查找
 * @Author yangsj
 * @Date 20190911 14:40
 **/
public class InsertValueSearch {

    /**
     * @Description 递归实现二分查找，返回查找到值的索引，若没有返回 -1
     * @Param 有序数组（从小到大排列）
     * @Param 左索引
     * @Param 右索引
     * @Param 要查找的值
     * @Author yangsj
     * @Date 2019/9/10 10:10
     **/
    public int insertValueSearch(int[] arry ,int left ,int right,int value){
        //递归时，左右索引在不断靠近，当左索引大于右索引时，退出递归
        //所以要防止越界，当查找的关键字大于最大值，或小于最小值是直接退出
        if(left > right || value < arry[0] || value > arry[arry.length -1 ]){
            return  -1;
        }
        System.out.println("递归~");
        //确定分割索引，插值查找的查找关键字参与了分割索引的计算 ,因此计算的分割索引有可能会越界
        int index  = left  + (right - left )*(value - arry[left])/(arry[right]-arry[left]);
        if(value > arry[index]){//如果要查找的值大于分割索引的值，则向右递归继续查找
            return insertValueSearch(arry,index + 1, right,value);
        }else if(value < arry[index]) {//如果要查找的值小于分割索引的值，则向左递归继续查找
            return insertValueSearch(arry,left,index - 1,value);
        }else{//如果要查找的值等于分割索引，代表找到该值所在的位置，并回溯返回
            return  arry[index];
        }

    }
}
