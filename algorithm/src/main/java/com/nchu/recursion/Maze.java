package com.nchu.recursion;

import org.junit.Test;

/**
 * @Decription 递归解决迷宫问题
 * @Author yangsj
 * @Date 20190828 16:30
 **/
public class Maze {

    /**
     * @Description 迷宫测试
     * @Author yangsj
     * @Date 2019/8/28 16:33
     **/
    @Test
    public void test(){
        //定义一个二维数组表示迷宫的路径
        int[][] maze = new int[8][8];
        //1表示墙，0表示路，2表示已走过的路，3表示死路
        //给迷宫设置墙
        for(int i = 0 ; i < maze.length ; i++){
            maze[0][i] = 1;
            maze[7][i] = 1;
            maze[i][0] = 1;
            maze[i][7] = 1;
        }

        maze[1][1] = 1;
        maze[0][1] = 0 ;//迷宫入口
        maze[7][6] = 0 ;//迷宫出口
        System.out.println("----------原始迷宫---------");
        show(maze);
        System.out.println("----是否找到了出口：" +   go(maze,0,1)+"---");
        show(maze);
    }


    /**
     * @Description 走迷宫
     * 如果能走通 返回 true ，否则 返回 false
     * 策略：左下右上
     * @Author yangsj
     * @Date 2019/8/28 16:46
     **/
    public boolean go(int[][] maze ,int line ,int column){
        //如果坐标非法，直接返回false
        if(line > maze.length || column > maze[0].length || line < 0 || column < 0){
            return false;
        }
        if(line == 7 && column == 6 ){//退出迭代的条件，找到了出口
            maze[line][column] = 2 ;
            return true;
        }else if(maze[line][column] != 1 && maze[line][column] != 2
                 &&maze[line][column] != 3){//否则继续找出口
            //设置当前节点已走过
            maze[line][column] = 2;
            if(go(maze,line ,column -1 )){//左
                return true;
            }else if(go(maze,line + 1,column)){//下
                return  true;
            }else if(go(maze,line,column + 1)){//右
                return true;
            }else if(go(maze,line -1 ,column)){//上
                return true;
            }else {//上下左右都走不通，说明是死路
                maze[line][column] = 3;
                return false;
            }
        }else {
            return false;
        }
    }
    /**
     * @Description 展示迷宫
     * @Author yangsj
     * @Date 2019/8/28 16:43
     **/
    public void  show (int[][] maze){
        for(int i = 0  ;i < maze.length ; i++){//行
            for (int j = 0 ; j < maze[0].length ; j++ ){//列
                System.out.print(maze[i][j]+" ");
            }
            System.out.println("");
        }
    }
}
