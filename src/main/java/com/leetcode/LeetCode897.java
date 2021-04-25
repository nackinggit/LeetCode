package com.leetcode;

import com.TreeNode;

public class LeetCode897 {
    private TreeNode preNode;

    public TreeNode increasingBST(TreeNode root) {
        TreeNode dummyNode = new TreeNode(-1);
        preNode = dummyNode;
        inorder(root);
        return dummyNode.right;
    }

    public void inorder(TreeNode node) {
        if (node == null) {
            return;
        }
        inorder(node.left);

        // 在中序遍历的过程中修改节点指向
        preNode.right = node;
        node.left = null;
        preNode = node;

        inorder(node.right);
    }
}
