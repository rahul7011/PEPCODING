import java.util.ArrayList;
import java.util.LinkedList;

public class construction {
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

    private static TreeNode getRightMostNode(TreeNode node, TreeNode curr) {
        while (node.right != null && node.right != curr) {
            node = node.right;
        }
        return node;
    }

    private static TreeNode circularDoublyLinkedList(TreeNode root) {
        TreeNode dummy = new TreeNode(-1);
        TreeNode curr = root, prev = dummy;
        while (curr != null) {
            TreeNode left = curr.left;
            if (left == null) {
                prev.right = curr;
                curr.left = prev;
                prev = prev.right;

                curr = curr.right;
            } else {
                TreeNode rightMost = getRightMostNode(left, curr);
                if (rightMost.right == null) {
                    // backlink creation
                    rightMost.right = curr;
                    curr = curr.left;
                } else {
                    // backlink deletion
                    rightMost.right = null;

                    prev.right = curr;
                    curr.left = prev;
                    prev = prev.right;

                    curr = curr.right;
                }
            }
        }
        // Iur tree has been converted into doubly linked list
        // Now we are converting it into circular doubly linked list
        TreeNode head = dummy.right;
        dummy.right = head.left = null; // detaching dummy node

        head.left = prev;
        prev.right = head;

        return head;
    }

    // Binary Tree to CDLL
    // https://practice.geeksforgeeks.org/problems/binary-tree-to-cdll/1
    class Solution3 {
        // Function to convert binary tree into circular doubly linked list.
        private static Node getRightMostNode(Node node, Node curr) {
            while (node.right != null && node.right != curr) {
                node = node.right;
            }
            return node;
        }

        private static Node circularDoublyLinkedList(Node root) {
            Node dummy = new Node(-1);
            Node curr = root, prev = dummy;
            while (curr != null) {
                Node left = curr.left;
                if (left == null) {
                    prev.right = curr;
                    curr.left = prev;
                    prev = prev.right;

                    curr = curr.right;
                } else {
                    Node rightMost = getRightMostNode(left, curr);
                    if (rightMost.right == null) {
                        // backlink creation
                        rightMost.right = curr;
                        curr = curr.left;
                    } else {
                        // backlink deletion
                        rightMost.right = null;

                        prev.right = curr;
                        curr.left = prev;
                        prev = prev.right;

                        curr = curr.right;
                    }
                }
            }
            // Iur tree has been converted into doubly linked list
            // Now we are converting it into circular doubly linked list
            Node head = dummy.right;
            dummy.right = head.left = null; // detaching dummy node

            head.left = prev;
            prev.right = head;

            return head;
        }

        Node bTreeToClist(Node root) {
            return circularDoublyLinkedList(root);
        }

    }

    // 108. Convert Sorted Array to Binary Search Tree
    class Solution4 {
        private TreeNode sortedArrayToBST(int[] nums, int si, int ei) {
            if (si > ei) {
                return null;
            }
            int idx = (si + ei) / 2;
            TreeNode node = new TreeNode(nums[idx]);
            node.left = sortedArrayToBST(nums, si, idx - 1);
            node.right = sortedArrayToBST(nums, idx + 1, ei);
            return node;
        }

        public TreeNode sortedArrayToBST(int[] nums) {
            return sortedArrayToBST(nums, 0, nums.length - 1);
        }
    }

    // Convert Sorted Doubly Linked List To Binary Search Tree
    // https://www.pepcoding.com/resources/data-structures-and-algorithms-in-java-levelup/trees/convert-sorted-dll-to-bst/ojquestion#!
    private static Node getMidNode(Node head) {
        // code for first mid
        if (head == null || head.right == null) {
            return head;
        }
        Node slow = head, fast = head;
        while (fast.right != null && fast.right.right != null) {
            fast = fast.right.right;
            slow = slow.right;
        }
        return slow;
    }

    private static Node doublyLinkedListToBST(Node root) {
        if (root == null || root.right == null) {
            return root;
        }

        Node midNode = getMidNode(root);

        Node prev = midNode.left, frwd = midNode.right;

        midNode.left = midNode.right = frwd.left = null;
        if (prev != null) {
            prev.right = null;
        }

        midNode.left = doublyLinkedListToBST((prev != null) ? root : null);
        midNode.right = doublyLinkedListToBST(frwd);
        return midNode;
    }

