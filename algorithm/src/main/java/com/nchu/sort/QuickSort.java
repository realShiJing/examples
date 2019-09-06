package com.nchu.sort;

public class QuickSort {



    /**
     * @Description 快速排序
     * @Author yangsj
     * @Date 2019/9/6 11:29
     **/
    public void quickSort(int[] list, int left, int right){
        //退出迭代的条件
        if (left >= right) {
            return;
        }
        // 对数组进行分割，取出下次分割的基准标号
        int baseIndex = division(list, left, right);

        System.out.format("base = %d:\t", list[baseIndex]);
        printPart(list, left, right);
        //System.out.println("left:"+left +"              right:"+right+"             baseIndex:"+ baseIndex);

        // 对“基准标号“左侧的一组数值进行递归的切割，以至于将这些数值完整的排序
        quickSort(list, left, baseIndex - 1);

        // 对“基准标号“右侧的一组数值进行递归的切割，以至于将这些数值完整的排序
        quickSort(list, baseIndex + 1, right);
    }


    /**
     * @Description  对数组进行分割，并返回分割的基准标号
     * @Author yangsj
     * @Date 2019/9/6 11:31
     **/
    public int division(int[] list, int left, int right){
         // 以最左边的数(left)为基准
         int base = list[left];
         while (left < right) {
              // 从序列右端开始，向左遍历，直到找到小于base的数
              while (left < right && list[right] >= base)
                  right--;
              // 找到了比base小的元素，将这个元素放到左指针所在的位置
              list[left] = list[right];

              // 从序列左端开始，向右遍历，直到找到大于base的数
              while (left < right && list[left] <= base)
                  left++;
              // 找到了比base大的元素，将这个元素放到右指针所正在的位置
              list[right] = list[left];
         }

         // 最后将base放到left位置。此时，left位置的左侧数值应该都比left小；
         // 而left位置的右侧数值应该都比left大。
         list[left] = base;
         return left;
    }




    /**
     * @Description 快速排序分拆打印
     * @Author yangsj
     * @Date 2019/9/6 11:33
     **/
    public void printPart(int[] list, int begin, int end){
        for (int i = 0; i < begin; i++) {
            System.out.print("\t");
        }
        for (int i = begin; i <= end; i++) {
            System.out.print(list[i] + "\t");
        }
        System.out.println();
    }



    /**
     * @Description 快速排序简化版
     * @Author yangsj
     * @Date 2019/9/6 11:21
     **/
    /*public  void quick_sort(int arry[], int left, int right){
        if (left < right){
            int l = left, r = right, base = arry[left];
            while (l < r){
                while(l < r && arry[r] >= base) // 从右向左找一个小于等于x的数
                    r--;
                arry[l] = arry[r];

                while(l < r && arry[l] <= base) // 从左向右找一个大于等于x的数
                    l++;
                arry[r] = arry[l];
            }
            arry[l] = base;

            System.out.format("base = %d:\t", arry[l]);
            printPart(arry, left, right);

            quick_sort(arry, left, l - 1); // 递归调用
            quick_sort(arry, l + 1, right);
        }
    }*/
}

