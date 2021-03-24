package com.leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 给你一个整数数组 nums ，数组中共有 n 个整数。132 模式的子序列 由三个整数 nums[i]、nums[j] 和 nums[k] 组成，并同时满足：
 * i < j < k 和 nums[i] < nums[k] < nums[j] 。
 * <p>
 * 如果 nums 中存在 132 模式的子序列 ，返回 true ；否则，返回 false 。
 * <p>
 *
 * <p>
 * 进阶：很容易想到时间复杂度为 O(n^2) 的解决方案，你可以设计一个时间复杂度为 O(n logn) 或 O(n) 的解决方案吗？
 * <p>
 *
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,2,3,4]
 * 输出：false
 * 解释：序列中不存在 132 模式的子序列。
 * 示例 2：
 * <p>
 * 输入：nums = [3,1,4,2]
 * 输出：true
 * 解释：序列中有 1 个 132 模式的子序列： [1, 4, 2] 。
 * 示例 3：
 * <p>
 * 输入：nums = [-1,3,2,0]
 * 输出：true
 * 解释：序列中有 3 个 132 模式的的子序列：[-1, 3, 2]、[-1, 3, 0] 和 [-1, 2, 0] 。
 *
 * <p>
 * 提示：
 * <p>
 * n == nums.length
 * 1 <= n <= 104
 * -109 <= nums[i] <= 109
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/132-pattern
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode456 {
    public boolean find132pattern(int[] nums) {
        int len = nums.length;
        //保存所有备选k
        Deque<Integer> ck = new LinkedList<>();
        ck.push(nums[len - 1]);
        int maxK = Integer.MIN_VALUE;//备选k

        for (int i = len - 2; i >= 0; i--) {
            if (nums[i] < maxK) { // 此时nums[i] 是否可为1(132)
                return true;
            }

            //nums[i]是否可以为3(ck中元素的坐标都大于当前坐标，因此ck中的任何元素都可以为2)
            while (!ck.isEmpty() && ck.peek() < nums[i]) {
                System.out.println(ck);
                maxK = ck.pop(); //从备选K中选一个最大的当做k
            }

            if (nums[i] > maxK) { //nums[i]比maxK大,nums[i]可以作为2的备选
                ck.push(nums[i]);
            }
        }

        return false;
    }

    public static void main(String[] args) {
        int[] nums = {-1,3,2,0};
        System.out.println(new LeetCode456().find132pattern(nums));
    }

}
