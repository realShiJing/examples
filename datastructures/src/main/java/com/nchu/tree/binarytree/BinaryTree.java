package com.nchu.tree.binarytree;

/**
 * @Decription 二叉树的前序、中序、后序遍历
 * @Author yangsj
 * @Date 2019-09-15 21:07
 **/
public class BinaryTree {
    //节点值
    private int data;
    //左子节点
    private BinaryTree left;
    //右子节点
    private BinaryTree right;

    //构造器 左子节点和右子节点默认为null
    public BinaryTree(int data) {
        this.data = data;
    }
    /**
     * @Description  节点的插入
     * @Author yangsj
     * @Date 2019-09-15 21:13
     **/
    public void insert(BinaryTree node){
        int data = node.data;
        if(data > this.data){//插入左子树
            if(this.left != null){
                this.left.insert(node);
            }else {
                this.left = node;
            }
        }else {//插入右子树
            if(this.right != null){
                this.right.insert(node);
            }else{
                this.right = node;
            }
        }
    }
    /**
     * @Description  前序遍历
     * @Author yangsj
     * @Date 2019-09-15 21:12
     **/
    public void preOrder(){
        if(this != null){
            //输出中间节点
            System.out.println(this);
            //对左子树进行前序遍历
            if(this.left != null){
                this.left.preOrder();
            }
            //对右子树进行前序遍历
            if(this.right != null){
                this.right.preOrder();
            }
        }
    }

    /**
     * @Description  前序遍历
     * @Author yangsj
     * @Date 2019-09-15 21:12
     **/
    public void infixOrder(){
        if(this != null){
            //对左子树进行前序遍历
            if(this.left != null){
                this.left.infixOrder();
            }
            //输出中间节点
            System.out.println(this);
            //对右子树进行前序遍历
            if(this.right != null){
                this.right.infixOrder();
            }
        }
    }

    /**
     * @Description  后序遍历
     * @Author yangsj
     * @Date 2019-09-15 21:12
     **/
    public void postOrder(){
        if(this != null){
            //对左子树进行前序遍历
            if(this.left != null){
                this.left.postOrder();
            }
            //对右子树进行前序遍历
            if(this.right != null){
                this.right.postOrder();
            }
            //输出中间节点
            System.out.println(this);
        }
    }

    @Override
    public String toString() {
        return "TreeNode{" + "data=" + data + '}';
    }
}
