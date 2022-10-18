import java.util.ArrayList;

public class l001 {
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

        idx = findEdge(graph, v, u);
        Edges = graph[v];
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

    public static boolean hasPath(ArrayList<Edge>[] graph, int src, int dest, boolean[] visited) {
        if (src == dest) {
            return true;
        }
        visited[src] = true;
        for (Edge e : graph[src]) {
            if (visited[e.v] == false) {
                if (hasPath(graph, e.v, dest, visited) == true) {
                    return true;
                }
            }
        }
        return false;
    }

    public static int allPath(ArrayList<Edge>[] graph, int src, int dest, boolean[] visited, String psf) {
        if (src == dest) {
            System.out.println(psf + "" + dest);
            return 1;
        }
        visited[src] = true;
        int count = 0;
        for (Edge e : graph[src]) {
            if (visited[e.v] == false) {
                count += allPath(graph, e.v, dest, visited, psf + src + " ");
            }
        }
        visited[src] = false;
        return count;
    }

    public static class Pair {
        int heavyPath = 0;
        String psf = "";

        Pair() {
        }

        Pair(int heavyPath, String psf) {
            this.heavyPath = heavyPath;
            this.psf = psf;
        }
    }

    // heaviest path from src to dest
    public static Pair heavyPath(ArrayList<Edge>[] graph, int src, int dest, boolean[] visited) {
        if (src == dest) {
            return new Pair(0, src + "");
        }
        visited[src] = true;
        Pair ans = new Pair(-1, "");
        for (Edge e : graph[src]) {
            if (visited[e.v] == false) {
                Pair recAns = heavyPath(graph, e.v, dest, visited);
                if (recAns.heavyPath != -1 && recAns.heavyPath + e.w > ans.heavyPath) {
                    ans.heavyPath = recAns.heavyPath + e.w;
                    ans.psf = recAns.psf + src;
                }
            }
        }
        visited[src] = false;
        return ans;
    }

    public static void hamiltonianPath(ArrayList<Edge>[] graph, int src, int visitedCount, boolean[] visited,
            String psf) {
        if (visitedCount == graph.length - 1) {
            System.out.println(psf + "" + src);
        }
        visited[src] = true;
        for (Edge e : graph[src]) {
            if (visited[e.v] == false) {
                hamiltonianPath(graph, e.v, visitedCount + 1, visited, psf + src + " ");
            }
        }
        visited[src] = false;
    }

    public static void hamiltonianCycle(ArrayList<Edge>[] graph, int src, int visitedCount, boolean[] visited,
            String psf) {
        if (visitedCount == graph.length - 1) {
            for (Edge e : graph[src]) {
                if (e.v == 0)
                    System.out.println(psf + "" + src);
            }
        }
        visited[src] = true;
        for (Edge e : graph[src]) {
            if (visited[e.v] == false) {
                hamiltonianCycle(graph, e.v, visitedCount + 1, visited, psf + src + " ");
            }
        }
        visited[src] = false;
    }

    // get connected components
    private static void dfs_compo(ArrayList<Edge>[] graph, int src, boolean[] visited) {
        visited[src] = true;
        for (Edge e : graph[src]) {
            if (visited[e.v] == false) {
                dfs_compo(graph, e.v, visited);
            }
        }
    }

    public static void gcc(ArrayList<Edge>[] graph) {
        int N = graph.length;
        boolean[] visited = new boolean[N];
        int components = 0;
        for (int i = 0; i < N; i++) {
            if (visited[i] == false) {
                components++;
                dfs_compo(graph, i, visited);
            }
        }
        System.out.println(components);
    }


    @SuppressWarnings("unchecked")
    public static void constructGraph() {
        int N = 7;
        ArrayList<Edge>[] graph = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            graph[i] = new ArrayList<>();
        }

        addEdge(graph, 0, 1, 10);
        addEdge(graph, 0, 3, 10);
        addEdge(graph, 1, 2, 10);
        addEdge(graph, 2, 3, 40);
        addEdge(graph, 3, 4, 2);
        addEdge(graph, 4, 5, 2);
        addEdge(graph, 4, 6, 8);
        addEdge(graph, 5, 6, 3);
        addEdge(graph, 6, 0, 60);
        display(graph);

        // System.out.println(findEdge(graph, 3, 2));
        // removeEdge(graph, 3, 2);
        // System.out.println();
        // display(graph);

        // removeVtx(graph, 3);
        // System.out.println();
        // display(graph);

        boolean[] visited = new boolean[N];
        // System.out.println(hasPath(graph, 0, 7, visited));

        // System.out.println(allPath(graph, 0, 6, visited, " "));

        // Pair ans = heavyPath(graph, 0, 7, visited);
        Pair ans = heavyPath(graph, 0, 6, visited);
        System.out.println(ans.heavyPath + " @ " + ans.psf);

        // hamiltonianPath(graph, 0, 0, visited, "");

        // hamiltonianCycle(graph, 0, 0, visited, "");
    }

    public static void main(String[] args) {
        constructGraph();
    }
}