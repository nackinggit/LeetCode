package com.leetcode;

public class LeetCode26 {
    public int removeDuplicates(int[] nums) {
        if (nums.length <= 1) return nums.length;
        int idx = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[idx] != nums[i]) {
                idx += 1;
                nums[idx] = nums[i];
            }
        }

        return idx + 1;
    }
}
