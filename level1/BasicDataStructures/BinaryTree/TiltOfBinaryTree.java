import java.util.*;

public class TiltOfBinaryTree {
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

    // Approach-1 using static
    static int tilt = 0;

    public static int tilt1(Node node) {
        if (node == null) {
            return 0;
        }
        int leftTreeSum = tilt1(node.left);
        int rightTreeSum = tilt1(node.right);
        tilt += Math.abs(leftTreeSum - rightTreeSum);
        int sum = leftTreeSum + rightTreeSum + node.data;
        return sum;
    }

    // Approach-2 using tiltPair class
    public static class tiltPair {
        int tilt = 0;
        int sum = 0;
    }

    public static tiltPair tilt2(Node node) {
        if (node == null) {
            tiltPair tp = new tiltPair();
            tp.tilt = 0;
            tp.sum = 0;
            return tp;
        }
        tiltPair leftTp = tilt2(node.left);
        tiltPair rightTp = tilt2(node.right);
        tiltPair currentTp = new tiltPair();
        currentTp.tilt = Math.abs(leftTp.sum - rightTp.sum) + leftTp.tilt + rightTp.tilt;
        currentTp.sum = leftTp.sum + rightTp.sum + node.data;
        return currentTp;
    }

    public static void main(String[] args) {
        Integer[] arr = new Integer[] { 50, 25, 12, null, null, 37, 30, null, null, null, 75, 62, null, 70, null, null,
                87, null, null };
        Node root = construct(arr);

        System.out.println("Approach-1 using static:");
        tilt1(root);
        System.out.println(tilt);

        System.out.println("Approach-2 using tiltPair class:");
        tiltPair tp=new tiltPair();
        tp=tilt2(root);
        System.out.println(tp.tilt);
    }
}
