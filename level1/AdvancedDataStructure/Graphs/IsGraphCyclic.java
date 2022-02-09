import java.util.*;
import java.io.*;
public class IsGraphCyclic {
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

    public static boolean isCyclic(ArrayList<Edge>[] graph, int src, boolean[] visited) {
        Queue<Integer> q = new ArrayDeque<>();
        q.add(src);
        while (q.size() != 0) {
            Integer top = q.peek();
            q.remove();

            if (visited[top] == true) {
                // Cycle is present
                return true;
            }
            visited[top] = true;

            for (Edge edge : graph[top]) {
                if (visited[edge.nbr] == false) {
                    q.add(edge.nbr);
                }
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException,NumberFormatException {
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

        boolean[] visited = new boolean[vtces];
        for (int i = 0; i < vtces; i++) {
            // Graph can be disconnected
            if (visited[i] == false) {
                boolean check = isCyclic(graph, i, visited);
                if (check == true) {
                    System.out.println("true");
                    return;
                }

            }
        }
        System.out.println("false");
    }
}
