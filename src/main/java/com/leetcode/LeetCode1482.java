package com.leetcode;

import java.util.Arrays;

public class LeetCode1482 {
    public int minDays(int[] bloomDay, int m, int k) {
        if (m * k > bloomDay.length) return -1;

        int low = Arrays.stream(bloomDay).min().orElse(Integer.MAX_VALUE);
        int high = Arrays.stream(bloomDay).max().orElse(Integer.MIN_VALUE);

        while (low < high) {
            int days = (low + high) / 2;
            if (canMake(bloomDay, days, m, k)) {
                high = days;
            } else {
                low = days + 1;
            }
        }

        return low;
    }

    private boolean canMake(int[] bloomDay, int days, int m, int k) {
        int count = 0;
        int flowers = 0;
        int len = bloomDay.length;
        for (int i = 0; i < len && count < m; i++) {
            if (bloomDay[i] <= days) {
                flowers += 1;
                if (flowers == k) {
                    count += 1;
                    flowers = 0;
                }
            } else {
                flowers = 0;
            }
        }
        return count >= m;
    }

    public static void main(String[] args) {
        System.out.println(new LeetCode1482().minDays(new int[]{1, 10, 3, 10, 2}, 3, 1));
    }
}
