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

    // Morris Inorder Traversal
    private static TreeNode getRightMostNode(TreeNode node, TreeNode curr) {
        while (node.right != null && node.right != curr) {
            node = node.right;
        }
        return node;
    }

    private static ArrayList<Integer> morrisInorderTraversal(TreeNode root) {
        ArrayList<Integer> ans = new ArrayList<>();
        TreeNode curr = root;
        while (curr != null) {
            TreeNode left = curr.left;
            if (left == null) {
                ans.add(curr.val);
                curr = curr.right;
            } else {
                TreeNode rightMost = getRightMostNode(left, curr);
                if (rightMost.right == null) {
                    // thread creation block
                    rightMost.right = curr; // thread is created
                    curr = curr.left;
                } else {
                    // thread deletion block
                    rightMost.right = null; // thread cut down
                    ans.add(curr.val);
                    curr = curr.right;
                }
            }
        }
        return ans;
    }

    private static ArrayList<Integer> morrisPreorderTraversal(TreeNode root) {
        ArrayList<Integer> ans = new ArrayList<>();
        TreeNode curr = root;
        while (curr != null) {
            TreeNode left = curr.left;
            if (left == null) {
                ans.add(curr.val);
                curr = curr.right;
            } else {
                TreeNode rightMost = getRightMostNode(left, curr);
                if (rightMost.right == null) {
                    // thread creation block
                    rightMost.right = curr; // thread is created
                    ans.add(curr.val);
                    curr = curr.left;
                } else {
                    // thread deletion block
                    rightMost.right = null; // thread cut down
                    curr = curr.right;
                }
            }
        }
        return ans;
    }

    // 98. Validate Binary Search Tree
    class Solution {
        private static TreeNode getRightMost(TreeNode node, TreeNode curr) {
            while (node.right != null && node.right != curr) {
                node = node.right;
            }
            return node;
        }

        private static boolean morrisInorderTraversal(TreeNode root) {
            TreeNode curr = root;
            long prev = -(long) 1e13;
            while (curr != null) {
                TreeNode left = curr.left;
                if (left == null) {

                    if (prev >= curr.val) {
                        return false;
                    }
                    prev = curr.val;

                    curr = curr.right;
                } else {
                    TreeNode rightMost = getRightMost(left, curr);
                    if (rightMost.right == null) {
                        rightMost.right = curr;
                        curr = curr.left;
                    } else {
                        rightMost.right = null;

                        if (prev >= curr.val) {
                            return false;
                        }
                        prev = curr.val;

                        curr = curr.right;
                    }
                }
            }
            return true;
        }

        public boolean isValidBST(TreeNode root) {
            return morrisInorderTraversal(root);
        }
    }

    // Implementing manual stack and solving isValidBST(98) with the help of it
    // 98. Validate Binary Search Tree
    private static void insertAllLeft(TreeNode node, LinkedList<TreeNode> st) {
        while (node != null) {
            st.addFirst(node);
            node = node.left;
        }
    }

    public boolean isValidBST(TreeNode root) {
        LinkedList<TreeNode> st = new LinkedList<>();
        insertAllLeft(root, st);
        Long prev = -(long) 1e13;
        while (st.size() != 0) {
            TreeNode curr = st.removeFirst();

            // Logic
            if (prev >= curr.val) {
                return false;
            }
            prev = (long) curr.val;

            insertAllLeft(curr.right, st);
        }
        return true;
    }

    // 173. Binary Search Tree Iterator
    class BSTIterator {
        // Implemented using manual call stack approach
        private void insertAllLeft(TreeNode node, LinkedList<TreeNode> st) {
            while (node != null) {
                st.addFirst(node);
                node = node.left;
            }
        }

        LinkedList<TreeNode> st;

        public BSTIterator(TreeNode root) {
            st = new LinkedList<>();
            insertAllLeft(root, st);
        }

        public int next() {
            TreeNode pop = st.removeFirst();
            insertAllLeft(pop.right, st);
            return pop.val;
        }

        public boolean hasNext() {
            return st.size() == 0 ? false : true;
        }
    }

    // 173. Binary Search Tree Iterator
    class BSTIterator1 {
        // Implenting using Morris Inorder Traversal
        private static TreeNode getRightMostNode(TreeNode node, TreeNode curr) {
            while (node.right != null && node.right != curr) {
                node = node.right;
            }
            return node;
        }

        ArrayList<Integer> ans;
        TreeNode curr;

        public BSTIterator1(TreeNode root) {
            ans = new ArrayList<>();
            curr = root;
        }

        public int next() {
            while (curr != null) {
                TreeNode left = curr.left;
                if (left == null) {

                    int store = curr.val;

                    curr = curr.right;

                    return store;
                } else {
                    TreeNode rightMost = getRightMostNode(left, curr);
                    if (rightMost.right == null) {
                        // thread creation block
                        rightMost.right = curr; // thread is created
                        curr = curr.left;
                    } else {
                        // thread deletion block
                        rightMost.right = null; // thread cut down
                        int store = curr.val;

                        curr = curr.right;

                        return store;
                    }
                }
            }
            return -1;
        }

        public boolean hasNext() {
            return curr == null ? false : true;
        }
    }

    // 230. Kth Smallest Element in a BST
    class Solution1 {
        // solved using Morris Inorder Traversal
        private static TreeNode getRightMost(TreeNode node, TreeNode curr) {
            while (node.right != null && node.right != curr) {
                node = node.right;
            }
            return node;
        }

        private static int morrisInorderTraversal(TreeNode root, int k) {
            TreeNode curr = root;
            long prev = -(long) 1e13;
            while (curr != null) {
                TreeNode left = curr.left;
                if (left == null) {

                    k--;
                    if (k == 0) {
                        return curr.val;
                    }

                    curr = curr.right;
                } else {
                    TreeNode rightMost = getRightMost(left, curr);
                    if (rightMost.right == null) {
                        rightMost.right = curr;
                        curr = curr.left;
                    } else {
                        rightMost.right = null;

                        k--;
                        if (k == 0) {
                            return curr.val;
                        }

                        curr = curr.right;
                    }
                }
            }
            return -1;
        }

        public int kthSmallest(TreeNode root, int k) {
            return morrisInorderTraversal(root, k);
        }
    }

    // Kth largest element in BST
    // https://practice.geeksforgeeks.org/problems/kth-largest-element-in-bst/1
    class Solution2 {
        private static Node getRightMost(Node node, Node curr) {
            while (node.right != null && node.right != curr) {
                node = node.right;
            }
            return node;
        }

        private static int size(Node root) {
            return root == null ? 0 : (size(root.left) + size(root.right) + 1);
        }

        private static int morrisInorderTraversal(Node root, int k) {
            Node curr = root;
            long prev = -(long) 1e13;
            int ans = -1;
            while (curr != null) {
                Node left = curr.left;
                if (left == null) {

                    k--;
                    if (k == 0) {
                        ans = curr.data;
                    }

                    curr = curr.right;
                } else {
                    Node rightMost = getRightMost(left, curr);
                    if (rightMost.right == null) {
                        rightMost.right = curr;
                        curr = curr.left;
                    } else {
                        rightMost.right = null;

                        k--;
                        if (k == 0) {
                            ans = curr.data;
                        }

                        curr = curr.right;
                    }
                }
            }
            return ans;
        }

        // return the Kth largest element in the given BST rooted at 'root'
        public int kthLargest(Node root, int k) {
            k = size(root) - k + 1;
            return morrisInorderTraversal(root, k);
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(6);
        root.left = new TreeNode(2);
        root.right = new TreeNode(8);
        root.right.left = new TreeNode(7);
        root.right.right = new TreeNode(9);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(4);
        TreeNode right = root.left.right;
        right.left = new TreeNode(3);
        right.right = new TreeNode(5);

        ArrayList<TreeNode> ans = new ArrayList<>();
        // nodeToRootPath(root, 11, ans);
        for (TreeNode x : ans)
            System.out.println(x.val);
        System.out.println(morrisInorderTraversal(root));
        System.out.println(morrisPreorderTraversal(root));
    }
}