package com.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 200. 岛屿数量
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 * <p>
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 * <p>
 * 此外，你可以假设该网格的四条边均被水包围。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：grid = [
 * ["1","1","1","1","0"],
 * ["1","1","0","1","0"],
 * ["1","1","0","0","0"],
 * ["0","0","0","0","0"]
 * ]
 * 输出：1
 * 示例 2：
 * <p>
 * 输入：grid = [
 * ["1","1","0","0","0"],
 * ["1","1","0","0","0"],
 * ["0","0","1","0","0"],
 * ["0","0","0","1","1"]
 * ]
 * 输出：3
 * <p>
 * <p>
 * 提示：
 * <p>
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 300
 * grid[i][j] 的值为 '0' 或 '1'
 */
public class LeetCode200 {
    int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int numIslands2(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int rows = grid.length;
        int cols = grid[0].length;
        boolean[][] visited = new boolean[rows][cols];
        int res = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '1' && !visited[i][j]) {
                    res += 1;
                    visited[i][j] = true;
//                    grid[i][j] = 0;
                    Queue<int[]> neighbors = new LinkedList<>();
                    neighbors.add(new int[]{i, j});
                    while (!neighbors.isEmpty()) {
                        int[] xy = neighbors.remove();
                        for (int[] dir : dirs) {
                            int nx = xy[0] + dir[0];
                            int ny = xy[1] + dir[1];
                            if ((nx >= 0 && nx < rows)
                                    && (ny >= 0 && ny < cols)
                                    && grid[nx][ny] == '1'
                                    && !visited[nx][ny]) {
//                                grid[nx][ny] = '0';
                                visited[nx][ny] = true;
                                neighbors.add(new int[]{nx, ny});
                            }
                        }
                    }
                }
            }
        }

        return res;
    }

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        int rows = grid.length, cols = grid[0].length;
        UnionFind uf = new UnionFind(grid);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '1') {
                    grid[i][j] = '0';
                    for (int[] dir : dirs) {
                        int ni = i + dir[0], nj = j + dir[1];
                        if ((ni >= 0 && ni < rows)
                                && (nj >= 0 && nj < cols)
                                && grid[ni][nj] == '1') {
                            uf.union(ni * cols + nj, i * cols + j);
                        }
                    }
                }
            }
        }

        return uf.count;
    }

    static class UnionFind {
        private int[] parents;
        private int[] rank;
        private int count;

        public UnionFind(char[][] grid) {
            count = 0;
            parents = new int[grid.length * grid[0].length];
            rank = new int[parents.length];
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    int idx = i * grid[0].length + j;
                    if (grid[i][j] == '1') {
                        parents[idx] = idx;
                        count += 1;
                    }
                }
            }
        }

        public int find(int param) {
            if (param == parents[param]) return param;
            else {
                parents[param] = find(parents[param]);
                return parents[param];
            }
        }

        public void union(int x, int y) {
            int r1 = find(x), r2 = find(y);
            if (r1 == r2) return;
            if (rank[r1] > rank[r2]) {
                parents[r2] = r1;
            } else if (rank[r1] < rank[r2]) {
                parents[r1] = r2;
            } else {
                parents[r1] = r2;
                rank[r2] += 1;
            }
            count--;
        }
    }
}
