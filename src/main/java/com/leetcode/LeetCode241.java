package com.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 241. 为运算表达式设计优先级
 * 给你一个由数字和运算符组成的字符串 expression ，按不同优先级组合数字和运算符，计算并返回所有可能组合的结果。你可以 按任意顺序 返回答案。
 * <p>
 * 生成的测试用例满足其对应输出值符合 32 位整数范围，不同结果的数量不超过 104 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：expression = "2-1-1"
 * 输出：[0,2]
 * 解释：
 * ((2-1)-1) = 0
 * (2-(1-1)) = 2
 * 示例 2：
 * <p>
 * 输入：expression = "2*3-4*5"
 * 输出：[-34,-14,-10,-10,10]
 * 解释：
 * (2*(3-(4*5))) = -34
 * ((2*3)-(4*5)) = -14
 * ((2*(3-4))*5) = -10
 * (2*((3-4)*5)) = -10
 * (((2*3)-4)*5) = 10
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= expression.length <= 20
 * expression 由数字和算符 '+'、'-' 和 '*' 组成。
 * 输入表达式中的所有整数值在范围 [0, 99]
 */
public class LeetCode241 {
    public static List<Integer> diffWaysToCompute(String expression) {
        if (expression == null || "".endsWith(expression)) return new ArrayList<>();
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (!Character.isDigit(c)) {
                List<Integer> left = diffWaysToCompute(expression.substring(0, i));
                List<Integer> right = diffWaysToCompute(expression.substring(i + 1));
                for (int l : left) {
                    for (int r : right) {
                        if (c == '+') {
                            ans.add(r + l);
                        } else if (c == '*') {
                            ans.add(l * r);
                        } else {
                            ans.add(l - r);
                        }
                    }
                }
            }
        }
        if (ans.isEmpty()) {
            ans.add(Integer.parseInt(expression));
        }
        return ans;
    }

    public static void main(String[] args) {
        String str = "2+3+4+5";
        System.out.println(diffWaysToCompute(str));
    }
}
