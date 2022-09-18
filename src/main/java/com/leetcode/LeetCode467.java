package com.leetcode;

import java.util.Arrays;

/**
 * 467. 环绕字符串中唯一的子字符串
 * 把字符串 s 看作是 “abcdefghijklmnopqrstuvwxyz” 的无限环绕字符串，所以 s 看起来是这样的：
 * <p>
 * "...zabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcd...." .
 * 现在给定另一个字符串 p 。返回 s 中 唯一 的 p 的 非空子串 的数量 。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: p = "a"
 * 输出: 1
 * 解释: 字符串 s 中只有一个"a"子字符。
 * 示例 2:
 * <p>
 * 输入: p = "cac"
 * 输出: 2
 * 解释: 字符串 s 中的字符串“cac”只有两个子串“a”、“c”。.
 * 示例 3:
 * <p>
 * 输入: p = "zab"
 * 输出: 6
 * 解释: 在字符串 s 中有六个子串“z”、“a”、“b”、“za”、“ab”、“zab”。
 * <p>
 * <p>
 * 提示:
 * <p>
 * 1 <= p.length <= 105
 * p 由小写英文字母构成
 */
public class LeetCode467 {
    /**
     * dp[i] 代表以字符i结尾的最长子串，知道最长子串，
     * 即可知有多少个以i结尾的子串，如dp[0] = 2,
     * 代表最长子串为“za”, 同时也可知道包含子串“za”，"a"，
     * 因此，找到以26个字母结尾的最长子串，其值之和即为所有子串
     * @param p
     * @return
     */
    public int findSubstringInWraproundString(String p) {
        if (p == null || p.length() == 0) return 0;
        int[] dp = new int[26];

        int k = 0;
        for (int i = 0; i < p.length(); i++) {
            if (i > 0 && (p.charAt(i) - p.charAt(i - 1) + 26) % 26 == 1) { //a-z=-25
                k++;
            } else {
                k = 1;
            }
            int idx = p.charAt(i) - 'a';
            dp[idx] = Math.max(k, dp[idx]);
        }
        return Arrays.stream(dp).sum();
    }
}
