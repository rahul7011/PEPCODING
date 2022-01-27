import java.util.*;

public class DiameterOfGenericTree {
    public static class Node {
        int data;
        ArrayList<Node> children = new ArrayList<>();

        // Constructor
        Node(int data) {
            this.data = data;
        }
    }

    public static void levelOrder(Node root) {
        Queue<Node> q = new ArrayDeque<>();
        q.add(root);
        // Three step Process:
        // a) Remove
        // b) Print
        // c) Add Children
        while (q.size() != 0) {
            // remove
            Node curreNode = q.remove();
            // print
            System.out.print(curreNode.data + " ");
            // Add childrem
            for (int i = 0; i < curreNode.children.size(); i++) {
                q.add(curreNode.children.get(i));
            }
        }

    }

    public static void levelOrderLineWise(Node root) {
        Queue<Node> Q = new ArrayDeque<>();
        Queue<Node> childQ = new ArrayDeque<>();
        Q.add(root);
        while (Q.size() != 0) {
            // remove
            Node curNode = Q.remove();
            // print
            System.out.print(curNode.data + " ");
            // add children by maintaining two Q's
            for (Node child : curNode.children) {
                childQ.add(child);
            }

            if (Q.size() == 0) {
                Q = childQ;
                childQ = new ArrayDeque<>();
                System.out.println();
            }
        }
    }

    public static Node construct(int[] arr) {
        Stack<Node> st = new Stack<>();
        Node root = null;
        for (int val : arr) {
            if (val != -1) {
                // make a node and push it to the stack
                Node newNode = new Node(val);
                st.push(newNode);
            } else {
                Node newNode = st.pop();
                // pop the Node and make this node as an children to the top of the stack Node
                if (st.size() > 0) {
                    Node parent = st.peek();
                    parent.children.add(newNode);
                } else {
                    root = newNode;
                }
            }
        }
        return root;
    }

    public static int size(Node root) {
        int localsize = 0;
        for (int i = 0; i < root.children.size(); i++) {
            localsize += size(root.children.get(i));
        }
        return localsize + 1;
    }

    public static int height(Node root) {
        int localHeight = -1;
        for (Node child : root.children) {
            int tempHeight = height(child);
            localHeight = Math.max(localHeight, tempHeight);
        }
        return localHeight + 1;
    }

    // Approach use PairClass O(n)
    public static class diaPair {
        int height = 0;
        int dia = 0;
    }

    public static diaPair diameter(Node node) {
        int currlargestHeight = -1;
        int secondlargestHeight = -1;
        int maxChildDiameter = 0;
        for (Node child : node.children) {
            diaPair dp = diameter(child);
            if (dp.height > currlargestHeight) {
                secondlargestHeight = currlargestHeight;
                currlargestHeight = dp.height;
            } else if (dp.height > secondlargestHeight) {
                secondlargestHeight = dp.height;
            }
            maxChildDiameter = Math.max(maxChildDiameter, dp.dia);
        }
        diaPair cp = new diaPair();
        int newDiameter = currlargestHeight + secondlargestHeight + 2;
        cp.height = currlargestHeight + 1;
        if (maxChildDiameter < newDiameter) {
            cp.dia = newDiameter;
        } else {
            cp.dia = maxChildDiameter;
        }
        return cp;
    }

    public static void main(String[] args) {
        //construct the tree from the given array
        int[] arr={10,20,50,-1,60,-1,-1,30,70,-1,80,110,-1,120,-1,-1,90,-1,-1,40,100,-1,-1,-1};
        
        Node root=construct(arr);
        diaPair dp=diameter(root);
        System.out.println(dp.dia);
    }
}