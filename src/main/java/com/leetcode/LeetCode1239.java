package com.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 1239. 串联字符串的最大长度
 * 给定一个字符串数组 arr，字符串 s 是将 arr 某一子序列字符串连接所得的字符串，如果 s 中的每一个字符都只出现过一次，那么它就是一个可行解。
 * <p>
 * 请返回所有可行解 s 中最长长度。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：arr = ["un","iq","ue"]
 * 输出：4
 * 解释：所有可能的串联组合是 "","un","iq","ue","uniq" 和 "ique"，最大长度为 4。
 * 示例 2：
 * <p>
 * 输入：arr = ["cha","r","act","ers"]
 * 输出：6
 * 解释：可能的解答有 "chaers" 和 "acters"。
 * 示例 3：
 * <p>
 * 输入：arr = ["abcdefghijklmnopqrstuvwxyz"]
 * 输出：26
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= arr.length <= 16
 * 1 <= arr[i].length <= 26
 * arr[i] 中只含有小写英文字母
 */
public class LeetCode1239 {
    int ans = 0;

    public int maxLength(List<String> arr) {
        List<Integer> masks = new ArrayList<>();
        for (String str : arr) {
            int mask = 0;
            for (char c : str.toCharArray()) {
                int i = c - 'a'; //arr 只有小写字母
                if (((mask >> i) & 1) != 0) {
                    mask = 0;
                    break;
                }
                mask |= 1 << i;
            }
            if (mask > 0) {
                masks.add(mask);
            }
        }

        backtrace(masks, 0, 0);
        return ans;
    }

    private void backtrace(List<Integer> masks, int pos, int mask) {
        if (masks.size() == pos) {
            ans = Math.max(ans, Integer.bitCount(mask));
            return;
        }

        if ((mask & masks.get(pos)) == 0) {
            backtrace(masks, pos + 1, mask | masks.get(pos));
        }

        backtrace(masks, pos + 1, mask);
    }
}
