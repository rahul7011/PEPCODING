import java.util.*;
public class levelOrderTraversal {
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

        levelOrder(root);

    }
}