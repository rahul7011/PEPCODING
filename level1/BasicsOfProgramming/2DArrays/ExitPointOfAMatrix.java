import java.util.Scanner;

public class ExitPointOfAMatrix {
    public static void main(String[] args) {
        Scanner scn=new Scanner(System.in);
        int n=scn.nextInt();
        int m=scn.nextInt();
        int[][] arr=new int[n][m];
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<m;j++)
            {
                arr[i][j]=scn.nextInt();
            }
        }
        int dir=0;  //East
        int i=0;
        int j=0;
        while(true)
        {
            dir=(dir+arr[i][j])%4;
            if(dir==0)
            {
                //East
                j++;
                if(j==arr[0].length)
                {
                    j--;
                    break;
                }
            }
            else if(dir==1)
            {
                //South
                i++;
                if(i==arr.length)
                {
                    i--;
                    break;
                }
            }
            else if(dir==2)
            {
                //West
                j--;
                if(j==-1)
                {
                    j++;
                    break;
                }
            }
            else
            {
                //North
                i--;
                if(i==-1)
                {
                    i++;
                    break;
                }
            }
        }
        System.out.print(i+"\n"+j);
        scn.close();

    }
}
