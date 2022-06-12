package com.leetcode;

import com.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 105. 从前序与中序遍历序列构造二叉树
 * 给定两个整数数组 preorder 和 inorder ，其中 preorder 是二叉树的先序遍历， inorder 是同一棵树的中序遍历，请构造二叉树并返回其根节点。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * <p>
 * 输入: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
 * 输出: [3,9,20,null,null,15,7]
 * 示例 2:
 * <p>
 * 输入: preorder = [-1], inorder = [-1]
 * 输出: [-1]
 * <p>
 * <p>
 * 提示:
 * <p>
 * 1 <= preorder.length <= 3000
 * inorder.length == preorder.length
 * -3000 <= preorder[i], inorder[i] <= 3000
 * preorder 和 inorder 均 无重复 元素
 * inorder 均出现在 preorder
 * preorder 保证 为二叉树的前序遍历序列
 * inorder 保证 为二叉树的中序遍历序列
 */
public class LeetCode105 {
    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null
                || preorder.length == 0 || inorder.length == 0
                || preorder.length != inorder.length) {
            return null;
        }

        return doBuild(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    private static TreeNode doBuild(int[] preorder, int ps, int pe, int[] inorder, int is, int ie) {
        if (ps > pe || is > ie) return null;
        int val = preorder[ps];
        TreeNode root = new TreeNode(val);
        int idx = -1;
        for (int i = is; i <= ie; i++) {
            if (inorder[i] == val) {
                idx = i;
                break;
            }
        }
        int llen = idx - is;
        root.left = doBuild(preorder, ps + 1, ps + llen, inorder, is, idx - 1);
        root.right = doBuild(preorder, ps + 1 + llen, pe, inorder, idx + 1, ie);
        return root;
    }

    public static TreeNode buildTree2(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null
                || preorder.length == 0 || inorder.length == 0
                || preorder.length != inorder.length) {
            return null;
        }

        TreeNode root = new TreeNode(preorder[0]);
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        int idx = 0;
        for (int i = 1; i < preorder.length; i++) {
            TreeNode cur = new TreeNode(preorder[i]);
            TreeNode parent = stack.peek();
            assert parent != null;
            if (parent.val != inorder[idx]) {
                parent.left = cur;
                stack.push(parent.left);
            } else {
                while (!stack.isEmpty() && stack.peek().val == inorder[idx]) {
                    parent = stack.pop();
                    idx++;
                }
                parent.right = cur;
                stack.push(parent.right);
            }
        }
        return root;
    }


}
