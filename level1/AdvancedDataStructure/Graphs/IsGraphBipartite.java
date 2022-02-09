import java.io.*;
import java.util.*;
public class IsGraphBipartite {
    static class Edge {
        int src;
        int nbr;
        int wt;

        Edge(int src, int nbr, int wt) {
            this.src = src;
            this.nbr = nbr;
            this.wt = wt;
        }
    }

    public static class Pair {
        int src;
        int level;

        Pair(int src, int level) {
            this.src = src;
            this.level = level;
        }
    }

    public static boolean isBipartite(ArrayList<Edge>[] graph, int src, Integer[] visited) {
        // logic for visited:
        // null for unvisited
        // 1-for odd
        // 2-for even

        // Simple bfs
        Queue<Pair> q = new ArrayDeque<>();
        q.add(new Pair(src, 0));
        while (q.size() != 0) {
            // remove
            Pair top = q.peek();
            q.remove();

            // mark*
            if (visited[top.src] != null) {
                // means this node is already visited

                // now check whether this is odd or even cycle
                if ((top.level % 2) == (visited[top.src] % 2)) {
                    // this is even cycle hence we can't conclude whether it is
                    // bipartite or not at this point
                    continue;
                } else {
                    // odd cycle ,means it is not bipartite
                    return false;
                }
            }

            if (top.level % 2 == 0) {
                // even
                visited[top.src] = 0;
            } else {
                // odd
                visited[top.src] = 1;
            }

            // add*
            for (Edge edge : graph[top.src]) {
                if (visited[edge.nbr] == null) {
                    q.add(new Pair(edge.nbr, top.level + 1));
                }
            }
        }
        // means this is bipartite
        return true;
    }

    public static void main(String[] args)throws IOException,NumberFormatException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int vtces = Integer.parseInt(br.readLine());
        @SuppressWarnings("unchecked")
        ArrayList<Edge>[] graph = new ArrayList[vtces];
        for (int i = 0; i < vtces; i++) {
            graph[i] = new ArrayList<>();
        }

        int edges = Integer.parseInt(br.readLine());
        for (int i = 0; i < edges; i++) {
            String[] parts = br.readLine().split(" ");
            int v1 = Integer.parseInt(parts[0]);
            int v2 = Integer.parseInt(parts[1]);
            int wt = Integer.parseInt(parts[2]);
            graph[v1].add(new Edge(v1, v2, wt));
            graph[v2].add(new Edge(v2, v1, wt));
        }

        // null for unvisited
        // 1-for odd
        // 2-for even
        Integer[] visited = new Integer[vtces];
        for (int i = 0; i < vtces; i++) {
            if (visited[i] == null) {
                boolean check = isBipartite(graph, i, visited);
                if (check == false) {
                    System.out.println("false");
                    return;
                }
            }
        }
        System.out.println("true");
    }
}