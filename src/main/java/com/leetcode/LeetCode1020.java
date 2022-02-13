package com.leetcode;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 1020. 飞地的数量
 * 给你一个大小为 m x n 的二进制矩阵 grid ，其中 0 表示一个海洋单元格、1 表示一个陆地单元格。
 * <p>
 * 一次 移动 是指从一个陆地单元格走到另一个相邻（上、下、左、右）的陆地单元格或跨过 grid 的边界。
 * <p>
 * 返回网格中 无法 在任意次数的移动中离开网格边界的陆地单元格的数量。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：grid = [[0,0,0,0],[1,0,1,0],[0,1,1,0],[0,0,0,0]]
 * 输出：3
 * 解释：有三个 1 被 0 包围。一个 1 没有被包围，因为它在边界上。
 * 示例 2：
 * <p>
 * <p>
 * 输入：grid = [[0,1,1,0],[0,0,1,0],[0,0,1,0],[0,0,0,0]]
 * 输出：0
 * 解释：所有 1 都在边界上或可以到达边界。
 */
public class LeetCode1020 {

    public static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int numEnclaves(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        Queue<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < m; i++) {
            if (grid[i][0] == 1) {
                visited[i][0] = true;
                queue.offer(new int[]{i, 0});
            }
            if (grid[i][n - 1] == 1) {
                visited[i][n - 1] = true;
                queue.offer(new int[]{i, n - 1});
            }
        }
        for (int j = 1; j < n - 1; j++) {
            if (grid[0][j] == 1) {
                visited[0][j] = true;
                queue.offer(new int[]{0, j});
            }
            if (grid[m - 1][j] == 1) {
                visited[m - 1][j] = true;
                queue.offer(new int[]{m - 1, j});
            }
        }
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int currRow = cell[0], currCol = cell[1];
            for (int[] dir : dirs) {
                int nextRow = currRow + dir[0], nextCol = currCol + dir[1];
                if (nextRow >= 0 && nextRow < m
                        && nextCol >= 0 && nextCol < n
                        && grid[nextRow][nextCol] == 1
                        && !visited[nextRow][nextCol]) {
                    visited[nextRow][nextCol] = true;
                    queue.offer(new int[]{nextRow, nextCol});
                }
            }
        }
        int enclaves = 0;
        for (int i = 1; i < m - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    enclaves++;
                }
            }
        }
        return enclaves;
    }

    public int numEnclaves2(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        FindUnion findUnion = new FindUnion(grid);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int index = i * n + j;
                if (j + 1 < n && grid[i][j + 1] == 1) {
                    findUnion.union(index, index + 1);
                }

                if (i + 1 < n && grid[i + 1][j] == 1) {
                    findUnion.union(index, index + n);
                }
            }
        }

        int res = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1 && !findUnion.isOnEdge(i * n + j)) {
                    res++;
                }
            }
        }

        return res;
    }

    public static class FindUnion {
        private int[] parent;
        private boolean[] onEdge;
        private int[] rank;

        public FindUnion(int[][] grid) {
            int m = grid.length, n = grid[0].length;
            parent = new int[m * n];
            onEdge = new boolean[m * n];
            rank = new int[m * n];

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 1) {
                        int index = i * m + j;
                        parent[index] = index;
                        if (i == 0 || j == 0 || i == m - 1 || j == n - 1) {
                            onEdge[index] = true;
                        }
                    }
                }
            }
        }

        public int find(int i) {
            if (parent[i] != i) {
                parent[i] = find(parent[i]);
            }
            return parent[i];
        }

        public void union(int x, int y) {
            int px = find(x), py = find(y);

            if (py != px) {
                if (rank[px] > rank[py]) {
                    parent[py] = px;
                    onEdge[px] = onEdge[px] | onEdge[py];
                } else if (rank[px] < rank[py]) {
                    parent[px] = py;
                    onEdge[py] = onEdge[px] | onEdge[py];
                } else {
                    parent[py] = px;
                    onEdge[px] |= onEdge[py];
                    rank[px]++;
                }
            }
        }

        public boolean isOnEdge(int i) {
            return onEdge[find(i)];
        }

    }

    public static void main(String[] args) {
        LeetCode1020 leetCode1020 = new LeetCode1020();
        int[][] grid = {{0, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1},
                {1, 1, 1, 1, 0, 1, 0, 1, 1, 0, 0},
                {0, 1, 0, 1, 1, 0, 0, 0, 0, 1, 0},
                {1, 0, 1, 1, 1, 1, 1, 0, 0, 0, 1},
                {0, 0, 1, 0, 1, 1, 0, 0, 1, 0, 0},
                {1, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1},
                {0, 1, 0, 1, 1, 0, 0, 0, 1, 0, 0},
                {0, 1, 1, 0, 1, 0, 1, 1, 1, 0, 0},
                {1, 1, 0, 1, 1, 1, 0, 0, 0, 0, 0},
                {1, 0, 1, 1, 0, 0, 0, 1, 0, 0, 1}};
        System.out.println(leetCode1020.numEnclaves(grid));
    }
}
