package com.nchu.recursion;

/**
 * @Decription 八皇后问题
 * 问题描述：
 * 在8×8格的国际象棋上摆放八个皇后，使其不能互相攻击，
 * 即：任意两个皇后都不能处于同一行、同一列或同一斜线上，问有多少种摆法。
 * @Author yangsj
 * @Date 20190829 10:55
 **/
public class Queen8 {

    //皇后的个数
    public static int size =  8;

    //棋盘 board = {0,4,7,5,2,6,1,3}
    //数组中的值表示放在第几列，比如第二个值维4表示 第二个皇后放在第二行第五列
    public static int[] board = new int[size];

    //总共有多少种解法
    public static int count= 0;

    /**
     * @Description 摆放皇后
     * n：摆放第n个皇后
     * @Author yangsj
     * @Date 2019/8/29 11:26
     **/
    public void put(int n){
        //递归结束的条件
        //如果是摆放第九个皇后（从零开始），代表已摆放结束
        if(n == board.length ){
            //打印棋盘
            show();
            return;
        }
        //从第 1 列 到 第 8 列 遍历
        for(int i = 0 ; i < board.length;i++){
            if(check(n,i)){//如果不冲突，继续摆下皇后
                board[n] = i;//表示第n个皇后摆放在了第n行第i列
                put(n+1);
            }
        }
    }


    /**
     * @Description 检查 需摆放的皇后是否和 棋盘上已有的皇后冲突
     * 同一列：棋盘上的皇后的值和j相等；board[i] == j
     * 对角线:列差 = 行差 Math.abs(board[i] - j)==Math.abs(i-n)
     * n:第n个皇后摆在第n行
     * j:第n个皇后摆在第j列
     * @Author yangsj
     * @Date 2019/8/29 11:46
     **/
    public boolean check(int n,int j){
        if(n==0){//第一个皇后摆放时 一定可以摆放，直接返回true
            return true;
        }
        //i表示第i个皇后（同时表示第i行）
        for(int i = n - 1 ; i >= 0 ; i-- ){//当前皇后和已经摆放好的皇后一一比较
            if(board[i] == j || Math.abs(board[i] - j)==Math.abs(i-n)){
                return false;
            }
        }
        return true;
    }

    /**
     * @Description 显示棋盘
     * @Author yangsj
     * @Date 2019/8/29 11:46
     **/
    public void show(){
        count++;
        for(int i = 0 ; i < board.length; i++){
            System.out.print(board[i]+" ");
        }
        System.out.println();
    }
}
