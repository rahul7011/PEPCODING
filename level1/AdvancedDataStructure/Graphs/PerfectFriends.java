import java.io.*;
import java.util.*;
public class PerfectFriends {
    static class Edge {
        int src;
        int nbr;

        Edge(int src, int nbr) {
            this.src = src;
            this.nbr = nbr;
        }
    }

    public static void getComponenent(ArrayList<Edge>[] graph, int vertice, ArrayList<Integer> comp,
            boolean[] visited) {
        visited[vertice] = true;
        comp.add(vertice);
        for (Edge edge : graph[vertice]) {
            if (visited[edge.nbr] == false) {
                getComponenent(graph, edge.nbr, comp, visited);
            }
        }
    }

    public static int findfriends(ArrayList<Edge>[] graph) {
        ArrayList<ArrayList<Integer>> comps = new ArrayList<>();
        boolean[] visited = new boolean[graph.length];
        for (int i = 0; i < graph.length; i++) {
            if (visited[i] == false) {
                ArrayList<Integer> comp = new ArrayList<>();
                getComponenent(graph, i, comp, visited);
                comps.add(comp);
            }
        }
        int res = 0;
        for (int i = 0; i < comps.size(); i++) {
            int len = comps.get(i).size();
            for (int j = i + 1; j < comps.size(); j++) {
                int lsize = comps.get(j).size();
                res += (len * lsize);
            }
        }
        return res;
    }

    public static void main(String[] args)throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());
        @SuppressWarnings("unchecked")
        ArrayList<Edge>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        int edges = k;
        for (int i = 0; i < edges; i++) {
            String[] parts = br.readLine().split(" ");
            int v1 = Integer.parseInt(parts[0]);
            int v2 = Integer.parseInt(parts[1]);
            graph[v1].add(new Edge(v1, v2));
            graph[v2].add(new Edge(v2, v1));
        }
        // for(ArrayList<Edge> edges1:graph)
        // {
        // for(Edge edge:edges1)
        // System.out.println(edge);
        // }
        System.out.println(findfriends(graph));
    }
}
