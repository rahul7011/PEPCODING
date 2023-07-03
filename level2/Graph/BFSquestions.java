import java.util.*;
import java.io.*;

public class BFSquestions {
    // 785. Is Graph Bipartite?
    class Solution {
        public static boolean bipartite(int[][] graph, int src, int[] visited) {
            LinkedList<Integer> q = new LinkedList<>();
            q.addLast(src);
            // -1 :No color,0:Red,1:Green
            int color = 0;
            boolean isCycle = false, isBipartite = true;
            while (q.size() != 0) {
                int size = q.size();
                while (size-- > 0) {
                    int vtx = q.removeFirst();

                    if (visited[vtx] != -1) {
                        isCycle = true;
                        // Cycle has been found
                        if (visited[vtx] != color)// Means a conflict hence odd cycle
                        {
                            return false;
                        }
                        continue;
                    }
                    visited[vtx] = color;
                    for (int e : graph[vtx]) {
                        if (visited[e] == -1) {
                            q.addLast(e);
                        }
                    }
                }
                color = (color + 1) % 2;
            }
            return true;
        }

        public boolean isBipartite(int[][] graph) {
            int N = graph.length;
            int[] visited = new int[N];
            Arrays.fill(visited, -1);
            for (int i = 0; i < N; i++) {
                if (visited[i] == -1 && bipartite(graph, i, visited) == false) {
                    return false;
                }
            }
            return true;
        }
    }

    // 994. Rotting Oranges
    class Solution1 {
        private static int bfs(int[][] graph) {
            int m = graph.length;
            int n = graph[0].length;
            int[][] dir = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
            int level = 0;
            LinkedList<Integer> q = new LinkedList<>();
            int freshCount = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (graph[i][j] == 2)
                        q.addLast(i * n + j);
                    else if (graph[i][j] == 1)
                        freshCount++;
                }
            }
            if (freshCount == 0) {
                return 0;
            }

