import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class l004_Algos {
    public static class Edge {
        int v, w;

        Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }

    // O(2E)
    public static void display(ArrayList<Edge>[] graph) {
        for (int u = 0; u < graph.length; u++) {
            System.out.print(u + "->");
            for (int idx = 0; idx < graph[u].size(); idx++) {
                Edge e = graph[u].get(idx);
                System.out.print("(" + e.v + "," + e.w + ") ");
            }
            System.out.println();
        }
    }

    public static int findEdge(ArrayList<Edge>[] graph, int u, int v) {
        for (int idx = 0; idx < graph[u].size(); idx++) {
            Edge e = graph[u].get(idx);
            if (e.v == v) {
                return idx;
            }
        }
        return -1;
    }

    public static void removeEdge(ArrayList<Edge>[] graph, int u, int v) {
        int idx = findEdge(graph, u, v);
        if (idx == -1) {
            System.out.println("Edge not present");
            return;
        }
        ArrayList<Edge> Edges = graph[u];
        Edges.remove(idx);
    }

    public static void removeVtx(ArrayList<Edge>[] graph, int u) {
        for (int i = graph[u].size() - 1; i >= 0; i--) {
            Edge e = graph[u].get(i);
            removeEdge(graph, u, e.v);
        }
    }

    public static void addEdge(ArrayList<Edge>[] graph, int u, int v, int w) {
        graph[u].add(new Edge(v, w));
        graph[v].add(new Edge(u, w));
    }

    // ===========Algos=====================
    public static class Pair {
        int src = 0;
        int par = 0;
        int w = 0;
        int wsf = 0;

        // prims
        Pair(int src, int par, int w) {
            this(src, par, w, 0); // this is called constructor chaining
        }

        // dijkstra
        Pair(int src, int par, int w, int wsf) {
            this.src = src;
            this.par = par;
            this.w = w;
            this.wsf = wsf;
        }

        // dijkstra_better
        Pair(int src, int wsf) {
            this(src, -1, 0, wsf);
        }
    }

    // Standard dijkstra(Not optimal:sometimes add multiple same nodes onto the
    // PriorityQueue)
    public static void dijkstra(ArrayList<Edge>[] graph, int V, int src) {
        ArrayList<Edge>[] dijkGraph = new ArrayList[V];
        for (int i = 0; i < V; i++) {
            dijkGraph[i] = new ArrayList<>();
        }

        boolean[] vis = new boolean[V];
        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> {
            return a.wsf - b.wsf;
        });
        pq.add(new Pair(src, -1, 0, 0));
        while (pq.size() != 0) {
            Pair rp = pq.remove();
            if (vis[rp.src] == true) {
                continue;
            }
            vis[rp.src] = true;

            if (rp.par != -1) {
                // if we need to form graph
                addEdge(dijkGraph, rp.src, rp.par, rp.w);
            }

            for (Edge e : graph[rp.src]) {
                if (vis[e.v] == false) {
                    pq.add(new Pair(e.v, rp.src, e.w, rp.wsf + e.w));
                }
            }
        }
    }

    public static void prims(ArrayList<Edge>[] graph, int V, int src) {
        ArrayList<Edge>[] dijkGraph = new ArrayList[V];
        for (int i = 0; i < V; i++) {
            dijkGraph[i] = new ArrayList<>();
        }

        boolean[] vis = new boolean[V];
        // only change w.r.t Dijkstra
        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> {
            return a.w - b.w;
        });
        pq.add(new Pair(src, -1, 0, 0));
        while (pq.size() != 0) {
            Pair rp = pq.remove();
            if (vis[rp.src] == true) {
                continue;
            }
            vis[rp.src] = true;

            if (rp.par != -1) {
                // if we need to form graph
                addEdge(dijkGraph, rp.src, rp.par, rp.w);
            }

            for (Edge e : graph[rp.src]) {
                if (vis[e.v] == false) {
                    pq.add(new Pair(e.v, rp.src, e.w, rp.wsf + e.w));
                }
            }
        }
    }

    // Dijkstra optimised(with the help of distance array)
    public static void dijkstra_optimised(ArrayList<Edge>[] graph, int V, int src) {
        ArrayList<Edge>[] dijkGraph = new ArrayList[V];
        for (int i = 0; i < V; i++) {
            dijkGraph[i] = new ArrayList<>();
        }

        boolean[] vis = new boolean[V];

        // distance array
        int[] dis = new int[V];
        Arrays.fill(dis, (int) 1e9);

        // parent array
        int[] par = new int[V];
        Arrays.fill(par, (int) -1);

        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> {
            return a.wsf - b.wsf;
        });
        pq.add(new Pair(src, 0));
        par[src] = -1;
        dis[src] = 0;

        while (pq.size() != 0) {
            Pair rp = pq.remove();
            if (vis[rp.src] == true) { // or we can use: if(dis[rp.src]<=rp.wsf)
                continue;
            }
            vis[rp.src] = true;

            if (rp.par != -1) {
                // if we need to form graph
                addEdge(dijkGraph, rp.src, rp.par, rp.w);
            }

            for (Edge e : graph[rp.src]) {
                if (vis[e.v] == false && dis[e.v] > e.w + rp.wsf) {
                    pq.add(new Pair(e.v, rp.wsf + e.w));
                    par[e.v] = rp.src;
                    dis[e.v] = e.w + rp.wsf;
                }
            }
        }
    }

    // {src,dest,weight}
    // O(EV)
    private static void bellmanFord(int[][] edges, int N, int src) {
        int[] prev = new int[N];
        Arrays.fill(prev, (int) 1e9);   //infinity
        prev[src] = 0;
        boolean isAnyNegativeCycle = false;
        for (int edgeCount = 0; edgeCount <= N; edgeCount++) {
            // copying prev in each iteration
            int[] curr = new int[N];
            boolean isAnyUpdate = false;
            // Now iterating over each edge to determine shorter distance
            for (int[] e : edges) {
                int u = e[0], v = e[1], w = e[2];
                if (prev[u] + w < curr[v]) {
                    curr[v] = prev[u] + w;
                    isAnyUpdate = true;
                }
            }

            if (edgeCount == N && isAnyUpdate == true) {
                isAnyNegativeCycle = true;
            }

            if (isAnyUpdate == false) {
                break;
            }

            for (int i = 0; i < curr.length; i++) {
                prev[i] = curr[i];
            }
        }
    }
}
