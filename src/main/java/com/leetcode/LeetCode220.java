package com.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 给你一个整数数组 nums 和两个整数k 和 t 。请你判断是否存在 两个不同下标 i 和 j，使得abs(nums[i] - nums[j]) <= t ，同时又满足 abs(i - j) <= k 。
 * <p>
 * 如果存在则返回 true，不存在返回 false。
 * <p>
 * <p>
 * 示例1：
 * <p>
 * 输入：nums = [1,2,3,1], k = 3, t = 0
 * 输出：true
 * 示例 2：
 * <p>
 * 输入：nums = [1,0,1,1], k = 1, t = 2
 * 输出：true
 * 示例 3：
 * <p>
 * 输入：nums = [1,5,9,1,5,9], k = 2, t = 3
 * 输出：false
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/contains-duplicate-iii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode220 {
    //桶
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        int w = t + 1;
        Map<Long, Long> bucket = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            long bucketId = getBucketId(nums[i], w);
            if (bucket.containsKey(bucketId)) {
                return true;
            }

            if (bucket.containsKey(bucketId + 1) && Math.abs(bucket.get(bucketId + 1) - nums[i]) < w) {
                return true;
            }

            if (bucket.containsKey(bucketId - 1) && Math.abs(bucket.get(bucketId - 1) - nums[i]) < w) {
                return true;
            }

            bucket.put(bucketId, (long) nums[i]);

            if (i >= k) {
                bucket.remove(getBucketId(nums[i - k], w));
            }
        }
        return false;
    }

    private long getBucketId(int num, int w) {
        if (num >= 0) {
            return num / w;
        } else {
            return (num + 1) / w - 1;
        }
    }

    public static void main(String[] args) {
        System.out.println(new LeetCode220().containsNearbyAlmostDuplicate(new int[]{1,2,3,1}, 3, 0));
    }
}
