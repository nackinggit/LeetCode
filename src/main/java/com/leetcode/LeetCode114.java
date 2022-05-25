package com.leetcode;

import com.TreeNode;

/**
 * 114. 二叉树展开为链表
 * 给你二叉树的根结点 root ，请你将它展开为一个单链表：
 * <p>
 * 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
 * 展开后的单链表应该与二叉树 先序遍历 顺序相同。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：root = [1,2,5,3,4,null,6]
 * 输出：[1,null,2,null,3,null,4,null,5,null,6]
 * 示例 2：
 * <p>
 * 输入：root = []
 * 输出：[]
 * 示例 3：
 * <p>
 * 输入：root = [0]
 * 输出：[0]
 */
public class LeetCode114 {
    public void flatten(TreeNode root) {
        TreeNode cur = root;
        while (cur != null) {
            if(cur.left != null) {
                TreeNode next = cur.left;
                TreeNode parent = next;
                while (parent.right != null) {
                    parent = parent.right;
                }
                parent.right = cur.right;
                cur.right = next;
                cur.left = null;
            }
            cur = cur.right;
        }
    }
}
