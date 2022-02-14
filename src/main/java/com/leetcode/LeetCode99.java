package com.leetcode;

import com.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 99. 恢复二叉搜索树
 * 给你二叉搜索树的根节点 root ，该树中的 恰好 两个节点的值被错误地交换。请在不改变其结构的情况下，恢复这棵树 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：root = [1,3,null,null,2]
 * 输出：[3,1,null,null,2]
 * 解释：3 不能是 1 的左孩子，因为 3 > 1 。交换 1 和 3 使二叉搜索树有效。
 * 示例 2：
 * <p>
 * <p>
 * 输入：root = [3,1,4,null,null,2]
 * 输出：[2,1,4,null,null,3]
 * 解释：2 不能在 3 的右子树中，因为 2 < 3 。交换 2 和 3 使二叉搜索树有效。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 树上节点的数目在范围 [2, 1000] 内
 * -231 <= Node.val <= 231 - 1
 * <p>
 * <p>
 * 进阶：使用 O(n) 空间复杂度的解法很容易实现。你能想出一个只使用 O(1) 空间的解决方案吗？
 * <p>
 * 通过次数86,103提交次数141,523
 */
public class LeetCode99 {
    public void recoverTree(TreeNode root) {
        Deque<TreeNode> deque = new ArrayDeque<>();

        TreeNode one = null, two = null;
        TreeNode pre = null;
        while (!deque.isEmpty() || root != null) {
            while (root != null) {
                deque.push(root);
                root = root.left;
            }

            root = deque.pop();

            if (pre != null && pre.val >= root.val) {
                two = root;
                if (one == null) {
                    one = pre;
                } else {
                    break;
                }
            }
            pre = root;
            root = root.right;
        }

        if (one != null) {
            int tmp = one.val;
            one.val = two.val;
            two.val = tmp;
        }
    }

}
