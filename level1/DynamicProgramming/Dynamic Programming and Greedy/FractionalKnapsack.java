import java.util.Arrays;
import java.util.Scanner;

public class FractionalKnapsack {
    public static class Pair implements Comparable<Pair> {
        int wt;
        int val;
        double ProfitRatio;

        public int compareTo(Pair that) {
            if (this.ProfitRatio < that.ProfitRatio) {
                return -1;
            } else if (this.ProfitRatio > that.ProfitRatio) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        Pair[] knap = new Pair[n];
        for (int i = 0; i < n; i++) {
            knap[i] = new Pair();
            knap[i].val = scn.nextInt();
        }
        for (int i = 0; i < n; i++) {
            knap[i].wt = scn.nextInt();
            knap[i].ProfitRatio = knap[i].val * 1.0 / knap[i].wt;
        }
        int capacity = scn.nextInt();
        Arrays.sort(knap);
        int i = n - 1;

        double maxProfit = 0.0;
        while (capacity > 0 && i >= 0) {
            if (capacity >= knap[i].wt) {
                capacity -= knap[i].wt;
                maxProfit += knap[i].val;
            } else {
                maxProfit += (capacity * knap[i].val * 1.0) / knap[i].wt;
                capacity = 0;
            }
            i--;
        }
        System.out.println(maxProfit);
        scn.close();
    }

}