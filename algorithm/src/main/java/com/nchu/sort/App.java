package com.nchu.sort;

import org.junit.Test;

/**
 * @Decription 排序测试
 * @Author yangsj
 * @Date 20190902 10:19
 **/
public class App {

    /**
     * @Description 测试选择排序
     * @Author yangsj
     * @Date 2019/9/2 10:21
     **/
    @Test
    public void testSelectSort(){
        int[] arry = {2,4,5,1};
        arry = new int[]{8, 3, 2, 1, 7, 4, 6, 5};
        SelectSort sort = new SelectSort();
        sort.selectSort(arry);
    }

    /**
     * @Description 测试插入排序
     * @Author yangsj
     * @Date 2019/9/2 10:21
     **/
    @Test
    public void testInsertSort(){
        int[] arry = new int[]{8, 3, 2, 1, 7, 4, 6, 5};
        InsertSort sort = new InsertSort();
        sort.insertSort(arry);
    }
}
