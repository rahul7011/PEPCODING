import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
public class SlidingWindowMaximum {
    public static int[] NextGreaterFromRight(int[] arr) {
        int[] ans = new int[arr.length];
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            while (st.size() > 0 && arr[st.peek()] < arr[i]) {
                int pidx = st.peek();
                ans[pidx] = i;
                st.pop();
            }
            st.push(i);
        }
        while (st.size() > 0) {
            int pidx = st.peek();
            ans[pidx] = arr.length;
            st.pop();
        }
        return ans;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(br.readLine());
        }
        int k = Integer.parseInt(br.readLine());

        // Write your code here
        int[] nge = NextGreaterFromRight(a);
        int winMaxIdx = 0;
        int[] ans = new int[a.length - k + 1];
        for (int i = 0; i <= a.length - k; i++) {
            //winMaxIdx points to the nge of the current window
            if (winMaxIdx < i) {
                //resetting the j
                winMaxIdx = i;
            }
            while (nge[winMaxIdx] < k + i) {
                winMaxIdx = nge[winMaxIdx];
            }
            ans[i] = a[winMaxIdx];
        }
        for (int val : ans) {
            System.out.println(val);
        }
    }
}