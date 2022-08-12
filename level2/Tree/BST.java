import java.util.ArrayList;
import java.util.LinkedList;

public class BST {
    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public static class Node {
        int data;
        Node left, right;

        Node(int d) {
            data = d;
            left = right = null;
        }
    }

    private static int size(TreeNode root) {
        return root == null ? 0 : (size(root.left) + size(root.right) + 1);
    }

    private static int height(TreeNode root) {
        return root == null ? -1 : (Math.max(height(root.left), height(root.right)) + 1);
    }

    private static int maximum(TreeNode root) {
        TreeNode curr = root;
        while (curr.right != null) {
            curr = curr.right;
        }
        return curr.val;
    }

    private static int minimum(TreeNode root) {
        TreeNode curr = root;
        while (curr.left != null) {
            curr = curr.left;
        }
        return curr.val;
    }

    private static boolean findData(TreeNode root, int data) {
        TreeNode curr = root;
        while (curr != null) {
            if (curr.val == data) {
                return true;
            } else if (curr.val > data) {
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }
        return false;
    }

    public static boolean nodeToRootPath(TreeNode root, int data, ArrayList<TreeNode> ans) {
        TreeNode curr = root;
        while (curr != null) {
            ans.add(curr);
            if (curr.val == data) {
                return true;
            } else if (curr.val > data) {
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }
        ans.clear();
        return false;
    }

    // 235. Lowest Common Ancestor of a Binary Search Tree
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode lca = null;
        TreeNode curr = root;
        while (curr != null) {
            boolean left = (curr.val > p.val) || (curr.val > q.val);
            boolean right = (curr.val < q.val) || (curr.val < p.val);
            boolean self = (curr.val == p.val) || (curr.val == q.val);
            if ((left && right) || (left && self) || (right && self)) {
                lca = curr;
                break;
            }
            if (left == true) {
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }
        // condition:
        // where we determining that both the nodes are present inside the tree
        return (lca != null && findData(lca, p.val) && findData(lca, q.val)) ? lca : null;
    }

    public static void main(String[] args) {
        // TreeNode root = new TreeNode(6);
        // root.left = new TreeNode(2);
        // root.right = new TreeNode(8);
        // root.right.left = new TreeNode(7);
        // root.right.right = new TreeNode(9);
        // root.left.left = new TreeNode(0);
        // root.left.right = new TreeNode(4);
        // TreeNode right = root.left.right;
        // right.left = new TreeNode(3);
        // right.right = new TreeNode(5);

        // ArrayList<TreeNode> ans = new ArrayList<>();
        // // nodeToRootPath(root, 11, ans);
        // for (TreeNode x : ans)
        // System.out.println(x.val);
    }
}