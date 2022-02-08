import java.io.*;

public class NumberOfIslands {
    public static void getComponents(int[][] arr, int row, int col) {
        arr[row][col] = 1;
        int[] xdir = { 0, 0, 1, -1 };
        int[] ydir = { 1, -1, 0, 0 };
        for (int i = 0; i < 4; i++) {
            int newx = row + xdir[i];
            int newy = col + ydir[i];
            if (newx < 0 || newx >= arr.length || newy < 0 || newy >= arr[0].length || arr[newx][newy] == 1) {
                continue;
            }
            getComponents(arr, newx, newy);
        }
    }

    public static void main(String[] args) throws NumberFormatException,IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int m = Integer.parseInt(br.readLine());
        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[m][n];

        for (int i = 0; i < arr.length; i++) {
            String parts = br.readLine();
            for (int j = 0; j < arr[0].length; j++) {
                arr[i][j] = Integer.parseInt(parts.split(" ")[j]);
            }
        }

        // exactly same as getConnected component with minor tweaks
        int islands = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if (arr[i][j] == 0) {
                    islands++;
                    getComponents(arr, i, j);
                }
            }
        }

        System.out.println(islands);
    }
}
