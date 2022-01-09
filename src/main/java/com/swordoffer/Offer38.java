package com.swordoffer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 剑指 Offer 38. 字符串的排列
 * 输入一个字符串，打印出该字符串中字符的所有排列。
 * <p>
 * <p>
 * <p>
 * 你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。
 * <p>
 * <p>
 * <p>
 * 示例:
 * <p>
 * 输入：s = "abc"
 * 输出：["abc","acb","bac","bca","cab","cba"]
 * <p>
 * <p>
 * 限制：
 * <p>
 * 1 <= s 的长度 <= 8
 */
public class Offer38 {

    public String[] permutation(String s) {
        int n = s.length();
        List<String> res = new ArrayList<>();
        boolean[] visited = new boolean[s.length()];
        char[] arr = s.toCharArray();
        Arrays.sort(arr);
        StringBuilder stringBuilder = new StringBuilder();
        backtrace(arr, 0, n, stringBuilder, res, visited);
        return res.toArray(new String[0]);
    }

    private void backtrace(char[] arr, int index, int length,
                           StringBuilder stringBuilder, List<String> res, boolean[] visited) {
        if (index == length) {
            res.add(stringBuilder.toString());
            return;
        }

        for (int i = 0; i < length; i++) {
            if (visited[i] || (i > 0 && !visited[i - 1] && arr[i - 1] == arr[i])) {
                continue;
            }

            visited[i] = true;
            stringBuilder.append(arr[i]);
            backtrace(arr, index + 1, length, stringBuilder, res, visited);
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            visited[i] = false;
        }
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Offer38().permutation("abc")));
    }
}
