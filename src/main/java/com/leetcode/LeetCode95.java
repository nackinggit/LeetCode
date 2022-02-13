package com.leetcode;

import com.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 95. 不同的二叉搜索树 II
 * 给你一个整数 n ，请你生成并返回所有由 n 个节点组成且节点值从 1 到 n 互不相同的不同 二叉搜索树 。可以按 任意顺序 返回答案。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：n = 3
 * 输出：[[1,null,2,null,3],[1,null,3,2],[2,1,3],[3,1,null,null,2],[3,2,null,1]]
 * 示例 2：
 * <p>
 * 输入：n = 1
 * 输出：[[1]]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 8
 */
public class LeetCode95 {
    public List<TreeNode> generateTrees(int n) {
        if (n <= 0) return new ArrayList<>();

        return generateTrees(1, n);
    }

    private List<TreeNode> generateTrees(int start, int end) {
        List<TreeNode> res = new ArrayList<>();

        if (start > end) {
            res.add(null);
        } else {
            for (int i = start; i <= end; i++) {
                List<TreeNode> leftTrees = generateTrees(start, i - 1);
                List<TreeNode> rightTrees = generateTrees(i + 1, end);

                for (TreeNode left : leftTrees) {
                    for (TreeNode right : rightTrees) {
                        TreeNode cur = new TreeNode(i);
                        cur.left = left;
                        cur.right = right;
                        res.add(cur);
                    }
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new LeetCode95().generateTrees(3));
    }
}
