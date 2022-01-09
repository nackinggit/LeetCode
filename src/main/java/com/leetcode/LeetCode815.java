package com.leetcode;

import java.util.*;

/**
 * 815. 公交路线
 * 给你一个数组 routes ，表示一系列公交线路，其中每个 routes[i] 表示一条公交线路，第 i 辆公交车将会在上面循环行驶。
 * <p>
 * 例如，路线 routes[0] = [1, 5, 7] 表示第 0 辆公交车会一直按序列 1 -> 5 -> 7 -> 1 -> 5 -> 7 -> 1 -> ... 这样的车站路线行驶。
 * 现在从 source 车站出发（初始时不在公交车上），要前往 target 车站。 期间仅可乘坐公交车。
 * <p>
 * 求出 最少乘坐的公交车数量 。如果不可能到达终点车站，返回 -1 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：routes = [[1,2,7],[3,6,7]], source = 1, target = 6
 * 输出：2
 * 解释：最优策略是先乘坐第一辆公交车到达车站 7 , 然后换乘第二辆公交车到车站 6 。
 * 示例 2：
 * <p>
 * 输入：routes = [[7,12],[4,5,15],[6],[15,19],[9,12,13]], source = 15, target = 12
 * 输出：-1
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= routes.length <= 500.
 * 1 <= routes[i].length <= 105
 * routes[i] 中的所有值 互不相同
 * sum(routes[i].length) <= 105
 * 0 <= routes[i][j] < 106
 * 0 <= source, target < 106
 */
public class LeetCode815 {
    public int numBusesToDestination(int[][] routes, int source, int target) {
        if (source == target) return 0;

        int n = routes.length;
        boolean[][] edge = new boolean[n][n]; //edge[i][j] 代表busi->busj是否可达
        Map<Integer, List<Integer>> rec = new HashMap<>(); //经过站点key的bus列表
        for (int i = 0; i < n; i++) {
            for (int site : routes[i]) {
                List<Integer> list = rec.getOrDefault(site, new ArrayList<>());
                for (int j : list) {
                    edge[i][j] = edge[j][i] = true;
                }
                list.add(i);
                rec.put(site, list);
            }
        }

        int[] dis = new int[n]; //dis[i]source出发的bus到编号为i的bus所需换乘的最少次数
        Arrays.fill(dis, -1);
        Queue<Integer> queue = new LinkedList<>();
        for (int bus : rec.getOrDefault(source, new ArrayList<>())) { //路线包含source的bus列表
            dis[bus] = 1;//自己换乘自己只需要一次
            queue.add(bus);
        }

        while (!queue.isEmpty()) {
            int x = queue.poll();
            for (int y = 0; y < n; y++) {
                if (edge[x][y] && dis[y] == -1) { //busx->busy可达，且未记录次数
                    dis[y] = dis[x] + 1;
                    queue.add(y);
                }
            }
        }

        int ret = Integer.MAX_VALUE;
        for (int bus : rec.getOrDefault(target, new ArrayList<>())) { //路线包含target的bus列表
            if (dis[bus] != -1) { //从source到target的换乘次数
                ret = Math.min(ret, dis[bus]);
            }
        }
        return ret == Integer.MAX_VALUE ? -1 : ret;
    }
}
