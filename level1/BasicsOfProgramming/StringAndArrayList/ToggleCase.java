import java.util.Scanner;

public class ToggleCase {
    public static void main(String[] args) {
        Scanner scn=new Scanner(System.in);
        String s=scn.nextLine();
        StringBuilder str=new StringBuilder(s);
        for(int i=0;i<str.length();i++)
        {
            char ch=str.charAt(i);
            if(ch>='A' && ch<='Z')
            {
                ch=(char)(ch+'c'-'C');
            }
            else
            {
                //for small case a-z
                ch=(char)(ch+'C'-'c');
            }
            str.setCharAt(i, ch);
        }
        scn.close();

    }
}
