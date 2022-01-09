package com.leetcode;

import java.util.Stack;

/**
 * 678. 有效的括号字符串
 * 给定一个只包含三种字符的字符串：（ ，） 和 *，写一个函数来检验这个字符串是否为有效字符串。有效字符串具有如下规则：
 * <p>
 * 任何左括号 ( 必须有相应的右括号 )。
 * 任何右括号 ) 必须有相应的左括号 ( 。
 * 左括号 ( 必须在对应的右括号之前 )。
 * * 可以被视为单个右括号 ) ，或单个左括号 ( ，或一个空字符串。
 * 一个空字符串也被视为有效字符串。
 * 示例 1:
 * <p>
 * 输入: "()"
 * 输出: True
 * 示例 2:
 * <p>
 * 输入: "(*)"
 * 输出: True
 * 示例 3:
 * <p>
 * 输入: "(*))"
 * 输出: True
 */
public class LeetCode678 {
    public boolean checkValidString(String s) {
        Stack<Integer> leftStack = new Stack<>();
        Stack<Integer> wildStack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                leftStack.push(i);
            } else if (c == '*') {
                wildStack.push(i);
            } else {
                if (!leftStack.empty()) {
                    leftStack.pop();
                } else if (!wildStack.empty()) {
                    wildStack.pop();
                } else {
                    return false;
                }
            }
        }

        while (!leftStack.empty()) {
            if (wildStack.empty()) {
                return false;
            }
            int l = leftStack.pop();
            int w = wildStack.pop();
            if (l > w) {
                return false;
            }
        }

        return leftStack.empty();
    }

    public static void main(String[] args) {
        System.out.println(new LeetCode678().checkValidString("(*))"));
    }
}
