package com.nuch.tree.binarySearchTree;

/**
 * @Decription 二叉搜索树（有序二叉树）：中序遍历有序（左根右）
 * @Author yangshijing
 * @Date 20190722 10:34
 **/
public class BinarySearchTree {
    //节点值
    public int data;
    //左节点
    public BinarySearchTree left;
    //右节点
    public BinarySearchTree right;

    public BinarySearchTree(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }

    /**
     * @Description 插入数据
     * @Author yangsj
     * @Date 2019/7/22 10:51
     * @Param data
     * @return
     **/
    public void insert(int data ){
        BinarySearchTree node = new BinarySearchTree(data);
        if(this != null){
            if(data < this.data){//左子树
                if(this.left != null){
                    this.left.insert(data);
                }else{
                    this.left = node;
                }

            }else{//右子树
                if(this.right != null){
                    this.right.insert(data);
                }else{
                    this.right = node;
                }
            }
        }
    }

    /**
     * @Description 遍历树
     * @Author yangsj
     * @Date 2019/7/22 11:04
     * @Param root
     * @return
     **/
    public static void  in(BinarySearchTree root){
        if (root != null){
            in(root.left);//遍历左子树
            System.out.print(root.data +",");
            in(root.right);//遍历右子树
        }
    }

    /**
     * @Description
     * @Author yangsj
     * @Date 2019/7/22 11:15
     * @Param
     * @return
     **/
    public static void main(String[] args){
        int[] datas = {1,5,0,4,2,3};
        BinarySearchTree root = new BinarySearchTree(datas[0]);
        for(int i = 1;i < datas.length ;i++){
            root.insert(datas[i]);
        }
        in(root);
    }
}
