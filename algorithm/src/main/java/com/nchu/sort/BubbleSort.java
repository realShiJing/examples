package com.nchu.sort;

import java.util.Arrays;

public class BubbleSort {

    public static void bubbleSort(int[] arr) {
        int i, j, temp, len = arr.length;
        //外层循环代表排序趟数，一共要进行len-1趟排序
        for (i = 0; i < len - 1; i++){
            //用于标识一趟排序是否 有进行过比较和换位,默认值为false
            boolean flag = false;
            //内层循环代表每趟排序需要比较的次数 n+(n-1)+(n-2)+...+3+2+1 = n(n+1)/2
            for (j = 0; j < len - 1 - i; j++)
                if (arr[j] > arr[j + 1]) {
                    //进行过比较和换位，将flag标识置为 true
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
            }
            //如果在一趟排序中没有进行过比较和换位，就直接退出循环，结束排序
            if(!flag){
                break;
            }
            System.out.println("第"+(i+1)+"排序后的数组为:"+Arrays.toString(arr));
        }

    }
    public static void main(String[] args){
        // 初始化一个序列
        int[] array = {1, 3, 4, 5, 2, 6, 9, 7, 8, 10};
        bubbleSort(array);
        //System.out.print(Arrays.toString(array));
    }
}
