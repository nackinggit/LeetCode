package com.leetcode;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 1405. 最长快乐字符串
 * 如果字符串中不含有任何 'aaa'，'bbb' 或 'ccc' 这样的字符串作为子串，那么该字符串就是一个「快乐字符串」。
 * <p>
 * 给你三个整数 a，b ，c，请你返回 任意一个 满足下列全部条件的字符串 s：
 * <p>
 * s 是一个尽可能长的快乐字符串。
 * s 中 最多 有a 个字母 'a'、b 个字母 'b'、c 个字母 'c' 。
 * s 中只含有 'a'、'b' 、'c' 三种字母。
 * 如果不存在这样的字符串 s ，请返回一个空字符串 ""。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：a = 1, b = 1, c = 7
 * 输出："ccaccbcc"
 * 解释："ccbccacc" 也是一种正确答案。
 * 示例 2：
 * <p>
 * 输入：a = 2, b = 2, c = 1
 * 输出："aabbc"
 * 示例 3：
 * <p>
 * 输入：a = 7, b = 1, c = 0
 * 输出："aabaa"
 * 解释：这是该测试用例的唯一正确答案。
 */
public class LeetCode1405 {
    public String longestDiverseString(int a, int b, int c) {
        Pair[] pairs = {new Pair(a, 'a'), new Pair(b, 'b'), new Pair(c, 'c')};
        StringBuilder sb = new StringBuilder();

        while (true) {
            Arrays.sort(pairs, (o1, o2) -> o2.freq - o1.freq);
            boolean hasNext = false;
            for (Pair pair : pairs) {
                if (pair.freq <= 0) {
                    break;
                }

                int m = sb.length();
                if (m >= 2 && sb.charAt(m - 2) == pair.c && sb.charAt(m - 1) == pair.c) {
                    continue;
                }

                hasNext = true;
                sb.append(pair.c);
                pair.freq--;
                break;
            }

            if(!hasNext) {
                break;
            }
        }

        return sb.toString();
    }

    public static class Pair {
        int freq;
        char c;

        public Pair(int freq, char c) {
            this.freq = freq;
            this.c = c;
        }
    }
}