    public static Node SortedDLLToBST(Node head) {
        return doublyLinkedListToBST(head);
    }

    class Solution5 {
        private static TreeNode getRightMostTreeNode(TreeNode node, TreeNode curr) {
            while (node.right != null && node.right != curr) {
                node = node.right;
            }
            return node;
        }

        private static TreeNode DoublyLinkedList(TreeNode root) {
            TreeNode curr = root;
            TreeNode dummy = new TreeNode(-1);
            TreeNode prev = dummy;
            while (curr != null) {
                TreeNode left = curr.left;
                if (left == null) {
                    prev.right = curr;
                    curr.left = prev;
                    prev = curr;

                    curr = curr.right;
                } else {
                    TreeNode rightMost = getRightMostTreeNode(curr.left, curr);
                    if (rightMost.right == null) {
                        // link creation
                        rightMost.right = curr;
                        curr = curr.left;
                    } else {
                        // link destroyed
                        rightMost.right = null;

                        prev.right = curr;
                        curr.left = prev;
                        prev = curr;

                        curr = curr.right;
                    }
                }
            }
            TreeNode dll = dummy.right;
            dll.left = null;
            prev.right = null;
            return dll;
        }

        public static TreeNode mergeTwoLists(TreeNode list1, TreeNode list2) {
            TreeNode c1 = list1;
            TreeNode c2 = list2;
            TreeNode dummy = new TreeNode(-1);
            TreeNode connector = dummy;
            while (c1 != null && c2 != null) {
                if (c1.val < c2.val) {
                    connector.right = c1;
                    c1.left = connector;
                    connector = c1;

                    c1 = c1.right;
                } else {
                    connector.right = c2;
                    c2.left = connector;
                    connector = c2;

                    c2 = c2.right;
                }
            }
            if (c1 != null) {
                connector.right = c1;
                c1.left = connector;
            } else if (c2 != null) {
                connector.right = c2;
                c2.left = connector;
            }
            TreeNode head = dummy.right;
            head.left = null;
            return head;
        }

        public static TreeNode getMidTreeNode(TreeNode root) {
            if (root == null || root.right == null) {
                return root;
            }
            TreeNode slow = root;
            TreeNode fast = root;
            while (fast.right != null && fast.right.right != null) {
                slow = slow.right;
                fast = fast.right.right;
            }
            return slow;
        }

        public static TreeNode mergeSortForDLL(TreeNode root) {
            if (root == null || root.right == null) {
                return root;
            }
            TreeNode mid = getMidTreeNode(root);
            TreeNode secHead = mid.right;
            secHead.left = null;
            mid.right = null;
            root = mergeSortForDLL(root);
            secHead = mergeSortForDLL(secHead);
            return mergeTwoLists(root, secHead);
        }

        private static TreeNode doublyLinkedListToBST(TreeNode root) {
            if (root == null || root.right == null) {
                return root;
            }
            TreeNode midTreeNode = getMidTreeNode(root);

            TreeNode prev = midTreeNode.left, frwd = midTreeNode.right;

            midTreeNode.left = midTreeNode.right = frwd.left = null;
            if (prev != null) {
                prev.right = null;
            }

            midTreeNode.left = doublyLinkedListToBST(prev != null ? root : null);
            midTreeNode.right = doublyLinkedListToBST(frwd);
            return midTreeNode;
        }

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

