import java.util.ArrayList;
import java.util.Scanner;

public class GetSubsequence {
    public static void main(String[] args) {
        Scanner scn=new Scanner(System.in);
        String str=scn.next();
        ArrayList<String>Subseq=Subsequence(str);
        System.out.println(Subseq);
        scn.close();
    }
    public static ArrayList<String> Subsequence(String str)
    {
        if(str.length()==0)
        {
            ArrayList<String>temp=new ArrayList<>();
            temp.add("");
            return temp;
        }
        char FirstChar=str.charAt(0);
        String RemainingSubString=str.substring(1);
        ArrayList<String>tempSubsequence=Subsequence(RemainingSubString);
        ArrayList<String>FinalSubsequence=new ArrayList<>();
        for(String s:tempSubsequence)
        {
            FinalSubsequence.add(""+s);
        }
        for(String s:tempSubsequence)
        {
            FinalSubsequence.add(FirstChar+s);
        }
        return FinalSubsequence;
    }
}
