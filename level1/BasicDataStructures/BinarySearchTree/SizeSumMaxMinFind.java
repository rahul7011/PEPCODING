import java.util.*;

public class SizeSumMaxMinFind {
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

    public static int size(Node node) {
        if (node == null) {
            return 0;
        }
        int leftSize = size(node.left);
        int rightSize = size(node.right);
        return leftSize + rightSize + 1;
    }

    public static int sum(Node node) {
        if (node == null) {
            return 0;
        }
        int leftSum = sum(node.left);
        int rightSum = sum(node.right);
        return leftSum + rightSum + node.data;
    }

    public static int max(Node node) {
        if (node.right == null) {
            return node.data;
        }
        return max(node.right);
    }

    public static int min(Node node) {
        if (node.left == null) {
            return node.data;
        }
        return min(node.left);
    }

    public static boolean find(Node node, int data) {
        if (node == null) {
            return false;
        }
        if (node.data == data) {
            return true;
        }
        if (node.data > data) {
            return find(node.left, data);
        } else {
            return find(node.right, data);
        }
    }

    public static void main(String[] args) {
        Integer[] arr = new Integer[] { 50, 25, 12, null, null, 37, 30, null, null, null, 75, 62, null, 70, null, null,
                87, null, null };
        Node root = construct(arr);
        System.out.println(size(root));
        System.out.println(sum(root));
        System.out.println(max(root));
        System.out.println(min(root));
        System.out.println(find(root, 30));
    }
}
