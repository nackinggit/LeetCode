package com.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 84. 柱状图中最大的矩形
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 * <p>
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * <p>
 * <p>
 * 输入：heights = [2,1,5,6,2,3]
 * 输出：10
 * 解释：最大的矩形为图中红色区域，面积为 10
 * 示例 2：
 * <p>
 * <p>
 * <p>
 * 输入： heights = [2,4]
 * 输出： 4
 */
public class LeetCode84 {
    public static int maxArea(int[] heights) {
        if (heights == null || heights.length == 0) return 0;
        int ans = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        for (int right = 0; right < heights.length; right++) {
            while (!stack.isEmpty() && heights[stack.peek()] > heights[right]) {
                int cur = stack.pop();
                int left = stack.isEmpty() ? -1 : stack.peek();
                ans = Math.max(ans, (right - left - 1) * heights[cur]);
            }
            stack.push(right);
        }

        while (!stack.isEmpty()) {
            int cur = stack.pop();
            int left = stack.isEmpty() ? -1 : stack.peek();
            ans = Math.max(ans, (heights.length - left - 1) * heights[cur]);
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] heights = {2, 1, 5, 6, 2, 3};
        System.out.println(maxArea(heights));
    }
}
