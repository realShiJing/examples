####二叉查找树（Binary Search Tree）

>也称为二叉搜索树、有序二叉树（ordered binary tree）或排序二叉树（sorted binary tree），是指一棵空树或者具有下列性质的二叉树：

1. 若任意节点的左子树不空，则左子树上所有节点的值均小于它的根节点的值
2. 若任意节点的右子树不空，则右子树上所有节点的值均大于它的根节点的值
3. 任意节点的左、右子树也分别为二叉查找树
4. 没有键值相等的节点

* ###### 二叉搜索树有可能退化为单向链表，这时查询的时间复杂度由log(n)退化为n
* ###### 而红黑树，没添加一个节点都会进行左旋或者右旋的自平衡，可以保证根节点的左子树和右子树拥有相近的深度，防止了二叉树的退化

#### 红黑树自平衡（左旋 & 右旋）
1. 根节点都是黑色
2. 不可能有连在一起的红色节点
3. 每个红色节点的两个节点都是黑色
##### hashMap源码
``` java
 
 static <K,V> TreeNode<K,V> balanceInsertion(TreeNode<K,V> root,
                                                    TreeNode<K,V> x) {
            //每插入的一个新的节点，其颜色都为红色，会在后续自平衡中进行变色
            x.red = true;
            // xp 当前节点的父节点
            // xpp 当前节点的祖父节点
            // xppl 当前节点的祖父节点的左节点
            // xppr 当前节点的祖父节点的右节点
            for (TreeNode<K,V> xp, xpp, xppl, xppr;;) {
                //如果当前节点的父节点为null,表示当前节点为根节点并返回，根节点为黑色
                if ((xp = x.parent) == null) {
                    x.red = false;
                    return x;
                }
                //如果当前节点的父节点为黑色 或者 当前节点没有祖父节点 （没有叔节点）
                else if (!xp.red || (xpp = xp.parent) == null)
                    return root;
                //父节点为红色，当前节点为红色，才需要进行变色或者左旋、右旋
                if (xp == (xppl = xpp.left)) {//父节点是左子树
                    if ((xppr = xpp.right) != null && xppr.red) {//叔节点为红色
                        xppr.red = false;//叔节点变为黑色
                        xp.red = false;//父节点变为黑色
                        xpp.red = true;//祖父节点变为红色
                        x = xpp;
                    }
                    else {//没有叔节点，或者叔节点为黑色
                        if (x == xp.right) {//父节点是左子树，当前节点是右节点
                            root = rotateLeft(root, x = xp);
                            xpp = (xp = x.parent) == null ? null : xp.parent;
                        }
                        if (xp != null) {
                            xp.red = false;//父节点变为黑色
                            if (xpp != null) {
                                xpp.red = true;//祖父节点变为红色
                                root = rotateRight(root, xpp);//右旋
                            }
                        }
                    }
                }
                else {//父节点为右子树
                    if (xppl != null && xppl.red) {//叔节点为红色
                        xppl.red = false;//叔节点变为黑色
                        xp.red = false;//父节点变为黑色
                        xpp.red = true;//祖父节点变为红色
                        x = xpp;
                    }
                    else {//没有叔节点，或者叔节点为黑色
                        if (x == xp.left) {//当前节点为左节点
                            root = rotateRight(root, x = xp);
                            xpp = (xp = x.parent) == null ? null : xp.parent;
                        }
                        if (xp != null) {
                            xp.red = false;//父节点变为黑色
                            if (xpp != null) {
                                xpp.red = true;//祖父节点变为红色
                                root = rotateLeft(root, xpp);//左旋
                            }
                        }
                    }
                }
            }
        }
```
* ###### 先左旋后右旋：父节点是左子树，当前节点是右节点，无叔节点或者叔节点为黑色
* ###### 先右旋后左旋：父节点是右子树，当前节点是左节点，无叔节点或者叔节点为黑色
* ###### 右旋：父节点是左子树，当前节点是左节点，无叔节点或者叔节点为黑色
* ###### 左旋：父节点是右子树，当前节点是右节点，无叔节点或者叔节点为黑色
##### 右旋
``` java
   static <K,V> TreeNode<K,V> rotateRight(TreeNode<K,V> root,
                                               TreeNode<K,V> p) {
            TreeNode<K,V> l, pp, lr;
            if (p != null && (l = p.left) != null) {//当前节点不为null，且当前节点有左节点
                if ((lr = p.left = l.right) != null)//当前节点的左子节点指向左子节点的右节点
                    //当前节点的左子节点的右子节点不为null
                    lr.parent = p;//左子节点的右节点的父节点指向当前节点
                if ((pp = l.parent = p.parent) == null)//左子节点的父节点指向当前节点的父节点
                    //当前节点的父节点为null,代表当前为根节点，这时将根节点指向左子节点，并变为黑色
                    (root = l).red = false;
                else if (pp.right == p)//当前节点为右节点时
                    pp.right = l;//父节点的右子节点指向左子节点
                else//当前节点为左节点时
                    pp.left = l;//父节点的左节点指向左子节点
                l.right = p;//左子节点的右节点指向当前节点
                p.parent = l;//当前节点的父节点指向左子节点
            }
            return root;
        }

```
##### 左旋
``` java
 static <K,V> TreeNode<K,V> rotateLeft(TreeNode<K,V> root,
                                              TreeNode<K,V> p) {
            TreeNode<K,V> r, pp, rl;
            if (p != null && (r = p.right) != null) {//当前节点不为null,且当前节点有右节点
                if ((rl = p.right = r.left) != null)//当前节点的右节点指向右子节点的左子节点
                    //当前节点的右子节点的左子节点不为null
                    rl.parent = p;//右子节点的左节点的父节点指向当前节点
                if ((pp = r.parent = p.parent) == null)//右子节点的父节点指向当前节点的父节点
                    (root = r).red = false;
                else if (pp.left == p)//当前节点为左节点时
                    pp.left = r;//父节点的左节点指向当前节点的右节点
                else
                    pp.right = r;
                r.left = p; //当前节点变为右节点的左子节点
                p.parent = r;//当前节点的父节点变为当前节点的右节点
            }
            return root;
        }
```