import java.util.ArrayList;
import java.util.List;

public class diaSet {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    class Node {
        int data;
        Node left, right;

        Node(int item) {
            data = item;
            left = right = null;
        }
    }

    // 543. Diameter of Binary Tree
    class Solution {
        // {diameter ,height}
        private static int[] diameter(TreeNode root) {
            if (root == null) {
                return new int[] { 0, -1 };
            }
            int[] left = diameter(root.left);
            int[] right = diameter(root.right);
            int[] curr = new int[2];
            // diameter
            curr[0] = Math.max(Math.max(left[0], right[0]), left[1] + right[1] + 2);
            curr[1] = Math.max(left[1], right[1]) + 1;
            return curr;
        }

        public int diameterOfBinaryTree(TreeNode root) {
            return diameter(root)[0];
        }
    }

    // 112. Path Sum
    class Solution1 {
        private static boolean solve(TreeNode root, int target) {
            if (root == null) {
                return false;
            }
            if (root.left == null && root.right == null) {
                return (target - root.val) == 0;
            }
            boolean left = solve(root.left, target - root.val);
            if (left == true) {
                return true;
            }
            boolean right = solve(root.right, target - root.val);
            if (right == true) {
                return true;
            }
            return false;
        }

        public boolean hasPathSum(TreeNode root, int targetSum) {
            return solve(root, targetSum);
        }
    }

    // 113. Path Sum II
    class Solution2 {
        private static void pathSum(TreeNode root, int target, List<Integer> smallAns, List<List<Integer>> ans) {
            if (root == null) {
                return;
            }
            if (root.left == null && root.right == null) {
                if (target - root.val == 0) {
                    smallAns.add(root.val);
                    ans.add(new ArrayList<>(smallAns));
                    smallAns.remove(smallAns.size() - 1);
                }
                return;
            }
            smallAns.add(root.val);
            pathSum(root.left, target - root.val, smallAns, ans);
            pathSum(root.right, target - root.val, smallAns, ans);
            smallAns.remove(smallAns.size() - 1);

        }

        public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
            List<List<Integer>> ans = new ArrayList<>();
            List<Integer> smallAns = new ArrayList<>();
            pathSum(root, targetSum, smallAns, ans);
            return ans;
        }
    }

    // Maximum Path Sum In Between Two Leaves Of Binary Tree
    // https://www.pepcoding.com/resources/data-structures-and-algorithms-in-java-levelup/trees/maximum-path-sum-in-between-two-leaves-of-bt/ojquestion
    class Solution3 {
        // {NodeToLeafMaxPathSum,LeafToLeafMaxPathSum}
        private static int[] solve(TreeNode root) {
            if (root == null) {
                return new int[] { (int) -1e9, (int) -1e9 };
            }
            if (root.left == null && root.right == null) {
                return new int[] { root.val, (int) -1e9 };
            }
            int[] left = solve(root.left);
            int[] right = solve(root.right);
            int[] res = new int[2];
            res[0] = Math.max(left[0], right[0]) + root.val;
            if (root.left != null && root.right != null)
                res[1] = Math.max(Math.max(left[1], right[1]), left[0] + right[0] + root.val);
            else
                res[1] = Math.max(left[0], right[0]);
            return res;
        }

        public static int maxPathSum(TreeNode root) {
            return solve(root)[1];
        }
    }

    // Maximum Path Sum between 2 Leaf Nodes(GFG version: Little incorrect on GFG)
    // https://practice.geeksforgeeks.org/problems/maximum-path-sum/1
    class Solution4 {
        private static int[] solve(Node root) {
            if (root == null) {
                return new int[] { (int) -1e9, (int) -1e9 };
            }
            if (root.left == null && root.right == null) {
                return new int[] { root.data, (int) -1e9 };
            }
            int[] left = solve(root.left);
            int[] right = solve(root.right);
            int[] res = new int[2];

            res[1] = Math.max(left[1], right[1]);
            if (root.left != null && root.right != null)
                res[1] = Math.max(res[1], left[0] + right[0] + root.data);
            res[0] = Math.max(left[0], right[0]) + root.data;
            return res;
        }

        int maxPathSum(Node root) {
            int[] ans = solve(root);
            if (root.left != null && root.right != null) {
                return ans[1];
            } else {
                return Math.max(ans[0], ans[1]);
            }
        }
    }

    // 124. Binary Tree Maximum Path Sum
    class Solution5 {
        // {MaxPathSum,Node}
        private static int[] maxPathSum_(TreeNode root) {
            if (root == null) {
                return new int[] { (int) -1e9, (int) -1e9 };
            }
            if (root.left == null && root.right == null) {
                return new int[] { root.val, root.val };
            }
            int[] left = maxPathSum_(root.left);
            int[] right = maxPathSum_(root.right);
            int[] res = new int[2];
            // handling all four cases
            res[0] = Math.max(Math.max(left[0], right[0]), Math.max(left[1] + root.val,
                    Math.max(right[1] + root.val, Math.max(left[1] + right[1] + root.val, root.val))));
            res[1] = Math.max(root.val, Math.max(left[1], right[1]) + root.val);
            return res;

        }

        public int maxPathSum(TreeNode root) {
            return maxPathSum_(root)[0];
        }
    }

    // 99. Recover Binary Search Tree
    class Solution6 {
        private static TreeNode getRightMost(TreeNode node, TreeNode curr) {
            while (node.right != null && node.right != curr) {
                node = node.right;
            }
            return node;
        }

        // Time-O(n) and Space-O(1)
        private static void morrisInorderTraversal(TreeNode root) {
            TreeNode curr = root;
            TreeNode prev = null;
            TreeNode a = null, b = null;
            while (curr != null) {
                TreeNode left = curr.left;
                if (left == null) {

                    if (prev != null && prev.val > curr.val) {
                        if (a == null) {
                            // means till now we haven't found a peak
                            a = prev;
                            b = curr;
                        } else {
                            // means peak has already been found and this is the second peak
                            b = curr;
                        }
                    }
                    prev = curr;

                    curr = curr.right;
                } else {
                    TreeNode rightMost = getRightMost(left, curr);
                    if (rightMost.right == null) {
                        rightMost.right = curr;
                        curr = curr.left;
                    } else {
                        rightMost.right = null;

                        if (prev != null && prev.val > curr.val) {
                            if (a == null) {
                                // means till now we haven't found a peak
                                a = prev;
                                b = curr;
                            } else {
                                // means peak has already been found and this is the second peak
                                b = curr;
                            }
                        }
                        prev = curr;

                        curr = curr.right;
                    }
                }
            }
            int temp = a.val;
            a.val = b.val;
            b.val = temp;
        }

        public void recoverTree(TreeNode root) {
            morrisInorderTraversal(root);
        }
    }

    public static void main(String[] args) {

    }
}
