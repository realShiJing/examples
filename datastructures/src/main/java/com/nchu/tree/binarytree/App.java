package com.nchu.tree.binarytree;

import org.junit.Test;

/**
 * @Decription 二叉树的前序、中序、后序遍历的测试
 * @Author yangsj
 * @Date 2019-09-15 21:22
 **/
public class App {

    /**
     * @Description  测试二叉树遍历
     * @Author yangsj
     * @Date 2019-09-15 21:23
     **/
    @Test
    public void  testBinaryTree(){
        int[] datas = {1,5,0,4,2,3};
        BinaryTree root = new BinaryTree(datas[0]);
        for(int i = 1 ; i < datas.length ;i++){
            root.insert(new BinaryTree(datas[i]));
        }
        //前序遍历
        System.out.println("前序遍历：");
        //1、5、4、2、3、0
        root.preOrder();

        //中序遍历
        System.out.println("中序遍历：");
        //5、4、3、2、1、0
        root.infixOrder();

        //后序遍历
        System.out.println("后序遍历：");
        //3、2、4、5、0、1
        root.postOrder();
    }


    /**
     * @Description 测试二叉树遍历查找
     * @Author yangsj
     * @Date 2019/9/17 9:07
     **/
    @Test
    public void testBinaryTreeSearch(){
        int[] datas = {1,5,0,4,2,3};
        BinaryTree root = new BinaryTree(datas[0]);
        for(int i = 1 ; i < datas.length ; i++){
            root.insert(new BinaryTree(datas[i]));
        }
        //前序遍历查找
        //BinaryTree treeNode = root.preOrderSearch(3);
        //中序遍历查找
        //BinaryTree treeNode = root.infixOrderSearch(3);
        //后序遍历查找
        BinaryTree treeNode = root.postOrderSearch(3);
        if(treeNode != null){
            System.out.println("查找的节点为：" + treeNode);
        }else {
            System.out.println("未找到该节点！");
        }
    }


    /**
     * @Description 测试删除节点
     * @Author yangsj
     * @Date 2019/9/18 9:11
     **/
    @Test
    public void testDeleteNode(){
        int[] datas = {1,5,0,4,2,3};
        BinaryTree root = new BinaryTree(datas[0]);
        for(int i = 1 ; i < datas.length ; i++){
            root.insert(new BinaryTree(datas[i]));
        }
        root = root.deleteTree(0, root);
        if(root != null){
            root.preOrder();
        }
    }

    /**
     * @Description 测试以二叉树前、中、后序遍历的方式遍历数组
     * @Author yangsj
     * @Date 2019/9/18 13:45
     **/
    @Test
    public void testArrayPreOrder(){
        int[] datas = {1,2,3,4,5,6,7};
        ArrayBinaryTree arrayTree = new ArrayBinaryTree(datas);
        //前序遍历：1、2、4、5、3、6、7
        arrayTree.preOrder();
        //中序遍历：4、2、5、1、6、3、7
        arrayTree.infixOrder();
        //后序遍历：4、5、2、6、7、3、1
        arrayTree.postOrder();
    }

    /**
     * @Description 测试线索化二叉树
     * @Author yangsj
     * @Date 2019/9/20 13:46
     **/
    @Test
    public void testCluesBinaryTree(){
        int[] datas = {14,26,12,50,13,0};
        CluesBinaryTree root = new CluesBinaryTree(datas[0]);
        for(int i = 1 ; i < datas.length ; i++){
            root.insert(new CluesBinaryTree(datas[i]));
        }
        root.infixClues();
        //                       12     0    12   13
        System.out.println(root.right.right.left.left.data);

        //                       26   50   26
        System.out.println(root.left.left.right.data);

        //                       26   50
        System.out.println(root.left.left.rightType);
    }

}
