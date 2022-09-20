public class SegmentTree {

    // 303. Range Sum Query - Immutable
    // Space:O(w*x) where w=no.of words and x is the avg length of a word
    // Time:O(length of a word)
    class NumArray {
        // Construction of Segment Tree
        public static class Node {
            int ss; // segment start
            int se; // segment end
            int val; // value of the current segment

            Node left;
            Node right;
        }

        Node root;

        private static Node construct(int[] nums, int lo, int hi) {
            if (lo == hi) {
                Node node = new Node();
                node.left = node.right = null;
                node.ss = node.se = lo;
                node.val = nums[lo];
                return node;
            }
            Node node = new Node();
            node.ss = lo;
            node.se = hi;
            int mid = (lo + hi) / 2;
            node.left = construct(nums, lo, mid);
            node.right = construct(nums, mid + 1, hi);
            node.val = node.left.val + node.right.val;
            return node;
        }

        public NumArray(int[] nums) {
            root = construct(nums, 0, nums.length - 1);
        }

        private static void update(Node node, int idx, int val) {
            if (node.ss == node.se) {
                node.val = val;
                return;
            }
            int mid = (node.ss + node.se) / 2;
            if (idx <= mid) {
                update(node.left, idx, val);
            } else {
                update(node.right, idx, val);
            }
            node.val = node.left.val + node.right.val;
        }

        public void update(int idx, int val) {
            update(root, idx, val);
        }

        // qs=query start
        // qe=query end
        private static int query(Node node, int qs, int qe) {
            if (node == null || node.ss > qe || node.se < qs) {
                return 0;
            } else if (node.ss >= qs && node.se <= qe) {
                return node.val;
            } else {
                int lval = query(node.left, qs, qe);
                int rval = query(node.right, qs, qe);
                return lval + rval;
            }
        }

        public int sumRange(int left, int right) {
            return query(root, left, right);
        }
    }
}
