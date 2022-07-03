package com.leetcode;

/**
 * 522. 最长特殊序列 II
 * 给定字符串列表 strs ，返回其中 最长的特殊序列 。如果最长特殊序列不存在，返回 -1 。
 * <p>
 * 特殊序列 定义如下：该序列为某字符串 独有的子序列（即不能是其他字符串的子序列）。
 * <p>
 * s 的 子序列可以通过删去字符串 s 中的某些字符实现。
 * <p>
 * 例如，"abc" 是 "aebdc" 的子序列，因为您可以删除"aebdc"中的下划线字符来得到 "abc" 。"aebdc"的子序列还包括"aebdc"、 "aeb" 和 "" (空字符串)。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入: strs = ["aba","cdc","eae"]
 * 输出: 3
 * 示例 2:
 * <p>
 * 输入: strs = ["aaa","aaa","aa"]
 * 输出: -1
 * <p>
 * <p>
 * 提示:
 * <p>
 * 2 <= strs.length <= 50
 * 1 <= strs[i].length <= 10
 * strs[i] 只包含小写英文字母
 */
public class LeetCode522 {
    public static int findLUSlength(String[] strs) {
        int n = strs.length;
        int ans = -1;
        for (int i = 0; i < n; i++) {
            boolean ok = true;
            for (int j = 0; j < n; j++) {
                if (i != j && isSub(strs[i], strs[j])) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                ans = Math.max(strs[i].length(), ans);
            }
        }

        return ans;
    }

    public static boolean isSub(String s, String t) {
        int ps = 0, pt = 0;
        while (ps < s.length() && pt < t.length()) {
            if (s.charAt(ps) == t.charAt(pt)) {
                ps += 1;
            }
            pt += 1;
        }

        return ps == s.length();
    }

    public static void main(String[] args) {
        String[] strings = {"aba", "cdc", "eae"};
        System.out.println(findLUSlength(strings));
    }
}
