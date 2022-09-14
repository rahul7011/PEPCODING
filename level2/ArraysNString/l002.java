public class l002 {
    // Generic template to solve these type of questions

    // 3. Longest Substring Without Repeating Characters
    class Solution {
        public int lengthOfLongestSubstring(String s) {
            if (s.length() <= 1) {
                return s.length();
            }
            int n = s.length(), si = 0, ei = 0, len = 0, count = 0;
            int[] map = new int[128]; // consists of English letters, digits, symbols and spaces
            while (ei < n) {
                if (map[s.charAt(ei)] > 0) {
                    count++;
                }
                map[s.charAt(ei)]++;
                ei++;
                while (count > 0) {
                    if (map[s.charAt(si)] > 1) {
                        count--;
                    }
                    map[s.charAt(si)]--;
                    si++;
                }
                len = Math.max(len, ei - si);
            }
            return len;
        }
    }

    // 928 · Longest Substring with At Most Two Distinct Characters
    // https://www.lintcode.com/problem/928/
    public class Solution1 {
        public int lengthOfLongestSubstringTwoDistinct(String s) {
            if (s.length() <= 1) {
                return s.length();
            }
            int n = s.length(), si = 0, ei = 0, len = 0, count = 0;
            int[] map = new int[128]; // consists of English letters, digits, symbols and spaces
            while (ei < n) {
                if (map[s.charAt(ei)] == 0) {
                    count++;
                }
                map[s.charAt(ei)]++;
                ei++;
                while (count > 2) {
                    if (map[s.charAt(si)] > 0) {
                        if (map[s.charAt(si)] == 1)
                            count--;
                    }
                    map[s.charAt(si)]--;
                    si++;
                }
                len = Math.max(len, ei - si);
            }
            return len;
        }
    }

    // 76. Minimum Window Substring
    class Solution2 {
        public String minWindow(String s, String t) {
            int[] freq = new int[128]; // consists of English letters, digits, symbols and spaces
            int i = 0;
            while (i < t.length()) {
                freq[t.charAt(i++)]++;
            }
            int n = s.length(), si = 0, ei = 0, len = (int) 1e9, count = t.length();
            int startIdx = 0;
            while (ei < n) {
                if (freq[s.charAt(ei)] > 0) {
                    count--;
                }
                freq[s.charAt(ei)]--;
                ei++;
                while (count == 0) {
                    if (len > (ei - si)) {
                        len = ei - si;
                        startIdx = si;
                    }
                    if (freq[s.charAt(si)] == 0) {
                        count++;
                    }
                    freq[s.charAt(si)]++;
                    si++;
                }

            }
            return len == (int) 1e9 ? "" : s.substring(startIdx, startIdx + len);
        }
    }
}
