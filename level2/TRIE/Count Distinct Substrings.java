
import java.util.ArrayList;
// https://www.codingninjas.com/codestudio/problems/count-distinct-substrings_985292?utm_source=youtube&utm_medium=affiliate&utm_campaign=striver_tries_videos&leftPanelTab=0
public class Solution 
{
	public static class Node{
		Node[] children=new Node[26];
	}
	public static Node root;
	public static int Insert(String word)
	{
		Node node=root;
		int count=0;
		for(char ch:word.toCharArray())
		{
			if(node.children[ch-'a']==null)
			{
				count++;
				node.children[ch-'a']=new Node();
			}
			node=node.children[ch-'a'];
		}
		return count;
	}
	public static int countDistinctSubstrings(String s) 
	{
		root=new Node();
		int count=0;
		for (int i = 0; i < s.length(); i++) {
			String temp="";
			for(int j=i;j<s.length();j++)
			{
				char ch=s.charAt(j);
				temp+=ch;
				count+=Insert(temp);
			}
		}
		return count+1;	// +1 for empty string
	}
}