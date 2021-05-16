package com.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * 给你一个整数数组 nums ，返回 nums[i] XOR nums[j] 的最大运算结果，其中 0 ≤ i ≤ j < n 。
 * <p>
 * 进阶：你可以在 O(n) 的时间解决这个问题吗？
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [3,10,5,25,2,8]
 * 输出：28
 * 解释：最大运算结果是 5 XOR 25 = 28.
 * 示例 2：
 * <p>
 * 输入：nums = [0]
 * 输出：0
 * 示例 3：
 * <p>
 * 输入：nums = [2,4]
 * 输出：6
 * 示例 4：
 * <p>
 * 输入：nums = [8,10,2]
 * 输出：10
 * 示例 5：
 * <p>
 * 输入：nums = [14,70,53,83,49,91,36,80,92,51,66,70]
 * 输出：127
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-xor-of-two-numbers-in-an-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode421 {
    public int findMaximumXOR(int[] nums) {
        int BITS = 30;

        int res = 0;
        for (int i = BITS; i >= 0; i--) {
            Set<Integer> set = new HashSet<>();
            for (int n : nums) {
                set.add(n >> i);
            }

            int nextRes = res * 2 + 1;
            boolean found = false;
            for (int n : nums) {
                if (set.contains(nextRes ^ (n >> i))) {
                    found = true;
                    break;
                }
            }

            if (found) {
                res = nextRes;
            } else {
                res = nextRes - 1;
            }
        }

        return res;
    }
}
