package com.leetcode;

import java.util.*;

/**
 * 在给定的二维二进制数组 A 中，存在两座岛。（岛是由四面相连的 1 形成的一个最大组。）
 * <p>
 * 现在，我们可以将 0 变为 1，以使两座岛连接起来，变成一座岛。
 * <p>
 * 返回必须翻转的 0 的最小数目。（可以保证答案至少是 1 。）
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：A = [[0,1],[1,0]]
 * 输出：1
 * 示例 2：
 * <p>
 * 输入：A = [[0,1,0],[0,0,0],[0,0,1]]
 * 输出：2
 * 示例 3：
 * <p>
 * 输入：A = [[1,1,1,1,1],[1,0,0,0,1],[1,0,1,0,1],[1,0,0,0,1],[1,1,1,1,1]]
 * 输出：1
 *
 * <p>
 * 提示：
 * <p>
 * 2 <= A.length == A[0].length <= 100
 * A[i][j] == 0 或 A[i][j] == 1
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shortest-bridge
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode934 {
    public int shortestBridge(int[][] grid) {
        int rows = grid.length, cols = grid[0].length;
        //dfs找出两个岛屿
        int[][] colors = dfs(grid);

        Queue<Node> queue = new LinkedList<>();
        Set<Integer> target = new HashSet<>();

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (colors[r][c] == 1) {
                    queue.add(new Node(r, c, 0));
                } else if (colors[r][c] == 2) {
                    target.add(r * rows + c);
                }
            }
        }

        //bfs 找出最小距离
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (target.contains(node.r * rows + node.c)) {
                return node.depth - 1;
            }

            for (int[] pair : neighbors(new int[]{node.r, node.c}, rows, cols)) {
                int x = pair[0], y = pair[1];
                if (colors[x][y] != 1) {
                    queue.add(new Node(x, y, node.depth + 1));
                    colors[x][y] = 1;
                }
            }
        }

        return 1;
    }

    private int[][] dfs(int[][] grid) {
        int rows = grid.length, cols = grid[0].length;
        int[][] colors = new int[rows][cols];
        int t = 0;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (colors[r][c] == 0 && grid[r][c] == 1) {
                    Stack<int[]> stack = new Stack<>();
                    stack.push(new int[]{r, c});
                    colors[r][c] = ++t;

                    while (!stack.isEmpty()) {
                        int[] pair = stack.pop();
                        for (int[] neighbor : neighbors(pair, rows, cols)) {
                            int nr = neighbor[0], nc = neighbor[1];
                            if (grid[nr][nc] == 1 && colors[nr][nc] == 0) {
                                colors[nr][nc] = t;
                                stack.push(new int[]{nr, nc});
                            }
                        }
                    }
                }
            }
        }
        printA(colors);
        return colors;
    }

    private List<int[]> neighbors(int[] pair, int rows, int cols) {
        int r = pair[0], c = pair[1];
        List<int[]> res = new ArrayList<>();
        if (r - 1 >= 0) res.add(new int[]{r - 1, c});
        if (c - 1 >= 0) res.add(new int[]{r, c - 1});
        if (r + 1 < rows) res.add(new int[]{r + 1, c});
        if (c + 1 < cols) res.add(new int[]{r, c + 1});
        return res;
    }

    public static class Node {
        int r, c, depth;

        Node(int r, int c, int d) {
            this.r = r;
            this.c = c;
            depth = d;
        }
    }

    public static void printA(int[][] grid) {
        for (int[] a : grid) {
            System.out.println(Arrays.toString(a));
        }
        System.out.println("------------------------");
    }

    public static void main(String[] args) {
        int[][] grid = {{1, 1, 1, 1, 1}, {1, 0, 0, 0, 1}, {1, 0, 1, 0, 1}, {1, 0, 0, 0, 1}, {1, 1, 1, 1, 1}};
        printA(grid);
        System.out.println(new LeetCode934().shortestBridge(grid));
    }
}
