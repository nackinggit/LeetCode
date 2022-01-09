package com.leetcode;

import java.util.Arrays;

/**
 * 给你一个正整数 n ，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：n = 3
 * 输出：[[1,2,3],[8,9,4],[7,6,5]]
 * 示例 2：
 * <p>
 * 输入：n = 1
 * 输出：[[1]]
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 20
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/spiral-matrix-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode59 {
    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        int col = 0, row = 0;
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int directionIndex = 0;
        for (int i = 1; i <= n * n; i++) {
            res[row][col] = i;
            int nrow = row + directions[directionIndex][0], ncol = col + directions[directionIndex][1];
            if (nrow < 0 || nrow >= n || ncol < 0 || ncol >= n || res[nrow][ncol] != 0) {
                directionIndex = (directionIndex + 1) % 4;
            }
            row += directions[directionIndex][0];
            col += directions[directionIndex][1];
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(new LeetCode59().generateMatrix(3)));
    }
}
