package com.nchu.tree.binarytree;

/**
 * @Decription 线索化二叉树
 * 线索化，充分利用二叉树的空指针域
 * 为二叉树的遍历提供区别于递归的解决方案
 * @Author yangsj
 * @Date 20190920 11:53
 **/
public class CluesBinaryTree{

    //用于保存线索化过程中的前一个节点
    public static CluesBinaryTree  pre ;
    //左子节点
    public CluesBinaryTree left;
    //右子节点
    public CluesBinaryTree right;
    //节点值
    public int data;


    //左节点的类型，0：左节点 1：前驱
    public int leftType;

    //右节点的类型，0：右节点 1：后驱
    public int rightType;
    //节点类型-0
    public static final int NODE_TYPE = 0;
    //线索化类型-1
    public static final int CLUES_TYPE = 1;

    public CluesBinaryTree(int data) {
        this.data = data;
    }

    /**
     * @Description 中序线索化二叉树
     * @Author yangsj
     * @Date 2019/9/20 14:01
     **/
    public void infixClues(){
        //首先递归线索化当前节点的左子树
        if(this.left != null){
            this.left.infixClues();
        }
        //设置节点的前驱
        if( this.left == null){
            //然后线索化节点,将节点的左指针指向前驱
            this.left = pre;
            //并将当前节点的左指针类型设置为 线索化类型
            this.leftType = CLUES_TYPE;
        }

        //设置节点的后驱
        if(pre != null && pre.right == null){
            //将前一个节点的后驱指向当前节点
            pre.right = this;
            //并将当前节点的右指针类型设置为 线索化类型
            pre.rightType = CLUES_TYPE;
        }

        //当前节点的前驱和后驱设置完成后，设置当前结点为下一个结点的前驱结点
        pre = this;
        //最后线索化当前节点的右子树
        if(this.right != null){
            this.right.infixClues();
        }


    }

    /**
     * @Description  节点的插入
     * @Author yangsj
     * @Date 2019-09-15 21:13
     **/
    public void insert(CluesBinaryTree node){
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


}
