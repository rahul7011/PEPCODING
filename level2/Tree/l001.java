import java.util.ArrayList;
import java.util.List;

public class l001 {
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

    private static long maximum(TreeNode root) {
        return root == null ? Integer.MIN_VALUE
                : (Math.max(maximum(root.left), Math.max(maximum(root.right), root.val)));
    }

    private static long minimum(TreeNode root) {
        return root == null ? Integer.MAX_VALUE
                : (Math.min(minimum(root.left), Math.min(minimum(root.right), root.val)));
    }

    // From now onwards we will be using this as an refrence syntax for other
    // problems
    private static boolean find(TreeNode root, int data) {
        if (root == null) {
            return false;
        }
        if (root.val == data) {
            return true;
        }
        return find(root.left, data) || find(root.right, data);
    }

    // old approach
    private static ArrayList<TreeNode> nodeToRootPath(TreeNode root, int data) {
        if (root == null) {
            return new ArrayList<>();
        }
        if (root.val == data) {
            ArrayList<TreeNode> temp = new ArrayList<>();
            temp.add(root);
            return temp;
        }
        ArrayList<TreeNode> left = nodeToRootPath(root.left, data);
        if (left.size() > 0) {
            left.add(root);
            return left;
        }
        ArrayList<TreeNode> right = nodeToRootPath(root.right, data);
        if (right.size() > 0) {
            right.add(root);
            return right;
        }
        return new ArrayList<>();
    }

    // solve below these:
    // https://www.pepcoding.com/resources/data-structures-and-algorithms-in-java-levelup/trees/node-to-root-path-binary-tree/ojquestion
    public static boolean nodeToRootPath(TreeNode root, int data, ArrayList<TreeNode> ans) {
        if (root == null) {
            return false;
        }
        if (root.val == data) {
            ans.add(root);
            return true;
        }
        boolean res = nodeToRootPath(root.left, data, ans) || nodeToRootPath(root.right, data, ans);
        if (res == true) {
            ans.add(root);
        }
        return res;
    }

    class Tree {
        private static void Paths(Node root, ArrayList<ArrayList<Integer>> ans, ArrayList<Integer> smallAns) {
            if (root == null) {
                return;
            }
            if (root.left == null && root.right == null) {
                smallAns.add(root.data);
                ans.add(new ArrayList<>(smallAns));
                smallAns.remove(smallAns.size() - 1);
                return;
            }
            smallAns.add(root.data);
            Paths(root.left, ans, smallAns);
            Paths(root.right, ans, smallAns);
            smallAns.remove(smallAns.size() - 1);
        }

        public ArrayList<ArrayList<Integer>> Paths(Node root) {
            ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
            ArrayList<Integer> smallAns = new ArrayList<>();
            Paths(root, ans, smallAns);
            return ans;

        }
    }

    // https://www.pepcoding.com/resources/data-structures-and-algorithms-in-java-levelup/trees/all-single-child-parent-in-binary-tree/ojquestion
    public static void exactlyOneChild(TreeNode root, ArrayList<Integer> ans) {
        if (root == null || (root.left == null && root.right == null)) {
            return;
        }
        if (root.left == null || root.right == null) {
            ans.add(root.val);
        }
        exactlyOneChild(root.left, ans);
        exactlyOneChild(root.right, ans);
    }

    // https://www.pepcoding.com/resources/data-structures-and-algorithms-in-java-levelup/trees/count-all-single-child-parent-in-binary-tree/ojquestion
    public static int countExactlyOneChild(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            return 0;
        }
        int count = countExactlyOneChild(root.left);
        count += countExactlyOneChild(root.right);
        if (root.left == null || root.right == null) {
            count += 1;
        }
        return count;
    }

    // 863. All Nodes Distance K in Binary Tree
    class Solution {
        private boolean nodeToRootPath(TreeNode root, TreeNode node, ArrayList<TreeNode> path) {
            if (root == null) {
                return false;
            }
            if (root == node) {
                path.add(root);
                return true;
            }
            boolean res = nodeToRootPath(root.left, node, path) || nodeToRootPath(root.right, node, path);
            if (res == true) {
                path.add(root);
            }
            return res;
        }

        private void distanceK(TreeNode root, TreeNode blocked, int k, ArrayList<Integer> ans) {
            if (root == null || k == 0) {
                if (root != null && k == 0)
                    ans.add(root.val);
                return;
            }
            if (root.left != blocked) {
                distanceK(root.left, blocked, k - 1, ans);
            }
            if (root.right != blocked) {
                distanceK(root.right, blocked, k - 1, ans);
            }

        }

        public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
            ArrayList<TreeNode> path = new ArrayList<>();
            nodeToRootPath(root, target, path);
            int i = 0;
            ArrayList<Integer> ans = new ArrayList<>();
            while (i < path.size()) {
                TreeNode node = path.get(i);
                TreeNode blocked = i == 0 ? null : path.get(i - 1);
                distanceK(node, blocked, k - i, ans);
                i++;
            }
            return ans;
        }
    }

    // 863. All Nodes Distance K in Binary Tree
    // Space optimised
    class Solution1 {
        private int nodeToRootPath(TreeNode root, TreeNode node, TreeNode blocked, int k, ArrayList<Integer> ans) {
            if (root == null) {
                return -1;
            }
            if (root == node) {
                distanceK(root, blocked, k, ans);
                return 1;
            }
            int left = nodeToRootPath(root.left, node, blocked, k, ans);
            if (left > 0) {
                distanceK(root, root.left, k - left, ans);
                return left + 1;
            }
            int right = nodeToRootPath(root.right, node, blocked, k, ans);
            if (right > 0) {
                distanceK(root, root.right, k - right, ans);
                return right + 1;
            }
            return -1;
        }

        private void distanceK(TreeNode root, TreeNode blocked, int k, ArrayList<Integer> ans) {
            if (root == null || k <= 0) {
                if (root != null && k == 0)
                    ans.add(root.val);
                return;
            }
            if (root.left != blocked) {
                distanceK(root.left, blocked, k - 1, ans);
            }
            if (root.right != blocked) {
                distanceK(root.right, blocked, k - 1, ans);
            }

        }

        public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
            ArrayList<Integer> ans = new ArrayList<>();
            nodeToRootPath(root, target, null, k, ans);
            return ans;
        }
    }

    public static void main(String[] args) {

    }
}