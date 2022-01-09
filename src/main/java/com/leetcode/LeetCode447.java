package com.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 447. 回旋镖的数量
 * 给定平面上 n 对 互不相同 的点 points ，其中 points[i] = [xi, yi] 。回旋镖 是由点 (i, j, k) 表示的元组 ，其中 i 和 j 之间的距离和 i 和 k 之间的距离相等（需要考虑元组的顺序）。
 * <p>
 * 返回平面上所有回旋镖的数量。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：points = [[0,0],[1,0],[2,0]]
 * 输出：2
 * 解释：两个回旋镖为 [[1,0],[0,0],[2,0]] 和 [[1,0],[2,0],[0,0]]
 * 示例 2：
 * <p>
 * 输入：points = [[1,1],[2,2],[3,3]]
 * 输出：2
 * 示例 3：
 * <p>
 * 输入：points = [[1,1]]
 * 输出：0
 * <p>
 * <p>
 * 提示：
 * <p>
 * n == points.length
 * 1 <= n <= 500
 * points[i].length == 2
 * -104 <= xi, yi <= 104
 * 所有点都 互不相同
 */
public class LeetCode447 {
    public int numberOfBoomerangs(int[][] points) {
        int res = 0;

        for (int[] p : points) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int[] q : points) {
                int dis = (q[0] - p[0]) * (q[0] - p[0]) + (q[1] - p[1]) * (q[1] - p[1]);
                map.put(dis, map.getOrDefault(dis, 0) + 1);
            }

            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                int count = entry.getValue();
                res += (count - 1) * count;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] a = {{0, 0}, {1, 0}, {2, 0}};
        System.out.println(new LeetCode447().numberOfBoomerangs(a));
    }
}
