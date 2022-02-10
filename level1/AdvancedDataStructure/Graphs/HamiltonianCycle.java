import java.util.Scanner;

public class HamiltonianCycle {

    public static boolean hamiltonianCycle(int N, int[][] graph, int src, int count, int origin, boolean[] visited) {

        if (count == N) {
            for (int i = 0; i < graph[src].length; i++) {
                if (graph[src][i] == 1 && origin == i) {
                    return true;
                }
            }
            return false;
        }

        visited[src] = true;
        for (int i = 0; i < graph[src].length; i++) {
            if (visited[i] == false && graph[src][i] != 0) {
                boolean check = hamiltonianCycle(N, graph, i, count + 1, origin, visited);
                if (check == true) {
                    return true;
                }
            }
        }
        visited[src] = false;
        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int[] edges = new int[2 * M];
        for (int i = 0; i < edges.length; i++) {
            edges[i] = sc.nextInt();
        }
        int[][] graph = new int[N][N];
        for (int i = 0; i < M; i++) {
            graph[edges[2 * i + 1] - 1][edges[2 * i] - 1] = 1;
            graph[edges[2 * i] - 1][edges[2 * i + 1] - 1] = 1;
        }

        boolean[] visited = new boolean[N];
        System.out.println(hamiltonianCycle(N, graph, 0, 1, 0, visited));
        sc.close();
    }
}