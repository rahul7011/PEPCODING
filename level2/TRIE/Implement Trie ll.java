// https://www.codingninjas.com/codestudio/problems/implement-trie_1387095?utm_source=youtube&utm_medium=affiliate&utm_campaign=striver_tries_videos&leftPanelTab=0

import java.util.* ;
import java.io.*; 
public class Trie {
    public static class Node{
        int ew;
        int prefixCount;
        Node[] children=new Node[26];
    }
    Node root;
    public Trie() {
        root=new Node();
    }

    public void insert(String word) {
        Node node=root;
        for(char ch:word.toCharArray())
        {
            if(node.children[ch-'a']==null)
            {
                node.children[ch-'a']=new Node();
            }
            node=node.children[ch-'a'];
            node.prefixCount++;
        }
        node.ew++;
    }

    public int countWordsEqualTo(String word) {
        Node node=root;
        for(char ch:word.toCharArray())
        {
            if(node.children[ch-'a']==null)
            {
                return 0;
            }
            node=node.children[ch-'a'];
        }
        return node.ew;
    }

    public int countWordsStartingWith(String word) {
        Node node=root;
        for(char ch:word.toCharArray())
        {
            if(node.children[ch-'a']==null)
            {
                return 0;
            }
            node=node.children[ch-'a'];
        }
        return node.prefixCount;
    }

    public void erase(String word) {
        Node node=root;
        for(char ch:word.toCharArray())
        {
            if(node.children[ch-'a'].prefixCount==1)
            {
                node.children[ch-'a']=null;
                break;
            }
            node=node.children[ch-'a'];
            node.prefixCount--;
        }
        node.ew--;
    }

}
