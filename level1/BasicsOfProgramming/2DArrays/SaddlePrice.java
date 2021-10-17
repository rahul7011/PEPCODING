import java.util.Scanner;

public class SaddlePrice {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int[][] arr = new int[n][n];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                arr[i][j] = scn.nextInt();
            }
        }

        // for (int i = 0; i < arr.length; i++) {
        //     for (int j = 0; j < arr[0].length; j++) {
        //         System.out.print(arr[i][j] + " ");
        //     }
        //     System.out.println();
        // }
        saddlePrice(arr);
        scn.close();
    }

    public static void saddlePrice(int[][] arr) {
        // should be smallest in it's row and highest in it's col
        for (int i = 0; i < arr.length; i++) {
            int min = Integer.MAX_VALUE;
            int midx = -1;
            for (int j = 0; j < arr[0].length; j++) {
                if (min > arr[i][j]) {
                    min = arr[i][j];
                    midx = j;
                }
            }
            // System.out.print(min + " as ");
            // In col
            boolean flag = true;
            for (int k = 0; k < arr.length; k++) {
                if (min < arr[k][midx]) {
                    flag = false;
                    break;
                }
            }
            if (flag == true) {
                System.out.println(min);
                return;
            }
        }
        System.out.print("Invalid input");
    }
}
