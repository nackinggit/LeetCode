package com.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。
 * <p>
 * 解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,2,2]
 * 输出：[[],[1],[1,2],[1,2,2],[2],[2,2]]
 * 示例 2：
 * <p>
 * 输入：nums = [0]
 * 输出：[[],[0]]
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 10
 * -10 <= nums[i] <= 10
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/subsets-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode90 {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        subsetsWithDup(false, nums, 0, new ArrayList<>(), res);
        return res;
    }

    private void subsetsWithDup(boolean choosePre, int[] nums, int start, ArrayList<Integer> cur, List<List<Integer>> res) {
        if (start == nums.length) {
            res.add(new ArrayList<>(cur));
            return;
        }
        subsetsWithDup(false, nums, start + 1, cur, res);

        if (!choosePre && start > 0 && nums[start - 1] == nums[start]) {
            return;
        }

        cur.add(nums[start]);
        subsetsWithDup(true, nums, start + 1, cur, res);
        cur.remove(cur.size() - 1);
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 2};
        System.out.println(new LeetCode90().subsetsWithDup(nums));
    }
}
