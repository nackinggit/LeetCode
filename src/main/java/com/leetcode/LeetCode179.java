package com.leetcode;

import java.util.Arrays;

public class LeetCode179 {
    public String largestNumber(int[] nums) {
        Integer[] inums = Arrays.stream(nums).boxed().toArray(Integer[]::new);
        Arrays.sort(inums, (o1, o2) -> {
            String s1 = o1.toString();
            String s2 = o2.toString();
            return (s2 + s1).compareTo(s1 + s2);
        });

        System.out.println(Arrays.toString(inums));
        if (inums[0] == 0) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        for (Integer i : inums) {
            sb.append(i);
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(new LeetCode179().largestNumber(new int[]{3,30,34,5,9}));
    }
}
