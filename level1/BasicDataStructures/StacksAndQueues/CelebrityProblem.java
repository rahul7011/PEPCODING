import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class CelebrityProblem {
    public static void main(String[] args) throws Exception {
        // write your code here
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][n];

        for (int j = 0; j < n; j++) {
            String line = br.readLine();
            for (int k = 0; k < n; k++) {
                arr[j][k] = line.charAt(k) - '0';
            }
        }

        findCelebrity(arr);

    }

    public static void findCelebrity(int[][] arr) {
        // if a celebrity is there print it''s index (not position), if there is not
        // then
        // print "none"
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            st.push(i);
        }
        // now elimination
        while (st.size() != 1) {
            int val1 = st.pop();
            int val2 = st.pop();
            if (arr[val1][val2] == 1) {
                // means val1 knows val2 ,hence val1 can't be a celebrity,hence eliminated
                st.push(val2);
            } else {
                // since val1 doesn't know val2, this means val2 can't be a celebrirt,hence
                // eliminated
                st.push(val1);
            }
        }
        boolean check = true;
        // Now we are only checking for the eligible candidate left in the stack for
        // celebrity
        for (int i = 0; i < arr.length; i++) {
            if (arr[st.peek()][i] == 1) {
                check = false;
                break;
            }
            if (i != st.peek() && arr[i][st.peek()] == 0) {
                check = false;
                break;
            }
        }
        if (check == true) {
            System.out.println(st.peek());
        } else {
            System.out.println("none");
        }
    }

}