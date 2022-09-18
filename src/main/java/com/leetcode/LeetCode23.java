package com.leetcode;

import com.ListNode;

/**
 * 23. 合并K个升序链表
 * 给你一个链表数组，每个链表都已经按升序排列。
 * <p>
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：lists = [[1,4,5],[1,3,4],[2,6]]
 * 输出：[1,1,2,3,4,4,5,6]
 * 解释：链表数组如下：
 * [
 * 1->4->5,
 * 1->3->4,
 * 2->6
 * ]
 * 将它们合并到一个有序链表中得到。
 * 1->1->2->3->4->4->5->6
 * 示例 2：
 * <p>
 * 输入：lists = []
 * 输出：[]
 * 示例 3：
 * <p>
 * 输入：lists = [[]]
 * 输出：[]
 * <p>
 * <p>
 * 提示：
 * <p>
 * k == lists.length
 * 0 <= k <= 10^4
 * 0 <= lists[i].length <= 500
 * -10^4 <= lists[i][j] <= 10^4
 * lists[i] 按 升序 排列
 * lists[i].length 的总和不超过 10^4
 */
public class LeetCode23 {
    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 1) return lists[0];
        if (lists.length == 2) return mergeList(lists[0], lists[1]);

        int mid = lists.length / 2;
        ListNode[] left = new ListNode[mid];
        ListNode[] right = new ListNode[lists.length - mid];
        for (int i = 0; i < mid; i++) {
            left[i] = lists[i];
        }

        for (int i = mid, j = 0; i < lists.length; i++, j++) {
            right[j] = lists[i];
        }
        return mergeList(mergeKLists(left), mergeKLists(right));
    }

    public static ListNode mergeList(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        ListNode head;
        if (l1.val >= l2.val) {
            head = l2;
            head.next = mergeList(l1, l2.next);
        } else {
            head = l1;
            head.next = mergeList(l1.next, l2);
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(new int[]{1, 2, 3});
        ListNode head2 = new ListNode(new int[]{4, 5, 6});
        ListNode head3 = new ListNode(new int[]{5, 6, 7});
        ListNode[] listNodes = new ListNode[]{head, head2, head3};
        ListNode res = mergeKLists(listNodes);
        res.print();
    }
}
