import java.util.Scanner;

public class MergeTwoSortedArrays {
    public static int[] mergeTwoSortedArrays(int[] a, int[] b) {
        // write your code here
        int[] merge = new int[a.length + b.length];
        int i = 0, j = 0, k = 0;
        while (i < a.length && j < b.length) {
            if (a[i] < b[j]) {
                merge[k] = a[i];
                k++;
                i++;
            } else {
                merge[k] = b[j];
                k++;
                j++;
            }
        }
        while (i < a.length) {
            merge[k] = a[i];
            i++;
            k++;
        }
        while (j < b.length) {
            merge[k] = b[j];
            j++;
            k++;
        }

        return merge;
    }

    public static void print(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scn.nextInt();
        }
        int m = scn.nextInt();
        int[] b = new int[m];
        for (int i = 0; i < m; i++) {
            b[i] = scn.nextInt();
        }
        int[] mergedArray = mergeTwoSortedArrays(a, b);
        print(mergedArray);
        scn.close();
    }

}
