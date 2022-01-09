package com.leetcode;

import com.ListNode;

/**
 * 反转链表
 */
public class LeetCode206 {
    public ListNode reverseList(ListNode head) {
        ListNode cur = head;
        ListNode pre = null;
        while (cur != null) {
            ListNode tmp = cur;
            ListNode next = cur.next;
            cur.next = pre;
            pre = tmp;
            cur = next;

            if (cur == null) {
                return pre;
            }
        }
        return null;
    }

    public ListNode reverseList2(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode newHead = reverseList2(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    public static void main(String[] args) {
        LeetCode206 leetCode206 = new LeetCode206();
        leetCode206.reverseList(new ListNode(new int[]{1, 2, 3, 4})).print();
        leetCode206.reverseList2(new ListNode(new int[]{1, 2, 3, 4})).print();
    }
}
