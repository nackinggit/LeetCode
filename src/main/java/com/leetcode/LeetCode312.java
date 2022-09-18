package com.leetcode;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 312. 戳气球
 * 有 n 个气球，编号为0 到 n - 1，每个气球上都标有一个数字，这些数字存在数组 nums 中。
 * <p>
 * 现在要求你戳破所有的气球。戳破第 i 个气球，你可以获得 nums[i - 1] * nums[i] * nums[i + 1] 枚硬币。 这里的 i - 1 和 i + 1 代表和 i 相邻的两个气球的序号。如果 i - 1或 i + 1 超出了数组的边界，那么就当它是一个数字为 1 的气球。
 * <p>
 * 求所能获得硬币的最大数量。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * 输入：nums = [3,1,5,8]
 * 输出：167
 * 解释：
 * nums = [3,1,5,8] --> [3,5,8] --> [3,8] --> [8] --> []
 * coins =  3*1*5    +   3*5*8   +  1*3*8  + 1*8*1 = 167
 * 示例 2：
 * <p>
 * 输入：nums = [1,5]
 * 输出：10
 */
public class LeetCode312 {


    public static int maxCoins(int[] nums) {
        List<Integer> list = Arrays.stream(nums).boxed().collect(Collectors.toList());
        int[] max = new int[1];
        backtrack(list, 0, max);
        return max[0];
    }

    private static void backtrack(List<Integer> list, int tmp, int[] max) {
        if (list.isEmpty()) {
            max[0] = Math.max(tmp, max[0]);
        }
        for (int i = 0; i < list.size(); i++) {
            int val = list.get(i);
            int pre = i > 0 ? list.get(i - 1) : 1;
            int nxt = i < list.size() - 1 ? list.get(i + 1) : 1;
            list.remove(i);
            backtrack(list, tmp + val * pre * nxt, max);
            list.add(i, val);
        }
    }

    public static int maxCoins2(int[] nums) {
        int length = nums.length;
        int[] arr = new int[nums.length + 2];
        arr[0] = arr[nums.length + 1] = 1;
        System.arraycopy(nums, 0, arr, 1, nums.length);

        //dp[i][j] = max{dp[i][k] + dp[k][j] + arr[k]*arr[i]*arr[j]} i < k < j
        int[][] dp = new int[arr.length + 2][arr.length + 2]; //dp[i][j]->（i,j）之间的最大硬币
        for (int i = length + 1; i >= 0; i--) {
            for (int j = i + 2; j <= length + 1; j++) {
                for (int k = i + 1; k < j; k++) {
                    int val = arr[i] * arr[k] * arr[j];
                    val += dp[i][k] + dp[k][j];
                    dp[i][j] = Math.max(dp[i][j], val);
                }
            }
        }
        return dp[0][length + 1];
    }

    public static void main(String[] args) {
        int[] nums = {3, 1, 5, 8};
        System.out.println(maxCoins(nums));
        System.out.println(maxCoins2(nums));
    }
}
