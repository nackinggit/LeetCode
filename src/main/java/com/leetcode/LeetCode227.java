package com.leetcode;

import java.util.Stack;

/**
 * Given a string s which represents an expression, evaluate this expression and return its value. 
 * <p>
 * The integer division should truncate toward zero.
 * <p>
 *  
 * <p>
 * Example 1:
 * <p>
 * Input: s = "3+2*2"
 * Output: 7
 * Example 2:
 * <p>
 * Input: s = " 3/2 "
 * Output: 1
 * Example 3:
 * <p>
 * Input: s = " 3+5 / 2 "
 * Output: 5
 *  
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 3 * 105
 * s consists of integers and operators ('+', '-', '*', '/') separated by some number of spaces.
 * s represents a valid expression.
 * All the integers in the expression are non-negative integers in the range [0, 231 - 1].
 * The answer is guaranteed to fit in a 32-bit integer
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/basic-calculator-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode227 {
    public static int calculateII(String s) {
        char[] cs = s.toCharArray();
        Stack<Character> signs = new Stack<>();
        Stack<Integer> nums = new Stack<>();
        int i = 0;
        while (i < s.length()) {
            if (cs[i] == ' ') {
                i++;
            } else if (Character.isDigit(cs[i])) {
                int number = 0;
                while (i < s.length() && Character.isDigit(cs[i])) {
                    number = number * 10 + (cs[i] - '0');
                    i++;
                }

                if (signs.empty() || (signs.peek() != '*' && signs.peek() != '/')) {
                    nums.push(number);
                } else {
                    char c = signs.pop();
                    int number2 = nums.pop();
                    nums.push(c == '*' ? (number * number2) : (number2 / number));
                }
            } else {
                signs.push(cs[i]);
                i++;
            }
        }
        int res = 0;
        while (!signs.empty()) {
            int n = nums.pop();
            int si = signs.pop() == '+' ? 1 : -1;
            res += n * si;
        }

        if (!nums.empty()) {
            res += nums.pop();
        }
        return res;
    }

    public static void main(String[] args) {
        String[] cases = {"1+2*3", "1-2/3+5-4*3"};
        for (String s : cases) {
            System.out.println(calculateII(s));
        }
    }
}
