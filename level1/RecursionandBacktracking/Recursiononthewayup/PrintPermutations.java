import java.util.Scanner;

public class PrintPermutations {
    public static void main(String[] args) {
        Scanner scn=new Scanner(System.in);
        String s=scn.next();
        printPermutations(s,"");
        scn.close();
    }
    public static void printPermutations(String s,String asf)
    {
        if(s.length()==0)
        {
            System.out.println(asf);
            return;
        }
        for (int i = 0; i < s.length(); i++) {
            String fpart=s.substring(0, i);
            String lpart=s.substring(i+1);
            char cchar=s.charAt(i);
            String newString=fpart+lpart;
            printPermutations(newString, asf+cchar);
        }
    }
}
