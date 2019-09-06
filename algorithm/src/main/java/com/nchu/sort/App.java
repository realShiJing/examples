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
    public void testSelectSort() {
        int[] arry = {2, 4, 5, 1};
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


    /**
     * @Description 测试希尔排序
     * @Author yangsj
     * @Date 2019/9/4 10:44
     **/
    @Test
    public void testShellSort(){
        int[] arry = { 8, 9, 1, 7, 2, 3, 5, 4, 6, 0 };
        ShellSort sort = new ShellSort();
        //sort.shellSort(arry);
        sort.shellSortByBubble(arry);
    }


    /**
     * @Description 测试快速排序
     * @Author yangsj
     * @Date 2019/9/6 11:11
     **/
    @Test
    public void testQuickSort() {
        // 初始化一个序列
        int[] array = {5, 6, 7, 9, 2, 8, 1, 0, 3, 4};
        // 调用快速排序方法
        QuickSort sort = new QuickSort();
        //过程打印
        System.out.print("排序前:\t\t");
        sort.printPart(array, 0, array.length - 1);
        sort.quickSort(array, 0, array.length - 1);
        System.out.print("排序后:\t\t");
        sort.printPart(array, 0, array.length - 1);
    }
}
