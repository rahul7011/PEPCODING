public class l5_bits {
    public static int setTrue(int x,int idx){
        int mask=1<<idx;
        return x|mask;
    }
    public static int setFalse(int x,int idx){
        int mask=~(1<<idx);
        return x&mask;
    }
    public static boolean isEven(int x){
        return (x&1)==0;
    }

    //leetcode 231
    public boolean isPowerOfTwo(int n) {
        //-ve numbers can never be a power of 2
        //0 is not a power of 2
        return n>0&&(n&(n-1))==0;
    }
    //leetcode 342
    public boolean isPowerOfFour(int n) {
        //for a number to be a power of 4 ,it should be a power of 2 aswell atleast
        if(isPowerOfTwo(n)==false)
        {
            return false;
        }
        //now check for even zeroes
        int count=0;
        while(n!=0)
        {
            if((n&1)==0)
            {
                count++;
            }
    // triple shift always appends 0 to the MSB(protects from infinte loop when MSB is 1)
            n=n>>>1;
        }
        return (count&1)==0;
    }

    //leetcode 268
    public int missingNumber(int[] nums) {
        int ans=nums.length;    //To include the last number as well
        int i=0;
        while(i<nums.length)
        {
            ans^=nums[i]^i;
            i++;
        }
        return ans;
    }
    //leetcode 191(Concept of hamming distance)
    public int hammingWeight(int n) {
        int count=0;
        while(n!=0)
        {
            count++;
            n=(n&(n-1));
        }
        return count;
    }

    // leetcode 338(Counting Bits)
    public int[] countBits(int n) {
        int[] ans=new int[n+1];
        for(int i=1;i<=n;i++)
        {
            ans[i]=ans[(i&(i-1))]+1;
        }
        return ans;
    }

    //leetcode 260(Single Number III)
    public int[] singleNumber(int[] nums) {
        int xor=0;
        for(int i=0;i<nums.length;i++)
        {
            xor^=nums[i];
        }
        int firstSetBit=(xor&-xor);
        int a=0,b=0;
        for(int i=0;i<nums.length;i++){
            if((firstSetBit&nums[i])==0)
            {
                a^=nums[i];
            }else
            {
                b^=nums[i];
            }
        }
        return new int[]{a,b};
    }


    public static void main(String[] args) {
        System.out.println(setTrue(21, 1));
        System.out.println(setFalse(21, 2));
    }
}
