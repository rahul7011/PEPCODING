import java.io.*;
import java.util.*;

public class PostfixEvaluationAndConversions {

    public static void performCalculations(Character ch, Stack<Integer> Value) {
        int v2 = Value.pop();
        int v1 = Value.pop();
        if (ch == '+') {
            Value.push(v1 + v2);
        } else if (ch == '-') {
            Value.push(v1 - v2);
        } else if (ch == '*') {
            Value.push(v1 * v2);
        } else {
            Value.push(v1 / v2);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String exp = br.readLine();

        // Write your code here
        Stack<Integer> Value = new Stack<>();
        Stack<String> infix = new Stack<>();
        Stack<String> prefix = new Stack<>();
        for (int i = 0; i < exp.length(); i++) {
            if (exp.charAt(i) >= '0' && exp.charAt(i) <= '9') {
                Value.push(exp.charAt(i) - '0');
                infix.push(exp.charAt(i) + "");
                prefix.push(exp.charAt(i) + "");
            } else if (exp.charAt(i) == '+' || exp.charAt(i) == '-'
                    || exp.charAt(i) == '*' || exp.charAt(i) == '/') {
                performCalculations(exp.charAt(i), Value);
                String v2 = infix.pop();
                String v1 = infix.pop();
                infix.push("(" + v1 + exp.charAt(i) + v2 + ")");
                v2 = prefix.pop();
                v1 = prefix.pop();
                prefix.push(exp.charAt(i) + v1 + v2);
            }
        }
        System.out.println(Value.peek());
        System.out.println(infix.peek());
        System.out.println(prefix.peek());
    }
}