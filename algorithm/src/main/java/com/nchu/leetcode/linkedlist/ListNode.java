package com.nchu.leetcode.linkedlist;

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
