package com.leetcode;

import com.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 437. 路径总和 III
 * 给定一个二叉树的根节点 root ，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目。
 * <p>
 * 路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
 * 输出：3
 * 解释：和等于 8 的路径有 3 条，如图所示。
 * 示例 2：
 * <p>
 * 输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
 * 输出：3
 * <p>
 * <p>
 * 提示:
 * <p>
 * 二叉树的节点个数的范围是 [0,1000]
 * -109 <= Node.val <= 109
 * -1000 <= targetSum <= 1000
 */
public class LeetCode437 {
    int res = 0;

    public int pathSum(TreeNode root, int targetSum) {
        dfs(root, targetSum);
        if (root != null) {
            pathSum(root.left, targetSum);
            pathSum(root.right, targetSum);
        }
        return res;
    }

    private void dfs(TreeNode root, int targetSum) {
        if (root != null) {
            targetSum -= root.val;
            if (targetSum == 0) res += 1;

            dfs(root.left, targetSum);
            dfs(root.right, targetSum);
        }
    }

    public int pathSum2(TreeNode root, int targetSum) {
        Map<Long, Integer> prefix = new HashMap<>(); // k-v : 前缀和-次数
        prefix.put(0L, 1);//空路径 = 前缀和为0，次数为1
        return dfsPre(root, prefix, 0L, targetSum);

    }

    private int dfsPre(TreeNode root, Map<Long, Integer> prefix, long curSum, int targetSum) {
        if (root == null) return 0;
        int res = 0;
        curSum += root.val;
        res = prefix.getOrDefault(curSum - targetSum, 0);
        prefix.put(curSum, prefix.getOrDefault(curSum, 0) + 1);
        res += dfsPre(root.left, prefix, curSum, targetSum);
        res += dfsPre(root.right, prefix, curSum, targetSum);
        prefix.put(curSum, prefix.getOrDefault(curSum, 0) - 1);
        return res;
    }

    public static void main(String[] args) {
        TreeNode root = TreeNode.buildByArray(new int[]{1, 2, 3, 4, 5, 6});
        System.out.println(new LeetCode437().pathSum(root, 5));
        System.out.println(new LeetCode437().pathSum2(root, 5));
    }
}
