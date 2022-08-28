import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class viewSet {
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

    public static void levelOrderLineWise(TreeNode root) {
        LinkedList<TreeNode> q = new LinkedList<>();
        q.addLast(root);
        int level = 0;
        while (q.size() != 0) {
            int size = q.size();
            System.out.print("Level " + level + ": ");
            while (size-- > 0) {
                TreeNode rm = q.removeFirst();
                System.out.print(rm.val + ", ");
                if (rm.left != null) {
                    q.addLast(rm.left);
                }
                if (rm.right != null) {
                    q.addLast(rm.right);
                }
            }
            level++;
            System.out.println();
        }
    }

    public static void leftView(TreeNode root) {
        LinkedList<TreeNode> q = new LinkedList<>();
        q.addLast(root);
        ArrayList<Integer> ans = new ArrayList<>();
        while (q.size() != 0) {
            int size = q.size();
            ans.add(q.getFirst().val);
            while (size-- > 0) {
                TreeNode rm = q.removeFirst();
                if (rm.left != null) {
                    q.addLast(rm.left);
                }
                if (rm.right != null) {
                    q.addLast(rm.right);
                }
            }
        }
        System.out.println(ans);
    }

    public static void rightView(TreeNode root) {
        LinkedList<TreeNode> q = new LinkedList<>();
        q.addLast(root);
        ArrayList<Integer> ans = new ArrayList<>();
        while (q.size() != 0) {
            int size = q.size();
            ans.add(q.getFirst().val);
            while (size-- > 0) {
                TreeNode rm = q.removeFirst();
                // phle right ko insert kro and then last
                if (rm.right != null) {
                    q.addLast(rm.right);
                }
                if (rm.left != null) {
                    q.addLast(rm.left);
                }
            }
        }
        System.out.println(ans);
    }

    // verticalOrder traversal of a tree
    public static class Pair {
        TreeNode node;
        int vl; // vertical level

        Pair(TreeNode node, int vl) {
            this.node = node;
            this.vl = vl;
        }
    }

    public static void widthOfShadow(TreeNode root, int vl, int[] minMax) {
        if (root == null) {
            return;
        }
        minMax[0] = Math.min(minMax[0], vl);
        minMax[1] = Math.max(minMax[1], vl);

        widthOfShadow(root.left, vl - 1, minMax);
        widthOfShadow(root.right, vl + 1, minMax);
    }

    public static ArrayList<ArrayList<Integer>> verticalOrder(TreeNode root) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        int[] minMax = new int[2]; // this will contain max and min width of the tree;
        widthOfShadow(root, 0, minMax);
        int width = minMax[1] - minMax[0] + 1;
        for (int i = 0; i < width; i++) {
            ans.add(new ArrayList<>());
        }
        LinkedList<Pair> q = new LinkedList<>();
        q.addLast(new Pair(root, Math.abs(minMax[0])));
        while (q.size() != 0) {
            int size = q.size();
            while (size-- > 0) {
                Pair rp = q.removeFirst();
                TreeNode node = rp.node;
                int vl = rp.vl;

                ans.get(vl).add(node.val);

                if (node.left != null) {
                    q.addLast(new Pair(node.left, vl - 1));
                }
                if (node.right != null) {
                    q.addLast(new Pair(node.right, vl + 1));
                }
            }
        }
        return ans;
    }

    // bottom view of a tree
    public static ArrayList<Integer> bottomView(TreeNode root) {
        ArrayList<Integer> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        int[] minMax = new int[2]; // this will contain max and min width of the tree;
        widthOfShadow(root, 0, minMax);
        int width = minMax[1] - minMax[0] + 1;
        for (int i = 0; i < width; i++) {
            ans.add(null);
        }
        LinkedList<Pair> q = new LinkedList<>();
        q.addLast(new Pair(root, Math.abs(minMax[0])));
        while (q.size() != 0) {
            int size = q.size();
            while (size-- > 0) {
                Pair rp = q.removeFirst();
                TreeNode node = rp.node;
                int vl = rp.vl;

                ans.set(vl, node.val);

                if (node.left != null) {
                    q.addLast(new Pair(node.left, vl - 1));
                }
                if (node.right != null) {
                    q.addLast(new Pair(node.right, vl + 1));
                }
            }
        }
        return ans;
    }

    // bottom order with level of a tree
    public static ArrayList<ArrayList<Integer>> bottomOrder_Value(TreeNode root) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        int[] minMax = new int[2]; // this will contain max and min width of the tree;
        widthOfShadow(root, 0, minMax);
        int width = minMax[1] - minMax[0] + 1;
        for (int i = 0; i < width; i++) {
            ans.add(new ArrayList<>());
        }
        // Maintaining horizontal level to capture all the nodes(if present) at bottom
        // view level
        ArrayList<Integer> hLevel = new ArrayList<>();
        for (int i = 0; i < width; i++) {
            hLevel.add(-1);
        }
        LinkedList<Pair> q = new LinkedList<>();
        q.addLast(new Pair(root, Math.abs(minMax[0])));
        int level = 0;
        while (q.size() != 0) {
            int size = q.size();
            while (size-- > 0) {
                Pair rp = q.removeFirst();
                TreeNode node = rp.node;
                int vl = rp.vl;

                if (level > hLevel.get(vl)) {
                    ans.get(vl).clear();
                    hLevel.set(vl, level);
                }
                ans.get(vl).add(node.val);

                if (node.left != null) {
                    q.addLast(new Pair(node.left, vl - 1));
                }
                if (node.right != null) {
                    q.addLast(new Pair(node.right, vl + 1));
                }
            }
            level++;
        }
        return ans;
    }

    // Top view of a tree
    public static ArrayList<Integer> topView(TreeNode root) {
        ArrayList<Integer> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        int[] minMax = new int[2]; // this will contain max and min width of the tree;
        widthOfShadow(root, 0, minMax);
        int width = minMax[1] - minMax[0] + 1;
        for (int i = 0; i < width; i++) {
            ans.add(null);
        }
        LinkedList<Pair> q = new LinkedList<>();
        q.addLast(new Pair(root, Math.abs(minMax[0])));
        while (q.size() != 0) {
            int size = q.size();
            while (size-- > 0) {
                Pair rp = q.removeFirst();
                TreeNode node = rp.node;
                int vl = rp.vl;

                if (ans.get(vl) == null) {
                    ans.set(vl, node.val);
                }

                if (node.left != null) {
                    q.addLast(new Pair(node.left, vl - 1));
                }
                if (node.right != null) {
                    q.addLast(new Pair(node.right, vl + 1));
                }
            }
        }
        return ans;
    }

    // Vertical Sum of a tree
    public static ArrayList<Integer> verticalSum(TreeNode root) {
        ArrayList<Integer> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        int[] minMax = new int[2]; // this will contain max and min width of the tree;
        widthOfShadow(root, 0, minMax);
        int width = minMax[1] - minMax[0] + 1;
        for (int i = 0; i < width; i++) {
            ans.add(0);
        }
        LinkedList<Pair> q = new LinkedList<>();
        q.addLast(new Pair(root, Math.abs(minMax[0])));
        while (q.size() != 0) {
            int size = q.size();
            while (size-- > 0) {
                Pair rp = q.removeFirst();
                TreeNode node = rp.node;
                int vl = rp.vl;

                ans.set(vl, ans.get(vl) + node.val);

                if (node.left != null) {
                    q.addLast(new Pair(node.left, vl - 1));
                }
                if (node.right != null) {
                    q.addLast(new Pair(node.right, vl + 1));
                }
            }
        }
        return ans;
    }

    // left diagonal order
    // we have modified this function for leftdiagonal order
    // on moving left, we are decrementing vl but on moving right the vl remains
    // same
    public static void widthOfShadow_left(TreeNode root, int vl, int[] minMax) {
        if (root == null) {
            return;
        }
        minMax[0] = Math.min(minMax[0], vl);
        minMax[1] = Math.max(minMax[1], vl);
        // System.out.println(root.val+" -> "+vl);
        widthOfShadow_left(root.left, vl - 1, minMax);
        widthOfShadow_left(root.right, vl, minMax);
    }

    // Similarly,we have modified this function for leftdiagonal order
    // on moving left, we are decrementing vl but on moving right the vl remains
    // same
    public static ArrayList<ArrayList<Integer>> leftDiagonalOrder(TreeNode root) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        int[] minMax = new int[2]; // this will contain max and min width of the tree;
        widthOfShadow_left(root, 0, minMax);
        int width = minMax[1] - minMax[0] + 1;
        for (int i = 0; i < width; i++) {
            ans.add(new ArrayList<>());
        }
        LinkedList<Pair> q = new LinkedList<>();
        q.addLast(new Pair(root, Math.abs(minMax[0])));
        System.out.println(Math.abs(minMax[0]) + " " + minMax[1]);
        while (q.size() != 0) {
            int size = q.size();
            while (size-- > 0) {
                Pair rp = q.removeFirst();
                TreeNode node = rp.node;
                int vl = rp.vl;

                ans.get(vl).add(node.val);

                if (node.left != null) {
                    q.addLast(new Pair(node.left, vl - 1));
                }
                if (node.right != null) {
                    q.addLast(new Pair(node.right, vl));
                }
            }
        }
        return ans;
    }

    // Right diagonal order
    // we have modified this function for rightdiagonal order
    // on moving right, we are incrementing vl but on moving left the vl remains
    // same
    public static void widthOfShadow_right(TreeNode root, int vl, int[] minMax) {
        if (root == null) {
            return;
        }
        minMax[0] = Math.min(minMax[0], vl);
        minMax[1] = Math.max(minMax[1], vl);
        // System.out.println(root.val+" -> "+vl);
        widthOfShadow_right(root.left, vl, minMax);
        widthOfShadow_right(root.right, vl + 1, minMax);
    }

    // Similarly,we have modified this function for rightdiagonal order
    // on moving right, we are incrementing vl but on moving left the vl remains
    // same
    public static ArrayList<ArrayList<Integer>> rightDiagonalOrder(TreeNode root) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        int[] minMax = new int[2]; // this will contain max and min width of the tree;
        widthOfShadow_right(root, 0, minMax);
        int width = minMax[1] - minMax[0] + 1;
        for (int i = 0; i < width; i++) {
            ans.add(new ArrayList<>());
        }
        LinkedList<Pair> q = new LinkedList<>();
        q.addLast(new Pair(root, Math.abs(minMax[0])));
        System.out.println(Math.abs(minMax[0]) + " " + minMax[1]);
        while (q.size() != 0) {
            int size = q.size();
            while (size-- > 0) {
                Pair rp = q.removeFirst();
                TreeNode node = rp.node;
                int vl = rp.vl;

                ans.get(vl).add(node.val);

                if (node.left != null) {
                    q.addLast(new Pair(node.left, vl));
                }
                if (node.right != null) {
                    q.addLast(new Pair(node.right, vl + 1));
                }
            }
        }
        return ans;
    }

    public static ArrayList<ArrayList<Integer>> diagonalOrder_geeks(TreeNode root) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        LinkedList<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (q.size() != 0) {
            int size = q.size();
            ArrayList<Integer> smallAns = new ArrayList<>();
            while (size-- > 0) {
                TreeNode rn = q.removeFirst();
                while (rn != null) {
                    smallAns.add(rn.val);
                    if (rn.left != null) {
                        q.add(rn.left);
                    }
                    rn = rn.right;
                }
            }
            ans.add(smallAns);
        }
        return ans;
    }

    // Diagonal sum
    public static ArrayList<Integer> diagonalSum(TreeNode root) {
        ArrayList<Integer> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        LinkedList<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (q.size() != 0) {
            int size = q.size();
            int sum = 0;
            while (size-- > 0) {
                TreeNode rn = q.removeFirst();
                while (rn != null) {
                    sum += rn.val;
                    if (rn.left != null) {
                        q.add(rn.left);
                    }
                    rn = rn.right;
                }
            }
            ans.add(sum);
        }
        return ans;
    }

    // Vertical Order 2
    // https://www.pepcoding.com/resources/data-structures-and-algorithms-in-java-levelup/trees/vertical-order-traversal-of-a-binarytree-ii/ojquestion
    public static class Pair2 implements Comparable<Pair2> {
        TreeNode node;
        int hl;
        int vl;

        Pair2(TreeNode node, int hl, int vl) {
            this.node = node;
            this.hl = hl;
            this.vl = vl;
        }

        public int compareTo(Pair2 that) {
            if (this.hl != that.hl) {
                return this.hl - that.hl;
            } else {
                if (this.vl != that.vl) {
                    return this.vl - that.vl;
                } else {
                    return this.node.val - that.node.val;
                }
            }
        }
    }

    public static ArrayList<ArrayList<Integer>> verticalOrder_ii(TreeNode root) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        int[] minMax = new int[2]; // this will contain max and min width of the tree;
        widthOfShadow(root, 0, minMax);
        int width = minMax[1] - minMax[0] + 1;
        for (int i = 0; i < width; i++) {
            ans.add(new ArrayList<>());
        }
        PriorityQueue<Pair2> pq = new PriorityQueue();
        pq.add(new Pair2(root, 0, Math.abs(minMax[0])));
        while (pq.size() != 0) {
            int size = pq.size();
            while (size-- > 0) {
                Pair2 rp = pq.remove();
                TreeNode node = rp.node;
                int vl = rp.vl;
                int hl = rp.hl;
                ans.get(vl).add(node.val);

                if (node.left != null) {
                    pq.add(new Pair2(node.left, hl + 1, vl - 1));
                }
                if (node.right != null) {
                    pq.add(new Pair2(node.right, hl + 1, vl + 1));
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {

    }
}
