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
    /*--------------------------插入------------------------------*/

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
    /*--------------------------遍历------------------------------*/

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
     * @Description  中序遍历
     * @Author yangsj
     * @Date 2019-09-15 21:12
     **/
    public void infixOrder(){
        if(this != null){
            //对左子树进行中序遍历
            if(this.left != null){
                this.left.infixOrder();
            }
            //输出中间节点
            System.out.println(this);
            //对右子树进行中序遍历
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
            //对左子树进行后序遍历
            if(this.left != null){
                this.left.postOrder();
            }
            //对右子树进行后序遍历
            if(this.right != null){
                this.right.postOrder();
            }
            //输出中间节点
            System.out.println(this);
        }
    }
    /*--------------------------查找------------------------------*/

    /**
     * @Description 前序遍历查找
     * @Author yangsj
     * @Date 2019/9/17 8:59
     **/
    public BinaryTree preOrderSearch(int data){
        System.out.println("进入前序遍历查找比较~");
        //判断当前节点的值是否等于data
        if(this.data == data){
            return this;
        }
        BinaryTree treeNode = null;
        //当前节点比较完之后，再递归前序遍历查找左子树
        if(this.left != null){
            treeNode = this.left.preOrderSearch(data);
        }
        //如果左子树递归查找到，将其返回
        if(treeNode != null){
            return treeNode;
        }
        //如果当前节点和当前节点的左子树都没有我们要找的值，则对当前节点的右子树进行递归查找
        if(this.right != null){
            treeNode = this.right.preOrderSearch(data);
        }

        //此时不管找不找到都将treeNode返回
        return treeNode;
    }


    /**
     * @Description 中序遍历查找
     * @Author yangsj
     * @Date 2019/9/17 9:13
     **/
    public BinaryTree infixOrderSearch(int data){
        BinaryTree treeNode = null;
        //首先对当前节点的左子树进行递归查找
        if(this.left != null){
            treeNode = this.left.infixOrderSearch(data);
        }
        //如果当前节点的左子树包含该节点，则将其返回
        if(treeNode != null){
            return treeNode;
        }
        System.out.println("进入中序遍历查找比较~");
        //否则比较当前节点
        if(this.data == data){
            return this;
        }
        //如果遍历完当前节点的左子树和当前节点，仍然没有找到，则递归遍历当前节点的右子树
        if(this.right != null){
            treeNode = this.right.infixOrderSearch(data);
        }
        //此时不管找不找到都将其返回
        return treeNode;
    }

    /**
     * @Description 后序遍历查找
     * @Author yangsj
     * @Date 2019/9/17 9:24
     **/
    public BinaryTree postOrderSearch(int data){
        BinaryTree treeNode = null;
        //先对当前节点的左子树进行递归查找
        if(this.left != null){
            treeNode = this.left.postOrderSearch(data);
        }
        //如果当前节点的左子树递归查找到，则将其返回
        if(treeNode != null){
            return treeNode;
        }
        //否则，对当前节点的右子树进行递归查找
        if(this.right != null){
            treeNode = this.right.postOrderSearch(data);
        }
        //如果当前节点的右子树递归查找到，则将其返回
        if(treeNode != null){
            return  treeNode;
        }
        System.out.println("进入后序遍历查找比较~");
        //否则，和当前节点进行比较
        if(this.data == data){
            return this;
        }else {
            return null;
        }
    }

  /*--------------------------删除------------------------------*/
    /**
     * @Description 二叉树的删除
     * @Author yangsj
     * @Date 2019/9/18 10:26
     **/
    public BinaryTree deleteTree(int data,BinaryTree root){
        //如果根节点就是要删除的节点，则将整个树置空
        if(root.data == data){
            root = null;
        }else{
            root.deleteNode(data);
        }
        return root;
    }
    /**
     * @Description 删除节点
     * 因为每个节点只有left和right两个引用，
     * 所以只能通过找到的节点的父节点删除该节点
     * @Author yangsj
     * @Date 2019/9/18 8:51
     **/
    public void deleteNode(int data){
        //如果当前节点的左子节点要删除的节点，则将其置空，并结束递归
        if(this.left != null){
            if(this.left.data == data){
                this.left = null;
                return;
            }else {//否则对当前节点的左子树进行递归查找，删除
                this.left.deleteNode(data);
            }
        }

        //如果当前节点的左子树遍历查找完之后，仍然没有找到该节点，再对其右子树进行判断查找
        if(this.right != null){
            if(this.right.data == data){
                this.right = null;
                return;
            }else {//否则对当前节点的右子树进行递归遍历查找，删除
                this.right.deleteNode(data);
            }
        }

    }

    @Override
    public String toString() {
        return "TreeNode{" + "data=" + data + '}';
    }
}
