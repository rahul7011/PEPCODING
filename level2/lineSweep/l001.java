import java.util.*;
//====================Line Sweep======================
// https://leetcode.com/discuss/study-guide/2166045/line-sweep-algorithms

class Solution {
    public int maximumPopulation(int[][] logs) {
        // linesweep
        Arrays.sort(logs, (a, b) -> {
            return a[0] - b[0];
        });
        int[] pre = new int[2050 + 1];
        for (int[] l : logs) {
            pre[l[0]]++;
            pre[l[1]]--;
        }
        int max = 0;
        int sum = 0;
        int mxYear = 1950;
        for (int i = 1950; i <= 2050; i++) {
            sum += pre[i];
            if (max < sum) {
                max = sum;
                mxYear = i;
            }
        }
        return mxYear;
    }
}

// Meeting rooms ii
// https://leetcode.ca/all/253.html
// https://www.lintcode.com/problem/919/

public class Solution1 {
    /**
     * Definition of Interval:
     * public class Interval {
     * int start, end;
     * Interval(int start, int end) {
     * this.start = start;
     * this.end = end;
     * }
     * }
     */
    public int minMeetingRooms(List<Interval> intervals) {
        // TreeMap to sort on the basis of start time
        TreeMap<Integer, Integer> hm = new TreeMap<>();
        for (int i = 0; i < intervals.size(); i++) {
            int u = intervals.get(i).start;
            int v = intervals.get(i).end;
            hm.put(u, hm.getOrDefault(u, 0) + 1);
            hm.put(v, hm.getOrDefault(v, 0) - 1);
        }
        int mx = 0;
        int curr = 0;
        for (int key : hm.keySet()) {
            // everytime curr get incremented it means meetings are getting merged,hence we
            // need a new room
            curr += hm.get(key);
            mx = Math.max(mx, curr);
        }
        return mx;
    }
}

/**
 * Definition of Interval:
 * public class Interval {
 * int start, end;
 * Interval(int start, int end) {
 * this.start = start;
 * this.end = end;
 * }
 * }
 */

// 252. Meeting Rooms
// https://leetcode.ca/all/252.html
// https://www.lintcode.com/problem/920/
public class Solution9 {
    /**
     * @param intervals: an array of meeting time intervals
     * @return: if a person could attend all meetings
     */
    public boolean canAttendMeetings(List<Interval> intervals) {
        TreeMap<Integer, Integer> hm = new TreeMap<>();
        for (Interval v : intervals) {
            int st = v.start;
            int ed = v.end;
            hm.put(st, hm.getOrDefault(st, 0) + 1);
            hm.put(ed, hm.getOrDefault(ed, 0) - 1);
        }
        int sum = 0;
        for (int key : hm.keySet()) {
            sum += hm.get(key);
            if (sum > 1) {
                return false;
            }
        }
        return true;
    }
}

// 731. My Calendar II
// https://leetcode.com/problems/my-calendar-ii/description/
class MyCalendarTwo {
    TreeMap<Integer, Integer> hm;

    public MyCalendarTwo() {
        hm = new TreeMap<>();
    }

    public boolean book(int start, int end) {
        hm.put(start, hm.getOrDefault(start, 0) + 1);
        hm.put(end, hm.getOrDefault(end, 0) - 1);
        int curr = 0;
        // System.out.println("{"+start+","+end+"}");
        for (int key : hm.keySet()) {
            // System.out.println(key+"->"+hm.get(key));
            curr += hm.get(key);
            if (curr >= 3) {
                hm.put(start, hm.getOrDefault(start, 0) - 1);
                hm.put(end, hm.getOrDefault(end, 0) + 1);
                return false;
            }
        }
        return true;
    }
}

// 2237 - Count Positions on Street With Required Brightness
// https://leetcode.ca/2022-05-09-2237-Count-Positions-on-Street-With-Required-Brightness/
/*
 * Approach:
 * Similar, here we are given a threshold of brightness for each index,
 * first fill up the count by +1 and -1 index where brightness start and end.
 * Now scan the vector and see if threshold achieved , if yes ++ans.
 * 
 */

// 1893. Check if All the Integers in a Range Are Covered
// https://leetcode.com/problems/check-if-all-the-integers-in-a-range-are-covered/description/
class Solution2 {
    public boolean isCovered(int[][] ranges, int left, int right) {
        TreeMap<Integer, Integer> hm = new TreeMap<>();
        for (int[] x : ranges) {
            int start = x[0];
            int end = x[1] + 1;
            hm.put(start, hm.getOrDefault(start, 0) + 1);
            hm.put(end, hm.getOrDefault(end, 0) - 1);
        }
        int sum = 0;
        for (int i = 1; i <= 50; i++) {
            sum += hm.getOrDefault(i, 0);
            if (left <= i && i <= right && sum <= 0) {
                return false;
            }
        }
        return true;
    }
}

