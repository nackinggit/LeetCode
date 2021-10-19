package com.leetcode;

/**
 * 请你设计一个数据结构，支持 添加新单词 和 查找字符串是否与任何先前添加的字符串匹配 。
 * <p>
 * 实现词典类 WordDictionary ：
 * <p>
 * WordDictionary() 初始化词典对象
 * void addWord(word) 将 word 添加到数据结构中，之后可以对它进行匹配
 * bool search(word) 如果数据结构中存在字符串与 word 匹配，则返回 true ；否则，返回  false 。word 中可能包含一些 '.' ，每个 . 都可以表示任何一个字母。
 *  
 * <p>
 * 示例：
 * <p>
 * 输入：
 * ["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
 * [[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
 * 输出：
 * [null,null,null,null,false,true,true,true]
 * <p>
 * 解释：
 * WordDictionary wordDictionary = new WordDictionary();
 * wordDictionary.addWord("bad");
 * wordDictionary.addWord("dad");
 * wordDictionary.addWord("mad");
 * wordDictionary.search("pad"); // return False
 * wordDictionary.search("bad"); // return True
 * wordDictionary.search(".ad"); // return True
 * wordDictionary.search("b.."); // return True
 * <p>
 */
public class LeetCode211 {
    public static class WordDictionary {
        private Trie root;

        public WordDictionary() {
            this.root = new Trie();
        }

        public void addWord(String word) {
            this.root.insert(word);
        }

        public boolean search(String word) {
            return dfs(root, 0, word);
        }

        private boolean dfs(Trie root, int index, String word) {
            if (index == word.length()) {
                return root.isEnd();
            }

            char c = word.charAt(index);
            if (Character.isLetter(c)) {
                int childIdx = c - 'a';
                Trie child = root.getChildren()[childIdx];
                return child != null && dfs(child, index + 1, word);
            } else {
                for (int i = 0; i < 26; i++) {
                    Trie child = root.getChildren()[i];
                    if (child != null && dfs(child, index + 1, word)) {
                        return true;
                    }
                }
            }
            return false;
        }
    }


    public static class Trie {
        private Trie[] children;
        private boolean isEnd;

        public void insert(String word) {
            Trie node = this;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                int index = c - 'a';
                if (node.children[index] == null) {
                    node.children[index] = new Trie();
                }
                node = node.children[index];
            }

            node.isEnd = true;
        }

        public Trie[] getChildren() {
            return children;
        }

        public boolean isEnd() {
            return isEnd;
        }
    }
}
