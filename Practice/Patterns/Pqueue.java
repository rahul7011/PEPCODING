import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Pqueue {
    public static void main(String[] args) {
        Scanner scn=new Scanner(System.in);
        Queue<Integer> q=new ArrayDeque<>();
        q.add(10);
        q.add(20);
        q.add(30);
        System.out.println(q);
        System.out.println(q.peek());
        q.remove();
        System.out.println(q.peek());
        q.remove();
        System.out.println(q.peek());
        System.out.println(q);
        scn.close();


    }
}
