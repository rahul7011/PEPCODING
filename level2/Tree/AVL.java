import java.util.HashMap;

public class AVL {
    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;
        int bal = 0;
        int height = 0;

        TreeNode(int val) {
            this.val = val;
        }
    }

    // O(1)
    private static void updateBalAndHeight(TreeNode root) {
        int lh = root.left == null ? -1 : root.left.height;
        int rh = root.right == null ? -1 : root.right.height;
        int bal = lh - rh;
        root.height = Math.max(lh, rh) + 1;
        root.bal = bal;

    }

    // O(1)
    private static TreeNode rightRotation(TreeNode A) {
        TreeNode root = A.left;
        TreeNode temp = root.right;
        root.right = A;
        A.left = temp;
        updateBalAndHeight(A);
        updateBalAndHeight(root);
        return root;
    }

    // O(1)
    private static TreeNode leftRotation(TreeNode A) {
        TreeNode root = A.right;
        TreeNode temp = root.left;
        root.left = A;
        A.right = temp;
        updateBalAndHeight(A);
        updateBalAndHeight(root);
        return root;
    }

    // O(1)
    public static TreeNode getRotation(TreeNode root) {
        updateBalAndHeight(root);
        if (root.bal == 2) {
            // ll,lr
            if (root.left.bal == 1) {
                // ll
                return rightRotation(root);
            } else {
                // lr
                root.left = leftRotation(root.left);
                return rightRotation(root);
            }
        } else if (root.bal == -2) {
            if (root.right.bal == 1) {
                // rl
                root.right = rightRotation(root.right);
                return leftRotation(root);
            } else {
                // rr
                return leftRotation(root);
            }
        }
        return root;
    }

    // O(logN)
    private static TreeNode addNode(TreeNode root, int data) {
        if (root == null) {
            return new TreeNode(data);
        }
        if (data <= root.val) {
            root.left = addNode(root.left, data);
        } else {
            root.right = addNode(root.right, data);
        }

        root = getRotation(root);
        System.out.println(root.val + " " + root.height + " " + root.bal);
        return root;
    }

    private static int getMax(TreeNode root) {
        TreeNode curr = root;
        while (curr.right != null) {
            curr = curr.right;
        }
        return curr.val;
    }

    private static TreeNode removeNode(TreeNode root, int data) {
        if (root == null) {
            return null;
        }
        if (data < root.val) {
            root.left = removeNode(root.left, data);
        } else if (data > root.val) {
            root.right = removeNode(root.right, data);
        } else {
            if (root.left == null || root.right == null) {
                return root.left == null ? root.right : root.left;
            }
            root.val = getMax(root.left);
            root.left = removeNode(root.left, root.val);
        }

        return getRotation(root);
    }

    private static void display(TreeNode root) {
        if (root == null) {
            return;
        }
        String ans = "";
        ans += root.left == null ? "." : root.left.val + "";
        ans += " -> " + root.val + " <- ";
        ans += root.right == null ? "." : root.right.val + "";
        System.out.println(ans);

        display(root.left);
        display(root.right);
    }

    // 1382. Balance a Binary Search Tree
    // Time:O(n) and Space: O(n)
    class Solution {
        // We need to modify our AVL tree code for this question
        public static HashMap<Integer, Integer> hm = new HashMap<>();

        private static void updateBalAndHeight(TreeNode root) {
            int lh = root.left == null ? -1 : hm.get(root.left.val);
            int rh = root.right == null ? -1 : hm.get(root.right.val);
            int ht = Math.max(lh, rh) + 1;
            hm.put(root.val, ht);
        }

        // O(1)
        private static TreeNode rightRotation(TreeNode A) {
            TreeNode root = A.left;
            TreeNode temp = root.right;
            root.right = A;
            A.left = temp;

            root.right = getRotation(A);
            return getRotation(root);
        }

        // O(1)
        private static TreeNode leftRotation(TreeNode A) {
            TreeNode root = A.right;
            TreeNode temp = root.left;
            root.left = A;
            A.right = temp;

            root.left = getRotation(A);
            return getRotation(root);
        }

        // O(1)
        public static TreeNode getRotation(TreeNode root) {
            updateBalAndHeight(root);

            int lh = root.left == null ? -1 : hm.get(root.left.val);
            int rh = root.right == null ? -1 : hm.get(root.right.val);
            int bal = lh - rh;
            // balance factor can be greater than 2 as the tree was already formed(given)
            if (bal >= 2) {
                // ll,lr
                lh = root.left.left == null ? -1 : hm.get(root.left.left.val);
                rh = root.left.right == null ? -1 : hm.get(root.left.right.val);
                int leftbal = lh - rh;
                // Similarly, its leftBal can also be greater than 1
                if (leftbal >= 1) {
                    // ll
                    return rightRotation(root);
                } else {
                    // lr
                    root.left = leftRotation(root.left);
                    return rightRotation(root);
                }
            } else if (bal <= -2) {
                // balance factor can be smaller than 2 as the tree was already formed(given)
                lh = root.right.left == null ? -1 : hm.get(root.right.left.val);
                rh = root.right.right == null ? -1 : hm.get(root.right.right.val);
                int rightbal = lh - rh;
                // Similarly, its rightBal can also be smaller than 1
                if (rightbal >= 1) {
                    // rl
                    root.right = rightRotation(root.right);
                    return leftRotation(root);
                } else {
                    // rr
                    return leftRotation(root);
                }
            }
            return root;
        }

        public TreeNode balanceBST(TreeNode root) {
            if (root == null) {
                return null;
            }
            root.left = balanceBST(root.left);
            root.right = balanceBST(root.right);
            int lh = root.left == null ? -1 : hm.getOrDefault(root.left.val, -1);
            int rh = root.right == null ? -1 : hm.getOrDefault(root.right.val, -1);
            int ht = Math.max(lh, rh) + 1;
            hm.put(root.val, ht);
            // System.out.println(root.val+" "+ht);
            return getRotation(root);
        }
    }

    public static void main(String[] args) {
        TreeNode root = null;
        int[] vals = { 19, 10, 4, 17, 5 };
        for (int i = 0; i < vals.length; i++) {
            root = addNode(root, vals[i]);
        }

        // display(root);
    }
}
