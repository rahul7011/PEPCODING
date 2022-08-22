public class Try {
    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public static void display(TreeNode node) {
        if (node == null)
            return;

        StringBuilder sb = new StringBuilder();
        sb.append((node.left != null ? node.left.val : "."));
        sb.append(" -> " + node.val + " <- ");
        sb.append((node.right != null ? node.right.val : "."));

        System.out.println(sb.toString());

        display(node.left);
        display(node.right);

    }

    private static int solve(TreeNode root, int check) {
        int count = 0;
        if (root.left != null) {
            count += solve(root.left, 0);
        }
        if (root.right != null) {
            count += solve(root.right, 1);
        }

        if (check == 0) {
            // left
            if (root.val % 2 != 0) {
                count += 1;
            }

        } else if (check == 1) {
            // right
            if (root.val % 2 == 0) {
                count += 1;
            }
        } else if (check == -1) {
            count += (root.val == 1) ? 0 : root.val - 1;
        }
        return count;
    }

    public static void main(String[] args) {
        // TreeNode root = new TreeNode(1);
        // root.left = new TreeNode(2);
        // root.right = new TreeNode(2);
        // root.left.left = new TreeNode(5);
        // root.right.left = new TreeNode(6);
        // root.right.right = new TreeNode(8);
        TreeNode root = new TreeNode(758);
        root.right = new TreeNode(3);
        root.right.right = new TreeNode(5);
        display(root);
        System.out.println(solve(root, -1));
    }
}
