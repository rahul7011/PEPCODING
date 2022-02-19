import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class StockSpan {
    public static void display(int[] a) {
        StringBuilder sb = new StringBuilder();

        for (int val : a) {
            sb.append(val + "\n");
        }
        System.out.println(sb);
    }
    public static int[] Approach1(int[] arr)
    {
        Stack<Integer>st=new Stack<>();
        int[] ans=new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            while(st.size()>0&&arr[st.peek()]<=arr[i])
            {
                st.pop();
            }
            if(st.size()==0)
            {
                ans[i]=i+1;
            }else
            {
                //peek idx
                int pidx=st.peek();
                ans[i]=i-pidx;
            }
            st.push(i);
        }
        return ans;
    }
    
    public static int[] Approach2(int[] arr)
    {
        Stack<Integer>st=new Stack<>();
        int[] ans=new int[arr.length];
        for (int i = arr.length-1; i >= 0; i--) {
            while(st.size()>0&&arr[st.peek()]<=arr[i])
            {
                //peek idx
                int pidx=st.peek();
                ans[pidx]=pidx-i;
                st.pop();
            }
            st.push(i);
        }
        while(st.size()!=0)
        {
            //peek idx
            int pidx=st.peek();
            ans[pidx]=pidx+1;
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

        //from left to right
        // int[] span = Approach1(a);

        //from right to left
        int[] span = Approach2(a);
        display(span);
    }
}