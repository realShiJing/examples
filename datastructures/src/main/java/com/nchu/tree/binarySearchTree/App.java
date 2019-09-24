package com.nchu.tree.binarySearchTree;

import org.junit.Test;

/**
 * @Decription 测试二叉排序树
 * @Author yangsj
 * @Date 20190923 11:46
 **/
public class App {
    /**
     * @Description 测试删除二叉排序树的节点
     * @Author yangsj
     * @Date 2019/9/23 11:43
     **/
    @Test
    public void testDeleteNode(){
        int[] datas = {7,3,10,1,5,9,12,2,11,13};
        BinarySearchTree root = new BinarySearchTree(datas[0]);
        for(int i = 1;i < datas.length ;i++){
            root.insert(datas[i]);
        }
        BinarySearchTree binarySearchTree = root.searchNode(7);
        /*System.out.println(binarySearchTree.data);*/
        /*System.out.println(binarySearchTree.right.right.data);*/
        root.deleteNode(10);
        System.out.println(binarySearchTree.right.right.data);

        root.in(root);

    }
    /**
     * @Description 测试二叉排序树的高度
     * @Author yangsj
     * @Date 2019/9/23 11:43
     **/
    @Test
    public void testTreeHight(){
        int[] datas = {7,3,10,1,5,9,12,2,11,13};
        BinarySearchTree root = new BinarySearchTree(datas[0]);
        for(int i = 1;i < datas.length ;i++){
            root.insert(datas[i]);
        }
       System.out.println(root.height(root));
    }


    /**
     * @Description 测试平衡二叉树
     * @Author yangsj
     * @Date 2019/9/24 15:24
     **/
    @Test
    public void testAvlTree(){
        //int[] datas = {50,40,60,55,70,53};
        int[] datas =  {50,40,60,30,45,47};
     /*   BinarySearchTree binarySearchTree = new BinarySearchTree(datas[0]);
        for(int i = 1;i < datas.length ;i++){
            binarySearchTree.insert(datas[i]);
        }
        System.out.println("leftHight :" + binarySearchTree.height(binarySearchTree.left) + " rightHight :" +binarySearchTree.height( binarySearchTree.right) );*/
        AvlTree root = new AvlTree(datas[0]);
        for(int i = 1;i < datas.length ;i++){
             root = root.insert(datas[i], root);
        }
        root.in(root);
        System.out.println("leftHight :" + root.height(root) + " rightHight :" + root.height(root) );

    }

}
