public class TrieJava {

    // 208. Implement Trie (Prefix Tree)
    class Trie {

        public static class Node {
            boolean eow; // eow:end of word
            Node[] children = new Node[26];
        }

        Node root;

        public Trie() {
            root = new Node();
        }

        public void insert(String word) {
            Node node = root;
            for (char ch : word.toCharArray()) {
                if (node.children[ch - 'a'] == null) {
                    node.children[ch - 'a'] = new Node();
                }
                node = node.children[ch - 'a'];
            }
            node.eow = true;
        }

        public boolean search(String word) {
            Node node = root;
            for (char ch : word.toCharArray()) {
                if (node.children[ch - 'a'] == null) {
                    return false;
                }
                node = node.children[ch - 'a'];
            }
            return node.eow == true;
        }

        public boolean startsWith(String prefix) {
            Node node = root;
            for (char ch : prefix.toCharArray()) {
                if (node.children[ch - 'a'] == null) {
                    return false;
                }
                node = node.children[ch - 'a'];
            }
            return true;
        }
    }

    // 211. Design Add and Search Words Data Structure
    class WordDictionary {
        public static class Node {
            boolean eow; // eow:end of word
            Node[] children = new Node[26];
        }

        Node root;

        public WordDictionary() {
            root = new Node();
        }

        public void addWord(String word) {
            Node node = root;
            for (char ch : word.toCharArray()) {
                if (node.children[ch - 'a'] == null) {
                    node.children[ch - 'a'] = new Node();
                }
                node = node.children[ch - 'a'];
            }
            node.eow = true;
        }

        private static boolean helper(Node node, String word, int idx) {
            if (idx == word.length()) {
                return node.eow;
            }
            char ch = word.charAt(idx);
            boolean check = false;
            if (ch == '.') {
                for (Node nd : node.children) {
                    if (nd != null) {
                        check = check || helper(nd, word, idx + 1);
                        if (check == true) {
                            return true;
                        }
                    }

                }
            } else if (node.children[ch - 'a'] != null) {
                check = check || helper(node.children[ch - 'a'], word, idx + 1);
            }
            return check;
        }

        public boolean search(String word) {
            Node node = root;
            return helper(node, word, 0);
        }
    }

    // 677. Map Sum Pairs
    class MapSum {
        public static class Node {
            int val;
            Node[] children = new Node[26];
        }

        Node root;

        public MapSum() {
            root = new Node();
        }

        public void insert(String word, int val) {
            Node node = root;
            for (char ch : word.toCharArray()) {
                if (node.children[ch - 'a'] == null) {
                    node.children[ch - 'a'] = new Node();
                }
                node = node.children[ch - 'a'];
            }
            node.val = val;
        }

        private static int helper(Node node) {
            if (node == null) {
                return 0;
            }
            int count = 0;
            for (Node nd : node.children) {
                count += helper(nd);
            }
            count += node.val;
            return count;
        }

        public int sum(String prefix) {
            Node node = root;
            for (char ch : prefix.toCharArray()) {
                if (node.children[ch - 'a'] == null) {
                    return 0;
                }
                node = node.children[ch - 'a'];
            }
            return helper(node);
        }
    }
}
