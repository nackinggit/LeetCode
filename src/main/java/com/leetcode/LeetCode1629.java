package com.leetcode;

public class LeetCode1629 {
    public char slowestKey(int[] releaseTimes, String keysPressed) {
        int n = releaseTimes.length;
        char ans = keysPressed.charAt(0);
        int maxTime = releaseTimes[0];
        for (int i = 1; i < n; i++) {
            char key = keysPressed.charAt(i);
            int time = releaseTimes[i] - releaseTimes[i - 1];
            if (time > maxTime || (time == maxTime && key > ans)) {
                ans = key;
                maxTime = time;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        LeetCode1629 leetCode1629 = new LeetCode1629();
        System.out.println(leetCode1629.slowestKey(new int[]{4,3,21,5}, "abdc"));
    }
}
