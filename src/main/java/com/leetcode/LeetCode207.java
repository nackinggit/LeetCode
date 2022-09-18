package com.leetcode;

import java.util.*;

/**
 * 207. 课程表
 * 你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。
 * <p>
 * 在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，其中 prerequisites[i] = [ai, bi] ，表示如果要学习课程 ai 则 必须 先学习课程  bi 。
 * <p>
 * 例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
 * 请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：numCourses = 2, prerequisites = [[1,0]]
 * 输出：true
 * 解释：总共有 2 门课程。学习课程 1 之前，你需要完成课程 0 。这是可能的。
 * 示例 2：
 * <p>
 * 输入：numCourses = 2, prerequisites = [[1,0],[0,1]]
 * 输出：false
 * 解释：总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0 ；并且学习课程 0 之前，你还应先完成课程 1 。这是不可能的。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= numCourses <= 105
 * 0 <= prerequisites.length <= 5000
 * prerequisites[i].length == 2
 * 0 <= ai, bi < numCourses
 * prerequisites[i] 中的所有课程对 互不相同
 */
public class LeetCode207 {

    public static boolean canFinish2(int numCourses, int[][] prerequisites) {
        List<List<Integer>> edges = new ArrayList<>();
        int[] inDegrees = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            edges.add(new ArrayList<>());
        }

        for (int[] pre : prerequisites) {
            edges.get(pre[1]).add(pre[0]);
            inDegrees[pre[0]] += 1;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegrees[i] == 0) {
                queue.offer(i);
            }
        }
        int visited = 0;

        while (!queue.isEmpty()) {
            visited += 1;
            int i = queue.poll();
            for (int out : edges.get(i)) {
                inDegrees[out] -= 1;
                if (inDegrees[out] == 0) {
                    queue.offer(out);
                }
            }
        }
        return visited == numCourses;
    }

    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, Set<Integer>> requires = new HashMap<>();
        for (int[] pre : prerequisites) {
            Set<Integer> set = requires.getOrDefault(pre[0], new HashSet<>());
            set.add(pre[1]);
            requires.put(pre[0], set);
        }

        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < numCourses; i++) {
            if (!requires.containsKey(i)) {
                deque.add(i);
            }
        }

        while (!deque.isEmpty()) {
            int i = deque.poll();
            for (int k : requires.keySet()) {
                Set<Integer> set = requires.get(k);
                if (set != null && !set.isEmpty()) {
                    set.remove(i);
                    if (set.isEmpty()) {
                        deque.add(k);
                    }
                }
            }
        }

        for (Set<Integer> set : requires.values()) {
            if (!set.isEmpty()) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        int[][] prerequisites = {{1, 0}};
        System.out.println(canFinish(2, prerequisites));
    }
}
