import java.util.Scanner;
import java.util.*;

public class SortKsortedArray {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = scn.nextInt();
        }
        int k = scn.nextInt();
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < arr.length; i++) {
            if (i < k + 1) {
                pq.add(arr[i]);
            } else {
                System.out.println(pq.peek());
                pq.remove();
                pq.add(arr[i]);
            }
        }
        while (pq.size() != 0) {
            System.out.println(pq.peek());
            pq.remove();
        }
        scn.close();
    }
}
