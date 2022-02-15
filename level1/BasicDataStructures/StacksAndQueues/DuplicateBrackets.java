import java.util.*;
class DuplicateBrackets {
    public static boolean checkDuplicate(String s) {
        Stack<Character> st = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                // System.out.println(st);
                int flag = 0;
                while (st.peek() != '(') {
                    flag = 1;
                    st.pop();
                }
                st.pop();
                if (flag == 0) {
                    return true;
                }
                // System.out.println(st);
            } else {
                st.push(s.charAt(i));
            }
        }
        return false;
    }

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        String s = scn.nextLine();
        System.out.print(checkDuplicate(s));
        scn.close();
    }

}