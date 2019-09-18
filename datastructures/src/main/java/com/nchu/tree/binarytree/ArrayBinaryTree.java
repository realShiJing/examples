package com.nchu.tree.binarytree;

/**
 * @Decription 顺序存储二叉树（完全二叉树）
 * 使用数组，实现二叉树的前、中、后序遍历
 * @Author yangsj
 * @Date 20190918 11:49
 **/
public class ArrayBinaryTree {
    //用于存放二叉树节点的数组
    public int[] elements;
    //构造方法，初始化数组

    public ArrayBinaryTree(int[] elements) {
        this.elements = elements;
    }

    /**
     * @Description 从根节点零开始前序遍历整个数组
     * @Author yangsj
     * @Date 2019/9/18 13:44
     **/
    public void preOrder(){
        System.out.print("顺序存储二叉树的前序遍历：");
        preOrder(0);
        System.out.println();
    }
    /**
     * @Description 数组实现树的前序遍历
     * @Author yangsj
     * @Date 2019/9/18 11:52
     **/
    public void preOrder(int index){
        //数组为空或者，索引越界直接返回，结束递归
        if(elements == null || elements.length == 0 ||index >= elements.length){
            return;
        }
        //首先输出当前节点
        System.out.print(elements[index]+",");
        //再递归遍历其左子节点2*n+1
        if(2*index+1 < elements.length){
            preOrder(2*index+1);
        }
        //再递归遍历其右子节点2*n+2
        if(2*index+2 < elements.length){
            preOrder(2*index+2);
        }
    }

    /**
     * @Description 从根节点零开始中序遍历整个数组
     * @Author yangsj
     * @Date 2019/9/18 13:44
     **/
    public void infixOrder(){
        System.out.print("顺序存储二叉树的中序遍历：");
        infixOrder(0);
        System.out.println();

    }
    /**
     * @Description 数组实现树的中序遍历
     * @Author yangsj
     * @Date 2019/9/18 11:52
     **/
    public void infixOrder(int index){
        //数组为空或者，索引越界直接返回，结束递归
        if(elements == null || elements.length == 0 ||index >= elements.length){
            return;
        }
        //先递归遍历其左子节点2*n+1
        if(2*index+1 < elements.length){
            infixOrder(2*index+1);
        }
        //当前节点的左子树遍历完成后，输出当前节点
        System.out.print(elements[index]+",");
        //再递归遍历其右子节点2*n+2
        if(2*index+2 < elements.length){
            infixOrder(2*index+2);
        }
    }
    /**
     * @Description 从根节点零开始后序遍历整个数组
     * @Author yangsj
     * @Date 2019/9/18 13:44
     **/
    public void postOrder(){
        System.out.print("顺序存储二叉树的后序遍历：");
        postOrder(0);
        System.out.println();
    }
    /**
     * @Description 数组实现树的前序遍历
     * @Author yangsj
     * @Date 2019/9/18 11:52
     **/
    public void postOrder(int index){
        //数组为空或者，索引越界直接返回，结束递归
        if(elements == null || elements.length == 0 ||index >= elements.length){
            return;
        }
        //先递归遍历其左子节点2*n+1
        if(2*index+1 < elements.length){
            postOrder(2*index+1);
        }
        //再递归遍历其右子节点2*n+2
        if(2*index+2 < elements.length){
            postOrder(2*index+2);
        }
        //当前节点的左子树和右子树都遍历完成后，输出当前节点
        System.out.print(elements[index]+",");
    }

}
