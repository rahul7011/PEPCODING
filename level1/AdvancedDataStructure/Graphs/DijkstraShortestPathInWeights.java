import java.io.*;
import java.util.*;

public class DijkstraShortestPathInWeights {
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
        String psf;
        int wsf;

        Pair(int src, String psf, int wsf) {
            this.src = src;
            this.psf = psf;
            this.wsf = wsf;
        }

        public int compareTo(Pair that) {
            return this.wsf - that.wsf;
        }
    }

    public static void dijkstra(ArrayList<Edge>[] graph, int src, boolean[] visited) {
        // dijkstra:Bfs+PriorityQueue
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.add(new Pair(src, src + "", 0));
        while (pq.size() != 0) {
            // remove
            Pair top = pq.peek();
            pq.remove();

            // mark*
            if (visited[top.src] == true) {
                continue;
            }
            visited[top.src] = true;

            // work*
            System.out.println(top.src + " via " + top.psf + " @ " + top.wsf);

            // add*
            for (Edge edge : graph[top.src]) {
                if (visited[edge.nbr] == false) {
                    pq.add(new Pair(edge.nbr, top.psf + edge.nbr, top.wsf + edge.wt));
                }
            }
        }
    }

    public static void main(String[] args)throws NumberFormatException,IOException {
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

        int src = Integer.parseInt(br.readLine());
        boolean[] visited = new boolean[vtces];
        dijkstra(graph, src, visited);

    }
}