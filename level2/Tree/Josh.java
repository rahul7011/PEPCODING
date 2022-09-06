import java.util.LinkedList;

public class Josh {
    public static class Node {
        int data;
        Node left, right;

        Node(int d) {
            data = d;
            left = right = null;
        }
    }

    // https://practice.geeksforgeeks.org/problems/flatten-binary-tree-to-linked-list/1
    class Solution {
        public static Node flat(Node root) {
            if (root == null) {
                return null;
            }
            Node left = flat(root.left);
            Node right = flat(root.right);
            if (left == null && right != null) {
                return right;
            } else if (left != null && right == null) {
                Node temp = root.left;
                root.left = null;
                root.right = temp;
                return left;
            } else if (left != null && right != null) {
                // System.out.println(left==null?"null":left.data);
                // System.out.println(right==null?"null":right.data);
                Node temp = root.left;
                root.left = null;
                Node temp2 = root.right;
                root.right = temp;
                left.right = temp2;
                return right;
            } else {
                return root;
            }

        }

        public static void flatten(Node root) {
            flat(root);
            return;
        }
    }
    
    //sliding window
    private static int minFlips(String s,int k)
    {
        int ans=0;
        int sum=0;
        LinkedList<Integer>list=new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            int val=s.charAt(i)-'0';
            if(i<k)
            {
                sum+=val;
                list.addLast(val);
                continue;
            }
            if(sum==0)
            {
                ans++;
            }
            sum-=list.removeFirst();
            list.addLast(val);
            sum+=val;
        }
        if(sum==0)
        {
            ans++;
        }
        return ans;
    }
    public static void main(String[] args) {
        System.out.println(minFlips("0011", 2));
        System.out.println(minFlips("101", 2));
        System.out.println(minFlips("001010000", 3));
        System.out.println(minFlips("001010000", 9));
        // System.out.println(minFlips("0011", 2));
        // System.out.println(minFlips("0011", 2));
    }

}
