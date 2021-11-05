import java.util.Scanner;

public class PrintKpc {
    public static void main(String[] args) {
        Scanner scn=new Scanner(System.in);
        String s=scn.next();
        printKPC(s,"");
        scn.close();
    }
    public static String[] keys={".;","abc","def","ghi","jkl","mno","pqrs","tu","vwx","yz"};
    public static void printKPC(String s,String asf)
    {
        if(s.length()==0)
        {
            System.out.println(asf);
            return;
        }
        char fchar=s.charAt(0);
        String remaiString=s.substring(1);
        int number=fchar-'0';
        String key=keys[number];
        for (int i = 0; i < key.length(); i++) {
            printKPC(remaiString, asf+key.charAt(i));
        }
    }
}

// 0 -> .;
// 1 -> abc
// 2 -> def
// 3 -> ghi
// 4 -> jkl
// 5 -> mno
// 6 -> pqrs
// 7 -> tu
// 8 -> vwx
// 9 -> yz
