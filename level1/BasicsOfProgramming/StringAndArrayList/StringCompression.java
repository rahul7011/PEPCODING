import java.util.Scanner;

public class StringCompression {
    public static String compression1(String str) {
        char c = '#';
        String s = "";
        for (int i = 0; i < str.length(); i++) {
            if (c != str.charAt(i)) {
                c = str.charAt(i);
                s += c;
            }
        }

        return s;
    }

    public static String compression2(String str) {
        char c = str.charAt(0);
        int count = 1;
        String s = "";
        for (int i = 1; i < str.length(); i++) {
            if (c != str.charAt(i)) {
                s = s + c;
                if (count != 1)
                    s += count;
                c = str.charAt(i);
                count = 1;
            } else {
                count++;
            }
        }
        s = s + c;
        if (count != 1)
            s += count;
        return s;
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        String str = scn.next();
        System.out.println(compression1(str));
        System.out.println(compression2(str));
        scn.close();
    }
}
