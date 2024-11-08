import java.util.ArrayList;

public class PermutationSequence {
    //https://www.youtube.com/watch?v=wT7gcXLYoao
    class Solution {
        public String getPermutation(int n, int k) {
            // O(n2)
            int fact = 1;
            ArrayList<Integer> numbers = new ArrayList<>();
            for (int i = 1; i < n; i++) {
                fact = fact * i;
                numbers.add(i);
            }
            numbers.add(n);
            String ans = "";
            k = k - 1;
            while (true) {
                ans = ans + numbers.get(k / fact);
                numbers.remove(k / fact);
                if (numbers.size() == 0) {
                    break;
                }
                k = k % fact;
                fact = fact / numbers.size();
            }
            return ans;
        }
    }
}
