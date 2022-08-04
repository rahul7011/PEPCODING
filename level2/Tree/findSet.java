import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class findSet {
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

    // Burning Trees with description(which node will burn at which time)
    // https://www.geeksforgeeks.org/burn-the-binary-tree-starting-from-the-target-node/
    private static void burnTrees(TreeNode root, TreeNode blocked, int k, HashMap<Integer, HashSet<Integer>> hm) {
        if (root == null || root == blocked) {
            return;
        }
        HashSet<Integer> hs = hm.getOrDefault(k, new HashSet<>());
        hs.add(root.val);
        hm.put(k, hs);
        burnTrees(root.left, blocked, k + 1, hm);
        burnTrees(root.right, blocked, k + 1, hm);
    }

    private static void burnTrees() {
        /*
         * 12
         * / \
         * 13 10
         * / \
         * 14 15
         * / \ / \
         * 21 24 22 23
         * 
         * Let us create Binary Tree as shown
         * above
         */
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(13);
        root.right = new TreeNode(10);
        root.right.left = new TreeNode(14);
        root.right.right = new TreeNode(15);
        TreeNode left = root.right.left;
        TreeNode right = root.right.right;
        left.left = new TreeNode(21);
        left.right = new TreeNode(24);
        right.left = new TreeNode(22);
        right.right = new TreeNode(23);
        ArrayList<TreeNode> ans = new ArrayList<>();
        int data = 14;
        nodeToRootPath(root, data, ans);
        HashMap<Integer, HashSet<Integer>> hm = new HashMap<>();
        TreeNode blocked = null;
        for (int i = 0; i < ans.size(); i++) {
            TreeNode node = ans.get(i);
            burnTrees(node, blocked, i, hm);
            blocked = node;
        }
        for (int key : hm.keySet()) {
            System.out.println(key + "-> " + hm.get(key));
        }
    }

    // https://practice.geeksforgeeks.org/problems/burning-tree/1
    class Solution2 {
        /*
         * class Node {
         * int data;
         * Node left;
         * Node right;
         * 
         * Node(int data) {
         * this.data = data;
         * left = null;
         * right = null;
         * }
         * }
         */
        public static boolean nodeToRootPath(Node root, int data, ArrayList<Node> ans) {
            if (root == null) {
                return false;
            }
            if (root.data == data) {
                ans.add(root);
                return true;
            }
            boolean res = nodeToRootPath(root.left, data, ans) || nodeToRootPath(root.right, data, ans);
            if (res == true) {
                ans.add(root);
            }
            return res;
        }

        private static void burnTrees(Node root, Node blocked, int k, HashMap<Integer, HashSet<Integer>> hm) {
            if (root == null || root == blocked) {
                return;
            }
            HashSet<Integer> hs = hm.getOrDefault(k, new HashSet<>());
            hs.add(root.data);
            hm.put(k, hs);
            burnTrees(root.left, blocked, k + 1, hm);
            burnTrees(root.right, blocked, k + 1, hm);
        }

        public static int minTime(Node root, int data) {
            ArrayList<Node> ans = new ArrayList<>();
            nodeToRootPath(root, data, ans);
            HashMap<Integer, HashSet<Integer>> hm = new HashMap<>();
            Node blocked = null;
            for (int i = 0; i < ans.size(); i++) {
                Node node = ans.get(i);
                burnTrees(node, blocked, i, hm);
                blocked = node;
            }
            // for(int key:hm.keySet())
            // {
            // System.out.println(key+"-> "+hm.get(key));
            // }
            return hm.size() - 1;
        }
    }

    // Burning Tree optimised
    class Solution3 {
        /*
         * class Node {
         * int data;
         * Node left;
         * Node right;
         * 
         * Node(int data) {
         * this.data = data;
         * left = null;
         * right = null;
         * }
         * }
         */
        private static void burnTreeNode(Node root, Node blocked, int time, ArrayList<ArrayList<Integer>> ans) {
            if (root == null || root == blocked) {
                return;
            }
            if (time == ans.size()) {
                ans.add(new ArrayList<>());
            }
            ans.get(time).add(root.data);
            burnTreeNode(root.left, blocked, time + 1, ans);
            burnTreeNode(root.right, blocked, time + 1, ans);
        }

        public static int burningTree(Node root, int target, ArrayList<ArrayList<Integer>> ans) {
            if (root == null) {
                return -1;
            }
            if (root.data == target) {
                burnTreeNode(root, null, 0, ans);
                return 1;
            }
            int lt = burningTree(root.left, target, ans);
            if (lt != -1) {
                burnTreeNode(root, root.left, lt, ans);
                return lt + 1;
            }
            int rt = burningTree(root.right, target, ans);
            if (rt != -1) {
                burnTreeNode(root, root.right, rt, ans);
                return rt + 1;
            }
            return -1;
        }

        public static void burningTree(Node root, int data) {
            ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
            burningTree(root, data, ans);
        }

        public static int minTime(Node root, int target) {
            ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
            burningTree(root, target, ans);
            return ans.size() - 1;
        }
    }

    // Burning Tree Variance where some nodes contains water
    private static void burnTreeNodeWithWater(Node root, Node blocked, int time, HashSet<Integer> waterSet,
            ArrayList<ArrayList<Integer>> ans) {
        if (root == null || root == blocked || waterSet.contains(root.data) == true) {
            return;
        }
        if (time == ans.size()) {
            ans.add(new ArrayList<>());
        }
        ans.get(time).add(root.data);
        burnTreeNodeWithWater(root.left, blocked, time + 1, waterSet, ans);
        burnTreeNodeWithWater(root.right, blocked, time + 1, waterSet, ans);
    }

    public static int burningTreeWithWater(Node root, int target, HashSet<Integer> waterSet,
            ArrayList<ArrayList<Integer>> ans) {
        if (root == null) {
            return -1;
        }
        if (root.data == target) {
            burnTreeNodeWithWater(root, null, 0, waterSet, ans);
            return 1;
        }
        int lt = burningTreeWithWater(root.left, target, waterSet, ans);
        if (lt != -1) {
            if (waterSet.contains(root.data) == true) {
                // edge case:when a node that is in the nodeToRootPath contains water then we
                // need to discard all the nodeToRootPath nodes from that point
                return -1;
            }
            burnTreeNodeWithWater(root, root.left, lt, waterSet, ans);
            return lt + 1;
        }
        int rt = burningTreeWithWater(root.right, target, waterSet, ans);
        if (rt != -1) {
            if (waterSet.contains(root.data) == true) {
                // edge case:when a node that is in the nodeToRootPath contains water then we
                // need to discard all the nodeToRootPath nodes from that point
                return -1;
            }
            burnTreeNodeWithWater(root, root.right, rt, waterSet, ans);
            return rt + 1;
        }
        return -1;
    }

    public static void burningTreeWithWater(Node root, int data, HashSet<Integer> waterSet) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        burningTreeWithWater(root, data, waterSet, ans);
    }

    public static int minTime() {
        Node root = new Node(12);
        root.left = new Node(13);
        root.right = new Node(10);
        root.right.left = new Node(14);
        root.right.right = new Node(15);
        Node left = root.right.left;
        Node right = root.right.right;
        left.left = new Node(21);
        left.right = new Node(24);
        right.left = new Node(22);
        right.right = new Node(23);
        int target = 22;
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        HashSet<Integer> waterSet = new HashSet<>();
        waterSet.add(10);
        waterSet.add(14);
        if (waterSet.contains(target) == true) {
            // this means we can't burn the target node as it contains water
            return 0;
        }
        burningTreeWithWater(root, target, waterSet, ans);
        for (ArrayList<Integer> l : ans) {
            System.out.println(l);
        }
        return ans.size() - 1;
    }

    // 236. Lowest Common Ancestor of a Binary Tree
    class Solution4 {
        private static ArrayList<TreeNode> nodeToRootPath(TreeNode root, TreeNode data) {
            if (root == null) {
                return new ArrayList<>();
            }
            if (root == data) {
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

        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            ArrayList<TreeNode> p1 = nodeToRootPath(root, p);
            ArrayList<TreeNode> p2 = nodeToRootPath(root, q);
            TreeNode LCA = null;
            int i = p1.size() - 1, j = p2.size() - 1;
            while (Math.min(i, j) >= 0) {
                if (p1.get(i) != p2.get(j)) {
                    return LCA;
                }
                LCA = p1.get(i);
                i--;
                j--;
            }
            return LCA;
        }
    }

    // 236. Lowest Common Ancestor of a Binary Tree
    //Optimised:O(1) space
    class Solution5 {
        public TreeNode LCA = null;

        private boolean lca(TreeNode root, TreeNode p, TreeNode q) {
            if (root == null) {
                return false;
            }
            boolean selfPresent = (root == p) || (root == q);
            boolean leftPresent = lca(root.left, p, q);
            if (LCA != null) {
                return true;
            }
            boolean rightPresent = lca(root.right, p, q);
            if (LCA != null) {
                return true;
            }
            if ((leftPresent && rightPresent) || (leftPresent && selfPresent) || (rightPresent && selfPresent)) {
                LCA = root;
            }
            return leftPresent || rightPresent || selfPresent;

        }

        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            lca(root, p, q);
            return LCA;
        }
    }

    public static void main(String[] args) {
        // burnTrees();
        // minTime();
    }
}