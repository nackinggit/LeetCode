package com.leetcode;

import com.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 101. 对称二叉树
 * 给你一个二叉树的根节点 root ， 检查它是否轴对称。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：root = [1,2,2,3,4,4,3]
 * 输出：true
 * 示例 2：
 * <p>
 * <p>
 * 输入：root = [1,2,2,null,3,null,3]
 * 输出：false
 * <p>
 * <p>
 * 提示：
 * <p>
 * 树中节点数目在范围 [1, 1000] 内
 * -100 <= Node.val <= 100
 */
public class LeetCode101 {
    public static boolean isSymmetric(TreeNode root) {
        if (root == null) return false;
        return isSymmetric(root.left, root.right);
    }

    public static boolean isSymmetric(TreeNode left, TreeNode right) {
        if (left != null && right != null) {
            if (left.val != right.val) return false;
            return isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left);
        }
        return left == null && right == null;
    }

    public static boolean isSymmetric2(TreeNode root) {
        if (root == null) return false;
        LinkedList<TreeNode> left = new LinkedList<>();
        LinkedList<TreeNode> right = new LinkedList<>();
        left.push(root);
        right.push(root);
        while (!left.isEmpty() && !right.isEmpty()) {
            TreeNode l = left.pop();
            TreeNode r = right.pop();
            if(l == null && r == null) continue;
            if(l == null || r == null) return false;
            if(l.val != r.val) return false;
            left.push(l.left);
            right.push(r.right);
            left.push(l.right);
            right.push(r.left);
        }

        return true;
    }
}
