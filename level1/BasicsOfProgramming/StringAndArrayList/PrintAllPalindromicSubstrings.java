import java.util.Scanner;

public class PrintAllPalindromicSubstrings {
    public static void main(String[] args) {
        Scanner scn=new Scanner(System.in);
        String s=scn.nextLine();
        for(int i=0;i<s.length();i++)
        {
            for(int j=i;j<s.length();j++)
            {
                String sub=s.substring(i, j+1);
                if(isPalindrome(sub)==true)
                {
                    System.out.println(sub);
                }
            }
        }
        scn.close();
    }
    public static boolean isPalindrome(String s)
    {
        int left=0;
        int right=s.length()-1;
        boolean flag=true;
        while(left<right)
        {
            if(s.charAt(left)!=s.charAt(right))
            {
                flag=false;
                break;
            }
            left++;
            right--;
        }
        return flag;
    }
}