        private static void BinaryTreeToBST() {
            TreeNode root = null;

            /*
             * Constructing tree given in the above figure
             * 10
             * / \
             * 30 15
             * / \
             * 20 5
             */
            root = new TreeNode(10);
            root.left = new TreeNode(30);
            root.right = new TreeNode(15);
            root.left.left = new TreeNode(20);
            root.right.right = new TreeNode(5);

            // Before:
            System.out.println("Before:");
            System.out.println(morrisInorderTraversal(root));

            TreeNode dll = DoublyLinkedList(root);
            // printlist(dll);
            TreeNode sortedDll = mergeSortForDLL(dll);
            // printlist(sortedDll);
            TreeNode bst = doublyLinkedListToBST(sortedDll);

            // After:
            System.out.println("After:");
            System.out.println(morrisInorderTraversal(bst));
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

    // Construct Bst From Preorder Traversal
    // https://www.pepcoding.com/resources/data-structures-and-algorithms-in-java-levelup/trees/construct-bst-from-preorder-traversal/ojquestion

    // Note:we are storing idx variable inside an arrayList just to avoid static
    // variables
    private static TreeNode bstFromPreOrder(int[] preOrder, int lr, int rr, ArrayList<Integer> idx) {
        int i = idx.get(0);
        if (i >= preOrder.length || lr > preOrder[i] || rr < preOrder[i]) {
            return null;
        }
        TreeNode root = new TreeNode(preOrder[i]);
        idx.set(0, idx.get(0) + 1);
        root.left = bstFromPreOrder(preOrder, lr, preOrder[i], idx);
        root.right = bstFromPreOrder(preOrder, preOrder[i], rr, idx);
        return root;
    }

    // Construct Bst From Postorder Traversal
    // https://www.pepcoding.com/resources/data-structures-and-algorithms-in-java-levelup/trees/construct-bst-from-postorder-traversal/ojquestion
    private static TreeNode bstFromPostOrder(int[] postOrder, int lr, int rr, ArrayList<Integer> idx) {
        int i = idx.get(0);
        if (i < 0 || lr > postOrder[i] || rr < postOrder[i]) {
            // System.out.println(i+" "+lr+" "+rr);
            return null;
        }
        // System.out.println(postOrder[i]);
        TreeNode root = new TreeNode(postOrder[i]);
        idx.set(0, idx.get(0) - 1);
        root.right = bstFromPostOrder(postOrder, postOrder[i], rr, idx);
        root.left = bstFromPostOrder(postOrder, lr, postOrder[i], idx);
        return root;
    }

    private static void bstCall() {
        // System.out.println("from preOrder");
        // int[] preOrder = { 8, 3, 1, 6, 4, 7, 10, 14, 13 };
        // ArrayList<Integer> idx = new ArrayList<>();
        // idx.add(0);
        // TreeNode root = bstFromPreOrder(preOrder, (int) -1e9, (int) 1e9, idx);
        // display(root);

        System.out.println("from postOrder");
        int[] postOrder = { 1, 4, 7, 6, 3, 13, 14, 10, 8 };
        ArrayList<Integer> idx = new ArrayList<>();
        idx.add(postOrder.length - 1);
        TreeNode root = bstFromPostOrder(postOrder, (int) -1e9, (int) 1e9, idx);
        display(root);
    }

    // Construct Bst From Levelorder Traversal
    // https://www.pepcoding.com/resources/data-structures-and-algorithms-in-java-levelup/trees/construct-bst-from-levelorder-traversal/ojquestion
    public static class Pair {
        TreeNode parent = null;
        int lr = (int) -1e9;
        int rr = (int) 1e9;

        Pair(TreeNode parent) {
            this.parent = parent;
        }

        Pair(TreeNode parent, int lr, int rr) {
            this.parent = parent;
            this.lr = lr;
            this.rr = rr;
        }
    }

    public static TreeNode constructBSTFromLevelOrder(int[] LevelOrder) {
        LinkedList<Pair> q = new LinkedList<>();
        q.addLast(new Pair(null));
        TreeNode root = null;
        int idx = 0;
        while (q.size() != 0 && idx < LevelOrder.length) {
            Pair rp = q.removeFirst();
            int ele = LevelOrder[idx];
            if (rp.lr > ele || rp.rr < ele) {
                continue;
            }
            idx++;
            TreeNode node = new TreeNode(ele);
            if (rp.parent == null) {
                root = node;
            } else {
                if (rp.parent.val > ele) {
                    rp.parent.left = node;
                } else {
                    rp.parent.right = node;
                }
            }
            q.addLast(new Pair(node, rp.lr, ele));
            q.addLast(new Pair(node, ele, rp.rr));
        }
        return root;
    }

    // 449. Serialize and Deserialize BST
    public class Codec {

        // Encodes a tree to a single string.
        private void serialize(TreeNode root, StringBuilder sb) {
            if (root == null) {
                return;
            }
            sb.append(root.val + " ");
            serialize(root.left, sb);
            serialize(root.right, sb);
        }

        public String serialize(TreeNode root) {
            StringBuilder sb = new StringBuilder();
            serialize(root, sb);
            return sb.toString();
        }

        // Decodes your encoded data to tree.
        private TreeNode deserialize(String[] data, int lr, int rr, ArrayList<Integer> idx) {
            int i = idx.get(0);
            if (i >= data.length) {
                return null;
            }
            int ele = Integer.parseInt(data[i]);
            if (lr > ele || rr < ele) {
                return null;
            }
            idx.set(0, idx.get(0) + 1);
            TreeNode root = new TreeNode(ele);
            root.left = deserialize(data, lr, ele, idx);
            root.right = deserialize(data, ele, rr, idx);
            return root;
        }

        public TreeNode deserialize(String data) {
            if (data.length() == 0) {
                return null;
            }
            ArrayList<Integer> idx = new ArrayList<>();
            idx.add(0);
            String[] datas = data.split(" ");
            // System.out.println(datas.length);
            return deserialize(datas, (int) -1e9, (int) 1e9, idx);
        }
    }

    // 1307 · Verify Preorder Sequence in Binary Search Tree
    // https://www.lintcode.com/problem/1307/
    public class Solution {
        public static class TreeNode {
            int val;
            TreeNode left = null;
            TreeNode right = null;

            TreeNode(int val) {
                this.val = val;
            }
        }

        public static class Pair {
            TreeNode node = null;
            int lr = (int) -1e9;
            int rr = (int) 1e9;

            Pair() {
            }

            Pair(TreeNode node, int lr, int rr) {
                this.node = node;
                this.lr = lr;
                this.rr = rr;
            }
        }

        private static boolean bstFromPreOrder(int[] preOrder) {
            LinkedList<Pair> st = new LinkedList<>();
            st.addLast(new Pair());
            int idx = 0;
            while (st.size() != 0 && idx < preOrder.length) {
                Pair rp = st.removeLast();
                int ele = preOrder[idx];
                if (rp.lr > ele || rp.rr < ele) {
                    continue;
                }
                idx++;
                TreeNode node = new TreeNode(ele);
                if (rp.node == null) {
                    rp.node = node;
                } else {
                    if (rp.node.val > ele) {
                        rp.node.left = node;
                    } else {
                        rp.node.right = node;
                    }
                }
                st.addLast(new Pair(node, rp.lr, ele));
                st.addLast(new Pair(node, ele, rp.rr));
            }
            return (idx == preOrder.length);
        }

        public boolean verifyPreorder(int[] preorder) {
            return bstFromPreOrder(preorder);
        }
    }

    // 105. Construct Binary Tree from Preorder and Inorder Traversal
    class Solution1 {
        public static int findIdx(int[] arr, int left, int right, int tar) {
            for (int i = left; i <= right; i++) {
                if (arr[i] == tar)
                    return i;
            }
            return -1;

        }

        public static TreeNode buildTree(int[] preorder, int psi, int pei, int[] inorder, int isi, int iei) {
            if (psi > pei) {
                return null;
            }
            TreeNode node = new TreeNode(preorder[psi]);
            int idx = findIdx(inorder, isi, iei, preorder[psi]);
            int totalElement = idx - isi;
            node.left = buildTree(preorder, psi + 1, psi + totalElement, inorder, isi, idx - 1);
            node.right = buildTree(preorder, psi + totalElement + 1, pei, inorder, idx + 1, iei);
            return node;
        }

        public TreeNode buildTree(int[] preorder, int[] inorder) {
            // System.out.println(findIdx(inorder,0,preorder.length-1,preorder[0])+"
            // "+preorder[0]);
            // return null;
            return buildTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
        }
    }

    // 106. Construct Binary Tree from Inorder and Postorder Traversal
    class Solution2 {
        public static int findIdx(int[] arr, int left, int right, int tar) {
            for (int i = right; i >= left; i--) {
                if (arr[i] == tar)
                    return i;
            }
            return -1;

        }

        public static TreeNode buildTree(int[] postorder, int psi, int pei, int[] inorder, int isi, int iei) {
            if (psi > pei) {
                return null;
            }
            TreeNode node = new TreeNode(postorder[pei]);
            int idx = findIdx(inorder, isi, iei, postorder[pei]);
            int totalElement = iei - idx;
            node.right = buildTree(postorder, pei - totalElement, pei - 1, inorder, idx + 1, iei);
            node.left = buildTree(postorder, psi, pei - 1 - totalElement, inorder, isi, idx - 1);
            return node;
        }

        public TreeNode buildTree(int[] inorder, int[] postorder) {
            return buildTree(postorder, 0, postorder.length - 1, inorder, 0, inorder.length - 1);
        }
    }

    // 889. Construct Binary Tree from Preorder and Postorder Traversal
    class Solution6 {
        public static int findIdx(int[] arr, int left, int right, int tar) {
            for (int i = right; i >= left; i--) {
                if (arr[i] == tar)
                    return i;
            }
            return -1;

        }

        private TreeNode constructFromPrePost(int[] postOrder, int ppsi, int ppei, int[] preOrder, int psi, int pei) {
            if (ppsi > ppei) {
                return null;
            }
            TreeNode node = new TreeNode(preOrder[psi]);
            if (psi == pei) {
                return node;
            }
            int idx = findIdx(postOrder, ppsi, ppei, preOrder[psi + 1]);
            int totalElement = idx - ppsi + 1;
            node.left = constructFromPrePost(postOrder, ppsi, ppsi + totalElement - 1, preOrder, psi + 1,
                    psi + totalElement);
            node.right = constructFromPrePost(postOrder, ppsi + totalElement, ppei - 1, preOrder,
                    psi + totalElement + 1, pei);
            return node;
        }

        public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
            return constructFromPrePost(postorder, 0, postorder.length - 1, preorder, 0, preorder.length - 1);
        }
    }

