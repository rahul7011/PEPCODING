import java.util.*;

// https://cses.fi/problemset/task/2220#:~:text=CSES%20%2D%20Counting%20Numbers&text=Your%20task%20is%20to%20count,adjacent%20digits%20are%20the%20same.
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




//2827. Number of Beautiful Integers in the Range
class Solution {
    int[][][][][][]dp=new int[9+3][2][2][9+3][9+3][20+2];
    private int digitDp(String s,int idx,int leadingZero,int tight,int odd,int even,int rem,int k)
    {
        if(idx==s.length())
        {
            if(leadingZero==0 && odd==even && rem==0)
            {
                return dp[idx][leadingZero][tight][odd][even][rem]=1;
            }
            return dp[idx][leadingZero][tight][odd][even][rem]=0;
        }
        if(dp[idx][leadingZero][tight][odd][even][rem]!=-1)
        {
            return dp[idx][leadingZero][tight][odd][even][rem];
        }
        int ans=0;
        int low=0;
        int high=(tight==0)?9:(s.charAt(idx)-'0');
        for(int num=low; num <= high; num++)
        {
            int newOdd=odd;
            int newEven=even;
            if(leadingZero==0 || num!=0)
            {
                newOdd+=(num%2);
                newEven+=(num%2==0)?1:0;
            }
            ans+=digitDp(s,idx+1,(leadingZero==1 && num==0)?1:0, (tight==1 && num==high)?1:0,newOdd,newEven,(rem*10+num)%k,k);
        }
        return dp[idx][leadingZero][tight][odd][even][rem]=ans;
    }
    public int numberOfBeautifulIntegers(int low, int high, int k) {
        String a=(low-1)+"";
        String b=high+"";
        for(int[][][][][]ddddd:dp)
        {
            for(int[][][][]dddd:ddddd)
            {
                for(int[][][]ddd:dddd)
                {
                    for(int[][]dd:ddd)
                    {
                        for(int[]d:dd)
                        {
                            Arrays.fill(d,-1);
                        }
                    }
                }
            }
        }
        
        int bb=digitDp(b,0,1,1,0,0,0,k);

        for(int[][][][][]ddddd:dp)
        {
            for(int[][][][]dddd:ddddd)
            {
                for(int[][][]ddd:dddd)
                {
                    for(int[][]dd:ddd)
                    {
                        for(int[]d:dd)
                        {
                            Arrays.fill(d,-1);
                        }
                    }
                }
            }
        }
        int aa=digitDp(a,0,1,1,0,0,0,k);
        return bb-aa;
    }
}
