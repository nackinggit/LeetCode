package com;

import java.util.ArrayList;
import java.util.List;

public class ListNode {
    public int val;
    public ListNode next;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public ListNode(int[] vals) {
        this.val = vals[0];
        ListNode cur = this;
        for (int i = 1; i < vals.length; i++) {
            cur.next = new ListNode(vals[i]);
            cur = cur.next;
        }
    }

    public void print() {
        List<Integer> vals = new ArrayList<>();
        ListNode cur = this;
        while (cur != null) {
            vals.add(cur.val);
            cur = cur.next;
        }
        System.out.println(vals);
    }
}
