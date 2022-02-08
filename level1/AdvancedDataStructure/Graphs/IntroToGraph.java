import java.util.ArrayList;
import java.util.Scanner;

public class IntroToGraph {
    public static class Edge
    {
        int src;
        int nbr;
        int wt;
        
        Edge(int src,int nbr,int wt)
        {
            this.src=src;
            this.nbr=nbr;
            this.wt=wt;
        }
    }
    public static void main(String[] args) {
        Scanner scn=new Scanner(System.in);
        int vertices=scn.nextInt();
        @SuppressWarnings("unchecked")  //To supress the vscode warning
        ArrayList<Edge>[]graph=new ArrayList[vertices];
        for (int i = 0; i < graph.length; i++) {
            graph[i]=new ArrayList<>();
        }
        int edges=scn.nextInt();
        for (int i = 0; i < edges; i++) {
            int src=scn.nextInt();
            int nbr=scn.nextInt();
            int wt=scn.nextInt();
            graph[src].add(new Edge(src, nbr, wt));
            graph[nbr].add(new Edge(src, nbr, wt));
        }
        System.out.println("graph:");
        for(ArrayList<Edge>Edges:graph)
        {
            for(Edge edge:Edges)
            {
                System.out.println(edge.src+" "+edge.nbr+" "+edge.wt);
            }
        }
        scn.close();
    }
}
