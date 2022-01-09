package com.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个整数数组和一个整数 k，你需要找到该数组中和为 k 的连续的子数组的个数。
 * <p>
 * 示例 1 :
 * <p>
 * 输入:nums = [1,1,1], k = 2
 * 输出: 2 , [1,1] 与 [1,1] 为两种不同的情况。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/subarray-sum-equals-k
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode560 {
    public int subarraySum(int[] nums, int k) {
        int preSum = 0, res = 0;
        Map<Integer, Integer> sumCount = new HashMap<>();
        sumCount.put(0, 1); //preSum = 0时有一次计数
        for (int num : nums) {
            preSum += num;
            if (sumCount.containsKey(preSum - k)) {
                res += sumCount.get(preSum - k);
            }

            sumCount.put(preSum, sumCount.getOrDefault(preSum, 0) + 1);
        }

        return res;
    }
}