    // 297. Serialize and Deserialize Binary Tree
    // Alternate Aprroach could be to from levelorder traversal string and for
    // deserailization use Queue data structure with check
    public class Codec1 {

        public class Pair {
            TreeNode node = null;
            int check = 0;

            Pair(TreeNode node, int check) {
                this.node = node;
                this.check = check;
            }
        }

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            if (root == null) {
                return "-1001 ";
            }
            String preOrder = root.val + " ";
            preOrder += serialize(root.left);
            preOrder += serialize(root.right);
            return preOrder;
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            String[] preOrder = data.split(" ");
            if (preOrder.length == 1) {
                return null;
            }
            LinkedList<Pair> st = new LinkedList<>();
            TreeNode root = new TreeNode(Integer.parseInt(preOrder[0]));
            st.push(new Pair(root, 0));
            int idx = 1;
            while (st.size() != 0) {
                Pair p = st.removeLast();
                int elem = Integer.parseInt(preOrder[idx]);
                // System.out.println(elem);
                TreeNode node = elem == -1001 ? null : new TreeNode(elem);
                if (p.check == 0) {
                    // left add
                    p.node.left = node;
                    p.check++;
                    st.addLast(p);
                    if (node != null)
                        st.addLast(new Pair(node, 0));
                } else {
                    // right add
                    p.node.right = node;
                    p.check++;
                    if (node != null)
                        st.addLast(new Pair(node, 0));
                }
                idx++;
            }
            return root;
        }
    }

