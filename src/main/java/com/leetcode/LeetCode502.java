package com.leetcode;

import java.util.*;

/**
 * 502. IPO
 * 假设 力扣（LeetCode）即将开始 IPO 。为了以更高的价格将股票卖给风险投资公司，力扣 希望在 IPO 之前开展一些项目以增加其资本。 由于资源有限，它只能在 IPO 之前完成最多 k 个不同的项目。帮助 力扣 设计完成最多 k 个不同项目后得到最大总资本的方式。
 * <p>
 * 给你 n 个项目。对于每个项目 i ，它都有一个纯利润 profits[i] ，和启动该项目需要的最小资本 capital[i] 。
 * <p>
 * 最初，你的资本为 w 。当你完成一个项目时，你将获得纯利润，且利润将被添加到你的总资本中。
 * <p>
 * 总而言之，从给定项目中选择 最多 k 个不同项目的列表，以 最大化最终资本 ，并输出最终可获得的最多资本。
 * <p>
 * 答案保证在 32 位有符号整数范围内。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：k = 2, w = 0, profits = [1,2,3], capital = [0,1,1]
 * 输出：4
 * 解释：
 * 由于你的初始资本为 0，你仅可以从 0 号项目开始。
 * 在完成后，你将获得 1 的利润，你的总资本将变为 1。
 * 此时你可以选择开始 1 号或 2 号项目。
 * 由于你最多可以选择两个项目，所以你需要完成 2 号项目以获得最大的资本。
 * 因此，输出最后最大化的资本，为 0 + 1 + 3 = 4。
 * 示例 2：
 * <p>
 * 输入：k = 3, w = 0, profits = [1,2,3], capital = [0,1,2]
 * 输出：6
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= k <= 105
 * 0 <= w <= 109
 * n == profits.length
 * n == capital.length
 * 1 <= n <= 105
 * 0 <= profits[i] <= 104
 * 0 <= capital[i] <= 109
 */
public class LeetCode502 {
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        int n = profits.length;
        int curr = 0;
        int[][] arr = new int[n][2];

        for (int i = 0; i < n; ++i) {
            arr[i][0] = capital[i];
            arr[i][1] = profits[i];
        }
        Arrays.sort(arr, Comparator.comparingInt(a -> a[0]));

        PriorityQueue<Integer> pq = new PriorityQueue<>((x, y) -> y - x);
        for (int i = 0; i < k; ++i) {
            while (curr < n && arr[curr][0] <= w) {
                pq.add(arr[curr][1]);
                curr++;
            }
            if (!pq.isEmpty()) {
                w += pq.poll();
            } else {
                break;
            }
        }

        return w;
    }

    public static void main(String[] args) {
        int k = 3, w = 0;
        int[] profits = {1, 2, 3}, capital = {0, 1, 2};
        System.out.println(new LeetCode502().findMaximizedCapital(k, w, profits,capital));
    }
}
