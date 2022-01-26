import java.util.*;

public class PrintSingleChildNodes {
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

    public static void printSingleChildNodes(Node node, Node parent) {
        if (node == null) {
            return;
        }
        if (parent != null) {
            if (parent.left != null && parent.right == null) {
                System.out.println(node.data);
            }
            if (parent.right != null && parent.left == null) {
                System.out.println(node.data);
            }
        }
        printSingleChildNodes(node.left, node);
        printSingleChildNodes(node.right, node);
    }

    public static void main(String[] args) {
        Integer[] arr = new Integer[] { 50, 25, 12, null, null, 37, 30, null, null, null, 75, 62, null, 70, null, null,
                87, null, null };
        Node root = construct(arr);
        printSingleChildNodes(root, null);
    }
}
