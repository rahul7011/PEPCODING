import java.io.*;
import java.util.*;

public class InfixConversions {

    public static void doInfixConversion(Stack<Character> operator,
            Stack<String> pre, Stack<String> post) {
        char op = operator.pop();
        String v2 = pre.pop();
        String v1 = pre.pop();
        pre.push(op + "" + v1 + v2);

        v2 = post.pop();
        v1 = post.pop();
        post.push(v1 + v2 + "" + op);
    }

    public static int precedence(char ch) {
        if (ch == '+' || ch == '-') {
            return 1;
        } else {
            return 2;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String exp = br.readLine();
        Stack<Character> operator = new Stack<>();
        Stack<String> pre = new Stack<>();
        Stack<String> post = new Stack<>();
        for (int i = 0; i < exp.length(); i++) {
            if (exp.charAt(i) == '(') {
                operator.push(exp.charAt(i));
            } else if (exp.charAt(i) == ')') {
                while (operator.size() > 0 && operator.peek() != '(') {
                    doInfixConversion(operator, pre, post);
                }
                operator.pop();
            } else if ((exp.charAt(i) >= 'a' && exp.charAt(i) <= 'z')
                    || (exp.charAt(i) >= 'A' && exp.charAt(i) <= 'Z')
                    || (exp.charAt(i) >= '0' && exp.charAt(i) <= '9')) {
                pre.push(exp.charAt(i) + "");
                post.push(exp.charAt(i) + "");
            } else if ((exp.charAt(i) == '+' || exp.charAt(i) == '-')
                    || (exp.charAt(i) == '*' || exp.charAt(i) == '/')) {
                while (operator.size() > 0 && operator.peek() != '('
                        && precedence(operator.peek()) >= precedence(exp.charAt(i))) {
                    doInfixConversion(operator, pre, post);
                }
                operator.push(exp.charAt(i));
            }
        }
        while (operator.size() > 0) {
            doInfixConversion(operator, pre, post);
        }
        System.out.println(post.peek());
        System.out.println(pre.peek());
    }
}