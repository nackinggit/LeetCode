package com.leetcode;

/**
 * 1140. 石子游戏 II
 * 提示
 * 中等
 * 258
 * 相关企业
 * 爱丽丝和鲍勃继续他们的石子游戏。许多堆石子 排成一行，每堆都有正整数颗石子 piles[i]。游戏以谁手中的石子最多来决出胜负。
 * <p>
 * 爱丽丝和鲍勃轮流进行，爱丽丝先开始。最初，M = 1。
 * <p>
 * 在每个玩家的回合中，该玩家可以拿走剩下的 前 X 堆的所有石子，其中 1 <= X <= 2M。然后，令 M = max(M, X)。
 * <p>
 * 游戏一直持续到所有石子都被拿走。
 * <p>
 * 假设爱丽丝和鲍勃都发挥出最佳水平，返回爱丽丝可以得到的最大数量的石头。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：piles = [2,7,9,4,4]
 * 输出：10
 * 解释：如果一开始Alice取了一堆，Bob取了两堆，然后Alice再取两堆。爱丽丝可以得到2 + 4 + 4 = 10堆。如果Alice一开始拿走了两堆，那么Bob可以拿走剩下的三堆。在这种情况下，Alice得到2 + 7 = 9堆。返回10，因为它更大。
 * 示例 2:
 * <p>
 * 输入：piles = [1,2,3,4,5,100]
 * 输出：104
 */
public class LeetCode1140 {
    //dp[i][j] -> 剩余[i,len-1]，M = j时，先拿的人的最大收益
    //dp[i][M] = sum(i, len-1), 当2M + i >= len时
    //dp[i][M] = max{dp[i][M], sum(i, len-1) - dp[i+x][max{M,x}]}, 其中x >= 1 && x <= 2M
    public int stoneGameII(int[] piles) {
        int len = piles.length, sum = 0;
        int[][] dp = new int[len][len + 1];
        for (int i = len - 1; i >= 0; i--) {
            sum += piles[i];

            for (int M = 1; M <= len; M++) {
                if (i + 2 * M >= len) {
                    dp[i][M] = sum;
                } else {
                    for (int x = 1; x <= 2 * M; x++) {
                        dp[i][M] = Math.max(dp[i][M], sum - dp[i + x][Math.max(x, M)]);
                    }
                }
            }
        }
        return dp[0][1];
    }
}
