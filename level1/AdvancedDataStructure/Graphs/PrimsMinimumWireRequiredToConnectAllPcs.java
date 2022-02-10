import java.io.*;
import java.util.*;

public class PrimsMinimumWireRequiredToConnectAllPcs {
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

    public static class Pair implements Comparable<Pair> {
        int src;
        int prnt;
        int wt;

        Pair(int src, int prnt, int wt) {
            this.src = src;
            this.prnt = prnt;
            this.wt = wt;
        }

        public int compareTo(Pair that) {
            return this.wt - that.wt;
        }
    }

    public static void Prims(ArrayList<Edge>[] graph) {
        boolean[] visited = new boolean[graph.length];
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        // parent will considered as -1 for starting node
        pq.add(new Pair(0, -1, 0));
        while (pq.size() != 0) {
            Pair top = pq.peek();
            pq.remove();
            if (visited[top.src] == true) {
                continue;
            }
            visited[top.src] = true;
            if (top.prnt != -1) {
                // So that we don't the starting node which is having -1 as its parent
                System.out.println("[" + top.src + "-" + top.prnt + "@" + top.wt + "]");
            }
            for (Edge edge : graph[top.src]) {
                if (visited[edge.nbr] == false) {
                    pq.add(new Pair(edge.nbr, top.src, edge.wt));
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
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

        Prims(graph);
    }

}