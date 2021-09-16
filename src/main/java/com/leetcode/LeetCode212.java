package com.leetcode;

import java.util.*;

/**
 * 212. 单词搜索 II
 * 给定一个 m x n 二维字符网格 board 和一个单词（字符串）列表 words，找出所有同时在二维网格和字典中出现的单词。
 * <p>
 * 单词必须按照字母顺序，通过 相邻的单元格 内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母在一个单词中不允许被重复使用。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]], words = ["oath","pea","eat","rain"]
 * 输出：["eat","oath"]
 * 示例 2：
 * <p>
 * <p>
 * 输入：board = [["a","b"],["c","d"]], words = ["abcb"]
 * 输出：[]
 * <p>
 * <p>
 * 提示：
 * <p>
 * m == board.length
 * n == board[i].length
 * 1 <= m, n <= 12
 * board[i][j] 是一个小写英文字母
 * 1 <= words.length <= 3 * 104
 * 1 <= words[i].length <= 10
 * words[i] 由小写英文字母组成
 * words 中的所有字符串互不相同
 */
public class LeetCode212 {

    int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public List<String> findWords(char[][] board, String[] words) {
        Trie trie = new Trie();
        for (String word : words) {
            trie.insert(word);
        }

        Set<String> ans = new HashSet<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs(board, trie, i, j, ans);
            }
        }

        return new ArrayList<>(ans);
    }

    private void dfs(char[][] board, Trie trie, int i, int j, Set<String> ans) {
        if (!trie.children.containsKey(board[i][j])) {
            return;
        }
        char ch = board[i][j];
        trie = trie.children.get(ch);
        if (!"".equals(trie.word)) {
            ans.add(trie.word);
        }

        board[i][j] = '#';
        for (int[] dir : dirs) {
            int i2 = i + dir[0], j2 = j + dir[1];
            if (i2 >= 0 && i2 < board.length && j2 >= 0 && j2 < board[0].length) {
                dfs(board, trie, i2, j2, ans);
            }
        }
        board[i][j] = ch;
    }

    static class Trie {
        String word;
        Map<Character, Trie> children;

        public Trie() {
            this.word = "";
            this.children = new HashMap<>();
        }

        public void insert(String word) {
            Trie cur = this;
            for (char c : word.toCharArray()) {
                if (!cur.children.containsKey(c)) {
                    cur.children.put(c, new Trie());
                }
                cur = cur.children.get(c);
            }
            cur.word = word;
        }
    }
}
