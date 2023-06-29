// https://www.codingninjas.com/codestudio/problems/1062679?topList=striver-sde-sheet-problems&utm_source=striver&utm_medium=website
public class NthRoot {
    public static double multiply(double x, int n) {
        double ans = 1.0;
        for (int i = 0; i < n; i++) {
            ans = ans * x;
        }
        return ans;
    }

    public static double bSearch(int n, int m) {
        double low = 1;
        double high = m;
        double upto6Decimal = 1e-7;
        while ((high - low) > upto6Decimal) {
            double mid = (low + high) / 2.0;
            if (multiply(mid, n) < m) {
                // since we need to find upto 6 decimal places hence we can't
                // do low=mid+1 or high=mid-1
                low = mid;
            } else {
                high = mid;
            }
        }
        // Anything can be returned low or high
        return low;
    }

    public static double findNthRootOfM(int n, int m) {
        return bSearch(n, m);
    }
}