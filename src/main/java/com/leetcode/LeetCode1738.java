package com.leetcode;

import java.util.Arrays;

/**
 * 给你一个二维矩阵 matrix 和一个整数 k ，矩阵大小为 m x n 由非负整数组成。
 * <p>
 * 矩阵中坐标 (a, b) 的 值 可由对所有满足 0 <= i <= a < m 且 0 <= j <= b < n 的元素 matrix[i][j]（下标从 0 开始计数）执行异或运算得到。
 * <p>
 * 请你找出 matrix 的所有坐标中第 k 大的值（k 的值从 1 开始计数）。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：matrix = [[5,2],[1,6]], k = 1
 * 输出：7
 * 解释：坐标 (0,1) 的值是 5 XOR 2 = 7 ，为最大的值。
 * 示例 2：
 * <p>
 * 输入：matrix = [[5,2],[1,6]], k = 2
 * 输出：5
 * 解释：坐标 (0,0) 的值是 5 = 5 ，为第 2 大的值。
 * 示例 3：
 * <p>
 * 输入：matrix = [[5,2],[1,6]], k = 3
 * 输出：4
 * 解释：坐标 (1,0) 的值是 5 XOR 1 = 4 ，为第 3 大的值。
 * 示例 4：
 * <p>
 * 输入：matrix = [[5,2],[1,6]], k = 4
 * 输出：0
 * 解释：坐标 (1,1) 的值是 5 XOR 2 XOR 1 XOR 6 = 0 ，为第 4 大的值。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-kth-largest-xor-coordinate-value
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode1738 {
    public int kthLargestValue(int[][] matrix, int k) {
        int r = matrix.length, c = matrix[0].length;
        int[] s = new int[r * c];

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                int xor = matrix[i][j];
                if (i - 1 >= 0) {
                    xor ^= s[c * (i - 1) + j];
                }
                if (j - 1 >= 0) {
                    xor ^= s[c * i + j - 1];
                }
                if (i - 1 >= 0 && j - 1 >= 0) {
                    xor ^= s[(i - 1) * c + (j - 1)];
                }
                s[c * i + j] = xor;
            }
        }
//        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
//        Arrays.stream(s).forEach(priorityQueue::add);
//        for (int i = 0; i < r * c - k; i++) {
//            priorityQueue.remove();
//        }
//        return Optional.ofNullable(priorityQueue.poll()).orElse(-1);
        System.out.println(Arrays.toString(s));
        return partition(s, k);
    }

    private int partition(int[] nums, int k) {
        return binaryPartition(nums, 0, nums.length - 1, nums.length - k);
    }

    private int binaryPartition(int[] nums, int start, int end, int k) {
        int q = randomPartition(nums, start, end);
        if (q == k) {
            return nums[q];
        } else {
            return q < k ? binaryPartition(nums, q + 1, end, k) : binaryPartition(nums, start, q - 1, k);
        }
    }

    public int randomPartition(int[] a, int l, int r) {
        int i = (l + r) / 2;
        swap(a, i, r);
        return partition(a, l, r);
    }

    public int partition(int[] a, int l, int r) {
        int x = a[r], i = l - 1;
        for (int j = l; j < r; ++j) {
            if (a[j] <= x) {
                swap(a, ++i, j);
            }
        }
        swap(a, i + 1, r);
        return i + 1;
    }

    private void swap(int[] nums, int start, int end) {
        int tmp = nums[start];
        nums[start] = nums[end];
        nums[end] = tmp;
    }

    public static void main(String[] args) {
        int[][] nums = {{10, 9, 5}, {2, 0, 4}, {1, 0, 9}, {3, 4, 8}};
        System.out.println(new LeetCode1738().kthLargestValue(nums, 2));
    }
}
