package com.nchu.tree.binarySearchTree;

import org.checkerframework.checker.units.qual.A;

/**
 * @Decription 自平衡二叉树
 * @Author yangsj
 * @Date 20190910 09:24
 **/
public class AvlTree {

    //数据
    public int data;
    //左子节点
    public AvlTree left;
    //右子节点
    public AvlTree right;

    //当前节点的高度
    public int height;

    public AvlTree(int data) {
        this.data = data;
    }


    /**
     * @Description 计算节点的高度
     * @Author yangsj
     * @Date 2019/9/24 9:52
     **/
    public int height(AvlTree node){
        return node == null ? 0 :(Math.max(node.left == null ? 0 : height(node.left) ,node.right == null ? 0 : height(node.right))+ 1);
    }


    /**
     * @Description 右旋
     * @Param unbalance 不平衡点
     * @Return left 新的平衡点
     * @Author yangsj
     * @Date 2019/9/24 10:38
     **/
    public AvlTree rotateRight(AvlTree unbalance){
        //失衡点的左孩子
        AvlTree left = unbalance.left;

        //将失衡点的左孩子更新为left的右子树
        unbalance.left = left.right;

        //失衡点右旋，变成left的右孩子
        left.right = unbalance;

        //更新失衡点和left的高度
        unbalance.height = height(unbalance);
        left.height = height(left);
        //left替换掉了原来失衡点的位置，将left返回
        return left;
    }


    /**
     * @Description 左旋
     * @Param unbalance 不平衡点
     * @Return right 新的平衡点
     * @Author yangsj
     * @Date 2019/9/24 11:00
     **/
    public AvlTree rotateLeft(AvlTree unbalance){
        //失衡点的右子树
        AvlTree right = unbalance.right;

        //将失衡点的右孩子更新为right的左子树
        unbalance.right = right.left;

        //失衡点左旋，变成right的左孩子
        right.left = unbalance;

        //更新失衡点和right的高度
        unbalance.height = height(unbalance);
        right.height = height(right);
        //right替换了原来失衡点的位置，将right返回
        return right;
    }


    /**
     * @Description 先左旋后右旋
     * @Author yangsj
     * @Date 2019/9/24 11:35
     **/
    public AvlTree rotateLeftRight(AvlTree unbalance){
        //失衡点的左子节点进行左旋
        unbalance.left = rotateLeft(unbalance.left);

        //失衡点进行右旋
        AvlTree avlTree = rotateRight(unbalance);

        //将平衡后的子树进行返回
        return avlTree;
    }


    /**
     * @Description 先右旋再左旋
     * @Author yangsj
     * @Date 2019/9/24 11:46
     **/
    public AvlTree rotateRightLeft(AvlTree unbalance){
        //失衡点的右子节点进行右旋
        unbalance.right = rotateRight(unbalance.right);

        //失衡点进行左旋
        AvlTree avlTree = rotateLeft(unbalance);

        //将平衡后的节点返回
        return avlTree;
    }

    /**
     * @Description   插入
     * @Author yangsj
     * @Date 2019-09-24 20:35
     **/
    public AvlTree insert(int data){
        return  this.insert(data,this);
    }
    /**
     * @Description 插入
     * @Author yangsj
     * @Return 平衡后的节点
     * @Date 2019/9/24 14:12
     **/
    public AvlTree insert(int data,AvlTree node){
        //node 初始为二叉树的根节点
        //递归到最后node会为null,说明找到了要插入节点的位置，这时开始回溯处理
        if(node == null){
            return new AvlTree(data);
        }

        //如果要插入值小于当前节点的值，则向左递归插入
        if(data < node.data){
            //返回平衡后的节点，并重新赋值
            node.left = insert(data, node.left);
        }else{//如果要插入值大于当前节点的值，则向右递归插入
            node.right = insert(data , node.right);
        }

        //当添加完一个结点后，如果: (右子树的高度-左子树的高度) > 1 , 左旋转
        if(height(node.right) - height(node.left) > 1){
            //如果它的右子树的左子树的高度大于它的右子树的右子树的高度
            if(right != null && height(right.left) > height(right.right)){
                node = rotateRightLeft(node);
            }else{
                node = rotateLeft(node);
            }
        }
        //当添加完一个结点后，如果 (左子树的高度 - 右子树的高度) > 1, 右旋转
        if(height(node.left) - height(node.right) > 1){
            //如果它的左子树的右子树高度大于它的左子树的高度
            if(left != null && height(left.right) > height(left.left)) {
                node = rotateLeftRight(node);
            }else{
                node = rotateRight(node);
            }
        }
        //重新计算当前节点的高度
        node.height = height(node);
        return node;
    }

    /**
     * @Description  中序
     * @Author yangsj
     * @Date 2019-09-24 20:34
     **/
    public void infixOrder(){
        this.in(this);
    }
    /**
     * @Description 中序遍历
     * @Author yangsj
     * @Date 2019/7/22 11:04
     * @Param root
     * @return
     **/
    private   void  in(AvlTree root){
        if (root != null){
            in(root.left);//遍历左子树
            System.out.print(root.data +",");
            in(root.right);//遍历右子树
        }
    }
}
