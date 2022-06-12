package com.leetcode;

import java.util.Arrays;

/**
 * 152. 乘积最大子数组
 * 给你一个整数数组 nums ，请你找出数组中乘积最大的非空连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
 * <p>
 * 测试用例的答案是一个 32-位 整数。
 * <p>
 * 子数组 是数组的连续子序列。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [2,3,-2,4]
 * 输出: 6
 * 解释: 子数组 [2,3] 有最大乘积 6。
 * 示例 2:
 * <p>
 * 输入: nums = [-2,0,-1]
 * 输出: 0
 * 解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
 * <p>
 * <p>
 * 提示:
 * <p>
 * 1 <= nums.length <= 2 * 104
 * -10 <= nums[i] <= 10
 * nums 的任何前缀或后缀的乘积都 保证 是一个 32-位 整数
 */
public class LeetCode152 {
    public static int maxProduct(int[] nums) {
        int ans = nums[0];
        int min = nums[0], max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int preMin = min, preMax = max;
            min = Math.min(nums[i], Math.min(preMin * nums[i], preMax * nums[i]));
            max = Math.max(nums[i], Math.max(preMin * nums[i], preMax * nums[i]));
            ans = Math.max(max, ans);
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {2, 3, -1, -7, -8, 9};
        System.out.println(maxProduct(nums));
    }
}
