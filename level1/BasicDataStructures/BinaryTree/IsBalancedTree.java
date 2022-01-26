import java.util.*;

public class IsBalancedTree {
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
    //Approach-1 using static method
    public static boolean check = true;

    public static int isBalanced(Node node) {
        if (node == null) {
            return -1;
        }
        if (check == false) {
            return -1;
        }
        int leftHeight = isBalanced(node.left);
        int rightHeight = isBalanced(node.right);
        if (Math.abs(leftHeight - rightHeight) > 1) {
            check = false;
        }
        return Math.max(leftHeight, rightHeight) + 1;
    }

    //Approach-2 using balancePair class
    public static class balancePair {
        boolean check;
        int height;
    }

    public static balancePair isBalanced2(Node node) {
        if (node == null) {
            balancePair bp = new balancePair();
            bp.check = true;
            bp.height = -1;
            return bp;
        }
        balancePair lp = isBalanced2(node.left);
        balancePair rp = isBalanced2(node.right);
        balancePair cp = new balancePair();
        if (lp.check == false || rp.check == false) {
            cp.check = false;
        } else {
            if (Math.abs(lp.height - rp.height) > 1) {
                cp.check = false;
            } else {
                cp.check = true;
            }

        }
        cp.height = Math.max(lp.height, rp.height) + 1;
        return cp;
    }

    public static void main(String[] args) {
        Integer[] arr = new Integer[] { 50, 25, 12, null, null, 37, 30, null, null, null, 75, 62, null, 70, null, null,
                87, null, null };
        Node root = construct(arr);

        System.out.println("Approach-1 using static method:");
        isBalanced(root);
        System.out.println(check);

        System.out.println("Approach-2 using balancePair class:");
        balancePair tp=isBalanced2(root);
        System.out.println(tp.check);
    }
}
