package com.leetcode;

/**
 * 540. 有序数组中的单一元素
 * 给你一个仅由整数组成的有序数组，其中每个元素都会出现两次，唯有一个数只会出现一次。
 * <p>
 * 请你找出并返回只出现一次的那个数。
 * <p>
 * 你设计的解决方案必须满足 O(log n) 时间复杂度和 O(1) 空间复杂度。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [1,1,2,3,3,4,4,8,8]
 * 输出: 2
 * 示例 2:
 * <p>
 * 输入: nums =  [3,3,7,7,10,11,11]
 * 输出: 10
 * <p>
 * <p>
 * 提示:
 * <p>
 * 1 <= nums.length <= 105
 * 0 <= nums[i] <= 105
 * 通过次数77,496提交次数127,384
 */
public class LeetCode540 {
    public int singleNonDuplicate(int[] nums) {
        int low = 0, high = nums.length - 1;

        while (low < high) {
            int mid = (low + high) / 2;
            mid -= mid & 1;

            if (nums[mid] == nums[mid + 1]) {
                low = mid + 2;
            } else {
                high = mid;
            }
        }

        return nums[low];
    }
}
