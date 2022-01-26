import java.util.*;

public class PrintNodesKDistanceAway {
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

    public static void printKLevelsDown(Node node, int k, Node blocker) {
        // Modified with blocker to block one side.
        if (node == null || k < 0 || node == blocker) {
            return;
        }
        if (k == 0) {
            System.out.println(node.data);
            return;
        }
        printKLevelsDown(node.left, k - 1, blocker);
        printKLevelsDown(node.right, k - 1, blocker);
        return;
    }

    public static ArrayList<Node> nodeToRootPath(Node node, int data) {
        // Modified ArrayList to accept Nodes instead of Integer.
        if (node == null) {
            return new ArrayList<>();
        }
        if (node.data == data) {
            ArrayList<Node> arr = new ArrayList<>();
            arr.add(node);
            return arr;
        }
        ArrayList<Node> arr1 = new ArrayList<>();
        arr1 = nodeToRootPath(node.left, data);
        if (arr1.size() > 0) {
            arr1.add(node);
            return arr1;
        }
        ArrayList<Node> arr2 = new ArrayList<>();
        arr2 = nodeToRootPath(node.right, data);
        if (arr2.size() > 0) {
            arr2.add(node);
            return arr2;
        }
        return new ArrayList<>();
    }

    public static void printKNodesFar(Node node, int data, int k) {
        ArrayList<Node> n2rpath = nodeToRootPath(node, data);
        for (int i = 0; i < n2rpath.size(); i++) {
            printKLevelsDown(n2rpath.get(i), k - i, i == 0 ? null : n2rpath.get(i - 1));
        }
    }

    public static void main(String[] args) {
        Integer[] arr = new Integer[] { 50, 25, 12, null, null, 37, 30, null, null, null, 75, 62, null, 70, null, null,
                87, null, null };
        Node root = construct(arr);
        printKNodesFar(root, 37, 2);
    }
}
