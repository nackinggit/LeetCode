package com.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 873. 最长的斐波那契子序列的长度
 * 如果序列 X_1, X_2, ..., X_n 满足下列条件，就说它是 斐波那契式 的：
 * <p>
 * n >= 3
 * 对于所有 i + 2 <= n，都有 X_i + X_{i+1} = X_{i+2}
 * 给定一个严格递增的正整数数组形成序列 arr ，找到 arr 中最长的斐波那契式的子序列的长度。如果一个不存在，返回  0 。
 * <p>
 * （回想一下，子序列是从原序列 arr 中派生出来的，它从 arr 中删掉任意数量的元素（也可以不删），而不改变其余元素的顺序。例如， [3, 5, 8] 是 [3, 4, 5, 6, 7, 8] 的一个子序列）
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入: arr = [1,2,3,4,5,6,7,8]
 * 输出: 5
 * 解释: 最长的斐波那契式子序列为 [1,2,3,5,8] 。
 * 示例 2：
 * <p>
 * 输入: arr = [1,3,7,11,12,14,18]
 * 输出: 3
 * 解释: 最长的斐波那契式子序列有 [1,11,12]、[3,11,14] 以及 [7,11,18] 。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 3 <= arr.length <= 1000
 * 1 <= arr[i] < arr[i + 1] <= 10^9
 */
public class LeetCode873 {
    public static int lenLongestFibSubseq(int[] arr) {
        if (arr == null || arr.length < 3) return 0;

        int ans = 0;
        int length = arr.length;
        //dp[i][j] -> 后两个为arr[i]、arr[j]时的最大fib长度
        //dp[i][j] = max{dp[k][i] + 1, 3} k < i, i < j
        int[][] dp = new int[length][length];
        Map<Integer, Integer> idx = new HashMap<>();
        for (int i = 0; i < length; i++) {
            idx.put(arr[i], i);
        }

        for (int i = 2; i < length; i++) {
            for (int j = i - 1; j >= 0 && arr[j] * 2 > arr[i]; j--) {
                int k = idx.getOrDefault(arr[i] - arr[j], -1);
                if (k != -1) {
                    dp[j][i] = Math.max(dp[k][j] + 1, 3);
                }
                ans = Math.max(ans, dp[j][i]);
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7,8};
        System.out.println(lenLongestFibSubseq(arr));
    }
}
