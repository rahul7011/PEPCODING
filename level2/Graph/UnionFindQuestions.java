import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class UnionFindQuestions {

    class Point {
        int x;
        int y;

        Point() {
            x = 0;
            y = 0;
        }

        Point(int a, int b) {
            x = a;
            y = b;
        }
    }

    // 684. Redundant Connection
    class Solution {

        static int[] par, size;

        private static void union(int p1, int p2) {
            if (size[p1] < size[p2]) {
                par[p1] = p2;
                size[p2] += size[p1];
            } else {
                par[p2] = p1;
                size[p1] += size[p2];
            }
        }

        private static int findPar(int u) {
            return par[u] == u ? u : (par[u] = findPar(par[u]));
        }

        private static int[] unionFind(int[][] edges) {
            int N = edges.length + 1;
            par = new int[N];
            size = new int[N];
            for (int i = 0; i < N; i++) {
                par[i] = i;
                size[i] = 1;
            }
            int[] ans = new int[2];
            for (int[] e : edges) {
                int u = e[0], v = e[1];
                int p1 = findPar(u);
                int p2 = findPar(v);
                if (p1 != p2) {
                    union(p1, p2);
                } else {
                    ans[0] = u;
                    ans[1] = v;
                    return ans;
                }
            }
            return ans;
        }

        public int[] findRedundantConnection(int[][] edges) {
            return unionFind(edges);
        }
    }

    // Lexicographically smallest equivalent string
    // https://www.codingninjas.com/codestudio/problems/smallest-equivalent-string_1381859?leftPanelTab=0
    public class Solution1 {
        static int[] par, size;

        private static void union(int p1, int p2) {
            if (p1 > p2) {
                par[p1] = p2;
                size[p2] += size[p1];
            } else {
                par[p2] = p1;
                size[p1] += size[p2];
            }
        }

        private static int findPar(int u) {
            return par[u] == u ? u : (par[u] = findPar(par[u]));
        }

        private static String unionFind(String s, String t, String str) {
            int N = 26;
            par = new int[N];
            size = new int[N];
            for (int i = 0; i < N; i++)
                par[i] = i;
            for (int i = 0; i < s.length(); i++) {
                char sh = s.charAt(i);
                char th = t.charAt(i);
                par[sh - 'a'] = sh - 'a';
                par[th - 'a'] = th - 'a';
                size[sh - 'a'] += 1;
                size[th - 'a'] += 1;
            }
            String ans = "";

            for (int i = 0; i < s.length(); i++) {
                char sh = s.charAt(i);
                char th = t.charAt(i);
                int u = sh - 'a', v = th - 'a';
                int p1 = findPar(u);
                int p2 = findPar(v);
                if (p1 != p2) {
                    union(p1, p2);
                }
            }
            for (int i = 0; i < str.length(); i++) {
                ans += (char) (findPar(str.charAt(i) - 'a') + 'a');
            }
            return ans;
        }

        public static String smallestString(String s, String t, String str) {
            return unionFind(s, t, str);
        }
    }

    // Solved 2D matrix(fetched components and also fetched their sizes) using DSU
    // 695. Max Area of Island
    class Solution2 {
        static int[] par, size;

        private static int findPar(int u) {
            return par[u] == u ? u : (par[u] = findPar(par[u]));
        }

        public int maxAreaOfIsland(int[][] grid) {
            int m = grid.length;
            int n = grid[0].length;
            par = new int[m * n];
            size = new int[m * n];
            for (int i = 0; i < par.length; i++) {
                par[i] = i;
                size[i] = 1;
            }
            int max = 0;
            int[][] dir = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 1) {
                        int p1 = findPar(i * n + j);
                        for (int d = 0; d < dir.length; d++) {
                            int r = i + dir[d][0];
                            int c = j + dir[d][1];
                            if (r >= 0 && c >= 0 && r < m && c < n && grid[r][c] == 1) {
                                int p2 = findPar(r * n + c);
                                if (p1 != p2) {
                                    par[p2] = p1; // we can't do par[p1]=p2 bcoz p1 variable won't be update above
                                    size[p1] += size[p2];
                                }
                            }
                        }
                        max = Math.max(max, size[p1]);
                    }
                }
            }
            return max;
        }
    }

    // 839. Similar String Groups
    class Solution3 {
        static int[] par, size;

        private static int findPar(int u) {
            return par[u] == u ? u : (par[u] = findPar(par[u]));
        }

        static boolean isSimilar(String s1, String s2) {
            int diff = 0;
            for (int i = 0; i < s1.length(); i++) {
                if (s1.charAt(i) != s2.charAt(i))
                    diff++;
                if (diff > 2) {
                    return false;
                }
            }
            return true;
        }

        public int numSimilarGroups(String[] strs) {
            par = new int[strs.length];
            size = new int[strs.length];

            for (int i = 0; i < strs.length; i++) {
                par[i] = i;
                size[i] = 1;
            }
            int components = strs.length;
            for (int i = 0; i < strs.length; i++) {
                String s = strs[i];
                int p1 = findPar(i);
                // Instead of swapping char's ,we are checking each string with other string,if
                // they are similar then we are forming DSU and fetch the total Components(get
                // Connected components)
                for (int j = i + 1; j < strs.length; j++) {
                    if (isSimilar(s, strs[j]) == true) {
                        int p2 = findPar(j);
                        if (p1 != p2) {
                            par[p2] = p1;
                            size[p1] += size[p2];
                            components--;
                        }
                        if (components == 1) {
                            return components;
                        }
                    }
                }
            }
            return components;
        }
    }

    // 1905. Count Sub Islands
    class Solution4 {
        static int[][] dir = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

        private static boolean dfs(int[][] grid1, int[][] grid2, int i, int j) {
            grid2[i][j] = 0;
            boolean res = true;
            for (int d = 0; d < dir.length; d++) {
                int r = i + dir[d][0];
                int c = j + dir[d][1];
                if (r >= 0 && c >= 0 && r < grid1.length && c < grid1[0].length && grid2[r][c] == 1)
                    res = dfs(grid1, grid2, r, c) && res;
            }
            return res && (grid1[i][j] == 1);
        }

        public int countSubIslands(int[][] grid1, int[][] grid2) {
            int m = grid1.length;
            int n = grid1[0].length;
            int ans = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid2[i][j] == 1) {

                        if (dfs(grid1, grid2, i, j) == true)
                            ans++;
                    }

                }
            }
            return ans;
        }
    }

    // 839. Similar String Groups
    class Solution6 {
        static int[] par, size;

        private static int findPar(int u) {
            return par[u] == u ? u : (par[u] = findPar(par[u]));
        }

        static boolean isSimilar(String s1, String s2) {
            int diff = 0;
            for (int i = 0; i < s1.length(); i++) {
                if (s1.charAt(i) != s2.charAt(i))
                    diff++;
                if (diff > 2) {
                    return false;
                }
            }
            return true;
        }

        public int numSimilarGroups(String[] strs) {
            par = new int[strs.length];
            size = new int[strs.length];

            for (int i = 0; i < strs.length; i++) {
                par[i] = i;
                size[i] = 1;
            }
            int components = strs.length;
            for (int i = 0; i < strs.length; i++) {
                String s = strs[i];
                int p1 = findPar(i);
                for (int j = i + 1; j < strs.length; j++) {
                    if (isSimilar(s, strs[j]) == true) {
                        int p2 = findPar(j);
                        if (p1 != p2) {
                            par[p2] = p1;
                            size[p1] += size[p2];
                            components--;
                        }
                        if (components == 1) {
                            return components;
                        }
                    }
                }
            }
            return components;
        }
    }

    // 695. Max Area of Island
    class Solution7 {
        static int[] par, size;

        private static int findPar(int u) {
            return par[u] == u ? u : (par[u] = findPar(par[u]));
        }

        public int maxAreaOfIsland(int[][] grid) {
            int m = grid.length;
            int n = grid[0].length;
            par = new int[m * n];
            size = new int[m * n];
            for (int i = 0; i < par.length; i++) {
                par[i] = i;
                size[i] = 1;
            }
            int max = 0;
            int[][] dir = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 1) {
                        int p1 = findPar(i * n + j);
                        for (int d = 0; d < dir.length; d++) {
                            int r = i + dir[d][0];
                            int c = j + dir[d][1];
                            if (r >= 0 && c >= 0 && r < m && c < n && grid[r][c] == 1) {
                                int p2 = findPar(r * n + c);
                                if (p1 != p2) {
                                    par[p2] = p1; // we can't do par[p1]=p2 bcoz p1 variable won't be update above
                                    size[p1] += size[p2];
                                }
                            }
                        }
                        max = Math.max(max, size[p1]);
                    }
                }
            }
            return max;
        }
    }

    // 1905. Count Sub Islands
    class Solution8 {
        static int[][] dir = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

        private static boolean dfs(int[][] grid1, int[][] grid2, int i, int j) {
            grid2[i][j] = 0;
            boolean res = true;
            for (int d = 0; d < dir.length; d++) {
                int r = i + dir[d][0];
                int c = j + dir[d][1];
                if (r >= 0 && c >= 0 && r < grid1.length && c < grid1[0].length && grid2[r][c] == 1)
                    res = dfs(grid1, grid2, r, c) && res;
            }
            return res && (grid1[i][j] == 1);
        }

        public int countSubIslands(int[][] grid1, int[][] grid2) {
            int m = grid1.length;
            int n = grid1[0].length;
            int ans = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid2[i][j] == 1) {

                        if (dfs(grid1, grid2, i, j) == true)
                            ans++;
                    }

                }
            }
            return ans;
        }
    }

    // 434 · Number of Islands II
    // https://www.lintcode.com/problem/434/description
    public class Solution9 {
        static int[] par, size;

        private static int findPar(int u) {
            return par[u] == u ? u : (par[u] = findPar(par[u]));
        }

        List<Integer> ans = new ArrayList<>();

        public List<Integer> numIslands2(int m, int n, Point[] grid) {
            par = new int[m * n];
            size = new int[m * n];
            int[][] mat = new int[m][n];
            int[][] dir = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
            int count = 0;
            for (int k = 0; k < grid.length; k++) {
                int i = grid[k].x;
                int j = grid[k].y;
                if (mat[i][j] == 1) {
                    // If already land is present,then no need for checking again
                    ans.add(count);
                    continue;
                }
                count++; // for new addition of land
                mat[i][j] = 1;
                par[i * n + j] = (i * n + j);
                int p1 = findPar(i * n + j); // we can skip this step
                for (int d = 0; d < dir.length; d++) {
                    int r = i + dir[d][0];
                    int c = j + dir[d][1];
                    if (r >= 0 && c >= 0 && r < m && c < n && mat[r][c] == 1) {
                        int p2 = findPar(r * n + c);
                        if (p1 != p2) {
                            par[p2] = p1; // we can't do par[p1]=p2 bcoz p1 variable won't be update above
                            // size[p1]+=size[p2];
                            count--; // gcc(get connected components,removing the same island)
                        }
                    }
                }
                ans.add(count);
            }
            return ans;
        }
    }

    // 434 · Number of Islands II
    // https://www.lintcode.com/problem/434/description/

    // Optimised:Without Mat[][] and decreased the height of DSU
    public class Solution10 {
        static int[] par;

        private static int findPar(int u) {
            return par[u] == u ? u : (par[u] = findPar(par[u]));
        }

        List<Integer> ans = new ArrayList<>();

        public List<Integer> numIslands2(int m, int n, Point[] grid) {
            par = new int[m * n];
            Arrays.fill(par, -1);// Initializes with -1,here we can't use 0 as it can be a parent
            int[][] dir = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
            int count = 0;
            for (int k = 0; k < grid.length; k++) {
                int i = grid[k].x;
                int j = grid[k].y;
                if (par[i * n + j] != -1) {
                    // If already land is present,then no need for checking again
                    ans.add(count);
                    continue;
                }
                count++; // for new addition of land
                par[i * n + j] = (i * n + j);
                for (int d = 0; d < dir.length; d++) {
                    int r = i + dir[d][0];
                    int c = j + dir[d][1];
                    if (r >= 0 && c >= 0 && r < m && c < n && par[r * n + c] != -1) {
                        int p1 = findPar(i * n + j); // now we are merging p1(new) to p2(old) present structure
                        int p2 = findPar(r * n + c);
                        if (p1 != p2) {
                            par[p1] = p2; // This tym p1 variable would update
                            count--; // gcc(get connected components,removing the same island)
                        }
                    }
                }
                ans.add(count);
            }
            return ans;
        }
    }

    //Kruskal Algorithm

    // Water Supply In A Village
    // https://www.codingninjas.com/codestudio/problems/water-supply-in-a-village_1380956
    // https://leetcode.ca/2019-02-10-1168-Optimize-Water-Distribution-in-a-Village/
    public class Solution11 {
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
        public static int unionFind(int[][] Edges, int N) {
            par = new int[N];
            size = new int[N];

            for (int i = 0; i < N; i++) {
                par[i] = i; // Initially all are parent of themselves
                size[i] = 1; // and their size is 1
            }
            int ans = 0;
            for (int[] e : Edges) {
                int u = e[0], v = e[1], w = e[2];

                int p1 = findPar(u);
                int p2 = findPar(v);

                if (p1 != p2) {
                    union(p1, p2);
                    ans += w;
                } else {
                    // this part denotes a cycle
                }
            }
            return ans;
        }

        public static int kruskal(int[][] Edges, int N) {
            // {{u,v,w}}
            // Sorted on the basis of weight
            Arrays.sort(Edges, (a, b) -> {
                return a[2] - b[2];
            });

            // Now just simple plain DSU
            return unionFind(Edges, N);
        }

        public static int supplyWater(int n, int k, int[] wells, int[][] pipes) {
            // Forming new Edges
            int[][] Edges = new int[n + k][3];
            for (int i = 0; i < pipes.length; i++) {
                for (int j = 0; j < pipes[i].length; j++) {
                    // Getting old Edges
                    Edges[i][j] = pipes[i][j];
                }
            }
            int kk = 1;
            for (int i = k; i < Edges.length; i++) {
                // Now Making new Edges with vertice 0 and their weights
                Edges[i] = new int[] { 0, (kk), wells[kk - 1] };
                kk++;
            }
            return kruskal(Edges, Edges.length);
        }

    }

    // 1584. Min Cost to Connect All Points
    class Solution12 {
        static int[] par, size;

        private static int findPar(int u) {
            return par[u] == u ? u : (par[u] = findPar(par[u]));
        }

        private static void union(int p1, int p2) {
            if (size[p1] < size[p2]) {
                par[p1] = p2;
                size[p2] += size[p1];
            } else {
                par[p2] = p1;
                size[p1] += size[p2];
            }
        }

        private static int unionFind(ArrayList<int[]> newPoints, int N) {
            par = new int[N];
            size = new int[N];
            for (int i = 0; i < N; i++) {
                par[i] = i;
                size[i] = 1;
            }
            int minWt = 0;
            for (int[] e : newPoints) {
                int u = e[0], v = e[1], w = e[2];
                int p1 = findPar(u);
                int p2 = findPar(v);
                if (p1 != p2) {
                    union(p1, p2);
                    minWt += w;
                }
            }
            return minWt;
        }

        private static int kruskal(ArrayList<int[]> newPoints, int N) {
            Collections.sort(newPoints, (a, b) -> {
                return a[2] - b[2];
            });
            return unionFind(newPoints, N);
        }

        public int minCostConnectPoints(int[][] points) {
            ArrayList<int[]> newPoints = new ArrayList<>();
            int n = points[0].length;
            int mxDist = 0;
            for (int i = 0; i < points.length; i++) {
                int x1 = points[i][0];
                int y1 = points[i][1];
                for (int j = i + 1; j < points.length; j++) {
                    int x2 = points[j][0];
                    int y2 = points[j][1];

                    int dist = Math.abs(Math.abs(x1 - x2) + Math.abs(y1 - y2));
                    mxDist = Math.max(mxDist, dist);
                    newPoints.add(new int[] { i, j, dist });
                }
            }
            return kruskal(newPoints, points.length);
        }
    }

    // 924. Minimize Malware Spread
    class Solution13 {
        static int[] par, population; // population a.k.a size

        private static int findPar(int u) {
            return par[u] == u ? u : (par[u] = findPar(par[u]));
        }

        private static void union(int p1, int p2) {
            if (population[p1] < population[p2]) {
                par[p1] = p2;
                population[p2] += population[p1];
            } else {
                par[p2] = p1;
                population[p1] += population[p2];
            }
        }

        public int minMalwareSpread(int[][] graph, int[] initial) {
            int N = graph.length;
            par = new int[N];
            population = new int[N];
            for (int i = 0; i < N; i++) {
                par[i] = i;
                population[i] = 1;
            }
            // Now we will get the connected components
            for (int i = 0; i < graph.length; i++) {
                for (int j = 0; j < graph[i].length; j++) {
                    if (graph[i][j] == 1) {
                        int p1 = findPar(i);
                        int p2 = findPar(j);
                        if (p1 != p2) {
                            union(p1, p2);
                        }
                    }
                }
            }
            // now we are finding ,infected nodes in the country,initially
            int[] infected = new int[N];
            for (int e : initial) {
                int p = findPar(e);
                infected[p]++;
            }
            // Now determing which node(malware) to choose for removal so that we can
            // minimize the affect maximally
            Arrays.sort(initial);

            int maxPopulation = 0;
            int ans = initial[0];
            for (int e : initial) {
                int p = findPar(e);
                if (infected[p] == 1 && population[p] > maxPopulation) {
                    maxPopulation = population[p];
                    ans = e;
                }
            }
            return ans;
        }
    }

    // 959. Regions Cut By Slashes
    class Solution14 {
        static int[] par, size;

        private static int findPar(int u) {
            return par[u] == u ? u : (par[u] = findPar(par[u]));
        }

        private static void union(int p1, int p2) {
            if (size[p1] < size[p2]) {
                par[p1] = p2;
                size[p2] += size[p1];
            } else {
                par[p2] = p1;
                size[p1] += size[p2];
            }
        }

        public int regionsBySlashes(String[] grid) {
            int n = grid.length + 1;
            par = new int[n * n];
            size = new int[n * n];
            for (int i = 0; i < par.length; i++) {
                par[i] = i;
                size[i] = 1;
            }
            int[][] newGrid = new int[n][n];
            // Making boundary elements parents same
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i == 0 || i == n - 1 || j == 0 || j == n - 1) {
                        int p1 = findPar(0);
                        int p2 = findPar(i * n + j);
                        union(p1, p2);
                    }
                }
            }
            int count = 1;// default
            for (int i = 0; i < grid.length; i++) {
                String s = grid[i];
                int j = 0;
                while (j < s.length()) {
                    char ch = s.charAt(j);
                    if (ch == '/') {
                        // (x,y)->(x,y+1) and (x+1,y)
                        int p1 = findPar(i * n + (j + 1));
                        int p2 = findPar((i + 1) * n + j);
                        if (p1 == p2) {
                            count++;

                        } else {
                            union(p1, p2);
                        }
                    } else if (ch == '\\') {
                        // (x,y)->(x,y) and (x+1,y+1)
                        int p1 = findPar(i * n + j);
                        int p2 = findPar((i + 1) * n + (j + 1));
                        if (p1 == p2) {
                            count++;
                        } else {
                            union(p1, p2);
                        }
                    }
                    j++;
                }
            }
            return count; 
        }
    }

    // 990. Satisfiability of Equality Equations
    class Solution15 {
        static int[] par, size;

        private static int findPar(int u) {
            return par[u] == u ? u : (par[u] = findPar(par[u]));
        }

        private static void union(int p1, int p2) {
            if (size[p1] < size[p2]) {
                par[p1] = p2;
                size[p2] += size[p1];
            } else {
                par[p2] = p1;
                size[p1] += size[p2];
            }
        }

        public boolean equationsPossible(String[] equations) {
            int n = equations.length;
            par = new int[26];
            size = new int[26];
            for (int i = 0; i < 26; i++) {
                par[i] = i;
                size[i] = 1;
            }
            // sbse phle sbko join krdo,jo join hone wale ho
            for (String s : equations) {
                char a = s.charAt(0);
                char sign = s.charAt(1);
                char b = s.charAt(3);

                if (sign == '=') {
                    int p1 = findPar(a - 'a');
                    int p2 = findPar(b - 'a');
                    if (p1 != p2) {
                        union(p1, p2);
                    }
                }
            }
            // ab check kro,jinko join nhi hona tha agr woh bhi joined hai toh return false
            for (String s : equations) {
                char a = s.charAt(0);
                char sign = s.charAt(1);
                char b = s.charAt(3);

                if (sign == '!') {
                    int p1 = findPar(a - 'a');
                    int p2 = findPar(b - 'a');
                    if (p1 == p2) {
                        return false;
                    }
                }
            }
            return true;
        }
    }
}
