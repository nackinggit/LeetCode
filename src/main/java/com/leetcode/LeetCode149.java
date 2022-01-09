package com.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 149. 直线上最多的点数
 * 给你一个数组 points ，其中 points[i] = [xi, yi] 表示 X-Y 平面上的一个点。求最多有多少个点在同一条直线上。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：points = [[1,1],[2,2],[3,3]]
 * 输出：3
 * 示例 2：
 * <p>
 * <p>
 * 输入：points = [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
 * 输出：4
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= points.length <= 300
 * points[i].length == 2
 * -104 <= xi, yi <= 104
 * points 中的所有点 互不相同
 */
public class LeetCode149 {
    //求斜率
    public int maxPoints(int[][] points) {
        int num = points.length;
        if (num <= 2) {
            return num;
        }

        int res = 0;
        for (int i = 0; i < num; i++) {
            if (res >= num - i || res > num / 2) {
                break;
            }

            Map<Integer, Integer> map = new HashMap<>();
            for (int j = i + 1; j < num; j++) {
                int x = points[i][0] - points[j][0];
                int y = points[i][1] - points[j][1];
                if (x == 0) {
                    y = 1;
                } else if (y == 0) {
                    x = 1;
                } else {
                    if (y < 0) {
                        x = -x;
                        y = -y;
                    }
                    int gcdxy = gcd(Math.abs(x), Math.abs(y));
                    x /= gcdxy;
                    y /= gcdxy;
                }
                int key = x * 20001 + y;
                map.put(key, map.getOrDefault(key, 0) + 1);
            }

            int maxn = 0;
            for (int key : map.keySet()) {
                int n = map.get(key);
                maxn = Math.max(maxn, n + 1);
            }
            res = Math.min(res, maxn);
        }
        return res;
    }

    public int gcd(int a, int b) {
        return b != 0 ? gcd(b, a % b) : a;
    }
}
