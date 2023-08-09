import java.util.*;

public class countingNumbers {
    private static long solve(String num, int len, int prevDig, boolean leading_zero, boolean tight) {
        if (len == 0)
            return 1;
        int lb = 0;
        int ub = (tight == true) ? num.charAt(num.length() - len) - '0' : 9;
        long ans = 0;
        for (int dig = lb; dig <= ub; dig++) {
            if (dig == prevDig && leading_zero == false) {
                continue;
            }
            ans += solve(num, len - 1, dig, leading_zero == true && dig == 0, tight == true && dig == ub);
        }
        return ans;
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        long x = scn.nextLong();
        long y = scn.nextLong();
        String a=(x-1)+"";
        String b=y+"";
        System.out.println(solve(b, b.length(), -1, true, true)-solve(a, a.length(), -1, true, true));
    }
}
