import java.util.Scanner;
import java.util.Stack;

public class Pstacks {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        Stack<Integer> st=new Stack<>();
        st.push(10);
        System.out.println(st.peek());
        st.push(120);
        System.out.println(st.peek());
        st.push(103);
        System.out.println(st.peek());
        st.push(105);
        System.out.println(st.peek());
        st.pop();
        System.out.println(st.peek());
        st.pop();
        System.out.println(st.peek());
        st.pop();
        System.out.println(st.peek());
        st.pop();
        System.out.println("Stack is empty");
        scn.close();

    }
}
