
// https://www.codingninjas.com/codestudio/problems/complete-string_2687860?utm_source=youtube&utm_medium=affiliate&utm_campaign=striver_tries_videos&leftPanelTab=0
import java.util.*;
import java.io.*;

class Solution {
    public static class Node {
        boolean eow; // end of word
        Node[] children = new Node[26]; // 26 : one for each letter
    }

    static Node root;

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

    public static boolean Check(String word) {
        Node node = root;
        for (char ch : word.toCharArray()) {
            if (node.children[ch - 'a'].eow == false) {
                return false;
            }
            node = node.children[ch - 'a'];
        }
        return true;
    }

    public static String completeString(int n, String[] a) {
        root = new Node();
        for (int i = 0; i < n; i++) {
            String ele = a[i];
            insert(ele);
        }
        String ans = "";
        for (int i = 0; i < n; i++) {
            String ele = a[i];
            if (Check(ele) == true) {
                if (ans.length() < ele.length()) {
                    ans = ele;
                } else if (ans.length() == ele.length()) {
                    if (ans.compareTo(ele) > 0) {
                        ans = ele;
                    }
                }
            }
        }
        return ans.length() == 0 ? "None" : ans;
    }
}