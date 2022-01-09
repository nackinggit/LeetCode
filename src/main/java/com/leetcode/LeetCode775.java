package com.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeetCode775 {

    public int[][] insert(int[][] intervals, int[] newInterval) {
        int left = newInterval[0];
        int right = newInterval[1];
        boolean placed = false;

        List<int[]> res = new ArrayList<>();
        for (int[] one : intervals) {
            if (one[1] < left) {
                res.add(one);
            } else if (one[0] > right) {
                if (!placed) {
                    placed = true;
                    res.add(new int[]{left, right});
                }
                res.add(one);
            } else {
                left = Math.min(left, one[0]);
                right = Math.max(right, one[1]);
            }
        }

        if (!placed) {
            res.add(new int[]{left, right});
        }
        int[][] ans = new int[res.size()][2];

        for (int i = 0; i < res.size(); i++) {
            ans[i] = res.get(i);
        }

        return ans;
    }

    public static void main(String[] args) {
        int[][] intervals = {{1, 3}, {5, 7}, {11, 15}};
        int[] newInterval = {4, 20};
        System.out.println(Arrays.deepToString(new LeetCode775().insert(intervals, newInterval)));
    }

}
