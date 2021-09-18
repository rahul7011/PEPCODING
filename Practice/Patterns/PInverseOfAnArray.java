import java.util.Scanner;

public class PInverseOfAnArray {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scn.nextInt();

        }
        int[] inv = inverse(arr);
        for (int i = 0; i < inv.length; i++) {
            System.out.println(inv[i]);
        }
        scn.close();
    }

    public static int[] inverse(int[] arr) {
        int[] inv = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            int newPlace = arr[i];
            int newValue = i;
            inv[newPlace] = newValue;
        }
        return inv;
    }
}
