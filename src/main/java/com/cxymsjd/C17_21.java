package com.cxymsjd;

import org.apache.tomcat.util.collections.SynchronizedQueue;
import org.springframework.boot.SpringApplication;

import java.util.*;

/**
 * 给定一个直方图(也称柱状图)，假设有人从上面源源不断地倒水，最后直方图能存多少水量?直方图的宽度为 1。
 * <p>
 * <p>
 * <p>
 * 上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的直方图，在这种情况下，可以接 6 个单位的水（蓝色部分表示水）。感谢 Marcos 贡献此图。
 * <p>
 * 示例:
 * <p>
 * 输入: [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出: 6
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/volume-of-histogram-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class C17_21 {
    public int trap(int[] height) {
        SpringApplication.run(C17_21.class);
        Map map = Collections.synchronizedMap(new HashMap<>());
        int ans = 0;
        Deque<Integer> stack = new LinkedList<>();
        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[stack.peek()] < height[i]) {
                int top = stack.pop();
                if (stack.isEmpty()) {
                    break;
                }
                int left = stack.peek();
                int w = i - left - 1;
                int h = Math.min(height[left], height[i]) - height[top];
                ans += w * h;
            }

            stack.push(i);
        }

        return ans;
    }

    static int max = 0;

    public static int nearestNum(int[] nums, int target) {
        if (nums == null || nums.length == 0 || target == 0) return 0;
        Arrays.sort(nums);
        dfs(nums, target, 0);
        return max;
    }

    private static void dfs(int[] nums, int target, int cur) {
        if (cur > target) return;
        max = Math.max(max, cur);
        for (int i = nums.length - 1; i >= 0; i--) {
            int n = cur * 10 + nums[i];
            if ((n + "").compareTo(target + "") > 0) continue;
            dfs(nums, target, n);
        }
    }

    public static void main(String[] args) {
        int[] nums = {2, 4, 5, 9};
        System.out.println(nearestNum(nums, 32999));
    }
}
