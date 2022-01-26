import java.util.*;

public class RemoveNodeFromBst {
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

    public static int maxFromLeft(Node node) {
        if (node.right == null) {
            return node.data;
        } else {
            return maxFromLeft(node.right);
        }
    }

    public static Node remove(Node node, int data) {
        if (node == null) {
            return null;

        }
        if (node.data > data) {
            node.left = remove(node.left, data);
        } else if (node.data < data) {
            node.right = remove(node.right, data);
        } else {
            // if child is none
            if (node.left == null && node.right == null) {
                return null;
            } else if ((node.left != null && node.right == null) || (node.left == null && node.right != null)) {
                // if only one child is present
                if (node.left != null && node.right == null) {
                    return node.left;
                } else {
                    return node.right;
                }
            } else {
                // if both the child are present
                int maxdata = maxFromLeft(node.left);
                node.data = maxdata;
                node.left = remove(node.left, maxdata);
            }
        }
        return node;
    }

    public static void main(String[] args) {
        Integer[] arr = new Integer[] { 50, 25, 12, null, null, 37, 30, null, null, null, 75, 62, null, 70, null, null,
                87, null, null };
        Node root = construct(arr);
        System.out.println("Before:");
        display(root);
        System.out.println("After:");
        root=remove(root, 30);  //leaf Node(Zero Child)
        root=remove(root, 62);  //Single Child
        root=remove(root, 25);  //both Child
        display(root);
    }
}
