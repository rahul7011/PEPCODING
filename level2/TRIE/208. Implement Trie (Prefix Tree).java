class Trie {
    public static class Node {
        boolean eow; // end of word
        Node[] children = new Node[26]; // 26 : one for each letter
    }

    Node root;

    public Trie() {
        root = new Node(); // default would be false,null
    }

    public void insert(String word) {
        Node node = root; // dummy node
        for (char ch : word.toCharArray()) {
            if (node.children[ch - 'a'] == null) {
                node.children[ch - 'a'] = new Node(); // assigning a new node to that letter if not present
            }
            node = node.children[ch - 'a'];
        }
        // main thing:mark eow to true after your insertion
        node.eow = true;
    }

    public boolean search(String word) {
        Node node = root; // dummy;
        for (char ch : word.toCharArray()) {
            if (node.children[ch - 'a'] == null) {
                return false;
            }
            node = node.children[ch - 'a'];
        }
        return node.eow == true;
    }

    public boolean startsWith(String prefix) {
        Node node = root; // dummy;
        for (char ch : prefix.toCharArray()) {
            if (node.children[ch - 'a'] == null) {
                return false;
            }
            node = node.children[ch - 'a'];
        }
        return true;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */