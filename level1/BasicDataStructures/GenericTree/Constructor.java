import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;
public class Constructor {
    public static class Node {
        int data;
        ArrayList<Node> children = new ArrayList<>();

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
    
    public static Node construct(int[] arr) {
        Stack<Node>st=new Stack<>();
        Node root=null;
        for(int val:arr)
        {
            if(val!=-1)
            {
                //make a node and push it to the stack
                Node newNode=new Node(val);
                st.push(newNode);
            }
            else
            {
                Node newNode=st.pop();
                //pop the Node and make this node as an children to the top of the stack Node
                if(st.size()>0)
                {
                    Node parent=st.peek();
                    parent.children.add(newNode);
                }else
                {
                    root=newNode;
                }
            }
        }
        return root;
    }

    public static void main(String[] args) {
        //construct the tree from the given array
        int[] arr={10,20,50,-1,60,-1,-1,30,70,-1,80,110,-1,120,-1,-1,90,-1,-1,40,100,-1,-1,-1};
        
        Node root=construct(arr);
        levelOrderLineWise(root);
    }
}
