import java.util.ArrayList;
import java.util.Arrays;

public class l003_unionFind {
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

    // ===================== DSU ========================
    static int[] par, size;

    public static int findPar(int u) {
        return par[u] == u ? u : (par[u] = findPar(par[u]));
    }

    // Optional:With Union our TC would be O(4) else it would be O(6-7)
    public static void union(int p1, int p2) {
        if (size[p1] < size[p2]) {
            par[p1] = p2;
            size[p2] += size[p1];
        } else {
            par[p2] = p1;
            size[p1] += size[p2];
        }
    }

    // {{u,v,w}}
    public static void unionFind(int[][] Edges, int N) {
        ArrayList<Edge>[] graph = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            graph[i] = new ArrayList<>();
        }
        par = new int[N];
        size = new int[N];

        for (int i = 0; i < N; i++) {
            par[i] = i; // Initially all are parent of themselves
            size[i] = 1; // and their size is 1
        }

        for (int[] e : Edges) {
            int u = e[0], v = e[1], w = e[2];

            int p1 = findPar(u);
            int p2 = findPar(v);

            if (p1 != p2) {
                union(p1, p2);
                addEdge(graph, u, v, w); // This will be a spanning tree
            } else {
                // this part denotes a cycle
            }
        }
    }

    // ========KRUSKAL ALGO =================
    // Gives MST
    public static void kruskal(int[][] Edges, int N) {
        // {{u,v,w}}
        // Sorted on the basis of weight
        Arrays.sort(Edges, (a, b) -> {
            return a[2] - b[2];
        });

        // Now just simple plain DSU
        unionFind(Edges, N);
    }
}
