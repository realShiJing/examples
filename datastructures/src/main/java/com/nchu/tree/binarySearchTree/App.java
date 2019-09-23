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
}
