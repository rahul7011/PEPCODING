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
    private static void burnTrees(TreeNode root,TreeNode blocked,int k,HashMap<Integer,HashSet<Integer>>hm)
    {
        if(root==null||root==blocked)
        {
            return ;
        }
        HashSet<Integer>hs=hm.getOrDefault(k, new HashSet<>());
        hs.add(root.val);
        hm.put(k, hs);
        burnTrees(root.left,blocked,k+1,hm);
        burnTrees(root.right,blocked,k+1,hm);
    }
    private static void burnTrees() {
        /*  12
           /  \
          13  10
              / \
             14 15
            / \ / \
          21 24 22 23
 
        Let us create Binary Tree as shown
        above */
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
        HashMap<Integer,HashSet<Integer>>hm=new HashMap<>();
        TreeNode blocked=null;
        for(int i=0;i<ans.size();i++)
        {
            TreeNode node=ans.get(i);
            burnTrees(node,blocked,i,hm);
            blocked=node;
        }
        for(int key:hm.keySet())
        {
            System.out.println(key+"-> "+hm.get(key));
        }
    }

    // https://practice.geeksforgeeks.org/problems/burning-tree/1
    class Solution2
    {
        /*class Node {
            int data;
            Node left;
            Node right;
        
            Node(int data) {
                this.data = data;
                left = null;
                right = null;
            }
        }*/
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
    
        private static void burnTrees(Node root,Node blocked,int k,HashMap<Integer,HashSet<Integer>>hm)
        {
            if(root==null||root==blocked)
            {
                return ;
            }
            HashSet<Integer>hs=hm.getOrDefault(k, new HashSet<>());
            hs.add(root.data);
            hm.put(k, hs);
            burnTrees(root.left,blocked,k+1,hm);
            burnTrees(root.right,blocked,k+1,hm);
        }
        public static int minTime(Node root, int data) 
        {
            ArrayList<Node> ans = new ArrayList<>();
            nodeToRootPath(root, data, ans);
            HashMap<Integer,HashSet<Integer>>hm=new HashMap<>();
            Node blocked=null;
            for(int i=0;i<ans.size();i++)
            {
                Node node=ans.get(i);
                burnTrees(node,blocked,i,hm);
                blocked=node;
            }
            // for(int key:hm.keySet())
            // {
            //     System.out.println(key+"-> "+hm.get(key));
            // }
            return hm.size()-1;
        }
    }
    //Burning Tree optimised
    public static void burningTreeNode(TreeNode root, int time, TreeNode blockNode, ArrayList<ArrayList<Integer>> ans) {
        if (root == null || root == blockNode)
            return;
        if (time == ans.size())    // if(time == ans.size()) ans.push_back({});
            ans.add(new ArrayList<>());
        ans.get(time).add(root.val);

        burningTreeNode(root.left, time + 1, blockNode, ans);
        burningTreeNode(root.right, time + 1, blockNode, ans);

    }

    public static int burningTree(TreeNode root, int fireNode, ArrayList<ArrayList<Integer>> ans) {
        if (root == null)
            return -1;
        if (root.val == fireNode) {
            burningTreeNode(root, 0, null, ans);
            return 1;
        }

        int lt = burningTree(root.left, fireNode, ans);
        if (lt != -1) {
            burningTreeNode(root, lt, root.left, ans);
            return lt + 1;
        }

        int rt = burningTree(root.right, fireNode, ans);
        if (rt != -1) {
            burningTreeNode(root, lt, root.right, ans);
            return rt + 1;
        }

        return -1;
    }
    public static void burningTree(TreeNode root, int data) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        burningTree(root, data, ans);
    }
    public static void main(String[] args) {
        burnTrees();
    }
}