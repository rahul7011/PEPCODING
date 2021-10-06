import java.util.Scanner;

public class MatrixMultiplication {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n1 = scn.nextInt();
        int m1 = scn.nextInt();

        int[][] arr1 = new int[n1][m1];
        for (int i = 0; i < n1; i++) {
            for (int j = 0; j < m1; j++) {
                arr1[i][j] = scn.nextInt();
            }
        }
        int n2 = scn.nextInt();
        int m2 = scn.nextInt();

        int[][] arr2 = new int[n2][m2];
        for (int i = 0; i < n2; i++) {
            for (int j = 0; j < m2; j++) {
                arr2[i][j] = scn.nextInt();
            }
        }

        // Matrix Multiplication
        if (n2 != m1) {
            System.out.print("Invalid input");
        } else {
            int[][] mul = new int[n1][m2];
            for (int i = 0; i < n1; i++) {
                for (int j = 0; j < m2; j++) {
                    int val = 0;
                    for (int k = 0; k < n2; k++) {
                        val += arr1[i][k] * arr2[k][j];
                    }
                    mul[i][j] = val;
                }
            }

            for (int i = 0; i < n1; i++) {
                for (int j = 0; j < m2; j++) {
                    System.out.print(mul[i][j]+" ");
                }
                System.out.println();
            }
        }
        scn.close();
    }
}
