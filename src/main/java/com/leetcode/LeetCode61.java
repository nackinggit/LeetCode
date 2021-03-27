package com.leetcode;

import com.ListNode;

public class LeetCode61 {
    public ListNode rotateRight(ListNode head, int k) {
        if (k == 0 || head == null || head.next == null) return head;
        ListNode cur = head;
        int n = 1;
        while (cur.next != null) {
            n += 1;
            cur = cur.next;
        }

        int add = n - k % n;
        if (add == n) {
            return head;
        }

        cur.next = head;
        while (add > 0) {
            add--;
            cur = cur.next;
        }

        ListNode res = cur.next;
        cur.next = null;
        return res;
    }


    public static void main(String[] args) {
        ListNode head = new ListNode(new int[]{1, 2, 3, 4, 5});
        ListNode res = new LeetCode61().rotateRight(head, 2);
        res.print();

    }
}
