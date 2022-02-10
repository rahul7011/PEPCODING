import java.io.*;
import java.util.*;

public class HamiltonianPathAndCycle {
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

    public static void Hamiltonian(ArrayList<Edge>[] graph, int src, int origin, int count, String psf,
            boolean[] visited) {
        if (count == graph.length) {
            System.out.print(psf);
            // Now checking for cycle
            int flag = 0;
            for (Edge edge : graph[src]) {
                if (origin == edge.nbr) {
                    flag = 1;
                    break;
                }
            }
            if (flag == 1) {
                // cycle
                System.out.println("*");
            } else {
                System.out.println(".");
            }
        }
        visited[src] = true;
        for (Edge edge : graph[src]) {
            if (visited[edge.nbr] == false) {
                Hamiltonian(graph, edge.nbr, origin, count + 1, psf + edge.nbr, visited);
            }
        }
        visited[src] = false;
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
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
        Hamiltonian(graph, src, src, 1, src + "", visited);
    }
}
