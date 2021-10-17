import java.util.Scanner;

public class RingRotate {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int m = scn.nextInt();
        int[][] arr = new int[n][m];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                arr[i][j] = scn.nextInt();
            }
        }
        int s = scn.nextInt();
        int r = scn.nextInt();
        int[] oneD = Fill1DFromShell(arr, s);
        rotate1D(oneD, r);
        FillShellFrom1D(arr, oneD, s);
        display(arr);
        scn.close();
    }

    public static int[] Fill1DFromShell(int[][] arr, int s) {
        int minr = s - 1;
        int minc = s - 1;
        int maxr = arr.length - s;
        int maxc = arr[0].length - s;
        int size = 2 * (maxr - minr + 1) + 2 * (maxc - minc + 1) - 4;
        int[] oneD = new int[size];
        int idx = 0;
        System.out.println(minr + " " + minc + " " + maxr + " " + maxc + " " + size);
        // downwards
        for (int j = minc, i = minr; i <= maxr; i++) {
            oneD[idx] = arr[i][j];
            idx++;
        }
        minc++;
        // Rightwards
        for (int i = maxr, j = minc; j <= maxc; j++) {
            oneD[idx] = arr[i][j];
            idx++;
        }
        maxr--;
        // Upwards
        for (int i = maxr, j = maxc; i >= minr; i--) {
            oneD[idx] = arr[i][j];
            idx++;
        }
        maxc--;
        // leftwards
        for (int i = minr, j = maxc; j >= minc; j--) {
            oneD[idx] = arr[i][j];
            idx++;
        }
        minr--;
        return oneD;
    }

    public static void FillShellFrom1D(int[][] arr, int[] oneD, int s) {
        int minr = s - 1;
        int minc = s - 1;
        int maxr = arr.length - s;
        int maxc = arr[0].length - s;

        int idx = 0;
        // downwards
        for (int j = minc, i = minr; i <= maxr; i++) {
            arr[i][j] = oneD[idx];
            idx++;
        }
        minc++;
        // Rightwards
        for (int i = maxr, j = minc; j <= maxc; j++) {
            arr[i][j] = oneD[idx];
            idx++;
        }
        maxr--;
        // Upwards
        for (int i = maxr, j = maxc; i >= minr; i--) {
            arr[i][j] = oneD[idx];
            idx++;
        }
        maxc--;
        // leftwards
        for (int i = minr, j = maxc; j >= minc; j--) {
            arr[i][j] = oneD[idx];
            idx++;
        }
        minr--;

    }

    public static void rotate1D(int[] oneD, int r) {
        r%=oneD.length;
        if(r<0)
        {
            r+=oneD.length;
        }
        reverse(oneD, 0, oneD.length - r - 1);
        reverse(oneD, oneD.length - r, oneD.length - 1);
        reverse(oneD, 0, oneD.length - 1);
    }

    public static void reverse(int[] oneD, int l, int r) {
        while (l < r) {
            int temp = oneD[l];
            oneD[l] = oneD[r];
            oneD[r] = temp;
            l++;
            r--;
        }
    }

    public static void display(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
}