// 2779. Maximum Beauty of an Array After Applying Operation
// https://leetcode.com/problems/maximum-beauty-of-an-array-after-applying-operation/description/
class Solution4 {
    public int maximumBeauty(int[] nums, int k) {
        int[] pre = new int[300000 + 5];
        for (int x : nums) {
            int y = x - k;
            int z = x + k;
            pre[y + 100000]++;
            pre[z + 100000 + 1]--;
        }
        int sum = 0;
        int mx = 0;
        for (int i = 0; i < pre.length; i++) {
            sum += pre[i];
            mx = Math.max(mx, sum);
        }
        return mx;
    }
}

// 370. Range Addition
// https://leetcode.ca/all/370.html
// https://www.lintcode.com/problem/903/
class Solution3 {
    public int[] getModifiedArray(int length, int[][] updates) {
        int[] res = new int[length];
        for (int[] x : updates) {
            int u = x[0];
            int v = x[1];
            int factor = x[2];
            res[u] += factor;
            if (v + 1 < length)
                res[v + 1] -= factor;
        }
        int curr = 0;
        for (int i = 0; i < res.length; i++) {
            curr += res[i];
            res[i] = curr;
        }
        return res;
    }
}

// ==============Type 2==============
// https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/solutions/93735/a-concise-template-for-overlapping-interval-problem/

// Leetcode 452
class Solution5 {
    public int findMinArrowShots(int[][] points) {
        Arrays.sort(points, (a, b) -> {
            if (a[0] <= b[0])
                return -1;
            return 1;
        });
        int minEnd = Integer.MAX_VALUE;
        int count = 0;
        for (int[] x : points) {
            if (x[0] > minEnd) {
                count++;
                minEnd = x[1];
            } else {
                minEnd = Math.min(minEnd, x[1]);
            }
        }
        return count + (points.length == 0 ? 0 : 1);
    }
}

// 435. Non-overlapping Intervals
class Solution6 {
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0)
            return 0;
        int count = 0; // result
        // minEnd is Key parameter "active set" for overlapping intervals;
        int minEnd = Integer.MAX_VALUE;
        Arrays.sort(intervals, (a, b) -> (a[0] - b[0])); // ascending order
        for (int[] in : intervals) {
            if (in[0] >= minEnd) { // changing some start , start new " don't overlapp "
                count++;
                minEnd = in[1];
            } else {
                minEnd = Math.min(minEnd, in[1]);
            }
        }
        return intervals.length - count - 1; // there are count + 1 : overlapping
    }
}

// 56. Merge Intervals
class Solution7 {
    private List<int[]> solve(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> {
            return a[0] - b[0];
        });
        List<int[]> list = new ArrayList<>();
        int minEnd = intervals[0][0];
        int minBeg = intervals[0][1];
        for (int[] x : intervals) {
            if (x[0] > minEnd) {
                list.add(new int[] { minBeg, minEnd });
                minBeg = x[0];
                minEnd = x[1];
            } else {
                minBeg = Math.min(minBeg, x[0]);
                minEnd = Math.max(minEnd, x[1]);
            }
        }
        list.add(new int[] { minBeg, minEnd });
        return list;
    }

    public int[][] merge(int[][] intervals) {
        List<int[]> res = solve(intervals);
        int[][] ans = new int[res.size()][2];
        int k = 0;
        for (int[] x : res) {
            ans[k++] = x;
        }
        return ans;
    }
}

// 57. Insert Interval
class Solution8 {
    private int[][] solve(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> {
            return a[0] - b[0];
        });
        List<int[]> list = new ArrayList<>();
        int minEnd = intervals[0][0];
        int minBeg = intervals[0][1];
        for (int[] x : intervals) {
            if (x[0] > minEnd) {
                list.add(new int[] { minBeg, minEnd });
                minBeg = x[0];
                minEnd = x[1];
            } else {
                minBeg = Math.min(minBeg, x[0]);
                minEnd = Math.max(minEnd, x[1]);
            }
        }
        list.add(new int[] { minBeg, minEnd });
        int[][] ans = new int[list.size()][2];
        int k = 0;
        for (int[] x : list) {
            ans[k++] = x;
        }
        return ans;
    }

    public int[][] insert(int[][] intervals, int[] newInterval) {
        int[][] newIntervals = new int[intervals.length + 1][2];
        int k = 0;
        for (int[] x : intervals) {
            newIntervals[k++] = x;
        }
        newIntervals[k++] = newInterval;
        Arrays.sort(newIntervals, (a, b) -> {
            return a[0] - b[0];
        });
        return solve(newIntervals);
    }
}