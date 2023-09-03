// Can also solve LCA in O(logn)

import java.util.*;

public class binaryLifting {

    /*
     * ==========Binary Lifting===========
     * 
     * 1. For every node u
     * Store the following information:
     * Up(u,2^i) //i.e, Node that is 2^i levels above you
     * 
     * 2. Answer a query (q,k)
     * 
     * 3.Represent k in the form of binary
     * 
     * 4. k=(010110) =>2^4+2^2+2^1
     * when you reach 2^4 node from current node then ask that node for => 2^2+2^1
     * level node
     * when you reach 2^2 node from current node then ask that node for => 2^1 level
     * node
     * 
     * 5. base case Up(root,x)= -1 ,because there's no node above root so -1
     * 
     * 6. How to calculate the Up table
     * we know
     * Up(u,0)=par[u] // ie, 1 level above(means u's parent)
     * let us suppose we have calculated few levels
     * Up(u,1)=value
     * Up(u,2)=value
     * Up(u,3)=value
     * To calculate Up(u,x)
     * Up(u,x)=Up(x,Up(u,x-1)) ie,16 can be written as 8+8
     */

    // https://cses.fi/problemset/task/1687
    private static void bLifting(int node, int par, ArrayList<Integer>[] tree, int[][] up) {
        up[node][0] = par;
        for (int i = 1; i < 20; i++) {
            if (up[node][i - 1] != -1) {
                up[node][i] = up[up[node][i - 1]][i - 1];
            } else {
                up[node][i] = -1;
            }
        }

        for (int src : tree[node]) {
            if (src != par) {
                bLifting(src, node, tree, up);
            }
        }
    }

    private static int getNodeAtLevelK(int node, int k, int[][] up) {
        if (node == -1 || k == 0) {
            return node;
        }
        for (int i = 19; i >= 0; i--) {
            int mask = (1 << i);
            if ((mask & k) != 0) {
                return getNodeAtLevelK(up[node][i], k - mask, up);
            }
        }
        return -1; // default we won't reach here,java sucks
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int q = scn.nextInt();
        ArrayList<Integer>[] tree = new ArrayList[n + 1];

        for (int i = 0; i < tree.length; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 2; i <= n; i++) {
            int x = scn.nextInt();
            tree[x].add(i);
            // tree[i].add(x);
        }
        int[][] up = new int[n + 4][20];
        bLifting(1, -1, tree, up);
        while (q-- > 0) {
            int node = scn.nextInt();
            int k = scn.nextInt();
            System.out.println(getNodeAtLevelK(node, k, up));
        }
    }
}

//Lowest Common Ancestor 
//O(n) to precompute but after that logn operation for finding LCA
//Note:This solution is perfect but it wont work on leetcode because of the constraints of node's value(-1e5 to 1e5) which is not possible to s
//store inside an primitive array,to solve this using binary Lifting i have to use Maps but using maps I would have lost the original 
//binaryLifting concept thats why I havent used it here.
// https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/description/
class Solution {
    private void getDepth(TreeNode node,int currDepth,HashMap<Integer,Integer>depth)
    {
        if(node==null)
        {
            return;
        }
        depth.put(node.val,currDepth);
        getDepth(node.left,currDepth+1,depth);
        getDepth(node.right,currDepth+1,depth);
    }
    //up[node][2^i level]
    private static void bLifting(TreeNode node, TreeNode par,TreeNode[][] up) {
        if(node==null)
        {
            return;
        }
        up[node.val][0]=par;
        for(int i=1;i<20;i++)
        {
            if(up[node.val][i-1]!=null)
            {
                up[node.val][i]=up[(up[node.val][i-1]).val][i-1];
            }else{
                up[node.val][i]=null;
            }
        }
        bLifting(node.left,node,up);
        bLifting(node.right,node,up);
    }
    private TreeNode makeLevelSame(TreeNode node,int k,TreeNode[][] up)
    {
        if (node == null || k == 0) {
            return node;
        }
        for(int i=19;i>=0;i--)
        {
            int mask=(1<<i);
            if ((mask & k) != 0) {
                return makeLevelSame(up[node.val][i], k - mask, up);
            }
        }
        return null;
    }
    private TreeNode LCAusingBLifting(TreeNode p,TreeNode q,TreeNode[][]up)
    {
        //if parents are same then we have got the LCA
        if(up[p.val][0]==up[q.val][0])
        {
            return up[p.val][0];
        }
        for(int i=19;i>=0;i--)
        {
            if (up[p.val][i]!=up[q.val][i]) {
                return LCAusingBLifting(up[p.val][i],up[q.val][i],up);
            }
        }
        return null;
    }
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        HashMap<Integer,Integer>depth=new HashMap<>();
        getDepth(root,0,depth);
        //There are 10^5 nodes that means log2(10^5) ~= 17 => 17+3 => 20
        TreeNode[][]up=new TreeNode[(int)1e5+3][17+3];
        bLifting(root,null,up);
        int depthP=depth.get(p.val);
        int depthQ=depth.get(q.val);
        //Now we need to make sure that they are on the same depth
        if(depthP<depthQ)
        {
            q=makeLevelSame(q,depthQ-depthP,up);
        }else{
            p=makeLevelSame(p,depthP-depthQ,up);
        }
        //Now they are on same depth
        if(p==q)
        {
            return p;
        }else{
            return LCAusingBLifting(p,q,up);
        }
    }
}


