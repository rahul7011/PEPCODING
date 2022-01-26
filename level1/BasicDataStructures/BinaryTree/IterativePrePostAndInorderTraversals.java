import java.util.*;

public class IterativePrePostAndInorderTraversals {
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

    // New Pair Class
    public static class TraversalPair {
        Node node;
        int state;

        TraversalPair(Node node, int state) {
            this.node = node;
            this.state = state;
        }
    }

    public static void iterativePrePostInTraversal(Node node) {
        Stack<TraversalPair> st = new Stack<>();
        String pre = "";
        String in = "";
        String post = "";
        TraversalPair np = new TraversalPair(node, 1);
        st.push(np);
        while (st.size() != 0) {
            TraversalPair top = st.peek();
            if (top.state == 1) {
                top.state++;
                ;
                pre += top.node.data + " ";
                if (top.node.left != null) {
                    TraversalPair temp = new TraversalPair(top.node.left, 1);
                    st.push(temp);
                }
            } else if (top.state == 2) {
                top.state++;
                ;
                in += top.node.data + " ";
                if (top.node.right != null) {
                    TraversalPair temp = new TraversalPair(top.node.right, 1);
                    st.push(temp);
                }
            } else {
                post += top.node.data + " ";
                st.pop();
            }
        }
        System.out.println("Pre:"+pre);
        System.out.println("In:"+in);
        System.out.println("Post:"+post);
    }

    public static void main(String[] args) {
        Integer[] arr = new Integer[] { 50, 25, 12, null, null, 37, 30, null, null, null, 75, 62, null, 70, null, null,
                87, null, null };
        Node root = construct(arr);
        iterativePrePostInTraversal(root);
    }
}
