public class cutType {
    // Matrix Chain Multiplication
    // https://practice.geeksforgeeks.org/problems/matrix-chain-multiplication0303/1
    class Solution {
        private static int matrixMultiplication(int[] arr, int si, int ei) {
            if ((ei - si) == 1) {
                return 0;
            }
            int minCost = (int) 1e9;
            for (int cut = si + 1; cut < ei; cut++) {
                int leftChild = matrixMultiplication(arr, si, cut);
                int rightChild = matrixMultiplication(arr, cut, ei);
                int currCost = leftChild + (arr[si] * arr[cut] * arr[ei]) + rightChild;
                minCost = Math.min(minCost, currCost);
            }
            return minCost;
        }

        private static int matrixMultiplication_memo(int[] arr, int si, int ei, int[][] dp) {
            if ((ei - si) == 1) {
                return dp[si][ei] = 0;
            }
            if (dp[si][ei] != 0) {
                return dp[si][ei];
            }
            int minCost = (int) 1e9;
            for (int cut = si + 1; cut < ei; cut++) {
                int leftChild = matrixMultiplication_memo(arr, si, cut, dp);
                int rightChild = matrixMultiplication_memo(arr, cut, ei, dp);
                int currCost = leftChild + (arr[si] * arr[cut] * arr[ei]) + rightChild;
                minCost = Math.min(minCost, currCost);
            }
            return dp[si][ei] = minCost;
        }

        private static int matrixMultiplication_tabu(int[] arr, int SI, int EI, int[][] dp) {
            // Note: we have used gap strategy here,because si is staring from 0 and ei is
            // starting from last
            for (int gap = 0; gap <= EI; gap++) {
                for (int si = 0, ei = gap; ei <= EI; si++, ei++) {
                    if ((ei - si) <= 1) {
                        dp[si][ei] = 0;
                        continue;
                    }
                    int minCost = (int) 1e9;
                    for (int cut = si + 1; cut < ei; cut++) {
                        int leftChild = dp[si][cut]; // matrixMultiplication_memo(arr,si,cut,dp);
                        int rightChild = dp[cut][ei]; // matrixMultiplication_memo(arr,cut,ei,dp);
                        int currCost = leftChild + (arr[si] * arr[cut] * arr[ei]) + rightChild;
                        minCost = Math.min(minCost, currCost);
                    }
                    dp[si][ei] = minCost;
                }
            }
            return dp[SI][EI];
        }

        static int matrixMultiplication(int N, int arr[]) {
            // return matrixMultiplication(arr,0,N-1);
            int[][] dp = new int[N][N];
            // return matrixMultiplication_memo(arr,0,N-1,dp);
            return matrixMultiplication_tabu(arr, 0, N - 1, dp);

        }
    }

    // Brackets in Matrix Chain Multiplication
    // https://practice.geeksforgeeks.org/problems/brackets-in-matrix-chain-multiplication1024/1
    class Solution1 {
        private static String matrixMultiplication_tabu(int[] arr, int SI, int EI, int[][] dp) {
            String[][] sdp = new String[dp.length][dp[0].length];
            for (int gap = 0; gap <= EI; gap++) {
                for (int si = 0, ei = gap; ei <= EI; si++, ei++) {
                    if ((ei - si) <= 1) {
                        dp[si][ei] = 0;
                        sdp[si][ei] = (char) (si + 'A') + "";
                        continue;
                    }
                    int minCost = (int) 1e9;
                    String minCuts = "";
                    for (int cut = si + 1; cut < ei; cut++) {
                        int leftChild = dp[si][cut]; // matrixMultiplication_memo(arr,si,cut,dp);
                        int rightChild = dp[cut][ei]; // matrixMultiplication_memo(arr,cut,ei,dp);
                        int currCost = leftChild + (arr[si] * arr[cut] * arr[ei]) + rightChild;
                        if (minCost > currCost) {
                            minCost = currCost;
                            minCuts = "(" + sdp[si][cut] + sdp[cut][ei] + ")";
                        }
                    }
                    dp[si][ei] = minCost;
                    sdp[si][ei] = minCuts;
                }
            }
            return sdp[SI][EI];
        }

        static String matrixChainOrder(int arr[], int N) {
            int[][] dp = new int[N][N];
            return matrixMultiplication_tabu(arr, 0, N - 1, dp);
        }
    }

    public static class Pair {
        int max;
        int min;

        Pair(int max, int min) {
            this.max = max;
            this.min = min;
        }
    }

    private static Pair calculate(Pair a, Pair b, char symbol) {
        if (symbol == '+') {
            int max = a.max + b.max;
            int min = a.min + b.min;

            return new Pair(max, min);
        } else if (symbol == '*') {
            int max = a.max * b.max;
            int min = a.min * b.min;

            return new Pair(max, min);
        }
        return new Pair((int) -1e9, (int) 1e9);
    }

    private static Pair MinAndMaxValueOfExp(String s, int si, int ei, Pair[][] dp) {
        int flag = 1;
        int ti = si;
        int te = ei;
        while (ti <= te) {
            if (s.charAt(ti) >= '0' && s.charAt(ti) <= '9') {
                ti++;
                continue;
            }
            flag = 0;
            break;
        }
        if (flag == 1) {
            int x = Integer.parseInt(s.substring(si, ei + 1));
            Pair base = new Pair(x, x);
            dp[si][ei] = base;
            return dp[si][ei];
        }
        if (dp[si][ei] != null) {
            return dp[si][ei];
        }
        int minCost = (int) 1e9;
        int maxCost = (int) -1e9;
        Pair currCost = new Pair(maxCost, minCost);
        for (int cut = si + 1; cut < ei; cut++) {
            if (s.charAt(cut) >= '0' && s.charAt(cut) <= '9') {
                continue;
            }
            Pair leftChild = MinAndMaxValueOfExp(s, si, cut - 1, dp);
            Pair rightChild = MinAndMaxValueOfExp(s, cut + 1, ei, dp);
            currCost = calculate(leftChild, rightChild, s.charAt(cut));
            minCost = Math.min(minCost, currCost.min);
            maxCost = Math.max(maxCost, currCost.max);
        }
        currCost = new Pair(maxCost, minCost);
        return dp[si][ei] = currCost;
    }

    public static void main(String[] args) {
        // String s = "1+2*3+4*5";
        // String s = "12*12";
        String s = "11+2*342+4+11";
        Pair[][] dp = new Pair[s.length()][s.length()];
        Pair ans = MinAndMaxValueOfExp(s, 0, s.length() - 1, dp);
        System.out.println(ans.min + " " + ans.max);
    }
}
