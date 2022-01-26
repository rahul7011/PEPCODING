import java.util.*;

public class IsABinarySearchTree {
    public static class Node {
        int data;
        Node left;
        Node right;
    }

    public static class Pair {
        Node node;
        int state;
    }

    public static Node construct(Integer[] arr) {
        Stack<Pair> st = new Stack<>();
        // construct a node
        Node root = new Node();
        root.data = arr[0];

        Pair rootp = new Pair();
        rootp.node = root;
        rootp.state = 1;

        st.push(rootp);
        int idx = 1;
        while (st.size() != 0) {
            Pair peekp = st.peek();
            if (peekp.state == 1) {
                if (arr[idx] != null) {
                    // Assign left
                    Node newNode = new Node();
                    newNode.data = arr[idx];
                    peekp.node.left = newNode;

                    Pair newPair = new Pair();
                    newPair.node = newNode;
                    newPair.state = 1;
                    st.push(newPair);
                }
                peekp.state++;
                idx++;
            } else if (peekp.state == 2) {
                // assign right
                if (arr[idx] != null) {
                    Node newNode = new Node();
                    newNode.data = arr[idx];
                    peekp.node.right = newNode;

                    Pair newPair = new Pair();
                    newPair.node = newNode;
                    newPair.state = 1;
                    st.push(newPair);
                }
                peekp.state++;
                idx++;
            } else {
                // pop
                st.pop();
            }
        }
        return root;
    }

    public static void display(Node root) {
        if (root == null) {
            return;
        }

        String str = " <- " + root.data + " -> ";
        String lstr = root.left != null ? root.left.data + "" : ".";
        String rstr = root.right != null ? root.right.data + "" : ".";
        System.out.println(lstr + str + rstr);

        display(root.left);
        display(root.right);
    }

    public static class BSTpair {
        boolean check;
        int leftMax;
        int rightMin;
    }

    public static BSTpair isBST(Node node) {
        if (node == null) {
            BSTpair bp = new BSTpair();
            bp.check = true;
            bp.leftMax = Integer.MIN_VALUE;
            bp.rightMin = Integer.MAX_VALUE;
            return bp;
        }
        BSTpair lp = isBST(node.left);
        BSTpair rp = isBST(node.right);

        BSTpair cp = new BSTpair();
        if (lp.check == false || rp.check == false) {
            cp.check = false;
        } else {
            if (lp.leftMax < node.data && rp.rightMin > node.data) {
                cp.check = true;
            } else {
                cp.check = false;
            }
        }
        cp.leftMax = Math.max(lp.leftMax, Math.max(rp.leftMax, node.data));
        cp.rightMin = Math.min(lp.rightMin, Math.min(rp.rightMin, node.data));
        return cp;
    }

    public static void main(String[] args) {
        Integer[] arr = new Integer[] { 50, 25, 12, null, null, 37, 30, null, null, null, 75, 62, null, 70, null, null,
                87, null, null };
        Node root = construct(arr);

        System.out.println("Approach- using BSTpair class:");
        BSTpair tp=isBST(root);
        System.out.println(tp.check);
    }
}
