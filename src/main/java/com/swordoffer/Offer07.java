package com.swordoffer;

import com.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 输入某二叉树的前序遍历和中序遍历的结果，请重建该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 * <p>
 *  
 * <p>
 * 例如，给出
 * <p>
 * 前序遍历 preorder = [3,9,20,15,7]
 * 中序遍历 inorder = [9,3,15,20,7]
 * 返回如下的二叉树：
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 *  
 * <p>
 * 限制：
 * <p>
 * 0 <= 节点个数 <= 5000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/zhong-jian-er-cha-shu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Offer07 {
    Map<Integer, Integer> inIndex = new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        for (int i = 0; i < inorder.length; i++) {
            inIndex.put(inorder[i], i);
        }

        return buildTree(preorder, 0, preorder.length - 1, 0);
    }

    private TreeNode buildTree(int[] preorder, int presIdx, int preeIdx, int insIdx) {
        if (presIdx > preeIdx) {
            return null;
        }
        int inIdx = inIndex.get(preorder[presIdx]);
        TreeNode root = new TreeNode(preorder[presIdx]);
        int leftNodeNum = inIdx - insIdx;
        root.left = buildTree(preorder, presIdx + 1, presIdx + leftNodeNum, insIdx);
        root.right = buildTree(preorder, presIdx + leftNodeNum + 1, preeIdx, inIdx + 1);
        return root;
    }
}
