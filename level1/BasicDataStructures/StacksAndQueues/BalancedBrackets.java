import java.util.Scanner;
import java.util.*;
public class BalancedBrackets {
    public static boolean checkBalanced(String s) {
        Stack<Character> st = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ')' || s.charAt(i) == '}' || s.charAt(i) == ']') {
                if (st.size() != 0) {
                    if (s.charAt(i) == ')' && st.peek() != '(') {
                        return false;
                    } else if (s.charAt(i) == '}' && st.peek() != '{') {
                        return false;
                    } else if (s.charAt(i) == ']' && st.peek() != '[') {
                        return false;
                    } else {
                        st.pop();
                    }
                } else {
                    return false;
                }
            } else if (s.charAt(i) == '(' || s.charAt(i) == '{' || s.charAt(i) == '[') {
                st.push(s.charAt(i));
            }
        }
        if (st.size() != 0) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        String s = scn.nextLine();
        System.out.println(checkBalanced(s));
        scn.close();
    }

}