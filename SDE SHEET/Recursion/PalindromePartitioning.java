import java.util.*;
public class PalindromePartitioning {
    class Solution {
        private boolean isPalindrome(String s) {
            int left = 0;
            int right = s.length() - 1;
            while (left < right) {
                if (s.charAt(left) != s.charAt(right)) {
                    return false;
                }
                left++;
                right--;
            }
            return true;
        }

        private void partition(String s, List<List<String>> ans, List<String> smallAns) {
            if (s.length() == 0) {
                ans.add(new ArrayList(smallAns));
                return;
            }
            String check = "";
            for (int i = 0; i < s.length(); i++) {
                check += s.charAt(i);
                if (isPalindrome(check) == true) {
                    smallAns.add(new String(check));
                    partition(s.substring(i + 1), ans, smallAns);
                    smallAns.remove(smallAns.size() - 1);
                }
            }
        }

        public List<List<String>> partition(String s) {
            List<String> smallAns = new ArrayList<>();
            List<List<String>> ans = new ArrayList<>();
            partition(s, ans, smallAns);
            return ans;
        }
    }
}
