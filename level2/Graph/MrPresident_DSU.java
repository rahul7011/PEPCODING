import java.util.*;
import java.io.*;

public class MrPresident_DSU {
    class TestClass {
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
        public static int unionFind(int[][] Edges, int N, long k) {
            par = new int[N + 1];
            size = new int[N + 1];

            for (int i = 0; i < N + 1; i++) {
                par[i] = i; // Initially all are parent of themselves
                size[i] = 1; // and their size is 1
            }
            long sum = 0;
            int components = N;
            ArrayList<Integer> list = new ArrayList<>();
            for (int[] e : Edges) {
                int u = e[0], v = e[1], w = e[2];

                int p1 = findPar(u);
                int p2 = findPar(v);

                if (p1 != p2) {
                    union(p1, p2);
                    list.add(w);
                    sum += w;
                    components--;
                } else {
                    // this part denotes a cycle
                }
            }
            if (components > 1) {
                return -1;
            }
            Collections.reverse(list);
            int i = 0;
            while (i < list.size() && sum > k) {
                sum -= list.get(i);
                i++;
                sum++;
            }
            return sum > k ? -1 : i;
        }

        public static int kruskal(int[][] Edges, int N, long k) {
            // {{u,v,w}}
            // Sorted on the basis of weight
            Arrays.sort(Edges, (a, b) -> {
                return a[2] - b[2];
            });

            // Now just simple plain DSU
            return unionFind(Edges, N, k);
        }

        public static void main(String args[]) throws Exception {
            FastScanner scn = new FastScanner();    //fast IO for the optimisation
            int n = scn.nextInt();
            int m = scn.nextInt();
            long k = scn.nextLong();
            int[][] edges = new int[m][3];
            for (int i = 0; i < m; i++) {
                edges[i][0] = scn.nextInt();
                edges[i][1] = scn.nextInt();
                edges[i][2] = scn.nextInt();
            }
            System.out.println(kruskal(edges, n, k));
        }

    }

    static class FastScanner {

        // I don't understand how this works lmao
        private int BS = 1 << 16;
        private char NC = (char) 0;
        private byte[] buf = new byte[BS];
        private int bId = 0, size = 0;
        private char c = NC;
        private double cnt = 1;
        private BufferedInputStream in;

        public FastScanner() {
            in = new BufferedInputStream(System.in, BS);
        }

        public FastScanner(String s) {
            try {
                in = new BufferedInputStream(new FileInputStream(new File(s)), BS);
            } catch (Exception e) {
                in = new BufferedInputStream(System.in, BS);
            }
        }

        private char getChar() {
            while (bId == size) {
                try {
                    size = in.read(buf);
                } catch (Exception e) {
                    return NC;
                }
                if (size == -1)
                    return NC;
                bId = 0;
            }
            return (char) buf[bId++];
        }

        public int nextInt() {
            // returns Int
            return (int) nextLong();
        }

        public int[] nextInts(int N) {
            // returns array of integers of size N
            int[] res = new int[N];
            for (int i = 0; i < N; i++) {
                res[i] = (int) nextLong();
            }
            return res;
        }

        public long[] nextLongs(int N) {
            // returns array of Longs of size N
            long[] res = new long[N];
            for (int i = 0; i < N; i++) {
                res[i] = nextLong();
            }
            return res;
        }

        public long nextLong() {
            // return long
            cnt = 1;
            boolean neg = false;
            if (c == NC)
                c = getChar();
            for (; (c < '0' || c > '9'); c = getChar()) {
                if (c == '-')
                    neg = true;
            }
            long res = 0;
            for (; c >= '0' && c <= '9'; c = getChar()) {
                res = (res << 3) + (res << 1) + c - '0';
                cnt *= 10;
            }
            return neg ? -res : res;
        }

        public double nextDouble() {
            // return double
            double cur = nextLong();
            return c != '.' ? cur : cur + nextLong() / cnt;
        }

        public double[] nextDoubles(int N) {
            // return array of doubles
            double[] res = new double[N];
            for (int i = 0; i < N; i++) {
                res[i] = nextDouble();
            }
            return res;
        }

        public String next() {
            StringBuilder res = new StringBuilder();
            while (c <= 32)
                c = getChar();
            while (c > 32) {
                res.append(c);
                c = getChar();
            }
            return res.toString();
        }

        public String nextLine() {
            StringBuilder res = new StringBuilder();
            while (c <= 32)
                c = getChar();
            while (c != '\n') {
                res.append(c);
                c = getChar();
            }
            return res.toString();
        }

        public boolean hasNext() {
            if (c > 32)
                return true;
            while (true) {
                c = getChar();
                if (c == NC)
                    return false;
                else if (c > 32)
                    return true;
            }
        }
    }

}
