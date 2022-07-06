public class practice {
    private static int perInfi(int[] coins, int tar, String psf) {
        if (tar == 0) {
            System.out.println(psf);
            return 1;
        }
        int count = 0;
        for (int i = 0; i < coins.length; i++) {
            if (tar - coins[i] >= 0) {
                count += perInfi(coins, tar - coins[i], psf + coins[i] + " ");
            }
        }
        return count;
    }

    private static int combInfi(int[] coins, int idx, int tar, String psf) {
        if (tar == 0) {
            System.out.println(psf);
            return 1;
        }
        int count = 0;
        for (int i = idx; i < coins.length; i++) {
            if (tar - coins[i] >= 0) {
                count += combInfi(coins, i, tar - coins[i], psf + coins[i] + " ");
            }
        }
        return count;
    }

    private static int combSing(int[] coins, int idx, int tar, String psf) {
        if (tar == 0) {
            System.out.println(psf);
            return 1;
        }
        int count = 0;
        for (int i = idx; i < coins.length; i++) {
            if (tar - coins[i] >= 0) {
                count += combSing(coins, i + 1, tar - coins[i], psf + coins[i] + " ");
            }
        }
        return count;
    }

    private static int perSing(int[] coins, int tar, String psf) {
        if (tar == 0) {
            System.out.println(psf);
            return 1;
        }
        int count = 0;
        for (int i = 0; i < coins.length; i++) {
            if (coins[i] > 0 && tar - coins[i] >= 0) {
                coins[i] *= (-1);
                count += perSing(coins, tar - (coins[i] * (-1)), psf + (coins[i] * (-1)) + " ");
                coins[i] *= (-1);
            }
        }
        return count;
    }

    private static int combSingSubseq(int[] coins, int idx, int tar, String psf) {
        if (tar == 0||idx==coins.length) {
            if(tar==0)
            {
                System.out.println(psf);
                return 1;
            }
            return 0;
        }
        int count = 0;
        if (tar - coins[idx] >= 0) {
            count += combSingSubseq(coins, idx + 1, tar - coins[idx], psf + coins[idx] + " ");
        }
        count += combSingSubseq(coins, idx + 1, tar, psf);
        return count;
    }

    private static int permInfiSubseq(int[] coins,int idx,int tar,String psf){
        if(tar==0||idx==coins.length)
        {
            if(tar==0)
            {
                System.out.println(psf);
                return 1;
            }
            return 0;
        }
        int count=0;
        if(tar-coins[idx]>=0)
        {
            count+=permInfiSubseq(coins, 0, tar-coins[idx], psf+coins[idx]+" ");
        }
        count+=permInfiSubseq(coins, idx+1, tar, psf);
        return count;
    }
    private static int combInfiSubseq(int[] coins,int idx,int tar,String psf){
        if(tar==0||idx==coins.length)
        {
            if(tar==0)
            {
                System.out.println(psf);
                return 1;
            }
            return 0;
        }
        int count=0;
        if(tar-coins[idx]>=0)
        {
            count+=combInfiSubseq(coins, idx, tar-coins[idx], psf+coins[idx]+" ");
        }
        count+=combInfiSubseq(coins, idx+1, tar, psf);
        return count;
    }
    private static int permSingSubseq(int[] coins,int idx,int tar,String psf)
    {
        if(tar==0||idx==coins.length)
        {
            if(tar==0)
            {
                System.out.println(psf);
                return 1;
            }
            return 0;
        }
        int count=0;
        if(coins[idx]>0&&tar-coins[idx]>=0){
            coins[idx]*=(-1);
            count+=permSingSubseq(coins,0,tar-(coins[idx]*(-1)),psf+(coins[idx]*(-1))+" ");
            coins[idx]*=(-1);
        }
        count+=permSingSubseq(coins, idx+1, tar, psf);
        return count;
    }

    private static void permAndComb() {
        int[] coins = { 2, 3, 5, 7 };
        int tar = 10;
        System.out.println(perInfi(coins,tar,""));
        System.out.println(combInfi(coins,0,tar,""));
        System.out.println(combSing(coins,0,tar,""));
        System.out.println(perSing(coins, tar, ""));
    }

    private static void permAndCombSubseq()

    {
        int[] coins = { 2, 3, 5, 7 };
        int tar = 10;
        // System.out.println(combSingSubseq(coins, 0, tar, ""));
        // System.out.println(permInfiSubseq(coins, 0, tar, ""));
        // System.out.println(combInfiSubseq(coins, 0, tar, ""));
        System.out.println(permSingSubseq(coins, 0, tar, ""));
        

    }

    public static void main(String[] args) {

        // permAndComb();
        permAndCombSubseq();

    }
}
