package com.nchu.sort;

import org.junit.Test;

import java.util.Arrays;

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

    /**
     * @Description  测试归并排序
     * @Author yangsj
     * @Date 2019-09-08 13:02
     **/
    @Test
    public void testMergeSort(){
        int[] arry = {8,7,6,5,4,3,2,1,0};
        int[] temp = new int[arry.length];//声明临时数组，用于存放排好序的元素
        MergeSort sort = new MergeSort();
        sort.sort(arry,0 ,arry.length-1,temp);
        System.out.println(Arrays.toString(arry));
    }


    /**
     * @Description 测试基数排序
     * @Author yangsj
     * @Date 2019/9/9 10:50
     **/
    @Test
    public void testRadixSort(){
        //随机数组
        int n = 100000000;
        int[] arry = new int[n];
        //基数排序消耗时间
        for(int i = 0;i < arry.length ; i++){
            int value = (int)(Math.random() * n);
            arry[i]= value;
        }
        long begin = System.currentTimeMillis();
        RadixSort radixSort = new RadixSort();
        radixSort.radixSort(arry);
        long end = System.currentTimeMillis();
        //基数排序（100000）个数据消耗的时间：28毫秒
        System.out.println("基数排序（"+n+"）个数据消耗的时间：" + (end - begin)+"毫秒");
    }

    /**
     * @Description 测试比较各个排序的消耗
     * @Author yangsj
     * @Date 2019/9/9 11:02
     **/
    @Test
    public void testSpeed(){
        //随机数组
        int n = 100000;
        int[] arry = new int[n];
        for(int i = 0;i < arry.length ; i++){
            int value = (int)(Math.random() * n);
            arry[i]= value;
        }
        //冒泡排序消耗时间
        BubbleSort bubbleSort = new BubbleSort();
        long begin = System.currentTimeMillis();
        bubbleSort.bubbleSort(arry);
        long end = System.currentTimeMillis();
        //冒泡排序（100000）个数据消耗的时间：28062毫秒
        System.out.println("冒泡排序（"+n+"）个数据消耗的时间：" + (end - begin)+"毫秒");



        //选择排序消耗时间
        for(int i = 0;i < arry.length ; i++){
            int value = (int)(Math.random() * n);
            arry[i]= value;
        }
        SelectSort selectSort = new SelectSort();
        begin = System.currentTimeMillis();

        selectSort.selectSort(arry);
        end = System.currentTimeMillis();
        //选择排序（100000）个数据消耗的时间：4736毫秒
        System.out.println("选择排序（"+n+"）个数据消耗的时间：" + (end - begin)+"毫秒");

        //插入排序消耗时间
        for(int i = 0;i < arry.length ; i++){
            int value = (int)(Math.random() * n);
            arry[i]= value;
        }
        InsertSort insertSort = new InsertSort();
        begin = System.currentTimeMillis();

        insertSort.insertSort(arry);
        end = System.currentTimeMillis();
        //插入排序（100000）个数据消耗的时间：2454毫秒
        System.out.println("插入排序（"+n+"）个数据消耗的时间：" + (end - begin)+"毫秒");

        //希尔排序消耗时间
        for(int i = 0;i < arry.length ; i++){
            int value = (int)(Math.random() * n);
            arry[i]= value;
        }
        ShellSort shellSort = new ShellSort();
        begin = System.currentTimeMillis();

        shellSort.shellSort(arry);
        end = System.currentTimeMillis();
        //希尔排序（100000）个数据消耗的时间：34毫秒
        System.out.println("希尔排序（"+n+"）个数据消耗的时间：" + (end - begin)+"毫秒");


        //快速排序消耗时间
        for(int i = 0;i < arry.length ; i++){
            int value = (int)(Math.random() * n);
            arry[i]= value;
        }
        QuickSort quickSort = new QuickSort();
        begin = System.currentTimeMillis();

        quickSort.quickSort(arry,0,arry.length - 1);
        end = System.currentTimeMillis();
        //快速排序（100000）个数据消耗的时间：33毫秒
        System.out.println("快速排序（"+n+"）个数据消耗的时间：" + (end - begin)+"毫秒");

        //归并排序消耗时间
        for(int i = 0;i < arry.length ; i++){
            int value = (int)(Math.random() * n);
            arry[i]= value;
        }
        MergeSort mergeSort = new MergeSort();
        begin = System.currentTimeMillis();

        int[] temp = new int[arry.length];
        mergeSort.sort(arry,0,arry.length - 1,temp);
        end = System.currentTimeMillis();
        //归并排序（100000）个数据消耗的时间：28毫秒
        System.out.println("归并排序（"+n+"）个数据消耗的时间：" + (end - begin)+"毫秒");

        //基数排序消耗时间
        for(int i = 0;i < arry.length ; i++){
            int value = (int)(Math.random() * n);
            arry[i]= value;
        }
        begin = System.currentTimeMillis();
        RadixSort radixSort = new RadixSort();
        radixSort.radixSort(arry);
        end = System.currentTimeMillis();
        //基数排序（100000）个数据消耗的时间：28毫秒
        System.out.println("基数排序（"+n+"）个数据消耗的时间：" + (end - begin)+"毫秒");
    }
}
