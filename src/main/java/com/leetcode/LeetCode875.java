package com.leetcode;

/**
 * 875. 爱吃香蕉的珂珂
 * 珂珂喜欢吃香蕉。这里有 n 堆香蕉，第 i 堆中有 piles[i] 根香蕉。警卫已经离开了，将在 h 小时后回来。
 * <p>
 * 珂珂可以决定她吃香蕉的速度 k （单位：根/小时）。每个小时，她将会选择一堆香蕉，从中吃掉 k 根。如果这堆香蕉少于 k 根，她将吃掉这堆的所有香蕉，然后这一小时内不会再吃更多的香蕉。
 * <p>
 * 珂珂喜欢慢慢吃，但仍然想在警卫回来前吃掉所有的香蕉。
 * <p>
 * 返回她可以在 h 小时内吃掉所有香蕉的最小速度 k（k 为整数）。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：piles = [3,6,7,11], h = 8
 * 输出：4
 * 示例 2：
 * <p>
 * 输入：piles = [30,11,23,4,20], h = 5
 * 输出：30
 * 示例 3：
 * <p>
 * 输入：piles = [30,11,23,4,20], h = 6
 * 输出：23
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= piles.length <= 104
 * piles.length <= h <= 109
 * 1 <= piles[i] <= 109
 */
public class LeetCode875 {
    public static int minEatingSpeed(int[] piles, int h) {
        int min = 1;
        int max = 0;
        for (int pile : piles) {
            max = Math.max(pile, max);
        }
        int rate = max;
        while (min < max) {
            //以speed速度吃的次数如果比h小，最小速度在[min, speed]之间
            //如果比h大，最小速度在[speed+1, max]之间
            int speed = (max - min) / 2 + min;
            long times = eatTimes(piles, speed);
            if (times <= h) {
                rate = speed;
                max = speed;
            } else {
                min = speed + 1;
            }
        }

        return rate;
    }

    private static long eatTimes(int[] piles, int speed) {
        long times = 0;
        for (int pile : piles) {
            int curTime = (pile + speed - 1) / speed;
            times += curTime;
        }
        return times;
    }


    public static void main(String[] args) {
        int[] piles = {30,11,23,4,20};
        System.out.println(minEatingSpeed(piles, 5));
    }
}
