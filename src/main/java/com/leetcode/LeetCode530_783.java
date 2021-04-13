package com.leetcode;

import com.TreeNode;

public class LeetCode530_783 {
    int ans = Integer.MAX_VALUE;
    Integer pre = null;

    public int minDiffInBST(TreeNode root) {
        dfs(root);
        return ans;
    }

    private void dfs(TreeNode root) {
        if (root == null) return;
        dfs(root.left);
        if (pre != null) {
            ans = Math.min(ans, Math.abs(pre - root.val));
        }
        pre = root.val;
        dfs(root.right);
    }
}
