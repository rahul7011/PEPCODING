import java.util.*;
public class levelOrderTraversalLinewise {
    public static class Node {
        int data;
        ArrayList<Node> children = new ArrayList<>();

        // Constructor
        Node(int data) {
            this.data = data;
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

    public static void main(String[] args) {
        Node root = new Node(10);
        Node twenty = new Node(20);
        Node thirty = new Node(30);
        Node forty = new Node(40);
        root.children.add(twenty);
        root.children.add(thirty);
        root.children.add(forty);

        Node fifty = new Node(50);
        Node sixty = new Node(60);
        twenty.children.add(fifty);
        twenty.children.add(sixty);

        Node seventy = new Node(70);
        Node eighty = new Node(80);
        Node ninety = new Node(90);
        thirty.children.add(seventy);
        thirty.children.add(eighty);
        thirty.children.add(ninety);

        Node hundred = new Node(100);
        forty.children.add(hundred);

        Node hundredten = new Node(110);
        Node hundredtwenty = new Node(120);
        eighty.children.add(hundredten);
        eighty.children.add(hundredtwenty);
        levelOrderLineWise(root);

    }
}
