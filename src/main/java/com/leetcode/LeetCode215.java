package com.leetcode;

import java.util.Arrays;

/**
 * 215. 数组中的第K个最大元素
 * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
 * <p>
 * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: [3,2,1,5,6,4] 和 k = 2
 * 输出: 5
 * 示例 2:
 * <p>
 * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 * 输出: 4
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= k <= nums.length <= 104
 * -104 <= nums[i] <= 104
 */
public class LeetCode215 {
    public static int findKthLargest(int[] nums, int k) {
        return quickSort(nums, 0, nums.length - 1, k);
    }

    public static int findKthLargest2(int[] nums, int k) {
        for (int i = 0; i < k; i++) {
            buildMaxHeap(nums, nums.length - i);
            int tmp = nums[nums.length - i - 1];
            nums[nums.length - i - 1] = nums[0];
            nums[0] = tmp;
        }
        return nums[nums.length - k];
    }

    private static void buildMaxHeap(int[] nums, int size) {
        for (int i = size / 2; i >= 0; i--) {
            adjustMaxHeap(nums, i, size);
        }
    }

    private static void adjustMaxHeap(int[] nums, int i, int size) {
        int left = 2 * i + 1, right = 2 * i + 2, maxIdx = i;
        if (left < size && nums[maxIdx] < nums[left]) {
            maxIdx = left;
        }
        if (right < size && nums[maxIdx] < nums[right]) {
            maxIdx = right;
        }

        if (i != maxIdx) {
            int tmp = nums[maxIdx];
            nums[maxIdx] = nums[i];
            nums[i] = tmp;
            adjustMaxHeap(nums, maxIdx, size);
        }
    }

    private static int quickSort(int[] nums, int start, int end, int k) {
        int m = nums[start];
        int s = start, e = end;
        while (s < e) {
            while (s < e && nums[e] <= m) e--;
            nums[s] = nums[e];
            while (s < e && nums[s] >= m) s++;
            nums[e] = nums[s];
        }
        nums[s] = m;
        if (s == k - 1) return nums[s];
        else if (s < k - 1) return quickSort(nums, s + 1, end, k);
        else return quickSort(nums, start, s - 1, k);
    }

    public static void main(String[] args) {
        int[] nums = {3, 2, 1, 5, 6, 4};
//        System.out.println(findKthLargest(nums, 2));
        System.out.println(findKthLargest2(nums, 2));
    }
}
