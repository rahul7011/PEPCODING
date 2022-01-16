import java.util.*;
public class AreTreesMirrorInShape {
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
        public static int size(Node root)
        {
            int localsize=0;
            for(int i=0;i<root.children.size();i++)
            {
                localsize+=size(root.children.get(i));
            }
            return localsize+1;
        }
        public static int height(Node root)
        {
            int localHeight=-1;
            for(Node child:root.children)
            {
                int tempHeight=height(child);
                localHeight=Math.max(localHeight, tempHeight);
            }
            return localHeight+1;
        }
        public static boolean areMirror(Node n1, Node n2) {
            if(n1.children.size()!=n2.children.size())
            {
                return false;
            }
            int left=0;
            int right=n1.children.size()-1;
            while(left<right)
            {
                boolean check=areMirror(n1.children.get(left), n2.children.get(right));
                left++;
                right--;
                if(check==false)
                {
                    return false;
                }
            }
            return true;
        }
    public static void main(String[] args) {
        int[] arr1={10, 20, -1 ,30, 50, -1, 60, -1, -1, 40, -1, -1};
        int[] arr2={100, 200, -1, 300, 500, -1, 600, -1, -1, 400, -1, -1};
        Node root1=construct(arr1);
        Node root2=construct(arr2);
        System.out.print(areMirror(root2, root1));
    }
}
