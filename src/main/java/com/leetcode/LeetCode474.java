package com.leetcode;

public class LeetCode474 {
    public int findMaxForm(String[] strs, int m, int n) {
        int[][] dp = new int[m + 1][n + 1];
        for (String str : strs) {
            int[] zs = count0_1(str);
            int z = zs[0], o = zs[1];
            for (int j = m; j >= z; j--) {
                for (int k = n; k >= o; k--) {
                    dp[j][k] = Math.max(dp[j][k], dp[j - z][k - o] + 1);
                }
            }
        }
        return dp[m][n];
    }

    private int[] count0_1(String str) {
        int[] zerosOnes = new int[2];
        int length = str.length();
        for (int i = 0; i < length; i++) {
            zerosOnes[str.charAt(i) - '0']++;
        }
        return zerosOnes;
    }
}
