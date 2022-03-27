import java.util.*;

public class MaximumConsecutiveOnes1 {

    public static int solution(int[] arr){
        int zc=0;
        int j=-1;
        int ans=0;
        for(int i=0;i<arr.length;i++)
        {
            if(arr[i]==0)
            {
                zc++;   
            }
            if(zc>1)
            {
                j+=1;
                while(j<=i)
                {
                    if(arr[j]==0)
                    {
                        zc-=1;
                        break;
                    }
                    j++;
                }
            }else
            {
                ans=Math.max(ans,i-j);
            }
        }

        return ans;
    }
    
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int[] arr = new int[n];
        for(int i = 0 ; i  < n; i++){
            arr[i] = scn.nextInt();
        }
        System.out.println(solution(arr));
        scn.close();
	}

}
