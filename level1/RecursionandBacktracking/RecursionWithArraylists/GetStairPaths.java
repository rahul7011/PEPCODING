import java.util.ArrayList;
import java.util.Scanner;

public class GetStairPaths {
    public static void main(String[] args) {
        Scanner scn=new Scanner(System.in);
        int n=scn.nextInt();
        ArrayList<String>ans=getStairPaths(n);
        System.out.println(ans);
        scn.close();
    }
    public static ArrayList<String> getStairPaths(int n)
    {
        if(n==0)
        {
            ArrayList<String>tans=new ArrayList<>();
            tans.add("");
            return tans;
        }
        if(n<0)
        {
            ArrayList<String>tans=new ArrayList<>();
            // tans.add("");
            return tans;
        }

        //for n-1
        ArrayList<String>nm1=getStairPaths(n-1);
        //for n-2
        ArrayList<String>nm2=getStairPaths(n-2);
        //for n-3
        ArrayList<String>nm3=getStairPaths(n-3);
        ArrayList<String>finalans=new ArrayList<>();
        for(String s: nm1)
        {
            finalans.add("1"+s);
        }
        for(String s: nm2)
        {
            finalans.add("2"+s);
        }
        for(String s: nm3)
        {
            finalans.add("3"+s);
        }
        return finalans;
    }
}
