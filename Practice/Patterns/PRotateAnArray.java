import java.util.Scanner;

public class PRotateAnArray {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = scn.nextInt();
        }
        int k = scn.nextInt();
        rotate(arr, k);
        scn.close();
    }

    public static void rotate(int[] arr, int k) {
        k %= arr.length; // to remove the redundancy
        if (k < 0) {
            k += arr.length;
        }
        int[] sarr = new int[k];
        int count = 0;
        for (int i = arr.length - k; i < arr.length; i++) {
            sarr[count] = arr[i];
            count++;
        }
        for (int j = arr.length - k - 1; j >= 0; j--) {
            arr[j + k] = arr[j];
        }
        for (int i = 0; i < k; i++) {
            arr[i] = sarr[i];
        }
        // display
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "\t");
        }
    }
}
