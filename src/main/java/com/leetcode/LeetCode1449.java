package com.leetcode;

import java.util.Arrays;

/**
 * Given an array of integers cost and an integer target. Return the maximum integer you can paint under the following rules:
 * <p>
 * The cost of painting a digit (i+1) is given by cost[i] (0 indexed).
 * The total cost used must be equal to target.
 * Integer does not have digits 0.
 * Since the answer may be too large, return it as string.
 * <p>
 * If there is no way to paint any integer given the condition, return "0".
 * <p>
 *
 * <p>
 * Example 1:
 * <p>
 * Input: cost = [4,3,2,5,6,7,2,5,5], target = 9
 * Output: "7772"
 * Explanation:  The cost to paint the digit '7' is 2, and the digit '2' is 3. Then cost("7772") = 2*3+ 3*1 = 9. You could also paint "977", but "7772" is the largest number.
 * Digit    cost
 * 1  ->   4
 * 2  ->   3
 * 3  ->   2
 * 4  ->   5
 * 5  ->   6
 * 6  ->   7
 * 7  ->   2
 * 8  ->   5
 * 9  ->   5
 * Example 2:
 * <p>
 * Input: cost = [7,6,5,5,5,6,8,7,8], target = 12
 * Output: "85"
 * Explanation: The cost to paint the digit '8' is 7, and the digit '5' is 5. Then cost("85") = 7 + 5 = 12.
 * Example 3:
 * <p>
 * Input: cost = [2,4,6,2,4,6,4,4,4], target = 5
 * Output: "0"
 * Explanation: It's not possible to paint any integer with total cost equal to target.
 * Example 4:
 * <p>
 * Input: cost = [6,10,15,40,40,40,40,40,40], target = 47
 * Output: "32211"
 *
 * <p>
 * Constraints:
 * <p>
 * cost.length == 9
 * 1 <= cost[i] <= 5000
 * 1 <= target <= 5000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/form-largest-integer-with-digits-that-add-up-to-target
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode1449 {
    public String largestNumber(int[] cost, int target) {
        int[][] dp = new int[10][target + 1];//dp[i+1][j] 表示前i个数总成本恰好为j的最大位数
        for (int i = 0; i < 10; i++) {
            Arrays.fill(dp[i], Integer.MIN_VALUE);
        }

        int[][] form = new int[10][target + 1]; //保存中间状态
        dp[0][0] = 0;
        for (int i = 0; i < 10; i++) {
            int c = cost[i];
            for (int j = 0; j <= target; j++) {
                if (j < c) {
                    dp[i + 1][j] = dp[i][j];
                    form[i + 1][j] = j;
                } else {
                    if (dp[i][j] > dp[i + 1][j - c] + 1) {
                        dp[i + 1][j] = dp[i][j];
                        form[i + 1][j] = j;
                    } else {
                        dp[i + 1][j] = dp[i + 1][j - c] + 1;
                        form[i + 1][j] = j - c;
                    }
                }
            }
        }

        if (dp[9][target] < 0) {
            return "0";
        }

        StringBuilder stringBuilder = new StringBuilder();

        int i = 9, j = target;
        while (i > 0) {
            if (j == form[i][j]) {
                --i;
            } else {
                stringBuilder.append(i);
                j = form[i][j];
            }
        }

        return stringBuilder.toString();
    }
}
