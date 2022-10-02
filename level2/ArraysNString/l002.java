import java.util.HashMap;
import java.util.HashSet;

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

    // Smallest distinct window
    // https://practice.geeksforgeeks.org/problems/smallest-distant-window3132/1
    class Solution3 {
        public int findSubString(String str) {
            int[] freq = new int[128];
            int count = 0;
            for (char x : str.toCharArray()) {
                if (freq[x] == 0) {
                    count++;
                }
                freq[x] = 1;
            }
            int si = 0, ei = 0, len = (int) 1e9;
            while (ei < str.length()) {
                char ch = str.charAt(ei);
                if (freq[ch] == 1) {
                    count--;
                }
                freq[ch]--;
                ei++;

                while (count == 0) {
                    if (len > (ei - si)) {
                        len = ei - si;
                    }
                    ch = str.charAt(si);
                    if (freq[ch] == 0) {
                        count++;
                    }
                    freq[ch]++;
                    si++;
                }
            }
            return len;
        }
    }

    // Longest Substring with At Most K Distinct Characters
    // https://www.lintcode.com/problem/386/?fromId=207&_from=collection
    public class Solution4 {
        public int lengthOfLongestSubstringKDistinct(String s, int k) {
            int n = s.length(), si = 0, ei = 0, len = 0, count = 0;
            int[] map = new int[128]; // consists of English letters, digits, symbols and spaces
            while (ei < n) {
                if (map[s.charAt(ei)] == 0) {
                    k--;
                }
                map[s.charAt(ei)]++;
                ei++;
                while (k == -1) {
                    if (map[s.charAt(si)] == 1)
                        k++;
                    map[s.charAt(si)]--;
                    si++;
                }
                len = Math.max(len, ei - si);
            }
            return len;
        }
    }

    // 1456. Maximum Number of Vowels in a Substring of Given Length
    class Solution5 {
        public int maxVowels(String s, int k) {
            HashSet<Character> hs = new HashSet<>();
            hs.add('a');
            hs.add('e');
            hs.add('i');
            hs.add('o');
            hs.add('u');
            // HashMap<Integer,Integer>hm=new HashMap<>();
            int n = s.length(), si = 0, ei = 0, len = 0, count = 0;
            while (ei < n) {
                char ch = s.charAt(ei);
                if (hs.contains(ch) == true) {
                    count++;
                }
                k--;
                ei++;
                while (k == 0) {
                    len = Math.max(len, count);
                    if (hs.contains(s.charAt(si)) == true) {
                        count--;
                    }
                    si++;
                    k++;
                }
            }
            return len;
        }
    }

    // Subarrays With At Most ‘K’ Distinct Values
    // https://www.codingninjas.com/codestudio/problems/subarrays-with-at-most-k-distinct-values_1473804?leftPanelTab=2
    public class Solution6 {
        public static int kDistinctSubarrays(int arr[], int n, int k) {
            int si = 0, ei = 0, len = 0, count = 0;
            HashMap<Integer, Integer> hm = new HashMap<>();
            while (ei < n) {
                if (hm.containsKey(arr[ei]) == false) {
                    k--;
                }
                hm.put(arr[ei], hm.getOrDefault(arr[ei], 0) + 1);
                ei++;
                while (k == -1) {
                    if (hm.get(arr[si]) == 1) {
                        k++;
                        hm.remove(arr[si]);
                    } else
                        hm.put(arr[si], hm.get(arr[si]) - 1);
                    si++;
                }
                count += ei - si;
            }
            return count;
        }
    }

    // 992. Subarrays with K Different Integers
    class Solution7 {
        public static int kDistinctSubarrays(int arr[], int n, int k) {
            int si = 0, ei = 0, len = 0, count = 0;
            HashMap<Integer, Integer> hm = new HashMap<>();
            while (ei < n) {
                if (hm.containsKey(arr[ei]) == false) {
                    k--;
                }
                hm.put(arr[ei], hm.getOrDefault(arr[ei], 0) + 1);
                ei++;
                while (k == -1) {
                    if (hm.get(arr[si]) == 1) {
                        k++;
                        hm.remove(arr[si]);
                    } else
                        hm.put(arr[si], hm.get(arr[si]) - 1);
                    si++;
                }
                count += ei - si;
            }
            return count;
        }

        public int subarraysWithKDistinct(int[] nums, int k) {
            return kDistinctSubarrays(nums, nums.length, k) - kDistinctSubarrays(nums, nums.length, k - 1);
        }
    }

    // 1248. Count Number of Nice Subarrays
    class Solution8 {
        private int numberOfSubarraysAtMostK(int[] nums, int k) {
            int ei = 0, si = 0, count = 0, len = 0;
            int n = nums.length;
            while (ei < n) {
                if (nums[ei] % 2 != 0) {
                    k--;
                }
                ei++;
                while (k == -1) {
                    if (nums[si] % 2 != 0) {
                        k++;
                    }
                    si++;
                }
                count += ei - si;
            }
            return count;
        }

        public int numberOfSubarrays(int[] nums, int k) {
            return numberOfSubarraysAtMostK(nums, k) - numberOfSubarraysAtMostK(nums, k - 1);
        }
    }

    // 904. Fruit Into Baskets
    class Solution9 {
        public int totalFruit(int[] nums) {
            int n = nums.length, ei = 0, si = 0, len = 0, count = 0;
            HashMap<Integer, Integer> hm = new HashMap<>();
            int k = 2;
            while (ei < n) {
                if (hm.containsKey(nums[ei]) == false) {
                    k--;
                }
                hm.put(nums[ei], hm.getOrDefault(nums[ei], 0) + 1);
                ei++;
                while (k == -1) {
                    if (hm.get(nums[si]) == 1) {
                        k++;
                        hm.remove(nums[si]);
                    } else {
                        hm.put(nums[si], hm.get(nums[si]) - 1);
                    }
                    si++;
                }
                len = ei - si;
                count = Math.max(count, len);
            }
            return count;
        }
    }

    // 485. Max Consecutive Ones
    class Solution10 {
        public int findMaxConsecutiveOnes(int[] nums) {
            int count = 0, max = 0, len = 0;
            int ei = 0, si = 0, n = nums.length;
            while (ei < n) {
                if (nums[ei++] == 0) {
                    si = ei;
                }
                len = ei - si;
                max = Math.max(max, len);
            }
            return max;
        }
    }

    // https://www.lintcode.com/problem/883/description
    // 883 · Max Consecutive Ones II
    public class Solution11 {
        public int findMaxConsecutiveOnes(int[] nums) {
            int len = 0, count = 1, si = 0, ei = 0, n = nums.length;
            while (ei < n) {
                if (nums[ei] == 0) {
                    count--;
                }
                ei++;
                while (count == -1) {
                    if (nums[si] == 0) {
                        count++;
                    }
                    si++;
                }
                len = Math.max(len, ei - si);
            }
            return len;
        }
    }

    // 1004. Max Consecutive Ones III
    class Solution12 {
        public int longestOnes(int[] nums, int k) {
            int len = 0, count = k, si = 0, ei = 0, n = nums.length;
            while (ei < n) {
                if (nums[ei] == 0) {
                    count--;
                }
                ei++;
                while (count == -1) {
                    if (nums[si] == 0) {
                        count++;
                    }
                    si++;
                }
                len = Math.max(len, ei - si);
            }
            return len;
        }
    }
}
