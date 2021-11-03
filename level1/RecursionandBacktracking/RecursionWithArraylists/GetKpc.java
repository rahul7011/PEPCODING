import java.util.ArrayList;
import java.util.Scanner;

public class GetKpc {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        String s = scn.next();
        ArrayList<String> ans = getKPC(s);
        System.out.println(ans);
        scn.close();
    }

    static String[] keys = { ".;", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tu", "vwx", "yz" };

    public static ArrayList<String> getKPC(String s) {
        if(s.length()==0)
        {
            ArrayList<String>tans=new ArrayList<>();
            tans.add("");
            return tans;
        }
        String remaining = s.substring(1);
        ArrayList<String> cans = getKPC(remaining);
        ArrayList<String> finalans = new ArrayList<>();
        int letter=s.charAt(0)-'0';
        System.out.println(letter);
        String key = keys[letter];
        for (int i = 0; i < key.length(); i++) {
            char currentLetter = key.charAt(i);
            for (String currents : cans) {
                finalans.add(currentLetter + currents);
            }
        }
        return finalans;
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
 