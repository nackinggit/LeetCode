package com.leetcode;

public class LeetCode664 {
    public int strangePrinter(String s) {
        int len = s.length();
        int[][] f = new int[len][len];
        for (int i = len - 1; i >= 0; i--) {
            f[i][i] = 1;
            for (int j = i + 1; j < len; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    f[i][j] = f[i][j - 1];
                } else {
                    int mmin = Integer.MAX_VALUE;
                    for (int k = i; k < j; k++) {
                        mmin = Math.min(mmin, f[i][k] + f[k + 1][j]);
                    }
                    f[i][j] = mmin;
                }
            }
        }
        return f[0][len - 1];
    }

    public static void main(String[] args) {
        System.out.println(new LeetCode664().strangePrinter("avab"));
    }
}
