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
    
}
