package Recursion;
public class l2_RecursionTree {
    public static int coinChangePermutation_IN(int[] coins,int tar,String psf){
        if(tar==0)
        {
            System.out.println(psf);
            return 1;
        }
        int count=0;
        for (int i = 0; i < coins.length; i++) {
            if(tar-coins[i]>=0)
            {
                count+=coinChangePermutation_IN(coins, tar-coins[i], psf+coins[i]+" ");
            }
        }
        return count;
    }
    public static int coinChangeCombination_IN(int[] coins,int idx,int tar,String psf){
        if(tar==0)
        {
            System.out.println(psf);
            return 1;
        }
        int count=0;
        for (int i = idx; i < coins.length; i++) {
            if(tar-coins[i]>=0)
            {
                count+=coinChangeCombination_IN(coins, i,tar-coins[i], psf+coins[i]+" ");
            }
        }
        return count;
    }
    public static int coinChangePermutation_Sin(int[] coins,int tar,String psf){
        if(tar==0)
        {
            System.out.println(psf);
            return 1;
        }
        int count=0;
        for (int i = 0; i < coins.length; i++) {
            if(coins[i]>0&&tar-coins[i]>=0)
            {
                int val=coins[i];
                coins[i]=(-1)*coins[i];
                count+=coinChangePermutation_Sin(coins, tar-val,psf+val+" ");
                coins[i]=(-1)*coins[i];
            }
        }
        return count;
    }
    public static int coinChangeCombination_Sin(int[] coins,int idx,int tar,String psf){
        if(tar==0)
        {
            System.out.println(psf);
            return 1;
        }
        int count=0;
        for (int i = idx; i < coins.length; i++) {
            if(tar-coins[i]>=0)
            {
                count+=coinChangeCombination_Sin(coins, i+1, tar-coins[i], psf+coins[i]+" ");
            }
        }
        return count;
    }
    //============================
    
    public static int coinChangeCombination_Sin_Sub(int[] coins,int idx,int tar,String psf){
        if(tar==0||idx==coins.length)
        {
            if(tar==0){
                System.out.println(psf);
                return 1;
            }
            else
            {
                return 0;
            }
        }
        int count=0;
        if(tar-coins[idx]>=0)
        {
            count+=coinChangeCombination_Sin_Sub(coins, idx+1, tar-coins[idx], psf+coins[idx]+" ");
        }
        count+=coinChangeCombination_Sin_Sub(coins, idx+1, tar, psf);
        return count;
    }
    public static int coinChangePermutation_IN_Sub(int[] coins,int idx,int tar,String psf){
        if(tar==0||idx>=coins.length)
        {
            if(tar==0)
            {
                System.out.println(psf);
                return 1;
            }else
            {
                return 0;
            }
        }
        int count=0;
        if(tar-coins[idx]>=0){
            count+=coinChangePermutation_IN_Sub(coins, 0, tar-coins[idx], psf+coins[idx]+" ");
        }
        count+=coinChangePermutation_IN_Sub(coins, idx+1, tar, psf);
        return count;
    }

    public static int coinChangeCombination_IN_Sub(int[] coins,int idx,int tar,String psf){
        if(tar==0||idx>=coins.length)
        {
            if(tar==0)
            {
                System.out.println(psf);
                return 1;
            }else
            {
                return 0;
            }
        }
        int count=0;
        if(tar-coins[idx]>=0)
        {
            count+=coinChangeCombination_IN_Sub(coins, idx, tar-coins[idx], psf+coins[idx]);
        }
        count+=coinChangeCombination_IN_Sub(coins, idx+1, tar, psf);
        return count;
    }

    public static int coinChangePermutation_Sin_Sub(int[] coins,int idx,int tar,String psf){
        if(tar==0||idx>=coins.length)
        {
            if(tar==0)
            {
                System.out.println(psf);
                return 1;
            }else
            {
                return 0;
            }
        }
        int count=0;
        if(coins[idx]>0&&tar-coins[idx]>=0)
        {
            int val=coins[idx];
            coins[idx]=(-1)*coins[idx]; //Marking current element as visited
            count+=coinChangePermutation_Sin_Sub(coins,0,tar-val,psf+val+" ");
            coins[idx]=(-1)*coins[idx]; //UnMarking current element(as unvisited)
        }
        count+=coinChangePermutation_Sin_Sub(coins,idx+1,tar,psf);
        return count;
    }

    public static void main(String[] args) {
        int[] coins={2,3,5,7};
        int tar=10;
        // System.out.println(coinChangePermutation_IN(coins, tar, ""));
        // System.out.println(coinChangeCombination_IN(coins, 0, tar, ""));
        // System.out.println(coinChangePermutation_Sin(coins, tar, ""));
        // System.out.println(coinChangeCombination_Sin(coins, 0, tar, ""));
        // System.out.println(coinChangeCombination_Sin_Sub(coins, 0, tar, ""));
        // System.out.println(coinChangePermutation_IN_Sub(coins, 0, tar, ""));
        // System.out.println(coinChangeCombination_IN_Sub(coins, 0, tar, ""));
        System.out.println(coinChangePermutation_Sin_Sub(coins, 0, tar, ""));
    }
}