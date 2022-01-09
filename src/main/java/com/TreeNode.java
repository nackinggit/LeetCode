package com;

import java.util.LinkedList;

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int x) {
        val = x;
    }

    public static TreeNode buildByArray(int[] vals) {
        TreeNode root = new TreeNode(vals[0]);
        TreeNode lastParent = root;
        for (int i = 1; i < vals.length; i++) {
            int val = vals[i];
            TreeNode node = new TreeNode(val);
            if (lastParent.left == null) {
                lastParent.left = node;
            } else if (lastParent.right == null) {
                lastParent.right = node;
            }

            lastParent = nextLeaf(root);
        }
        return root;
    }

    private static TreeNode nextLeaf(TreeNode root) {
        LinkedList<TreeNode> list = new LinkedList<>();
        list.add(root);
        while (!list.isEmpty()) {
            TreeNode node = list.pop();
            if (!node.isFull()) return node;
            if (node.left != null) {
                list.add(node.left);
            }

            if (node.right != null) {
                list.add(node.right);
            }
        }
        return root;
    }

    private boolean isFull() {
        return this.left != null && this.right != null;
    }

    public static void main(String[] args) {
        int[] vs = {0, 1, 2, 3, 4, 5, 6, 7};
        TreeNode treeNode = buildByArray(vs);
        System.out.println(treeNode);
    }
}
