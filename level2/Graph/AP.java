import java.util.ArrayList;
import java.util.List;

public class AP {

    public static class Edge {
        int v, w;

        Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }

    public static void addEdge(ArrayList<Edge>[] graph, int u, int v, int w) {
        graph[u].add(new Edge(v, w));
        graph[v].add(new Edge(u, w));
    }

    private static int[] low, disc, AP;
    private static int time = 0, rootCalls;
    private static boolean[] vis, APoints;

    public static void dfs(int src, int par, ArrayList<Edge>[] graph) {
        disc[src] = low[src] = time++;
        vis[src] = true;

        for (Edge e : graph[src]) {
            if (!vis[e.v]) {

                // if (par == -1)
                // rootCalls++; //Only if we are finding Articulation Points and not
                // concerned with the amount of new connected components will form based on
                // removal of current point

                dfs(e.v, src, graph);

                // Articulation Points
                if (disc[src] <= low[e.v]) {
                    AP[src]++;
                    // APoints[src] = true; //Only if we are finding Articulation Points and not
                    // concerned with the amount of new connected components will form based on
                    // removal of current point
                }
                // Articulation Bridges
                if (disc[src] < low[e.v]) {
                    System.out.println("Articulation Edge : (" + src + "," + e.v + ") ");
                }

                low[src] = Math.min(low[src], low[e.v]);

            } else if (e.v != par)
                low[src] = Math.min(low[src], disc[e.v]);
        }
    }

    public static void tarjans(int src, int par, ArrayList<Edge>[] graph) {
        disc[src] = low[src] = time++;
        vis[src] = true;

        for (Edge e : graph[src]) {
            if (!vis[e.v]) {

                // if (par == -1)
                // rootCalls++; //Only if we are finding Articulation Points and not
                // concerned with the amount of new connected components will form based on
                // removal of current point

                dfs(e.v, src, graph);

                // // Articulation Points
                // if (disc[src] <= low[e.v]) {
                // AP[src]++;
                // // APoints[src] = true; //Only if we are finding Articulation Points and not
                // // concerned with the amount of new connected components will form based on
                // // removal of current point
                // }
                // // Articulation Bridges
                // if (disc[src] < low[e.v]) {
                // System.out.println("Articulation Edge : (" + src + "," + e.v + ") ");
                // }

                low[src] = Math.min(low[src], low[e.v]);

            } else if (e.v != par)
                low[src] = Math.min(low[src], disc[e.v]);
        }
    }

    public static void APB(int N, ArrayList<Edge>[] graph) {
        low = new int[N];
        disc = new int[N];
        AP = new int[N];
        vis = new boolean[N];
        APoints = new boolean[N];

        for (int i = 0; i < N; i++) {
            if (!vis[i]) {
                AP[i] = -1; // This is specially done to handle root node
                dfs(i, -1, graph);
                // if (rootCalls == 1)
                // APoints[i] = false; //Only if we are finding Articulation Points and not
                // concerned with the amount of new connected components will form based on
                // removal of current point
            }
        }

    }

    // Leetcode 1192
    class Solution {
        public static class Edge {
            int v, w;

            Edge(int v, int w) {
                this.v = v;
                this.w = w;
            }
        }

        private static int[] low, disc, AP;
        private static int time = 0;
        private static boolean[] vis;

        private void dfs(int src, int par, ArrayList<Edge>[] graph, List<List<Integer>> ans) {
            vis[src] = true;
            low[src] = disc[src] = time++;
            for (Edge e : graph[src]) {
                if (vis[e.v] == false) {
                    dfs(e.v, src, graph, ans);
                    if (disc[src] <= low[e.v]) {
                        AP[src]++;
                    }
                    if (disc[src] < low[e.v]) {
                        // System.out.println("Articulation Edge : (" + src + "," + e.v + ") ");
                        List<Integer> list = new ArrayList<>();
                        list.add(src);
                        list.add(e.v);
                        ans.add(list);
                    }

                    low[src] = Math.min(low[src], low[e.v]);
                } else if (e.v != par) {
                    low[src] = Math.min(low[src], disc[e.v]);
                }
            }
        }

        private List<List<Integer>> ArticulationBridge(int n, ArrayList<Edge>[] graph) {
            low = new int[n];
            disc = new int[n];
            AP = new int[n];
            vis = new boolean[n];
            List<List<Integer>> ans = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                if (vis[i] == false) {
                    AP[i] = -1;// Specially done to handle root node
                    dfs(i, -1, graph, ans);
                }
            }
            return ans;
        }

        public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
            ArrayList<Edge>[] graph = new ArrayList[n];
            for (int i = 0; i < n; i++) {
                graph[i] = new ArrayList<>();
            }
            for (List<Integer> l : connections) {
                int u = l.get(0);
                int v = l.get(1);
                graph[u].add(new Edge(v, 0));
                graph[v].add(new Edge(u, 0));
            }
            return ArticulationBridge(n, graph);
        }
    }
}