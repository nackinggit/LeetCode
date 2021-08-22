package com.leetcode;

/**
 * You are playing a game involving a circular array of non-zero integers nums. Each nums[i] denotes the number of indices forward/backward you must move if you are located at index i:
 * <p>
 * If nums[i] is positive, move nums[i] steps forward, and
 * If nums[i] is negative, move nums[i] steps backward.
 * Since the array is circular, you may assume that moving forward from the last element puts you on the first element, and moving backwards from the first element puts you on the last element.
 * <p>
 * A cycle in the array consists of a sequence of indices seq of length k where:
 * <p>
 * Following the movement rules above results in the repeating index sequence seq[0] -> seq[1] -> ... -> seq[k - 1] -> seq[0] -> ...
 * Every nums[seq[j]] is either all positive or all negative.
 * k > 1
 * Return true if there is a cycle in nums, or false otherwise.
 * <p>
 *  
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [2,-1,1,2,2]
 * Output: true
 * Explanation:
 * There is a cycle from index 0 -> 2 -> 3 -> 0 -> ...
 * The cycle's length is 3.
 * Example 2:
 * <p>
 * Input: nums = [-1,2]
 * Output: false
 * Explanation:
 * The sequence from index 1 -> 1 -> 1 -> ... is not a cycle because the sequence's length is 1.
 * By definition the sequence's length must be strictly greater than 1 to be a cycle.
 * Example 3:
 * <p>
 * Input: nums = [-2,1,-1,-2,-2]
 * Output: false
 * Explanation:
 * The sequence from index 1 -> 2 -> 1 -> ... is not a cycle because nums[1] is positive, but nums[2] is negative.
 * Every nums[seq[j]] must be either all positive or all negative.
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/circular-array-loop
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode457 {
    public boolean circularArrayLoop(int[] nums) {
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            if (nums[i] == 0) continue;

            int slow = i, fast = next(nums, i);
            while (nums[slow] * nums[fast] > 0 && nums[slow] * nums[next(nums, fast)] > 0) {
                if (slow == fast) {
                    if (slow != next(nums, slow)) {
                        //跳过环为1的情况
                        return true;
                    } else {
                        break;
                    }
                }
                slow = next(nums, slow);
                fast = next(nums, next(nums, fast));
            }
            int add = i;
            while (nums[add] * nums[next(nums, add)] > 0) {
                //访问过的点置为0
                int tmp = add;
                add = next(nums, add);
                nums[tmp] = 0;
            }
        }
        return false;
    }

    private int next(int[] nums, int i) {
        int length = nums.length;
        return ((i + nums[i]) % length + length) % length;
    }
}
