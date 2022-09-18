package com.leetcode;

/**
 * 4. 寻找两个正序数组的中位数
 * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
 * <p>
 * 算法的时间复杂度应该为 O(log (m+n)) 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums1 = [1,3], nums2 = [2]
 * 输出：2.00000
 * 解释：合并数组 = [1,2,3] ，中位数 2
 * 示例 2：
 * <p>
 * 输入：nums1 = [1,2], nums2 = [3,4]
 * 输出：2.50000
 * 解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
 * <p>
 * <p>
 * <p>
 * <p>
 * 提示：
 * <p>
 * nums1.length == m
 * nums2.length == n
 * 0 <= m <= 1000
 * 0 <= n <= 1000
 * 1 <= m + n <= 2000
 * -106 <= nums1[i], nums2[i] <= 106
 */
public class LeetCode4 {
    public static double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays2(nums2, nums1);
        }

        int len1 = nums1.length, len2 = nums2.length;
        int left = 0, right = len1;
        // median1：前一部分的最大值
        // median2：后一部分的最小值
        int median1 = 0, median2 = 0;

        while (left <= right) {
            // 前一部分包含 nums1[0 .. i-1] 和 nums2[0 .. j-1]
            // 后一部分包含 nums1[i .. m-1] 和 nums2[j .. n-1]
            // i + j = (len1 + len2 + 1) / 2
            int i = (left + right) / 2;
            int j = (len1 + len2 + 1) / 2 - i;

            int nums_im1 = (i == 0 ? Integer.MIN_VALUE : nums1[i - 1]);
            int nums_i = (i == len1 ? Integer.MAX_VALUE : nums1[i]);
            int nums_jm1 = (j == 0 ? Integer.MIN_VALUE : nums2[j - 1]);
            int nums_j = (j == len2 ? Integer.MAX_VALUE : nums2[j]);

            if (nums_im1 <= nums_j) {
                median1 = Math.max(nums_im1, nums_jm1);
                median2 = Math.min(nums_i, nums_j);
                left = i + 1;
            } else {
                right = i - 1;
            }
        }

        return (len1 + len2) % 2 == 0 ? (median1 + median2) / 2.0 : median1;
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len1 = nums1.length, len2 = nums2.length;
        int k = (len1 + len2 + 1) / 2;
        if ((len1 + len2) % 2 == 1) {
            return findKth(nums1, nums2, k);
        } else {
            return (findKth(nums1, nums2, k) + findKth(nums1, nums2, k + 1)) / 2.0;
        }
    }

    public static double findKth(int[] nums1, int[] nums2, int k) {
        int len1 = nums1.length, len2 = nums2.length;
        int idx1 = 0, idx2 = 0;
        while (true) {
            if (idx1 >= len1) {
                return nums2[idx2 + k - 1];
            }

            if (idx2 >= len2) {
                return nums1[idx1 + k - 1];
            }

            if (k == 1) {
                return Math.min(nums1[idx1], nums2[idx2]);
            }

            int half = k / 2;
            int newIdx1 = idx1 + half - 1, newIdx2 = idx2 + half - 1;
            if (newIdx1 >= len1) newIdx1 = len1 - 1;
            if (newIdx2 >= len2) newIdx2 = len2 - 1;
            int pivot1 = nums1[newIdx1], pivot2 = nums2[newIdx2];
            /**
             * 两个关键点中较小的一个，更新k的值
             */
            if (pivot1 <= pivot2) {
                k = k - (newIdx1 - idx1 + 1);
                idx1 = newIdx1 + 1;
            } else {
                k = k - (newIdx2 - idx2 + 1);
                idx2 = newIdx2 + 1;
            }
        }
    }
}
