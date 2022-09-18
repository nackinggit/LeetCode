package com.leetcode;

import java.util.PriorityQueue;

/**
 * 871. 最低加油次数
 * 汽车从起点出发驶向目的地，该目的地位于出发位置东面 target 英里处。
 * <p>
 * 沿途有加油站，每个 station[i] 代表一个加油站，它位于出发位置东面 station[i][0] 英里处，并且有 station[i][1] 升汽油。
 * <p>
 * 假设汽车油箱的容量是无限的，其中最初有 startFuel 升燃料。它每行驶 1 英里就会用掉 1 升汽油。
 * <p>
 * 当汽车到达加油站时，它可能停下来加油，将所有汽油从加油站转移到汽车中。
 * <p>
 * 为了到达目的地，汽车所必要的最低加油次数是多少？如果无法到达目的地，则返回 -1 。
 * <p>
 * 注意：如果汽车到达加油站时剩余燃料为 0，它仍然可以在那里加油。如果汽车到达目的地时剩余燃料为 0，仍然认为它已经到达目的地。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：target = 1, startFuel = 1, stations = []
 * 输出：0
 * 解释：我们可以在不加油的情况下到达目的地。
 * 示例 2：
 * <p>
 * 输入：target = 100, startFuel = 1, stations = [[10,100]]
 * 输出：-1
 * 解释：我们无法抵达目的地，甚至无法到达第一个加油站。
 * 示例 3：
 * <p>
 * 输入：target = 100, startFuel = 10, stations = [[10,60],[20,30],[30,30],[60,40]]
 * 输出：2
 * 解释：
 * 我们出发时有 10 升燃料。
 * 我们开车来到距起点 10 英里处的加油站，消耗 10 升燃料。将汽油从 0 升加到 60 升。
 * 然后，我们从 10 英里处的加油站开到 60 英里处的加油站（消耗 50 升燃料），
 * 并将汽油从 10 升加到 50 升。然后我们开车抵达目的地。
 * 我们沿途在1两个加油站停靠，所以返回 2 。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= target, startFuel, stations[i][1] <= 10^9
 * 0 <= stations.length <= 500
 * 0 < stations[0][0] < stations[1][0] < ... < stations[stations.length-1][0] < target
 */
public class LeetCode871 {
    //dp[i]加i次油跑的最远距离
    //dp[i] = max{dp[i-1] + station[k][1]} (station[k][0] <= dp[i-1]) || 0
    //0 <= k < length
    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        if (target <= startFuel) return 0;
        int n = stations.length;
        int[] dp = new int[n + 1];
        dp[0] = startFuel;
        for (int i = 0; i < n; i++) {
            int[] s = stations[i];
            //站点i最多加i次油，从前往后会累加，
            //所以从后往前计算
            for (int j = i; j >= 0; j--) {
                if (dp[j] >= s[0]) {
                    dp[j + 1] = Math.max(dp[j + 1], dp[j] + s[1]);
                }
            }
        }

        for (int i = 0; i <= n; i++) {
            if (dp[i] >= target) return i;
        }

        return -1;
    }

    public int minRefuelStops2(int target, int startFuel, int[][] stations) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        int ans = 0, prev = 0, rest = startFuel; //rest-当前剩余汽油,prev-上一站剩余汽油
        int n = stations.length;
        for (int i = 0; i <= n; i++) {
            int curr = i < n ? stations[i][0] : target; //到达站点i需要的汽油
            rest -= curr - prev;
            while (rest < 0 && !pq.isEmpty()) {
                rest += pq.poll();
                ans++;
            }
            if (rest < 0) {
                return -1;
            }
            if (i < n) {
                pq.offer(stations[i][1]);
                prev = curr;
            }
        }
        return ans;
    }
}
