package com.lcp;

import java.util.*;

/**
 * 小朋友 A 在和 ta 的小伙伴们玩传信息游戏，游戏规则如下：
 * <p>
 * 有 n 名玩家，所有玩家编号分别为 0 ～ n-1，其中小朋友 A 的编号为 0
 * 每个玩家都有固定的若干个可传信息的其他玩家（也可能没有）。传信息的关系是单向的（比如 A 可以向 B 传信息，但 B 不能向 A 传信息）。
 * 每轮信息必须需要传递给另一个人，且信息可重复经过同一个人
 * 给定总玩家数 n，以及按 [玩家编号,对应可传递玩家编号] 关系组成的二维数组 relation。返回信息从小 A (编号 0 ) 经过 k 轮传递到编号为 n-1 的小伙伴处的方案数；若不能到达，返回 0。
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 5, relation = [[0,2],[2,1],[3,4],[2,3],[1,4],[2,0],[0,4]], k = 3
 * <p>
 * 输出：3
 * <p>
 * 解释：信息从小 A 编号 0 处开始，经 3 轮传递，到达编号 4。共有 3 种方案，分别是 0->2->0->4， 0->2->1->4， 0->2->3->4。
 * <p>
 * 示例 2：
 * <p>
 * 输入：n = 3, relation = [[0,2],[2,1]], k = 2
 * <p>
 * 输出：0
 * <p>
 * 解释：信息不能从小 A 处经过 2 轮传递到编号 2
 * <p>
 * 限制：
 * <p>
 * 2 <= n <= 10
 * 1 <= k <= 5
 * 1 <= relation.length <= 90, 且 relation[i].length == 2
 * 0 <= relation[i][0],relation[i][1] < n 且 relation[i][0] != relation[i][1]
 * <p>
 */
public class LCP07 {
    int ways;


    public int numWays(int n, int[][] relation, int k) {
        ways = 0;
        List<List<Integer>> edges = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            edges.add(new ArrayList<>());
        }

        for (int[] edge : relation) {
            int src = edge[0], dest = edge[1];
            edges.get(src).add(dest);
        }
        dfs(0, 0, k, n, edges);
        return ways;
    }

    private void dfs(int index, int steps, int targetStep, int n, List<List<Integer>> edges) {
        if (steps == targetStep) {
            if (index == n - 1) {
                ways += 1;
            }
            return;
        }

        List<Integer> next = edges.get(index);
        for (int i : next) {
            dfs(i, steps + 1, targetStep, n, edges);
        }
    }

    public int numWays2(int n, int[][] relation, int k) {
        List<List<Integer>> edges = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            edges.add(new ArrayList<>());
        }

        for (int[] edge : relation) {
            int src = edge[0], dest = edge[1];
            edges.get(src).add(dest);
        }

        int steps = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        while (!queue.isEmpty() && steps < k) {
            steps += 1;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int index = Optional.ofNullable(queue.poll()).orElse(0);
                List<Integer> next = edges.get(index);
                queue.addAll(next);
            }
        }

        int ways = 0;
        if (steps == k) {
            while (!queue.isEmpty()) {
                if (queue.poll() == n - 1) {
                    ways += 1;
                }
            }
        }
        return ways;
    }

    public int numWays3(int n, int[][] relation, int k) {
        int[][] dp = new int[k + 1][n]; //dp[i][j] 代表i轮传递后，到j的方法数
        dp[0][0] = 1;
        for (int i = 0; i < k; i++) {
            for (int[] edge : relation) {
                int src = edge[0], dest = edge[1];
                dp[i + 1][dest] += dp[i][src];
            }
        }
        return dp[k][n - 1];
    }

    public int numWays4(int n, int[][] relation, int k) {
        int[] dp = new int[n]; //dp[i][j] 代表i轮传递后，到j的方法数
        dp[0] = 1;
        for (int i = 0; i < k; i++) {
            int[] next = new int[n];
            for (int[] edge : relation) {
                int src = edge[0], dest = edge[1];
                next[dest] += dp[src];
            }
            dp = next;
        }
        return dp[n - 1];
    }
}
