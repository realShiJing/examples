package com.nchu.linear.array;

/**
 * @Decription 稀疏数组
 * @Author yangsj
 * @Date 20190725 11:38
 **/
public class SparseArray {
    /**
     * 当一个数组中大部分元素为0，或者为同一个值的数组时，可以使用稀疏数组来保存该数组
     * 稀疏数组的处理方法是:
     * 1)记录数组一共有几行几列，有多少个不同的值
     * 2)把具有不同值的元素的行列及值记录在一个小规模的数组中，从而缩小程序的规模
     */
    public static void main(String [] args){
        // 创建一个原始的二维数组 11 * 11 （棋盘）
        // 0: 表示没有棋子， 1 表示 黑子 2 表示白子
        int chessArr1[][] = new int[5][5];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        chessArr1[3][4] = 1;


        //原始二维数组非零值的总数
        int sum = 0;
        System.out.println("-------原始数组------");
        for(int i = 0 ; i  < chessArr1.length ; i++){
            for(int j = 0 ; j < chessArr1[i].length;j++){
                System.out.print(chessArr1[i][j]+"\t");
                if(chessArr1[i][j] != 0)
                    sum++;
            }
            System.out.println();
        }

        //转换为稀疏数组,稀疏数组有三列
        // 行    列    值
        int[][] sparseArr = new int[sum + 1][3];
        sparseArr[0][0] = chessArr1.length;
        sparseArr[0][1] = chessArr1[0].length;
        sparseArr[0][2] = sum;


        int count = 1;
        for(int i = 0 ; i  < chessArr1.length ; i++){
            for(int j = 0 ; j < chessArr1[i].length;j++){
                if(chessArr1[i][j] != 0){
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr1[i][j];
                    count++;
                }
            }
        }
        System.out.println("-------稀疏数组--------");
        for(int i = 0 ; i  < sparseArr.length ; i++){
            for(int j = 0 ; j < sparseArr[i].length;j++){
                System.out.print(sparseArr[i][j]+ "\t");
            }
            System.out.println();
        }

        int[][]  chessArr2 = new int[sparseArr[0][0]][sparseArr[0][1]];

        for(int i = 1 ; i <= sum ; i++){
            chessArr2[sparseArr[i][0]][sparseArr[i][1]]  = sparseArr[i][2];
        }

        System.out.println("-------还原数组------");
        for(int i = 0 ; i  < chessArr2.length ; i++){
            for(int j = 0 ; j < chessArr2[i].length;j++){
                System.out.print(chessArr2[i][j] + "\t");
            }
            System.out.println();
        }
    }
}
