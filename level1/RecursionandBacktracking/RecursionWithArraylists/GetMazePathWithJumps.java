import java.util.ArrayList;
import java.util.Scanner;

public class GetMazePathWithJumps {
    public static void main(String[] args) {
        Scanner scn=new Scanner(System.in);
        int n=scn.nextInt();
        int m=scn.nextInt();
        ArrayList<String>ans=getMazePathsWithJumps(1,1,n,m);
        System.out.println(ans);
        scn.close();
    }
    public static ArrayList<String> getMazePathsWithJumps(int sr,int sc,int dr,int dc)
    {
        if(sr==dr && sc==dc)
        {
            ArrayList<String>tans=new ArrayList<>();
            tans.add("");
            return tans;
        }
        if(sr>dr || sc>dc)
        {
            ArrayList<String>tans=new ArrayList<>();
            return tans;
        }
        ArrayList<String>finalans=new ArrayList<>();
        //getting horizonatal paths
        for(int i=1;i<=dc-sc;i++)
        {
            ArrayList<String>horizontal=getMazePathsWithJumps(sr, sc+i, dr, dc);
            // System.out.println(horizontal.size()+" "+i);
            for(String move:horizontal)
            {
                finalans.add("h"+i+move);
            } 
        }
        //getting vertical paths
        for(int j=1;j<=dr-sr;j++)
        {
            ArrayList<String>vertical=getMazePathsWithJumps(sr+j, sc, dr, dc);
            // System.out.println(j);
            for(String move:vertical)
            {
                finalans.add("v"+j+move);
            }
        }
        //getting diagonal paths
        for(int k=1;k<=dr-sr && k<=dc-sc;k++)
        {
            ArrayList<String>diagonal=getMazePathsWithJumps(sr+k, sc+k, dr, dc);
            for(String move:diagonal)
            {
                finalans.add("d"+k+move);
            }
        }
        return finalans;
    }
}
