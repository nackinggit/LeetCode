package com.leetcode;

/**
 * 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都 围成一圈 ，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。
 * <p>
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，能够偷窃到的最高金额。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [2,3,2]
 * 输出：3
 * 解释：你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
 * 示例 2：
 * <p>
 * 输入：nums = [1,2,3,1]
 * 输出：4
 * 解释：你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
 *      偷窃到的最高金额 = 1 + 3 = 4 。
 * 示例 3：
 * <p>
 * 输入：nums = [0]
 * 输出：0
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/house-robber-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode213 {
    public int rob(int[] nums) {
        if (nums == null || nums.length <= 0) return 0;
        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return Math.max(nums[0], nums[1]);
        return Math.max(doRob(nums, 0, nums.length - 2), doRob(nums, 1, nums.length - 1));
    }

    private int doRob(int[] nums, int start, int end) {
        int f0 = nums[start], f1 = Math.max(nums[start], nums[start + 1]);
        for (int i = start + 2; i <= end; i++) {
            int tmp = f1;
            f1 = Math.max(f0 + nums[i], f1);
            f0 = tmp;
        }
        return f1;
    }

    public static void main(String[] args) {
        System.out.println(new LeetCode213().rob(new int[]{1, 2, 3, 4, 5, 6}));
    }
}
