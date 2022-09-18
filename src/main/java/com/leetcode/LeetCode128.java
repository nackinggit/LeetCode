package com.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 128. 最长连续序列
 * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
 * <p>
 * 请你设计并实现时间复杂度为 O(n) 的算法解决此问题。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [100,4,200,1,3,2]
 * 输出：4
 * 解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
 * 示例 2：
 * <p>
 * 输入：nums = [0,3,7,2,5,8,4,6,0,1]
 * 输出：9
 * <p>
 * <p>
 * 提示：
 * <p>
 * 0 <= nums.length <= 105
 * -109 <= nums[i] <= 109
 */
public class LeetCode128 {

    AtomicInteger cnt = new AtomicInteger(0);
    long startNano = System.nanoTime();
    long lastNano = startNano;

    public void add() {
        long now = System.nanoTime();
        if (now - startNano > 1000 * 1000 * 1000) {
            cnt.set(0);
            startNano = now;
        } else {
            cnt.incrementAndGet();
        }
        lastNano = now;
    }

    public double qps() {
        long now = System.nanoTime();
        if (now - lastNano > 1000 * 1000 * 1000) {
            cnt.set(0);
            startNano = now;
            lastNano = now;
            return 0;
        } else if (lastNano != startNano) {
            return cnt.doubleValue() / (lastNano - startNano) * 1000 * 1000 * 1000;
        }
        return 0;
    }

    public int longestConsecutive(int[] nums) {
        int res = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (map.containsKey(num)) continue;
            int left = map.getOrDefault(num - 1, 0);
            int right = map.getOrDefault(num + 1, 0);
            int len = left + right + 1;
            res = Math.max(res, left);
            map.put(num, len); //只是标记该值已计算过
            map.put(num - left, len); //更新左端点的长度
            map.put(num + right, len); //更新右端点的长度
        }

        return res;
    }
}
