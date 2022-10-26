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
}