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
    //
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
    public static void main(String[] args) {
        System.out.println(setTrue(21, 1));
        System.out.println(setFalse(21, 2));
    }
}
