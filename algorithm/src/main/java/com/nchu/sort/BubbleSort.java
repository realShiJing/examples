package com.nchu.sort;

import java.util.Arrays;

public class BubbleSort {

    public static void bubbleSort(int[] arr) {
        int i, j, temp, len = arr.length;
        //外层循环代表排序趟数，一共要进行len-1趟排序
        for (i = 0; i < len - 1; i++)
            //内层循环代表每趟排序需要比较的次数 n+(n-1)+(n-2)+...+3+2+1 = n(n+1)/2
            for (j = 0; j < len - 1 - i; j++)
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
    }
    public static void main(String[] args){
        // 初始化一个序列
        int[] array = {1, 3, 4, 5, 2, 6, 9, 7, 8, 0};
        bubbleSort(array);
        System.out.print(Arrays.toString(array));
    }
}
