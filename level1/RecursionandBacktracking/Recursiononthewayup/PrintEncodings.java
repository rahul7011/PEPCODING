import java.util.Scanner;

public class PrintEncodings {
    public static void main(String[] args) {
        Scanner scn=new Scanner(System.in);
        String s=scn.next();
        printEncodings(s,"");
        scn.close();
    }
    static String[] keys={"","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q",
                            "r","s","t","u","v","w","x","y","z"};
    public static void printEncodings(String s,String asf)
    {
        if(s.length()==0)
        {
            System.out.println(asf);
            return;
        }
        int fchar=s.charAt(0)-'0';
        if(fchar!=0)
        {
            String remaiString=s.substring(1);
            printEncodings(remaiString, asf+keys[fchar]);
            if(s.length()>=2)
            {
                int ftchar=Integer.parseInt(s.substring(0,2));
                String remaiString2=s.substring(2);
                if(ftchar<=26)
                {
                    printEncodings(remaiString2, asf+keys[ftchar]);
                }
            }
        }


    }
}
