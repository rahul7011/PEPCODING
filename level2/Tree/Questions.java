
public class Questions {
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

    // 968. Binary Tree Cameras
    class Solution {
        // -1:need a camera
        // 0:it has a camera
        // 1:doesn't need a camera
        int camera = 0;

        private int minCameraCover_(TreeNode root) {
            if (root == null)
                return 1;

            int lc = minCameraCover_(root.left);
            int rc = minCameraCover_(root.right);

            if (lc == -1 || rc == -1) {
                camera++;
                return 0;
            }

            if (lc == 0 || rc == 0) {
                return 1;
            }

            return -1;
        }

        public int minCameraCover(TreeNode root) {
            if (root == null)
                return 0;
            if (minCameraCover_(root) == -1)
                camera++;

            return camera;
        }
    }

    // 337. House Robber III
    

    public static void main(String[] args) {

    }
}
