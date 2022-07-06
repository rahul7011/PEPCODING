import java.util.*;
//https://practice.geeksforgeeks.org/problems/subset-sums2234/1#
class Solution {
    private int subsetSums(ArrayList<Integer> arr, int idx, ArrayList<Integer> ans, int total) {
        if (idx == arr.size()) {
            ans.add(total);
            return 1;
        }
        int count = 0;
        count += subsetSums(arr, idx + 1, ans, total);
        count += subsetSums(arr, idx + 1, ans, total + arr.get(idx));
        return count;
    }

    ArrayList<Integer> subsetSums(ArrayList<Integer> arr, int N) {
        // coin change combination single sub
        ArrayList<Integer> ans = new ArrayList<>();
        subsetSums(arr, 0, ans, 0);
        return ans;
    }
}