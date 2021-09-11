import java.util.Scanner;

public class Pattern18 {
    public static void main(String[] args) {
        Scanner scn=new Scanner(System.in);
        int n=scn.nextInt();
        for(int i=1;i<=n;i++)
        {
            for(int j=1;j<=n;j++)
            {
                if(i==1 || i==n || (i+j)==n+1 || (i==j)||((i>n/2)&&(i>j)&&(i+j>n+1)))
                {
                    System.out.print("*\t");
                }
                else
                {
                    System.out.print("\t");
                }
            }
            System.out.println();
        }
        scn.close();
    }
}

/*

*	*	*	*	*	*	*	
	*				*	
		*		*	
			*	
		*	*	*	
	*	*	*	*	*	
*	*	*	*	*	*	*

*/
