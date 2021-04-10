package com.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
 * <p>
 * 示例:
 * <p>
 * 输入: n = 4, k = 2
 * 输出:
 * [
 * [2,4],
 * [3,4],
 * [2,3],
 * [1,2],
 * [1,3],
 * [1,4],
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/combinations
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode77 {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(1, n, k, res, new ArrayList<>());
        return res;
    }

    private void dfs(int cur, int n, int k, List<List<Integer>> res, ArrayList<Integer> one) {
        if (cur > n + 1) {
            return;
        }

        if (one.size() == k) {
            res.add(new ArrayList<>(one));
            return;
        }

        one.add(cur);
        dfs(cur + 1, n, k, res, one);
        one.remove(one.size() - 1);
        dfs(cur + 1, n, k, res, one);
    }

    public static void main(String[] args) {
        System.out.println(new LeetCode77().combine(4, 2));
    }

}
