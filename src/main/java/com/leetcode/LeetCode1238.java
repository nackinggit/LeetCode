package com.leetcode;

import java.util.ArrayList;
import java.util.List;

public class LeetCode1238 {
    public List<Integer> circularPermutation(int n, int start) {
        List<Integer> ans = new ArrayList<>();
        int size = 1 << n;
        for (int i = 0; i < size; i++) {
            ans.add((i >> 1) ^ i ^ start);
        }
        return ans;
    }

    public List<Integer> grayCode(int n) {
        List<Integer> ans = new ArrayList<>();
        int size = 1 << n;
        for (int i = 0; i < size; i++) {
            ans.add((i >> 1) ^ i);
        }
        return ans;
    }
}