import java.util.*;

public class DiameterOfABinaryTree {
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

    public static int height(Node node) {
        if (node == null) {
            return -1;
        }

        int lh = height(node.left);
        int rh = height(node.right);

        int th = Math.max(lh, rh) + 1;
        return th;
    }

    // Approach-1(static method) O(n)
    public static int dia = 0;

    public static int diameter1(Node node) {
        if (node == null) {
            return -1;
        }
        int leftHeight = diameter1(node.left);
        int rightHeight = diameter1(node.right);
        int height = Math.max(leftHeight, rightHeight) + 1;
        if (leftHeight + rightHeight + 2 > dia) {
            dia = leftHeight + rightHeight + 2;
        }
        return height;
    }

    // Approach-2(Mover class) O(n)
    public static class DiaMover {
        int dia = 0;
    }

    public static int diameter2(Node node, DiaMover Mover) {
        if (node == null) {
            return -1;
        }
        int leftHeight = diameter2(node.left, Mover);
        int rightHeight = diameter2(node.right, Mover);
        int height = Math.max(leftHeight, rightHeight) + 1;
        if (leftHeight + rightHeight + 2 > Mover.dia) {
            Mover.dia = leftHeight + rightHeight + 2;
        }
        return height;
    }

    // Approach-3 Unoptimized Approach(O(n^2)
    public static int diameter3(Node node) {
        if (node == null) {
            return 0;
        }
        int leftHeight = height(node.left);
        int rightHeight = height(node.right);
        // This is my current Node diameter (including itself)
        int dia = leftHeight + rightHeight + 2;

        // This is my child's diameter
        int leftDiameter = diameter3(node.left);
        int rightDiameter = diameter3(node.right);
        dia = Math.max(dia, Math.max(leftDiameter, rightDiameter));
        return dia;

    }

    // Approach-4 Using diaPair class O(n){good for these type of questions where we
    // are using some other function to calculate the value of something in some
    // other function}

    public static class diaPair {
        int dia = 0;
        int height = -1;
    }

    public static diaPair diameter4(Node node) {
        if (node == null) {
            diaPair dp = new diaPair();
            dp.dia = 0;
            dp.height = -1;
            return dp;
        }
        diaPair leftDp = diameter4(node.left);
        diaPair rightDp = diameter4(node.right);

        diaPair currentDp = new diaPair();
        currentDp.dia = leftDp.height + rightDp.height + 2;
        currentDp.height = Math.max(leftDp.height, rightDp.height) + 1;
        return currentDp;

    }

    public static void main(String[] args) {
        Integer[] arr = new Integer[] { 50, 25, 12, null, null, 37, 30, null, null, null, 75, 62, null, 70, null, null,
                87, null, null };
        Node root = construct(arr);

        System.out.println("Approach-1(static method) O(n):");
        diameter1(root);
        System.out.println(dia);
        
        System.out.println("Approach-2(Mover class) O(n):");
        DiaMover Mover=new DiaMover();
        diameter2(root,Mover);
        System.out.println(Mover.dia);
        
        System.out.println("Approach-3 Unoptimized Approach(O(n^2):");
        System.out.println(diameter3(root));
        
        System.out.println("Approach-4 Using diaPair class O(n):");
        diaPair dp = new diaPair();
        dp = diameter4(root);
        System.out.println(dp.dia);
    }
}
