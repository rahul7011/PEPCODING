import java.util.ArrayList;
import java.util.LinkedList;

public class l002_directedGraph {
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
    }

    // ======================kahn's algorithm===================

    private static void kahnsAlgo(int n, ArrayList<Edge>[] graph) {
        // calculating indegree of each vertex
        int[] indegree = new int[n];
        for (ArrayList<Edge> list : graph) {
            for (Edge e : list) {
                indegree[e.v]++;
            }
        }
        LinkedList<Integer> q = new LinkedList<>();
        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0) {
                q.addLast(i);
            }
        }
        ArrayList<Integer> ans = new ArrayList<>();
        while (q.size() != 0) {
            int size = q.size();
            while (size-- > 0) {
                int rm = q.removeFirst();
                ans.add(rm);
                for (Edge e : graph[rm]) {
                    if (--indegree[e.v] == 0) {
                        q.addLast(e.v);
                    }
                }
            }
        }
        if (ans.size() != n) {
            System.out.println("Topological Sort can't be performed because graph contains cycle");
            return;
        }
        for (int i = ans.size() - 1; i >= 0; i--) {
            System.out.print(ans.get(i) + " ");
        }
    }

    private static void kahnsAlgo_levels(int n, ArrayList<Edge>[] graph) {
        // calculating indegree of each vertex
        int[] indegree = new int[n];
        for (ArrayList<Edge> list : graph) {
            for (Edge e : list) {
                indegree[e.v]++;
            }
        }
        LinkedList<Integer> q = new LinkedList<>();
        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0) {
                q.addLast(i);
            }
        }
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        while (q.size() != 0) {
            int size = q.size();
            ArrayList<Integer> sans = new ArrayList<>();
            while (size-- > 0) {
                int rm = q.removeFirst();
                sans.add(rm);
                for (Edge e : graph[rm]) {
                    if (--indegree[e.v] == 0) {
                        q.addLast(e.v);
                    }
                }
            }
            ans.add(sans);
        }
        if (ans.size() != n) {
            System.out.println("Topological Sort can't be performed because graph contains cycle");
            return;
        }
        for (int i = ans.size() - 1; i >= 0; i--) {
            System.out.println(ans.get(i) + " " + i);
        }
    }

    // 207. Course Schedule
    class Solution {
        public static class Edge {
            int v, w;

            Edge(int v, int w) {
                this.v = v;
                this.w = w;
            }
        }

        public boolean canFinish(int numCourses, int[][] prerequisites) {
            int N = numCourses;
            ArrayList<Edge>[] graph = new ArrayList[N];
            for (int i = 0; i < N; i++) {
                graph[i] = new ArrayList<>();
            }
            for (int[] list : prerequisites) {
                graph[list[0]].add(new Edge(list[1], 0));
            }
            int[] indegree = new int[N];
            for (int[] list : prerequisites) {
                indegree[list[1]]++;
            }
            LinkedList<Integer> q = new LinkedList<>();
            for (int i = 0; i < indegree.length; i++) {
                if (indegree[i] == 0) {
                    q.addLast(i);
                }
            }
            ArrayList<Integer> ans = new ArrayList<>();
            while (q.size() != 0) {
                int size = q.size();
                while (size-- > 0) {
                    int rm = q.removeFirst();
                    ans.add(rm);
                    for (Edge e : graph[rm]) {
                        if (--indegree[e.v] == 0) {
                            q.addLast(e.v);
                        }
                    }
                }
            }
            if (ans.size() != N) {
                // System.out.println("Topological Sort can't be performed because graph
                // contains cycle");
                return false;
            }
            // for (int i = ans.size() - 1; i >= 0; i--) {
            // System.out.print(ans.get(i) + " ");
            // }
            return true;
        }
    }

    // 210. Course Schedule II
    class Solution1 {
        public static class Edge {
            int v, w;

            Edge(int v, int w) {
                this.v = v;
                this.w = w;
            }
        }

        public int[] findOrder(int numCourses, int[][] prerequisites) {
            int N = numCourses;
            ArrayList<Edge>[] graph = new ArrayList[N];
            for (int i = 0; i < N; i++) {
                graph[i] = new ArrayList<>();
            }
            for (int[] list : prerequisites) {
                graph[list[0]].add(new Edge(list[1], 0));
            }
            int[] indegree = new int[N];
            for (int[] list : prerequisites) {
                indegree[list[1]]++;
            }
            LinkedList<Integer> q = new LinkedList<>();
            for (int i = 0; i < indegree.length; i++) {
                if (indegree[i] == 0) {
                    q.addLast(i);
                }
            }
            int[] ans = new int[N];
            int k = N - 1;
            while (q.size() != 0) {
                int size = q.size();
                while (size-- > 0) {
                    int rm = q.removeFirst();
                    ans[k--] = rm;
                    for (Edge e : graph[rm]) {
                        if (--indegree[e.v] == 0) {
                            q.addLast(e.v);
                        }
                    }
                }
            }
            if (k != -1) {
                // System.out.println("Topological Sort can't be performed because graph
                // contains cycle");
                return new int[] {};
            }
            // for (int i = ans.size() - 1; i >= 0; i--) {
            // System.out.print(ans.get(i) + " ");
            // }
            return ans;
        }
    }

    //================= DFS with cycle detection ============================

    // 207. Course Schedule
    // (again but this tym using dfs with cycle
    // detection(correct way))
    class Solution2 {
        public static class Edge {
            int v, w;

            Edge(int v, int w) {
                this.v = v;
                this.w = w;
            }
        }

        private boolean dfs(ArrayList<Edge>[] graph, int src, int[] visited) {
            visited[src] = 1;
            boolean check = true;
            for (Edge e : graph[src]) {
                if (visited[e.v] == 0) {
                    // unvisited
                    check = check && dfs(graph, e.v, visited);
                } else if (visited[e.v] == 1) {
                    // Means it is part of current path hence cycle
                    return false;
                }
            }
            visited[src] = 2; // backtracked,means visited but not in path
            return check;
        }

        public boolean canFinish(int numCourses, int[][] prerequisites) {
            int N = numCourses;
            ArrayList<Edge>[] graph = new ArrayList[N];
            for (int i = 0; i < N; i++) {
                graph[i] = new ArrayList<>();
            }
            for (int[] list : prerequisites) {
                graph[list[0]].add(new Edge(list[1], 0));
            }
            // 0:unvisited
            // 1:part of path
            // 2:backtracked,means visited but not in path
            int[] visited = new int[N];
            for (int i = 0; i < N; i++) {
                if (visited[i] == 0) {
                    if (dfs(graph, i, visited) == false) {
                        return false;
                    }
                }
            }
            return true;
        }
    }

    // 329. Longest Increasing Path in a Matrix
    // Solved using topological sort(can also be solved using DP)
    class Solution3 {
        int[][] dir = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

        // Solved using Topological sort(kahns algo)
        public int longestIncreasingPath(int[][] matrix) {
            int max = 0;
            int m = matrix.length;
            int n = matrix[0].length;
            int[][] indegree = new int[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    for (int d = 0; d < dir.length; d++) {
                        int r = i + dir[d][0];
                        int c = j + dir[d][1];
                        if (r >= 0 && c >= 0 && r < m && c < n && matrix[r][c] > matrix[i][j]) {
                            indegree[r][c]++;
                        }
                    }
                }
            }

            LinkedList<Integer> q = new LinkedList<>();
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (indegree[i][j] == 0) {
                        q.addLast(i * n + j);
                    }
                }
            }
            int level = 0;
            while (q.size() != 0) {
                int size = q.size();
                while (size-- > 0) {
                    int rm = q.removeFirst();
                    int i = rm / n;
                    int j = rm % n;
                    for (int d = 0; d < dir.length; d++) {
                        int r = i + dir[d][0];
                        int c = j + dir[d][1];
                        if (r >= 0 && c >= 0 && r < m && c < n && matrix[r][c] > matrix[i][j]) {
                            if (--indegree[r][c] == 0) {
                                q.addLast(r * n + c);
                            }
                        }
                    }
                }
                level += 1;
            }
            return level;
        }
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
        // addEdge(graph, 6, 0, 60);
        display(graph);

        // System.out.println(findEdge(graph, 3, 2));
        // removeEdge(graph, 3, 2);
        // System.out.println();
        // display(graph);

        // removeVtx(graph, 3);
        // System.out.println();
        // display(graph);

        boolean[] visited = new boolean[N];
    }

    public static void main(String[] args) {
        constructGraph();
    }
}
