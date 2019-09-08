package com.nchu.sort;

/**
 * @Decription 归并排序
 * @Author yangsj
 * @Date 2019-09-08 11:42
 **/
public class MergeSort {

    /**
     * @Description  递归进行分拆排序合并
     * @Author yangsj
     * @Date 2019-09-08 12:58
     **/
    public void sort(int[] arry,int left ,int right,int[] temp){
        //退出递归的条件，最后分拆的数组的左右索引相等，代表只有一个元素需要处理，就不需要进行迭代分拆了
        if(left == right){
            return;
        }
        int mid = (left + right)/2;//待分拆数组的中间索引


        prinrArry(arry,left,right);
        sort(arry,left,mid,temp);
        sort(arry,mid + 1 ,right,temp);
        //分拆合并
        merge(arry,left,mid,right,temp);
    }
    /**
     * @Description  被拆分数组的合并
     * @Author yangsj
     * @Param arry 待排序的数组
     * @Param left 左指针，用于确定被分拆子数组的起始位置
     * @Param right 右指针，用于确定被分拆子数组的末位置
     * @Param temp 临时数组，用于存取排好序的子数组
     * @Date 2019-09-08 11:47
     **/
    public void merge(int[] arry ,int left,int mid, int right,int[] temp){
        //被分拆的左右两个子数组的元素依次进行比较，并放到临时数组中
        int t = 0 ;//临时数组的起始索引
        int i = left ;//左序列起始索引
        int j = mid + 1;//右序列起始索引
        while(i <= mid && j <= right){//左右序列循环往后进行遍历
            if(arry[i] < arry[j]){//将较小的值拷贝到临时数组中
                temp[t++] = arry[i++];
            }else {
                temp[t++] = arry[j++];
            }
        }
        //上述遍历完成后，肯定两个子序列，肯定有一方还有剩余元素，需要将它们拷贝到临时数组中
        while (i <= mid){
            temp[t++] = arry[i++];
        }

        while (j <= right){
            temp[t++] = arry[j++];
        }
        //将排序好的临时数组覆盖到原始数组中
        t = 0 ;//临时数组从头到尾进行遍历赋值
        System.out.print("合并：");
        while (left <= right){
            arry[left] = temp[t];
            System.out.print(arry[left] + " ");
            left++;
            t++;
        }
        System.out.println();

    }

    /**
     * @Description  打印归并排序的过程
     * @Author yangsj
     * @Date 2019-09-08 13:22
     **/
    public void prinrArry(int[] arry, int begin, int end){
        System.out.print("分拆：");
        for(int i = begin ; i <= end; i++){
            System.out.print(arry[i]+" ");
        }
        System.out.println();
    }
}