    // Largest BST
    // https://practice.geeksforgeeks.org/problems/largest-bst/1
    class Solution7 {

        public static class Pair {
            int size;
            int max;
            int min;
            boolean isBst;

            Pair() {

            }

            Pair(int size, int max, int min, boolean isBst) {
                this.size = size;
                this.max = max;
                this.min = min;
                this.isBst = isBst;
            }
        }

        // Return the size of the largest sub-tree which is also a BST
        private static Pair largestBst_(Node root) {
            if (root == null) {
                return new Pair(0, (int) -1e9, (int) 1e9, true);
            }
            Pair left = largestBst_(root.left);
            Pair right = largestBst_(root.right);
            Pair nPair = new Pair();
            if (left.isBst && right.isBst) {
                if (root.data > left.max && root.data < right.min) {
                    nPair.size = left.size + right.size + 1;
                    nPair.max = Math.max(left.max, Math.max(right.max, root.data));
                    nPair.min = Math.min(left.min, Math.min(right.min, root.data));
                    nPair.isBst = true;
                } else {
                    nPair.size = Math.max(left.size, right.size);
                    nPair.max = Math.max(left.max, Math.max(right.max, root.data));
                    nPair.min = Math.min(left.min, Math.min(right.min, root.data));
                    nPair.isBst = false;
                }
            } else {
                nPair.size = Math.max(left.size, right.size);
                nPair.max = Math.max(left.max, Math.max(right.max, root.data));
                nPair.min = Math.min(left.min, Math.min(right.min, root.data));
                nPair.isBst = false;
            }
            return nPair;
        }

        static int largestBst(Node root) {
            Pair p = largestBst_(root);
            return p.size;
        }

    }

    public static void main(String[] args) {
        // Solution5.BinaryTreeToBST();
        // bstCall();
    }
}
