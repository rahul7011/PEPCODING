class Solution {
    public static class Node {
        boolean eow; // end of word
        Node[] children = new Node[26]; // 26 : one for each letter
    }

    public static Node root;

    public static void insert(String word) {
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

    public static boolean search(String word) {
        Node node = root; // dummy;
        for (char ch : word.toCharArray()) {
            if (node.children[ch - 'a'] == null) {
                return false;
            }
            node = node.children[ch - 'a'];
        }
        return node.eow == true;
    }

    private static boolean solve(String word,int idx,int cuts)
    {
        if(idx==word.length() && cuts > 1)
        {
            return true;
        }
        boolean check=false;
        String s="";
        for(int i=idx;i<word.length();i++)
        {
            char ch=word.charAt(i);
            s+=ch;
            if(search(s)==true)
            {
                check=check || solve(word,i+1,cuts+1);
            }
            if(check==true)
            {
                return check;
            }
        }
        return check;
    }
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        root=new Node();
        for(String word:words)
        {
            insert(word);
        }
        List<String>list=new ArrayList<>();
        for(String word:words)
        {
            if(solve(word,0,0)==true)
            {
                list.add(word);
            }
        }
        return list;
    }
}
