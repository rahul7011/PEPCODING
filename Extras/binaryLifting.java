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
