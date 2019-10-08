package com.nchu.leetcode.linkedlist;

import org.junit.Test;

/**
 * @Decription 翻转单链表
 * @Author yangsj
 * @Date 20190926 15:01
 **/
public class ReverseLinkedList {

    /**
     * @Description 翻转
     * 示例:
     *  输入: 1->2->3->4->5->NULL
     *  输出: 5->4->3->2->1->NULL
     *
     * @Param head 传入链表的头结点
     * @Return 返回反转后的头结点
     * @Author yangsj
     * @Date 2019/9/26 15:04
     **/
    @Test
    public void reverse(){
        ListNode head  = new ListNode(1);
        ListNode temp = head;
        for (int i = 2; i <= 5; i++) {
            temp.next = new ListNode(i);
            temp = temp.next;
        }
        System.out.println("反转前：" + head);
        ListNode reverseHead = test2(head);
        System.out.println("反转后：" + reverseHead);
    }

    /**
     * @Description 实现方式一,临时节点pre动态拼接，会破坏原有的链表结构
     * @Author yangsj
     * @Date 2019/10/8 10:04
     **/
    public ListNode test1(ListNode head){
        //临时节点，用于遍历链表
        ListNode temp = head;
        //翻转后的头结点,初始值为原节点
        ListNode pre = null;

        //如果链表为空，或者链表只有一个节点，将头结点返回
        if(head == null || head.next == null){
            return head;
        }

        while (temp != null){
            //保存当前节点的下个节点，防止断链
            ListNode next = temp.next;
            //将当前节点的下个节点指向前一个节点
            temp.next = pre;
            //设置当前节点为下一个节点的前节点
            pre = temp;
            //继续遍历下一个节点
            temp = next;
        }
        return pre;

    }
    /**
     * @Description 实现方式二
     * @Author yangsj
     * @Date 2019/10/8 10:04
     **/
    public ListNode test2(ListNode cur ){
        if (cur == null || cur.next == null)
            return cur;
        //递归遍历链表，直至最后一个节点开始，回溯返回
        ListNode p = test2(cur.next);
        //回溯返回处理，将当前节点的下个节点的下个指针指向当前节点
        cur.next.next = cur;
        //删除当前节点的下个节点
        cur.next = null;
        //返回反转后，以原尾节点为头指针的翻转链表
        return p;
    }
}
/**
 * @Description  单链表
 * @Author yangsj
 * @Date 2019/9/26 15:03
 **/
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                ", next=" + next +
                '}';
    }
}
