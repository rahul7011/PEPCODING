import java.util.*;
import java.io.*;

public class DFSquestions {
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

    // 200. Number of Islands
    class Solution {
        int[][] dir = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

        private void gcc(char[][] grid, int i, int j) {
            grid[i][j] = '0';
            for (int d = 0; d < dir.length; d++) {
                int sr = i + dir[d][0];
                int sc = j + dir[d][1];
                if (sr >= 0 && sc >= 0 && sr < grid.length && sc < grid[0].length && grid[sr][sc] == '1') {
                    gcc(grid, sr, sc);
                }
            }
        }

        public int numIslands(char[][] grid) {
            int islands = 0;
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    if (grid[i][j] == '1') {
                        gcc(grid, i, j);
                        islands++;
                    }
                }
            }
            return islands;
        }
    }

    // 695. Max Area of Island
    class Solution1 {
        int[][] dir = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

        private int gcc(int[][] grid, int i, int j) {
            grid[i][j] = 0;
            int count = 0;
            for (int d = 0; d < dir.length; d++) {
                int sr = i + dir[d][0];
                int sc = j + dir[d][1];
                if (sr >= 0 && sc >= 0 && sr < grid.length && sc < grid[0].length && grid[sr][sc] == 1) {
                    count += gcc(grid, sr, sc);
                }
            }
            return count + 1;
        }

        public int maxAreaOfIsland(int[][] grid) {
            int maxArea = 0;
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    if (grid[i][j] == 1) {
                        maxArea = Math.max(maxArea, gcc(grid, i, j));
                    }
                }
            }
            return maxArea;
        }
    }

    class Solution2 {
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

    // 463. Island Perimeter(iterative)
    class Solution3 {
        int[][] dir = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

        private int iterative(int[][] grid) {
            int Onescount = 0, nbrCount = 0;
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    if (grid[i][j] == 1) {
                        Onescount++;
                        for (int d = 0; d < dir.length; d++) {
                            int sr = i + dir[d][0];
                            int sc = j + dir[d][1];
                            if (sr >= 0 && sc >= 0 && sr < grid.length && sc < grid[0].length && grid[sr][sc] != 0) {
                                nbrCount++;
                            }
                        }
                    }
                }
            }

            return 4 * Onescount - nbrCount;
        }

        public int islandPerimeter(int[][] grid) {
            return iterative(grid);
        }
    }

    // 463. Island Perimeter(Recursive)
    class Solution4 {
        int[][] dir = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

        private int gcc(int[][] grid, int i, int j) {
            grid[i][j] = -1;
            int count = 0;
            for (int d = 0; d < dir.length; d++) {
                int sr = i + dir[d][0];
                int sc = j + dir[d][1];
                if (sr >= 0 && sc >= 0 && sr < grid.length && sc < grid[0].length && grid[sr][sc] != 0) {
                    if (grid[sr][sc] == -1)
                        continue;
                    count += gcc(grid, sr, sc);
                } else {
                    count++;
                }
            }
            return count;
        }

        public int islandPerimeter(int[][] grid) {
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    if (grid[i][j] == 1) {
                        return gcc(grid, i, j);
                    }
                }
            }
            return 0;
        }
    }

    // 130. Surrounded Regions
    class Solution5 {
        int[][] dir = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

        private void gcc(char[][] grid, int i, int j, char put) {
            grid[i][j] = put;
            for (int d = 0; d < dir.length; d++) {
                int sr = i + dir[d][0];
                int sc = j + dir[d][1];
                if (sr >= 0 && sc >= 0 && sr < grid.length && sc < grid[0].length && grid[sr][sc] == 'O') {
                    gcc(grid, sr, sc, put);
                }
            }
        }

        public void display(char[][] grid) {
            for (char[] d : grid) {
                for (char x : d) {
                    System.out.print(x + " ");
                }
                System.out.println();
            }
        }

        public void solve(char[][] grid) {
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    if (i == 0 || i == grid.length - 1 || j == 0 || j == grid[0].length - 1) {
                        if (grid[i][j] == 'O') {
                            gcc(grid, i, j, 'Y');
                        }
                    }
                }
            }
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    if (grid[i][j] == 'O')
                        grid[i][j] = 'X';
                    if (grid[i][j] == 'Y') {
                        grid[i][j] = 'O';
                    }
                }
            }
        }
    }

    // https://practice.geeksforgeeks.org/problems/number-of-distinct-islands/1
    // Number of Distinct Islands
    class Solution6 {
        int[][] dir = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
        char[] Pos = { 'U', 'R', 'D', 'L' };

        private String gcc(int[][] grid, int i, int j) {
            grid[i][j] = 0;
            String path = "";
            for (int d = 0; d < dir.length; d++) {
                int sr = i + dir[d][0];
                int sc = j + dir[d][1];
                if (sr >= 0 && sc >= 0 && sr < grid.length && sc < grid[0].length && grid[sr][sc] == 1) {
                    path = path + Pos[d] + gcc(grid, sr, sc);
                }
            }
            return path + "b";
        }

        int countDistinctIslands(int[][] grid) {
            HashSet<String> hs = new HashSet<>();
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    if (grid[i][j] == 1) {
                        String path = gcc(grid, i, j);
                        hs.add(path);
                    }
                }
            }
            return hs.size();
        }
    }

}
