import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;

public class IntroToGenericTree {
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

    public static void levelOrderZigZag(Node root) {
        Stack<Node> mainStack = new Stack<>();
        Stack<Node> localStack = new Stack<>();
        mainStack.add(root);
        int check = 0;
        while (mainStack.size() != 0) {
            // remove
            Node curNode = mainStack.pop();
            // print
            System.out.print(curNode.data+" ");
            // add children by maintaining two stack's
            if (check % 2 == 0) {
                // insert from left to right
                for (Node child : curNode.children) {
                    localStack.push(child);
                }
            } else {
                // insert from right to left
                for(int i=curNode.children.size()-1;i>=0;i--)
                {
                    localStack.push(curNode.children.get(i));
                }
            }

            if(mainStack.size()==0)
            {
                mainStack=localStack;
                localStack=new Stack<>();
                System.out.println();
                check++;
            }
        }

    }
    
    public static void levelOrderZigZag2(Node root)
    {
        int check=0;
        Queue<Node>q=new ArrayDeque<>();
        Stack<Node>st=new Stack<>();
        q.add(root);
        while(q.size()!=0)
        {
            //remove
            Node curNode=q.remove();
            //print
            System.out.print(curNode.data+" ");
            //add childen by maintaining one stack and queue
            if(check%2==0)
            {
                //add children from left to right
                for(Node child:curNode.children)
                {
                    st.push(child);
                }
            }
            else
            {
                for(int i=curNode.children.size()-1;i>=0;i--)
                {
                    st.push(curNode.children.get(i));
                }
            }

            if(q.size()==0)
            {
                while(st.size()!=0)
                {
                    q.add(st.pop());
                }
                System.out.println();
                check++;
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

        // levelOrder(root);
        // levelOrderLineWise(root);
        // levelOrderZigZag(root);
        levelOrderZigZag2(root);

    }
}