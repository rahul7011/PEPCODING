import java.util.ArrayList;
import java.util.Scanner;

public class GetMazePaths {
    public static void main(String[] args) {
        Scanner scn=new Scanner(System.in);
        int n=scn.nextInt();
        int m=scn.nextInt();
        ArrayList<String> ans=getMazePaths(1,1,n,m);
        System.out.println(ans);
        scn.close();
    }
    public static ArrayList<String>getMazePaths(int sr,int sc,int dr,int dc)
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
        ArrayList<String>horizontal=getMazePaths(sr, sc+1, dr, dc);
        ArrayList<String>vertical=getMazePaths(sr+1, sc, dr, dc);
        ArrayList<String>finalAns=new ArrayList<>();
        for(String move:horizontal)
        {
            finalAns.add("h"+move);
        }
        for(String move:vertical)
        {
            finalAns.add("v"+move);
        }
        return finalAns;
    }
}
