import java.util.*;

public class LevelOrderLineWiseAllFourApproach {
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

    // Approach 1:Queue and childQueue
    public static void levelOrder(Node node) {
        Queue<Node> Q = new ArrayDeque<>();
        Queue<Node> childQ = new ArrayDeque<>();
        Q.add(node);
        while (Q.size() != 0) {
            // RPA-Remove Print Add Method
            // remove
            Node rnode = Q.remove();
            // print
            System.out.print(rnode.data + " ");
            // add
            if (rnode.left != null) {
                childQ.add(rnode.left);
            }
            if (rnode.right != null) {
                childQ.add(rnode.right);
            }
            // check
            if (Q.size() == 0) {
                Q = childQ;
                System.out.println();
                childQ = new ArrayDeque<>();
            }
        }
    }

    // Approach 2:Count Method(Sigle Queue is Sufficient)
    public static void levelOrder2(Node node) {
        Queue<Node> Q = new ArrayDeque<>();
        Q.add(node);
        while (Q.size() != 0) {
            int size = Q.size();
            while (size != 0) {
                // RPA-Remove Print Add Method
                // remove
                Node rnode = Q.remove();
                // print
                System.out.print(rnode.data + " ");
                // add
                if (rnode.left != null) {
                    Q.add(rnode.left);
                }
                if (rnode.right != null) {
                    Q.add(rnode.right);
                }
                size--;
            }
            System.out.println();
        }
    }

    // Approach 3:Delimiter Method
    public static void levelOrder3(Node node) {
        // implementing Queue using linkedList as ArrayDeque can't hold null values(Null
        // pointer excepion)

        LinkedList<Node> Q = new LinkedList<>();
        Q.addLast(node);
        Q.addLast(null);
        while (Q.size() != 0) {
            // RPA-Remove Print Add Method
            // remove
            Node rnode = Q.removeFirst();
            // check
            if (rnode == null) {
                System.out.println();
                if (Q.size() != 0) {
                    Q.addLast(null);
                }
                continue;
            }
            // print
            System.out.print(rnode.data + " ");
            // add
            if (rnode.left != null) {
                Q.addLast(rnode.left);
            }
            if (rnode.right != null) {
                Q.addLast(rnode.right);
            }
        }

    }

    // Approach 4:Node pair
    public static class levelPair {
        Node node;
        int level;
    }

    public static void levelOrder4(Node node) {
        Queue<levelPair> Q = new ArrayDeque<>();
        levelPair lp = new levelPair();
        lp.node = node;
        lp.level = 0;
        Q.add(lp);
        int level = 0;
        while (Q.size() != 0) {
            // RPA-Remove Print Add Method
            // remove
            levelPair rpair = Q.remove();
            // check
            if (rpair.level != level) {
                System.out.println();
                level = level + 1;
            }
            // print
            System.out.print(rpair.node.data + " ");
            // add
            if (rpair.node.left != null) {
                levelPair temp = new levelPair();
                temp.node = rpair.node.left;
                temp.level = level + 1;
                Q.add(temp);
            }
            if (rpair.node.right != null) {
                levelPair temp = new levelPair();
                temp.node = rpair.node.right;
                temp.level = level + 1;
                Q.add(temp);
            }
        }
    }

    public static void main(String[] args) {
        Integer[] arr = new Integer[] { 50, 25, 12, null, null, 37, 30, null, null, null, 75, 62, null, 70, null, null,
                87, null, null };
        Node root = construct(arr);
        System.out.println("\nApproach 1(parent-child queue):\n");
        levelOrder(root);
        System.out.println("\nApproach 2(Count Method):\n");
        levelOrder2(root);
        System.out.println("\nApproach 3(Delimiter Method):\n");
        levelOrder3(root);
        System.out.println("\nApproach 4(Node Pair Method):\n");
        levelOrder4(root);
    }
}
