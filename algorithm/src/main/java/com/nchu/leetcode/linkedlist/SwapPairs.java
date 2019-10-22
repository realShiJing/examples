package com.nchu.leetcode.linkedlist;


import org.junit.Test;

/**
 * @Decription 两两交换链表中的节点
 *
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * 示例:
 *      给定 1->2->3->4, 你应该返回 2->1->4->3.
 * 来源：力扣（LeetCode）
 * @Author yangsj
 * @Date 20191009 14:33
 *
*/
public class SwapPairs {



    /**
     * @Description 测试
     * @Author yangsj
     * @Date 2019/10/21 15:11
     **/
    @Test
    public void swapPairs(){
        ListNode head  = new ListNode(1);
        ListNode temp = head;
        for (int i = 2; i <= 5; i++) {
            temp.next = new ListNode(i);
            temp = temp.next;
        }
        System.out.println("交换前：" + head);
        ListNode swapPairsNode = test2(head);
        System.out.println("交换后：" + swapPairsNode);

    }
    /**
     * @Description 实现方式一
     * @Param 传入单链表的头结点
     * @Return
     * @Author yangsj
     * @Date 2019/10/9 14:35
     *
     * */

   /* public ListNode test1(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode next = head.next;
        head.next = test1(next.next);
        next.next = head;
        return next;
    }*/


   /**
    * @Description 实现方式二
    * @Param 传入单链表的头结点
    * @Author yangsj
    * @Date 2019/10/21 16:19
    **/
    public ListNode test2(ListNode head) {
        //利用一个虚节点指向头结点，便于返回交换后的节点
        ListNode pre = new ListNode(0);
        pre.next = head;
        ListNode temp = pre;
        while(temp.next != null && temp.next.next != null) {
            ListNode start = temp.next;
            ListNode end = temp.next.next;
            temp.next = end;
            start.next = end.next;
            end.next = start;
            temp = start;
        }
        return pre.next;
    }


}
