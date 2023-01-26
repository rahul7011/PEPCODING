
// https://www.codingninjas.com/codestudio/problems/maximum-xor_973113?utm_source=youtube&utm_medium=affiliate&utm_campaign=striver_tries_videos&leftPanelTab=0
import java.util.ArrayList;

public class Solution {
	public static class Node {
		Node[] children = new Node[2];
	}

	static Node root;

	public static void insert(int num) {
		Node node = root;
		for (int i = 31; i >= 0; i--) {
			int bit = (num >> i) & 1;
			if (node.children[bit] == null) {
				node.children[bit] = new Node();
			}
			node = node.children[bit];
		}
	}

	public static int getMax(int num) {
		Node node = root;
		int ans = 0;
		for (int i = 31; i >= 0; i--) {
			int bit = (num >> i) & 1;
			if (node.children[1 - bit] != null) {
				ans = (ans | (1 << i));
				node = node.children[1 - bit];
			} else {
				node = node.children[bit];
			}
		}
		return ans;
	}

	public static int maxXOR(int n, int m, ArrayList<Integer> arr1, ArrayList<Integer> arr2) {
		root = new Node();
		for (int i = 0; i < n; i++) {
			insert(arr1.get(i));
		}
		int max = 0;
		for (int i = 0; i < m; i++) {
			max = Math.max(max, getMax(arr2.get(i)));
		}
		return max;
	}
}
