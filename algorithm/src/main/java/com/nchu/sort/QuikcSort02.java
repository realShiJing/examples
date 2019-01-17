package com.nchu.sort;

import java.util.Arrays;

public class QuikcSort02 {


    //快速排序
    public static void quick_sort(int s[], int l, int r){
        if (l < r){

            int i = l, j = r, x = s[l];
            while (i < j){
                while(i < j && s[j] >= x) // 从右向左找第一个小于等于x的数
                    j--;
                    s[i] = s[j];

                while(i < j && s[i] <= x) // 从左向右找第一个大于等于x的数
                    i++;
                    s[j] = s[i];
            }
            s[i] = x;
            quick_sort(s, l, i - 1); // 递归调用
            quick_sort(s, i + 1, r);
        }
    }
    public static void main (String[] args){
        int[] s= {1, 3, 4, 5, 2, 6, 9, 7, 8, 0};
        quick_sort(s,0,s.length-1);
        System.out.print(Arrays.toString(s));
    }
}
