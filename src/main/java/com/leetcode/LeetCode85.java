package com.leetcode;

/**
 * 85. 最大矩形
 * 给定一个仅包含 0 和 1 、大小为 rows x cols 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：matrix = [['1','0','1','0','0'],['1','0','1','1','1'],['1','1','1','1','1'],['1','0','0','1','0']]
 * 输出：6
 * 解释：最大矩形如上图所示。
 * 示例 2：
 * <p>
 * 输入：matrix = []
 * 输出：0
 * 示例 3：
 * <p>
 * 输入：matrix = [['0']]
 * 输出：0
 * 示例 4：
 * <p>
 * 输入：matrix = [['1']]
 * 输出：1
 * 示例 5：
 * <p>
 * 输入：matrix = [['0','0']]
 * 输出：0
 * <p>
 * <p>
 * 提示：
 * <p>
 * rows == matrix.length
 * cols == matrix[0].length
 * 1 <= row, cols <= 200
 * matrix[i][j] 为 '0' 或 '1'
 */
public class LeetCode85 {
    public static int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;
        int rows = matrix.length, cols = matrix[0].length;

        int ans = 0;
        int[][] heights = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            //对每一行算高度
            heights[i] = new int[cols];
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == '1') {
                    heights[i][j] = (i == 0 ? 0 : heights[i - 1][j]) + 1;
                }
            }

            ans = Math.max(ans, LeetCode84.maxArea(heights[i]));
        }

        return ans;
    }

    public static void main(String[] args) {
        char[][] matrix = {
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}};

        System.out.println(maximalRectangle(matrix));
    }
}
