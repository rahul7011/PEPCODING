import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class NextGreaterElementToTheRight {
    public static void display(int[] a) {
        StringBuilder sb = new StringBuilder();

        for (int val : a) {
            sb.append(val + "\n");
        }
        System.out.println(sb);
    }

    public static int[] Approach2(int[] arr) {
        int[] ans = new int[arr.length];
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            while (st.size() > 0 && arr[st.peek()] < arr[i]) {
                // peek idx
                int pidx = st.peek();
                ans[pidx] = arr[i];
                st.pop();
            }
            st.push(i);
        }
        // if elements are left on the stack then their next greater is not present
        // i.e,-1
        while (st.size() > 0) {
            // peek idx
            int pidx = st.peek();
            ans[pidx] = -1;
            st.pop();
        }
        return ans;
    }

    public static int[] Approach1(int[] arr){
        Stack<Integer>st=new Stack<>();
        int[] ans=new int[arr.length];
        for (int i = arr.length-1; i >= 0; i--) {
            while(st.size()>0&&arr[st.peek()]<arr[i])
            {
                st.pop();
            }
            if(st.size()==0)
            {
                ans[i]=-1;
            }else
            {
                int pidx=st.peek();
                ans[i]=arr[pidx];
            }
            st.push(i);
        }
        return ans;
    }
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(br.readLine());
        }

        // from left to right
        // int[] nge = Approach2(a);

        //from right to left
        int[] nge = Approach1(a);
        display(nge);
    }
}