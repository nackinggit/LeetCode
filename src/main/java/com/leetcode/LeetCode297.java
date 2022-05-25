package com.leetcode;

import com.TreeNode;

import java.util.Arrays;
import java.util.List;

/**
 * 297. 二叉树的序列化与反序列化
 * 序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。
 * <p>
 * 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
 * <p>
 * 提示: 输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：root = [1,2,3,null,null,4,5]
 * 输出：[1,2,3,null,null,4,5]
 * 示例 2：
 * <p>
 * 输入：root = []
 * 输出：[]
 * 示例 3：
 * <p>
 * 输入：root = [1]
 * 输出：[1]
 * 示例 4：
 * <p>
 * 输入：root = [1,2]
 * 输出：[1,2]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 树中结点数在范围 [0, 104] 内
 * -1000 <= Node.val <= 1000
 */
public class LeetCode297 {
    public String serialize(TreeNode root) {
        return doSerialize(root, "");
    }

    private String doSerialize(TreeNode root, String s) {
        if(root == null) {
            s += "NIL,";
        } else {
            s += root.val + ",";
            s = doSerialize(root.left, s);
            s = doSerialize(root.right, s);
        }
        return s;
    }

    public TreeNode deserialize(String data) {
        List<String> dataList = Arrays.asList(data.split(","));
        return doDeserialize(dataList);
    }

    private TreeNode doDeserialize(List<String> dataList) {
        if(dataList.get(0).equals("NIL")) {
            dataList.remove(0);
            return null;
        }

        TreeNode node = new TreeNode(Integer.parseInt(dataList.get(0)));
        dataList.remove(0);
        node.left = doDeserialize(dataList);
        node.right = doDeserialize(dataList);
        return node;
    }
}
