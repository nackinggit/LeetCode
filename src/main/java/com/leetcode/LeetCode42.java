package com.leetcode;

import java.util.Stack;

/**
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出：6
 * 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
 * 示例 2：
 * <p>
 * 输入：height = [4,2,0,3,2,5]
 * 输出：9
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/trapping-rain-water
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode42 {
    //单调栈
    public int trap(int[] height) {
        if (height == null || height.length <= 2) return 0;
        int res = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < height.length; i++) {

            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                int top = stack.pop();
                if (stack.isEmpty()) {
                    break;
                }
                int left = stack.peek();
                int curWidth = i - left - 1;
                int currHeight = Math.min(height[left], height[i]) - height[top];
                res += currHeight * curWidth;
                System.out.format("left = %s, right = %s, res = %s\n", left, i, res);
            }
            stack.push(i);
            System.out.println(stack);
        }
        return res;
    }

    public int trap2(int[] height) {
        if (height.length == 0) {
            return 0;
        }
        int res = 0;
        int maxIndex = 0;
        for (int i = 0; i < height.length; i++) {
            if (height[maxIndex] < height[i]) {
                maxIndex = i;
            }
        }

        int index = 0, h = height[index];
        while (++index < maxIndex) {
            if (height[index] > h) {
                h = height[index];
                continue;
            }
            res += h - height[index];
        }

        index = height.length - 1;
        h = height[index];

        while (--index > maxIndex) {
            if (height[index] > h) {
                h = height[index];
                continue;
            }

            res += h - height[index];
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(new LeetCode42().trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
        System.out.println(new LeetCode42().trap2(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
    }
}
