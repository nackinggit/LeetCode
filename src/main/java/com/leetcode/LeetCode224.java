package com.leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Given a string s representing an expression, implement a basic calculator to evaluate it.
 * <p>
 *  
 * <p>
 * Example 1:
 * <p>
 * Input: s = "1 + 1"
 * Output: 2
 * Example 2:
 * <p>
 * Input: s = " 2-1 + 2 "
 * Output: 3
 * Example 3:
 * <p>
 * Input: s = "(1+(4+5+2)-3)+(6+8)"
 * Output: 23
 *  
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 3 * 105
 * s consists of digits, '+', '-', '(', ')', and ' '.
 * s represents a valid expression.
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/basic-calculator
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode224 {
    public static int calculate(String s) {
        Deque<Integer> ops = new LinkedList<>();
        ops.push(1);
        int sign = 1;

        int res = 0;
        int len = s.length();
        int i = 0;
        char[] cs = s.toCharArray();
        while (i < len) {
            if (cs[i] == ' ') {
                i++;
            } else if (cs[i] == '+') {
                sign = ops.peek();
                i++;
            } else if (cs[i] == '-') {
                sign = -ops.peek();
                i++;
            } else if (cs[i] == '(') {
                ops.push(sign);
                i++;
            } else if (cs[i] == ')') {
                ops.pop();
                i++;
            } else if (Character.isDigit(cs[i])) {
                int number = 0;
                while (i < len && Character.isDigit(cs[i])) {
                    number = number * 10 + (cs[i] - '0');
                    i++;
                }

                res += sign * number;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String[] cases = {"2-1 + 2", "(1-(-4+5+2)-3)+(6+8)", "-1+2"};
        for (String s : cases) {
            System.out.println(calculate(s));
        }
    }
}
