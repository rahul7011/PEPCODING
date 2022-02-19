import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class LargestAreaHistogram {

    public static int[] NextSmallerLeft(int[] arr) {
        int[] ans = new int[arr.length];
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            while (st.size() > 0 && arr[st.peek()] >= arr[i]) {
                st.pop();
            }
            if (st.size() == 0) {
                ans[i] = -1;
            } else {
                int pidx = st.peek();
                ans[i] = pidx;
            }
            st.push(i);
        }
        return ans;
    }

    public static int[] NextSmallerRight(int[] arr) {
        int[] ans = new int[arr.length];
        Stack<Integer> st = new Stack<>();
        // we can traverse from any side but I am traversing from right side
        for (int i = arr.length - 1; i >= 0; i--) {
            while (st.size() > 0 && arr[st.peek()] >= arr[i]) {
                st.pop();
            }
            if (st.size() == 0) {
                ans[i] = arr.length;
            } else {
                int pidx = st.peek();
                ans[i] = pidx;
            }
            st.push(i);
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

        // Write your code here
        int[] left = NextSmallerLeft(a);
        int[] right = NextSmallerRight(a);

        // calculating area and finding maxArea out of it
        int maxArea = Integer.MIN_VALUE;
        for (int i = 0; i < a.length; i++) {
            int length = right[i] - left[i] - 1;
            int area = length * a[i];
            maxArea = Math.max(maxArea, area);
        }
        System.out.println(maxArea);
    }
}