//This is the same code only thing that is changes is up[][] to  HashMap<Pair<Integer,Integer>,TreeNode>up
// https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/description/
class Solution {
    private void getDepth(TreeNode node,int currDepth,HashMap<Integer,Integer>depth)
    {
        if(node==null)
        {
            return;
        }
        depth.put(node.val,currDepth);
        getDepth(node.left,currDepth+1,depth);
        getDepth(node.right,currDepth+1,depth);
    }
    //up[node][2^i level]
    private static void bLifting(TreeNode node, TreeNode par,HashMap<Pair<Integer,Integer>,TreeNode>up) {
        if(node==null)
        {
            return;
        }
        up.put(new Pair(node.val,0),par);
        // up[node.val][0]=par;
        for(int i=1;i<20;i++)
        {
            if(up.get(new Pair(node.val,i-1))!=null)
            {
                TreeNode x=up.get(new Pair(node.val,i-1));
                up.put(new Pair(node.val,i),(up.get(new Pair(x.val,i-1))));
            }else{
                up.put(new Pair(node.val,i),null);
            }
            // if(up[node.val][i-1]!=null)
            // {
            //     up[node.val][i]=up[(up[node.val][i-1]).val][i-1];
            // }else{
            //     up[node.val][i]=null;
            // }
        }
        bLifting(node.left,node,up);
        bLifting(node.right,node,up);
    }
    private TreeNode makeLevelSame(TreeNode node,int k,HashMap<Pair<Integer,Integer>,TreeNode>up)
    {
        if (node == null || k == 0) {
            return node;
        }
        for(int i=19;i>=0;i--)
        {
            int mask=(1<<i);
            if ((mask & k) != 0) {
                return makeLevelSame(up.get(new Pair(node.val,i)), k - mask, up);
                // return makeLevelSame(up[node.val][i], k - mask, up);
            }
        }
        return null;
    }
    private TreeNode LCAusingBLifting(TreeNode p,TreeNode q,HashMap<Pair<Integer,Integer>,TreeNode>up)
    {
        //if parents are same then we have got the LCA
        if(up.get(new Pair(p.val,0))==up.get(new Pair(q.val,0)))
        {
            return up.get(new Pair(p.val,0));
        }
        // if(up[p.val][0]==up[q.val][0])
        // {
        //     return up[p.val][0];
        // }
        for(int i=19;i>=0;i--)
        {
            if (up.get(new Pair(p.val,i))!=up.get(new Pair(q.val,i))) {
                return LCAusingBLifting(up.get(new Pair(p.val,i)),up.get(new Pair(q.val,i)),up);
            }
            // if (up[p.val][i]!=up[q.val][i]) {
            //     return LCAusingBLifting(up[p.val][i],up[q.val][i],up);
            // }
        }
        return null;
    }
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        HashMap<Integer,Integer>depth=new HashMap<>();
        getDepth(root,0,depth);
        //There are 10^5 nodes that means log2(10^5) ~= 17 => 17+3 => 20
        // TreeNode[][]up=new TreeNode[(int)1e5+3][17+3];
        HashMap<Pair<Integer,Integer>,TreeNode>up=new HashMap<>();
        bLifting(root,null,up);
        int depthP=depth.get(p.val);
        int depthQ=depth.get(q.val);
        //Now we need to make sure that they are on the same depth
        if(depthP<depthQ)
        {
            q=makeLevelSame(q,depthQ-depthP,up);
        }else{
            p=makeLevelSame(p,depthP-depthQ,up);
        }
        //Now they are on same depth
        if(p==q)
        {
            return p;
        }else{
            return LCAusingBLifting(p,q,up);
        }
    }
}
