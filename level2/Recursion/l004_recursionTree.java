import java.util.ArrayList;

public class l004_recursionTree {
    public static int permuatation_infinite(int[] coins, int tar, String psf) {
        if (tar == 0) {
            System.out.println(psf);
            return 1;
        }
        int count = 0;
        for (int idx = 0; idx < coins.length; idx++) {
            if (tar - coins[idx] >= 0) {
                count += permuatation_infinite(coins, tar - coins[idx], psf + coins[idx] + " ");
            }
        }
        return count;
    }

    public static int permuatation_single(int[] coins, int tar, String psf) {
        if (tar == 0) {
            System.out.println(psf);
            return 1;
        }
        int count = 0;
        for (int idx = 0; idx < coins.length; idx++) {
            if (coins[idx] > 0 && tar - coins[idx] >= 0) {
                int temp = coins[idx];
                coins[idx] *= -1;
                count += permuatation_single(coins, tar - temp, psf + temp + " ");
                coins[idx] = temp;
            }
        }
        return count;
    }

    public static void permuteCall() {
        int[] coins = { 2, 3, 5, 7 };
        // System.out.println(permuatation_infinite(coins, 10, ""));
        // System.out.println(permuatation_infinite_subseq(coins, 0, 10, ""));
        System.out.println(permuatation_single(coins, 10, ""));
        System.out.println(permuatation_single_subseq(coins, 0, 10, ""));
    }

    public static void combiCall() {
        int[] coins = { 2, 3, 5, 7 };
        // System.out.println(combination_infinite(coins, 0, 10, ""));
        // System.out.println(combination_infinite_subseq(coins, 0, 10, ""));
        // System.out.println(combination_single(coins, 0, 10, ""));
        // System.out.println(combination_single_subseq(coins, 0, 10, ""));
    }

    public static int combination_infinite(int[] coins, int idx, int tar, String psf) {
        if (tar == 0) {
            System.out.println(psf);
            return 1;
        }
        int count = 0;
        for (int i = idx; i < coins.length; i++) {
            if (tar - coins[i] >= 0) {
                count += combination_infinite(coins, i, tar - coins[i], psf + coins[i] + " ");
            }
        }
        return count;
    }

    public static int combination_single(int[] coins, int idx, int tar, String psf) {
        if (tar == 0) {
            System.out.println(psf);
            return 1;
        }
        int count = 0;
        for (int i = idx; i < coins.length; i++) {
            if (tar - coins[i] >= 0) {
                count += combination_single(coins, i + 1, tar - coins[i], psf + coins[i] + " ");
            }
        }
        return count;
    }

    // ========================================================

    public static int combination_single_subseq(int[] coins, int idx, int tar, String psf) {
        if (tar == 0 || idx == coins.length) {
            if (tar == 0) {
                System.out.println(psf);
                return 1;
            }
            return 0;
        }
        int count = 0;
        if (tar - coins[idx] >= 0) {
            count += combination_single_subseq(coins, idx + 1, tar - coins[idx], psf + coins[idx] + " ");
        }
        count += combination_single_subseq(coins, idx + 1, tar, psf);
        return count;
    }

    public static int combination_infinite_subseq(int[] coins, int idx, int tar, String psf) {
        if (tar == 0 || idx == coins.length) {
            if (tar == 0) {
                System.out.println(psf);
                return 1;
            }
            return 0;
        }

        int count = 0;
        if (tar - coins[idx] >= 0) {
            count += combination_infinite_subseq(coins, idx, tar - coins[idx], psf + coins[idx] + " ");
        }
        count += combination_infinite_subseq(coins, idx + 1, tar, psf);
        return count;
    }

    public static int permuatation_infinite_subseq(int[] coins, int idx, int tar, String psf) {
        if (tar == 0 || idx == coins.length) {
            if (tar == 0) {
                System.out.println(psf);
                return 1;
            }
            return 0;
        }
        int count = 0;
        if (tar - coins[idx] >= 0) {
            count += permuatation_infinite_subseq(coins, 0, tar - coins[idx], psf + coins[idx] + " ");
        }
        count += permuatation_infinite_subseq(coins, idx + 1, tar, psf);
        return count;
    }

    public static int permuatation_single_subseq(int[] coins, int idx, int tar, String psf) {
        if (tar == 0 || idx == coins.length) {
            if (tar == 0) {
                System.out.println(psf);
                return 1;
            }
            return 0;
        }
        int count = 0;
        if (coins[idx] > 0 && tar - coins[idx] >= 0) {
            int temp = coins[idx];
            coins[idx] *= -1;
            count += permuatation_single_subseq(coins, 0, tar - temp, psf + temp + " ");
            coins[idx] = temp;
        }
        count += permuatation_single_subseq(coins, idx + 1, tar, psf);
        return count;
    }

    //wrong code(was trying to build subseq using combination_single method)
    // public static int subSeq(String str, int idx, ArrayList<String> ans, String psf) {
    //     if (str.length() == idx) {
    //         ans.add(psf);
    //         return 1;
    //     }
    //     int count = 0;
    //     for (int i = idx; i < str.length(); i++) {
    //         count += subSeq(str, i + 1, ans, psf + str.charAt(i));
    //         // count += subSeq(str, i + 1, ans, psf);
    //     }
    //     return count;
    // }

    public static void main(String[] args) {
        // permuteCall();
        // combiCall();
        ArrayList<String> ans = new ArrayList<>();
        // System.out.println(subSeq("abc", 0, ans, ""));
        System.out.println(ans);
    }
}
