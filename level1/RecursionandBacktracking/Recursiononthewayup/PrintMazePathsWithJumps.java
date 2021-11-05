import java.util.Scanner;

public class PrintMazePathsWithJumps {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int m = scn.nextInt();
        printMazePaths(1, 1, n, m, "");
        scn.close();
    }

    public static void printMazePaths(int sr, int sc, int dr, int dc, String asf) {
        if (sr == dr && sc == dc) {
            System.out.println(asf);
            return;
        }
        if (sr > dr || sc > dc) {
            return;
        }
        for (int i = 1; i <= dc - sc; i++) {
            printMazePaths(sr, sc + i, dr, dc, asf + "h" + i);
        }
        for (int i = 1; i <= dr - sr; i++) {
            printMazePaths(sr + i, sc, dr, dc, asf + "v" + i);
        }
        for (int k = 1; k <= dc - sc && k <= dr - sr; k++) {
            printMazePaths(sr + k, sc + k, dr, dc, asf + "d" + k);
        }
    }
}
