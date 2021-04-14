package com.leetcode;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class LeetCode208 {
    public static class Trie2 {
        Trie2[] children;
        boolean isEnd;

        public Trie2() {
            children = new Trie2[26];
            isEnd = false;
        }

        public void insert(String word) {
            char[] chars = word.toCharArray();
            Trie2 node = this;
            for (char c : chars) {
                Trie2 child = node.children[c - 'a'];
                if (child == null) {
                    child = new Trie2();
                    node.children[c - 'a'] = child;
                }
                node = child;
            }
            node.isEnd = true;
            System.out.format("insert(%s) done\n", word);
        }

        public boolean search(String word) {
            Trie2 node = searchPrefix(word);
            boolean res = node != null && node.isEnd;
            System.out.format("search(%s) = %s\n", word, res);
            return res;
        }

        public boolean startsWith(String prefix) {
            Trie2 node = searchPrefix(prefix);
            boolean res = node != null;
            System.out.format("startsWith(%s) = %s\n", prefix, res);
            return res;
        }

        private Trie2 searchPrefix(String prefix) {
            Trie2 cur = this;
            for (int i = 0; i < prefix.length(); i++) {
                char ch = prefix.charAt(i);
                int index = ch - 'a';
                if (cur.children[index] == null) {
                    return null;
                }
                cur = cur.children[index];
            }
            return cur;
        }
    }

    public static class Trie {
        Map<Character, TrieNode> root;

        /**
         * Initialize your data structure here.
         */
        public Trie() {
            root = new HashMap<>();
        }

        /**
         * Inserts a word into the trie.
         */
        public void insert(String word) {
            char[] chars = word.toCharArray();
            Map<Character, TrieNode> cur = root;
            for (int i = 0; i < chars.length; i++) {
                char c = chars[i];
                TrieNode tmpRoot = cur.get(c);
                if (tmpRoot == null) {
                    tmpRoot = new TrieNode();
                    tmpRoot.value = c;
                }

                tmpRoot.count += 1;
                if (i == chars.length - 1) {
                    tmpRoot.isLast = true;
                }
                cur.put(c, tmpRoot);
                cur = tmpRoot.next;
            }
            System.out.format("insert(%s) done\n", word);
        }

        /**
         * Returns if the word is in the trie.
         */
        public boolean search(String word) {
            char[] chars = word.toCharArray();
            Map<Character, TrieNode> cur = root;
            for (int i = 0; i < chars.length; i++) {
                char c = chars[i];
                TrieNode node = cur.get(c);
                if (node != null && node.isLast && i == chars.length - 1) {
                    System.out.format("search(%s) = true\n", word);
                    return true;
                } else if (node == null) {
                    System.out.format("search(%s) = false\n", word);
                    return false;
                } else {
                    cur = node.next;
                }
            }
            System.out.format("search(%s) = false\n", word);
            return false;
        }

        /**
         * Returns if there is any word in the trie that starts with the given prefix.
         */
        public boolean startsWith(String prefix) {
            char[] chars = prefix.toCharArray();
            Map<Character, TrieNode> cur = root;
            for (char c : chars) {
                TrieNode node = cur.get(c);
                if (node == null) {
                    System.out.format("startsWith(%s) = false\n", prefix);
                    return false;
                } else {
                    cur = node.next;
                }
            }
            System.out.format("startsWith(%s) = true\n", prefix);
            return true;
        }
    }

    public static class TrieNode {
        char value;
        int count;
        boolean isLast;
        Map<Character, TrieNode> next = new HashMap<>();
    }

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Trie trie = new Trie();
        Trie2 trie2 = new Trie2();
        String[] operates = {"insert", "insert", "insert", "insert", "insert", "insert", "search", "search", "search", "search", "search", "search", "search", "search", "search", "startsWith", "startsWith", "startsWith", "startsWith", "startsWith", "startsWith", "startsWith", "startsWith", "startsWith"};
        String[] params = {"app", "apple", "beer", "add", "jam", "rental", "apps", "app", "ad", "applepie", "rest", "jan", "rent", "beer", "jam", "apps", "app", "ad", "applepie", "rest", "jan", "rent", "beer", "jam"};
        for (int i = 0; i < operates.length; i++) {
            String op = operates[i];
            String param = params[i];
            trie.getClass().getMethod(op, String.class).invoke(trie, param);
            trie2.getClass().getMethod(op, String.class).invoke(trie2, param);
        }
    }
}
