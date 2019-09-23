package com.nchu.tree.binarySearchTree;

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

    /*--------------------------添加------------------------------*/

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
    /*--------------------------遍历------------------------------*/

    /**
     * @Description 中序遍历
     * @Author yangsj
     * @Date 2019/7/22 11:04
     * @Param root
     * @return
     **/
    public  void  in(BinarySearchTree root){
        if (root != null){
            in(root.left);//遍历左子树
            System.out.print(root.data +",");
            in(root.right);//遍历右子树
        }
    }
  /*--------------------------删除------------------------------*/
    /**
     * @Description 二叉树排序树节点的删除
     * @Author yangsj
     * @Date 2019/9/23 11:30
     **/
    public  void deleteNode(int data){
        //要删除的节点
        BinarySearchTree node = this.searchNode(data);
        //要删除节点的父节点
        BinarySearchTree parentNode  = this.searchParent(data);
        //如果父节点为空表示，要删除的节点是根节点，这时直接返回
        if(parentNode == null){
            return;
        }

        if(node != null ){
            if(node.left == null && node.right == null){ //1、删除的节点是叶子节点
                //直接将父节点的子节点置空
                if(parentNode.left == node ){
                    parentNode.left = null;
                }else{
                    parentNode.right = null;
                }
            }else if(node.left != null && node. right != null){//2、删除的节点包含两个子节点
                //从该节点的左子树找一个最大值，或者从该节点的右子树找一个最小值，替换该节点
                BinarySearchTree maxNode = node.left.searchMaxNode();
                //最大节点的父节点
                BinarySearchTree maxParentNode = node.searchParent(maxNode.data);
                //最大值节点不是要删除节点的子节点
                if(maxParentNode != node){
                    //最大节点的父节点指向空，将最大值节点删除
                    maxParentNode.right = null;
                    maxNode.left = node.left;
                    maxNode.right = node.right;
                }else{//最大值节点是要删除节点的子节点,因为是向左查找，所以左子节点不用替换，否则会破坏二叉树的结构
                    maxNode.right = node.right;
                }
                //用最大值节点替换要删除的节点
                if(parentNode.left == node){//要删除的节点是左子节点
                    parentNode.left = maxNode;
                }else{//要删除的节点是右子节点
                    parentNode.right = maxNode;
                }

            }else{//3、删除的节点只包含一个子节点
                if(parentNode.left == node ){//要删除的节点是左子节点
                    if(node.left != null){//要删除的节点有左节点
                        parentNode.left = node.left;
                    }else{//要删除的节点有左节点
                        parentNode.left = node.right;
                    }
                }else{
                    if(node.right != null){
                        parentNode.right = node.left;
                    }else{
                        parentNode.right = node.right;
                    }
                }
            }
        }

    }

    /*--------------------------查找------------------------------*/

    /**
     * @Description 查找节点并返回
     * @Author yangsj
     * @Date 2019/9/23 11:35
     **/
    public  BinarySearchTree searchNode(int data ){
        BinarySearchTree node = null;
        //如果查找的值小于当前节点的值，向左递归查找
        if(this.left != null && this.data > data ){
            node =   this.left.searchNode(data);
        }
        //如果查找的值等于当前节点的值，则赋值并返回
        if(this.data == data){
            node =  this;
        }
        //如果查找的值大于当前节点的值，向右递归查找
        if(this.right != null && this.data < data){
            node =  this.right.searchNode(data);
        }
        return node;
    }


    /**
     * @Description 查找节点的父节点并返回
     * @Author yangsj
     * @Date 2019/9/23 11:36
     **/
    public  BinarySearchTree searchParent(int data){
        //如果当前节点的左右子节点的值等于要查找的值，则返回
        if(this.left != null && this.left.data == data
                || this.right != null && this.right.data == data){
            return this;
        }else if(this.left != null && this.data > data){
            //如果要查找的值小于当前节点的值，则向左递归查找
           return this.left.searchParent(data);
        }else if(this.right != null && this.data < data){
            //如果要查找的值大于当前节点的值，则向右递归查找
          return this.right.searchParent(data);
        }else{//如果遍历到最后都没有找到该节点的父节点，说明该节点是根节点，则返回null
          return null;
        }
    }
    
    
    /**
     * @Description 从当前节点遍历查找值最大的节点
     * @Author yangsj
     * @Date 2019/9/23 15:06
     **/
    public BinarySearchTree searchMaxNode(){
        BinarySearchTree node = this;
        while (node.right != null){
            node  = node.right;
        }
       return node;
    }


}