            while (q.size() != 0) {
                int size = q.size();
                while (size-- > 0) {
                    int idx = q.removeFirst();
                    int i = idx / n;
                    int j = idx % n;
                    for (int d = 0; d < dir.length; d++) {
                        int r = i + dir[d][0];
                        int c = j + dir[d][1];
                        if (r >= 0 && c >= 0 && r < m && c < n && graph[r][c] == 1) {
                            graph[r][c] = 2;
                            q.addLast(r * n + c);
                            freshCount--;
                            if (freshCount == 0) {
                                return level + 1;
                            }
                        }
                    }
                }
                level++;
            }
            return -1;
        }

        public int orangesRotting(int[][] graph) {
            return bfs(graph);
        }
    }

    // 1091. Shortest Path in Binary Matrix
    class Solution2 {
        private static int bfs(int[][] graph) {
            int m = graph.length;
            int n = graph[0].length;
            if (graph[0][0] == 1 || graph[m - 1][n - 1] == 1) {
                return -1;
            }
            if (graph.length == 1) {
                return 1;
            }
            int[][] dir = { { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, -1 }, { 0, 1 }, { 1, -1 }, { 1, 0 }, { 1, 1 } };
            int level = 1;
            LinkedList<Integer> q = new LinkedList<>();
            q.addLast(0);
            graph[0][0] = 1;
            while (q.size() != 0) {
                int size = q.size();
                while (size-- > 0) {
                    int idx = q.removeFirst();
                    int i = idx / n;
                    int j = idx % n;
                    for (int d = 0; d < dir.length; d++) {
                        int r = i + dir[d][0];
                        int c = j + dir[d][1];
                        if (r >= 0 && c >= 0 && r < m && c < n && graph[r][c] == 0) {
                            graph[r][c] = 1;
                            q.addLast(r * n + c);
                            if (r == m - 1 && c == n - 1) {
                                return level + 1;
                            }
                        }
                    }
                }
                level++;
            }
            return -1;
        }

        public int shortestPathBinaryMatrix(int[][] graph) {
            return bfs(graph);
        }
    }

    // 542. 01 Matrix
    class Solution3 {
        private static void bfs(int[][] graph) {
            int m = graph.length;
            int n = graph[0].length;
            int[][] dir = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
            int level = 0;
            LinkedList<Integer> q = new LinkedList<>();
            boolean[][] visited = new boolean[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (graph[i][j] == 0) {
                        q.addLast(i * n + j);
                        visited[i][j] = true;
                    }
                }
            }
            while (q.size() != 0) {
                int size = q.size();
                while (size-- > 0) {
                    int idx = q.removeFirst();
                    int i = idx / n;
                    int j = idx % n;
                    for (int d = 0; d < dir.length; d++) {
                        int r = i + dir[d][0];
                        int c = j + dir[d][1];
                        if (r >= 0 && c >= 0 && r < m && c < n && visited[r][c] == false) {
                            graph[r][c] = graph[i][j] + 1; // or graph[r][c] = level + 1;
                            visited[r][c] = true;
                            q.addLast(r * n + c);
                        }
                    }
                }
                level++;
            }
        }

        public int[][] updateMatrix(int[][] mat) {
            // Instead of appending 1's we are appending zeroes as appending zeroes will
            // lead to calling bfs for each 1's
            // Whereas appending 0's is better as we are finding nearest ones from 0's so we
            // need not call bfs again for each 0's
            bfs(mat);
            return mat;
        }
    }

    // 1020. Number of Enclaves
    class Solution4 {
        int[][] dir = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

        private int gcc(int[][] grid, int i, int j) {
            grid[i][j] = 0;
            int count = 0;
            for (int d = 0; d < dir.length; d++) {
                int sr = i + dir[d][0];
                int sc = j + dir[d][1];
                if (sr >= 0 && sc >= 0 && sr < grid.length && sc < grid[i].length && grid[sr][sc] == 1) {
                    count += gcc(grid, sr, sc);
                }
            }
            return count + 1;
        }

        public int numEnclaves(int[][] grid) {
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    if (i == 0 || i == grid.length - 1 || j == 0 || j == grid[i].length - 1) {
                        if (grid[i][j] == 1) {
                            gcc(grid, i, j);
                        }
                    }
                }
            }
            int count = 0;
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    if (grid[i][j] == 1) {
                        count += gcc(grid, i, j);
                    }
                }
            }
            return count;
        }
    }

    // https://www.lintcode.com/problem/663/
    // 663 · Walls and Gates
    public class Solution5 {
        private static void gcc(int[][] rooms) {
            LinkedList<Integer> q = new LinkedList<>();
            int m = rooms.length;
            int n = rooms[0].length;
            boolean[][] visited = new boolean[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (rooms[i][j] == 0) {
                        q.addLast(i * n + j);
                        visited[i][j] = true; // Marking visited
                    }
                }
            }
            int[][] dir = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
            int level = 0;
            while (q.size() != 0) {
                int size = q.size();
                while (size-- > 0) {
                    int idx = q.removeFirst();
                    int i = idx / n;
                    int j = idx % n;

                    for (int d = 0; d < dir.length; d++) {
                        int r = dir[d][0] + i;
                        int c = dir[d][1] + j;
                        if (r >= 0 && c >= 0 && r < m && c < n && visited[r][c] == false && rooms[r][c] != -1) {
                            rooms[r][c] = rooms[i][j] + 1;
                            q.addLast(r * n + c);
                            visited[r][c] = true;
                        }
                    }
                }
                level++;
            }
        }

        public void wallsAndGates(int[][] rooms) {
            gcc(rooms);
        }
    }

    // 743. Network Delay Time
    class Solution6 {
        private static int dijkstra(ArrayList<int[]>[] graph, int src) {
            int V = graph.length;
            boolean[] vis = new boolean[V];
            int[] dis = new int[V];

            Arrays.fill(dis, (int) 1e9);

            // {V,wsf}
            PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
                return a[1] - b[1];
            });

            pq.add(new int[] { src, 0 });

            while (pq.size() != 0) {
                int[] rp = pq.remove();
                if (vis[rp[0]] == true) {
                    continue;
                }
                vis[rp[0]] = true;
                dis[rp[0]] = rp[1];

                for (int[] p : graph[rp[0]]) {
                    if (vis[p[0]] == false) {
                        pq.add(new int[] { p[0], p[1] + rp[1] });
                    }
                }
            }
            // checking ,if we are abel to visited every node or not
            // from 1 because nodes are given from 1 to N
            int maxTime = -1;
            for (int i = 1; i < V; i++) {
                if (dis[i] == (int) 1e9) {
                    return -1;
                } else {
                    maxTime = Math.max(maxTime, dis[i]);
                }
            }
            return maxTime;
        }

        public int networkDelayTime(int[][] times, int n, int k) {
            // forming graph now
            // {v,w}
            ArrayList<int[]>[] graph = new ArrayList[n + 1];
            for (int i = 1; i < graph.length; i++) {
                graph[i] = new ArrayList<>();
            }

            for (int[] time : times) {
                int u = time[0];
                int v = time[1];
                int w = time[2];
                graph[u].add(new int[] { v, w });
            }
            return dijkstra(graph, k);
        }
    }

    // 743. Network Delay Time
    // With better dijkstra
    class Solution7 {
        public static class Pair {
            int src;
            int wsf;

            Pair(int src, int wsf) {
                this.src = src;
                this.wsf = wsf;
            }
        }

        public static class Edge {
            int v;
            int w;

            Edge(int v, int w) {
                this.v = v;
                this.w = w;
            }
        }

        private static int dijkstra_btr(ArrayList<Edge>[] graph, int src) {
            int N = graph.length;
            boolean[] vis = new boolean[N];
            int[] par = new int[N];
            Arrays.fill(par, -1);
            int[] dis = new int[N];
            Arrays.fill(dis, (int) 1e9);
            PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> {
                return a.wsf - b.wsf;
            });
            pq.add(new Pair(src, 0));
            par[src] = -1;
            dis[src] = 0;
            while (pq.size() != 0) {
                Pair rp = pq.remove();
                if (vis[rp.src] == true) {
                    continue;
                }
                vis[rp.src] = true;
                for (Edge e : graph[rp.src]) {
                    if (vis[e.v] == false && dis[e.v] > rp.wsf + e.w) {
                        pq.add(new Pair(e.v, rp.wsf + e.w));
                        par[e.v] = rp.src;
                        dis[e.v] = rp.wsf + e.w;
                    }
                }
            }
            // checking ,if we are abel to visited every node or not
            // from 1 because nodes are given from 1 to N
            int maxTime = -1;
            for (int i = 1; i < N; i++) {
                if (dis[i] == (int) 1e9) {
                    return -1;
                } else {
                    maxTime = Math.max(maxTime, dis[i]);
                }
            }
            return maxTime;
        }

        public int networkDelayTime(int[][] times, int n, int k) {
            // forming graph now
            // {v,w}
            ArrayList<Edge>[] graph = new ArrayList[n + 1];
            for (int i = 1; i < graph.length; i++) {
                graph[i] = new ArrayList<>();
            }

            for (int[] time : times) {
                int u = time[0];
                int v = time[1];
                int w = time[2];
                graph[u].add(new Edge(v, w));
            }
            return dijkstra_btr(graph, k);
        }
    }

    class Solution8 {
        private static int bellmanFord(int[][] edges, int N, int src, int dst, int k) {
            int[] prev = new int[N];
            Arrays.fill(prev, (int) 1e9);
            prev[src] = 0;

            for (int edgeCount = 0; edgeCount <= k; edgeCount++) {
                // copying prev in each iteration
                int[] curr = new int[N];
                for (int i = 0; i < prev.length; i++) {
                    curr[i] = prev[i];
                }
                boolean isAnyUpdate = false;
                // Now iterating over each edge to determine shorter distance
                for (int[] e : edges) {
                    int u = e[0], v = e[1], w = e[2];
                    if (prev[u] + w < curr[v]) {
                        curr[v] = prev[u] + w;
                        isAnyUpdate = true;
                    }
                }
                if (isAnyUpdate == false) {
                    return curr[dst] == (int) 1e9 ? -1 : curr[dst];
                }

                for (int i = 0; i < prev.length; i++) {
                    prev[i] = curr[i];
                }
            }
            return prev[dst] == (int) 1e9 ? -1 : prev[dst];
        }

        public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
            return bellmanFord(flights, n, src, dst, k);
        }
    }

    // Leetcode 490
    // https://leetcode.ca/all/490.html
    // https://www.lintcode.com/problem/787/description
    public class Solution9 {
        public boolean hasPath(int[][] maze, int[] start, int[] dst) {
            int m = maze.length;
            int n = maze[0].length;
            int sr = start[0], sc = start[1], er = dst[0], ec = dst[1];
            boolean[][] visited = new boolean[m][n];
            LinkedList<Integer> q = new LinkedList<>();
            q.addLast(sr * n + sc);
            visited[sr][sc] = true;
            while (q.size() != 0) {
                int size = q.size();
                while (size-- > 0) {
                    int rm = q.removeFirst();
                    int i = rm / n;
                    int j = rm % n;
                    int[][] dir = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
                    for (int d = 0; d < dir.length; d++) {
                        int r = i;
                        int c = j;
                        // This is another way of writing radius loop
                        while (r >= 0 && r < m && c >= 0 && c < n && maze[r][c] == 0) {
                            r += dir[d][0];
                            c += dir[d][1];
                        }
                        r -= dir[d][0];
                        c -= dir[d][1];

                        if (visited[r][c] == true) {
                            continue;
                        }
                        visited[r][c] = true;
                        q.addLast(r * n + c);
                        if (r == er && c == ec) {
                            // I can write this here,beacause we are just interested in finding a path
                            return true;
                        }
                    }
                }
            }
            return false;
        }
    }

    // 505. The Maze II
    // https://leetcode.ca/all/505.html
    // https://www.lintcode.com/problem/788/record
    public class Solution10 {
        public static class pair implements Comparable<pair> {
            int r = 0, c = 0, steps = 0;

            pair(int r, int c, int steps) {
                this.r = r;
                this.c = c;
                this.steps = steps;
            }

            @Override
            public int compareTo(pair o) {
                return this.steps - o.steps;
            }
        }

        public int shortestDistance(int[][] maze, int[] start, int[] destination) {
            int n = maze.length, m = maze[0].length, sr = start[0], sc = start[1], er = destination[0],
                    ec = destination[1];
            int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

            int[][] dis = new int[n][m];
            for (int[] d : dis)
                Arrays.fill(d, (int) 1e8);

            PriorityQueue<pair> que = new PriorityQueue<>();
            que.add(new pair(sr, sc, 0));
            dis[sr][sc] = 0;

            while (que.size() != 0) {
                int size = que.size();
                while (size-- > 0) {
                    pair p = que.remove();
                    if (p.r == er && p.c == ec) {
                        // I can't write this inside the dir loop because,here we want minimum distance
                        // and that's
                        // why we can't check the coordinates when we are inserting them into the PQ
                        // beacause that
                        // won't guarantee the min distance
                        return p.steps;
                    }
                    for (int[] d : dir) {
                        int r = p.r, c = p.c, steps = p.steps;
                        while (r >= 0 && c >= 0 && r < n && c < m && maze[r][c] == 0) {
                            r += d[0];
                            c += d[1];
                            steps++;
                        }

                        r -= d[0];
                        c -= d[1];
                        steps--;

                        if (steps >= dis[r][c])
                            continue;

                        que.add(new pair(r, c, steps));
                        dis[r][c] = steps;
                    }
                }
            }
            return -1;
        }
    }

    // https://leetcode.ca/all/499.html
    // premium question of leetcode and lintcode aswell :(
    public static class pair implements Comparable<pair> {
        int r = 0, c = 0, steps = 0;
        String psf = "";

        pair(int r, int c, int steps, String psf) {
            this.r = r;
            this.c = c;
            this.steps = steps;
            this.psf = psf;
        }

        @Override
        public int compareTo(pair o) {
            if (this.steps != o.steps)
                return this.steps - o.steps;
            else
                return this.psf.compareTo(o.psf);
        }
    }

    public String findShortestWay(int[][] maze, int[] start, int[] destination) {
        int n = maze.length, m = maze[0].length, sr = start[0], sc = start[1], er = destination[0], ec = destination[1];
        int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
        String[] dirS = { "d", "u", "r", "l" };
        pair[][] dis = new pair[n][m];
        for (int i = 0; i < n * m; i++)
            dis[i / m][i % m] = new pair(i / m, i % m, (int) 1e8, "");

        PriorityQueue<pair> que = new PriorityQueue<>();
        pair src = new pair(sr, sc, 0, "");

        que.add(src);
        dis[sr][sc] = src;

        while (que.size() != 0) {
            pair p = que.remove();
            for (int i = 0; i < 4; i++) {
                int[] d = dir[i];

                int r = p.r, c = p.c, steps = p.steps;
                while (r >= 0 && c >= 0 && r < n && c < m && maze[r][c] == 0 && !(r == er && c == ec)) {
                    /* !(r == er && c == ec) == (r != er ||c != ec) */
                    r += d[0];
                    c += d[1];
                    steps++;
                }

                if (!(r == er && c == ec)) { // why it is necc. ???
                    r -= d[0];
                    c -= d[1];
                    steps--;
                }

                pair np = new pair(r, c, steps, p.psf + dirS[i]);
                if (steps > dis[r][c].steps || dis[r][c].compareTo(np) <= 0) // why this kind of check ???
                    continue;

                que.add(np);
                dis[r][c] = np;
            }
        }

        pair ans = dis[er][ec];
        return ans.steps != (int) 1e8 ? ans.psf : "impossible";
    }
}