import java.io.*;
import java.util.*;
public class SpreadOfInfection {
    static class Edge {
        int src;
        int nbr;

        Edge(int src, int nbr) {
            this.src = src;
            this.nbr = nbr;
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

    public static int InfectionSpread(ArrayList<Edge>[] graph, int src, int time, boolean[] visited) {
        int count = 0;
        Queue<Pair> q = new ArrayDeque<>();
        q.add(new Pair(src, 1));
        while (q.size() != 0) {
            Pair top = q.peek();
            q.remove();

            if (visited[top.src] == true) {
                continue;
            }
            visited[top.src] = true;
            if (top.level > time) {
                return count;
            }
            count++;
            for (Edge edge : graph[top.src]) {
                if (visited[edge.nbr] == false) {
                    q.add(new Pair(edge.nbr, top.level + 1));
                }
            }
        }
        return count;
    }

    public static void main(String[] args)throws NumberFormatException,IOException{
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
            graph[v1].add(new Edge(v1, v2));
            graph[v2].add(new Edge(v2, v1));
        }

        int src = Integer.parseInt(br.readLine());
        int t = Integer.parseInt(br.readLine());

        boolean[] visited = new boolean[vtces];
        System.out.println(InfectionSpread(graph, src, t, visited));
    }

}