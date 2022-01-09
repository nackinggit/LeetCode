package com.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeetCode377 {
    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 0; i <= target; i++) {
            for (int num : nums) {
                if (num <= i) {
                    dp[i] += dp[i - num];
                }
            }
        }
        return dp[target];
    }

    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> combinationSum(int[] nums, int target) {
        if (target == 0) return res;
        Arrays.sort(nums);
        combinationSum(nums, 0, target, new ArrayList<>());
        return res;
    }

    private void combinationSum(int[] nums, int i, int target, ArrayList<Integer> one) {
        if (target == 0) res.add(new ArrayList<>(one));
        System.out.println(one + ", " + target);
        if (i < nums.length && nums[i] <= target) {
            one.add(nums[i]);
            combinationSum(nums, i, target - nums[i], one);
            one.remove(one.size() - 1);
            combinationSum(nums, i + 1, target, one);
        }
    }

    public static void main(String[] args) {
        System.out.println(new LeetCode377().combinationSum4(new int[]{1, 2, 3}, 4));
        System.out.println(new LeetCode377().combinationSum(new int[]{1, 2, 3}, 4));
    }
}
