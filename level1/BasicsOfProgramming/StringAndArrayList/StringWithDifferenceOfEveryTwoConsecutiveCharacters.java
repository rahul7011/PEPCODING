import java.util.Scanner;

public class StringWithDifferenceOfEveryTwoConsecutiveCharacters {
    public static void main(String[] args) {
        Scanner scn=new Scanner(System.in);
        String s=scn.nextLine();
        // For "abecd", the answer should be "a1b3e-2c1d", as 
        // 'b'-'a' = 1
        // 'e'-'b' = 3
        // 'c'-'e' = -2
        // 'd'-'c' = 1
        String s1="";
        StringBuilder str=new StringBuilder(s);
        for(int i=0;i<str.length()-1;i++)
        {
            int val=str.charAt(i+1)-str.charAt(i);
            // System.out.print(str.charAt(i)+""+val);
            s1+=str.charAt(i)+""+val;
        }
        s1+=str.charAt(str.length()-1);
        System.out.print(s1);
        scn.close();
    }
}
