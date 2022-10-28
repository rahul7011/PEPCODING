public class UnionFindQuestions {

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
}
