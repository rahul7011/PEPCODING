import java.io.*;
import java.util.*;
public class IterativeDepthFirstTraversal {
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
        String psf;

        Pair(int src, String psf) {
            this.src = src;
            this.psf = psf;
        }
    }

    public static void iterativeDfs(ArrayList<Edge>[] graph, int src, boolean[] visited) {
        Stack<Pair> st = new Stack<>();
        st.push(new Pair(src, src + ""));
        while (st.size() != 0) {
            // remove
            Pair top = st.peek();
            st.pop();

            // mark*
            if (visited[top.src] == true) {
                continue;
            }
            visited[top.src] = true;

            // work
            System.out.println(top.src + "@" + top.psf);

            // add*
            for (Edge edge : graph[top.src]) {
                if (visited[edge.nbr] == false) {
                    st.add(new Pair(edge.nbr, top.psf + edge.nbr));
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
            graph[v1].add(new Edge(v1, v2));
            graph[v2].add(new Edge(v2, v1));
        }

        int src = Integer.parseInt(br.readLine());

        boolean[] visited = new boolean[vtces];
        iterativeDfs(graph, src, visited);
    }
}