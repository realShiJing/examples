package com.nchu.recursion;

import org.junit.Test;

/**
 * @Decription 递归的测试
 * @Author yangsj
 * @Date 20190828 15:22
 **/
public class APP {


    /**
     * @Description 测试递归实现阶乘
     * @Author yangsj
     * @Date 2019/8/28 15:40
     **/
    @Test
    public void test(){
        int n = 6 ;
        int value = factorial(n);
        System.out.print("="+value);
    }
    /**
     * @Description 递归实现阶乘
     * 传入 参数 n
     * 打印 n*(n-1)*(n-2)...1 =
     * @Author yangsj
     * @Date 2019/8/28 15:24
     **/
    public int  factorial(int n){
        if(n==1){//递归退出条件
            System.out.print(""+n);
            return n;
        }else {
            System.out.print(n+"*");
            return factorial(n-1)*n;
        }
    }



    /**
     * @Description 测试八皇后问题
     * @Author yangsj
     * @Date 2019/8/29 15:01
     **/
    @Test
    public void testQueen8(){
        Queen8 queen = new Queen8();
        //从第一个开始放置
        queen.put(0);
        System.out.println("一共有"+Queen8.count+"种解法");
    }
}
