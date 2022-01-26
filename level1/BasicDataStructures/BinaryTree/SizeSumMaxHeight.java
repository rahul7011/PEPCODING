import java.util.*;

public class SizeSumMaxHeight {
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
    public static int size(Node root)
    {
        if(root==null)
        {
            return 0;
        }
        int leftSize=size(root.left);
        int rightSize=size(root.right);
        return leftSize+rightSize+1;
    }
    public static int Sum(Node root)
    {
        if(root==null)
        {
            return 0;
        }
        int leftSum=Sum(root.left);
        int rightSum=Sum(root.right);
        return leftSum+rightSum+root.data;
    }
    public static int Max(Node root)
    {
        if(root==null)
        {
            return Integer.MIN_VALUE;
        }
        int leftMax=Max(root.left);
        int rightMax=Max(root.right);
        return Math.max(Math.max(leftMax,rightMax),root.data);
    }
    public static int height(Node root)
    {
        if(root==null)
        {
            return -1;
        }
        int leftHeight=height(root.left);
        int rightHeight=height(root.right);
        return Math.max(leftHeight,rightHeight)+1;
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

    public static void main(String[] args) {
        Integer[] arr = new Integer[] { 50, 25, 12, null, null, 37, 30, null, null, null, 75, 62, null, 70, null, null,
                87, null, null };
        Node root = construct(arr);
        System.out.println("Size:"+size(root));
        System.out.println("Sum:"+Sum(root));
        System.out.println("Max:"+Max(root));
        System.out.println("Height:"+height(root));
    }
}
