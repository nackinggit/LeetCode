package com.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 22. 括号生成
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 3
 * 输出：["((()))","(()())","(())()","()(())","()()()"]
 * 示例 2：
 * <p>
 * 输入：n = 1
 * 输出：["()"]
 */
public class LeetCode22 {
    public static List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        backtrack(n, res, 0, 0, new StringBuilder());
        return res;
    }

    private static void backtrack(int n, List<String> res, int open, int close, StringBuilder sb) {
        if (sb.length() == 2 * n) {
            res.add(sb.toString());
            return;
        }

        if (open < n) {
            sb.append("(");
            backtrack(n, res, open + 1, close, sb);
            sb.deleteCharAt(sb.length() - 1);
        }

        if (close < open) {
            sb.append(")");
            backtrack(n, res, open, close + 1, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(generateParenthesis(3));
    }
}
