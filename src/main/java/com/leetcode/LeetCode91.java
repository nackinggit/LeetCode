package com.leetcode;

public class LeetCode91 {
    public int numDecodings(String s) {
        if (s == null || s.length() <= 0 || s.startsWith("0")) return 0;

        int[][] t = new int[s.length()][2];
        t[0][0] = 1;
        for (int i = 1; i < s.length(); i++) {
            int c = s.charAt(i) - '0';
            int c0 = s.charAt(i - 1) - '0';
            int sum = c0 * 10 + c;

            if (c != 0) {
                t[i][0] = t[i - 1][0] + t[i - 1][1];
            }

            if (c0 != 0 && sum <= 26) {
                if (i < 2) {
                    t[i][1] = 1;
                } else {
                    t[i][1] = t[i - 2][0] + t[i - 2][1];
                }
            }

        }
        return t[s.length() - 1][0] + t[s.length() - 1][1];
    }

    public int numDecodings2(String s) {
        int a = 0, b = 1, c = 0;
        for (int i = 1; i <= s.length(); i++) {
            c = 0;
            if (s.charAt(i - 1) != '0') {
                c += b;
            }

            if (i > 1 && s.charAt(i - 2) != '0' && ((s.charAt(i - 2) - '0') * 10 + (s.charAt(i - 1) - '0') <= 26)) {
                c += a;
            }

            a = b;
            b = c;
        }

        return c;
    }

    public static void main(String[] args) {
        System.out.println(new LeetCode91().numDecodings("2101"));
        System.out.println(new LeetCode91().numDecodings2("2101"));
    }
}
