import java.util.Scanner;

public class PrintMazePaths {
    public static void main(String[] args) {
        Scanner scn=new Scanner(System.in);
        int n=scn.nextInt();
        int m=scn.nextInt();
        printMazePaths(1,1,n,m,"");
        scn.close();
    }
    public static void printMazePaths(int sr,int sc,int dr,int dc,String asf)
    {
        if(sr==dr && sc==dc)
        {
            System.out.println(asf);
            return;
        }
        if(sr>dr || sc>dc)
        {
            return;
        }
        printMazePaths(sr, sc+1, dr, dc, asf+"h");
        printMazePaths(sr+1, sc, dr, dc, asf+"v");
    }
}
