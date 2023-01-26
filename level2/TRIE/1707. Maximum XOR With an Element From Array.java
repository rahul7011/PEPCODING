class Solution {
    public static class Node {
        Node[] children = new Node[2];
    }

    public static Node root;

    public static void insert(int num) {
        Node node = root;
        for (int i = 31; i >= 0; i--) {
            int bit = (num >> i) & 1;
            if (node.children[bit] == null) {
                node.children[bit] = new Node();
            }
            node = node.children[bit];
        }
    }

    public static int getMax(int num) {
        Node node = root;
        int ans = 0;
        for (int i = 31; i >= 0; i--) {
            int bit = (num >> i) & 1;
            if (node.children[1 - bit] != null) {
                ans = (ans | (1 << i));
                node = node.children[1 - bit];
            } else {
                node = node.children[bit];
            }
        }
        return ans;
    }

    public int[] maximizeXor(int[] nums, int[][] queries) {
        root = new Node();
        Arrays.sort(nums);
        int[][] queriesWithIdx = new int[queries.length][3];
        for (int i = 0; i < queries.length; i++) {
            int num = queries[i][0];
            int limit = queries[i][1];
            int idx = i;
            queriesWithIdx[i][0] = num;
            queriesWithIdx[i][1] = limit;
            queriesWithIdx[i][2] = idx;
        }
        Arrays.sort(queriesWithIdx, (a, b) -> {
            return a[1] - b[1];
        });
        int idx = 0;
        int[] ans = new int[queries.length];
        for (int i = 0; i < queriesWithIdx.length; i++) {
            int num = queriesWithIdx[i][0];
            int limit = queriesWithIdx[i][1];
            while (idx < nums.length && nums[idx] <= limit) {
                insert(nums[idx]);
                idx++;
            }
            if (idx == 0) {
                ans[queriesWithIdx[i][2]] = -1;
                continue;
            }
            int max = getMax(num);
            ans[queriesWithIdx[i][2]] = max;

        }
        return ans;
    }
}