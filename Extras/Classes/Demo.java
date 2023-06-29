import java.util.*;

public class Demo {
    private static void solve1(int n, int m) {
        boolean[] primes = new boolean[m + 1];
        primes[0] = true;
        primes[1] = true;
        for (int i = 2; i * i <= m; i++) {
            if (primes[i] == false) {
                for (int j = i * i; j <= m; j += i) {
                    primes[j] = true;
                }
            }
        }
        for (int i = n; i <= m; i++) {
            // if(primes[i]==false)
            // System.out.println(i);
            if (primes[i] == false && (i - 6) >= 0 && primes[i - 6] == false) {
                System.out.println("(" + (i - 6) + "," + i + ")");
            }
        }
    }

    private static void solve2(String s) {
        String[] str = s.split(" ");
        for (String x : str) {
            try {
                long num = Long.parseLong(x);
                boolean flag = false;
                while (num != 0) {
                    if (num % 10 == 9) {
                        flag = true;
                        break;
                    }
                    num /= 10;
                }
                if (flag == false)
                    System.out.println(x);
            } catch (NumberFormatException e) {
                continue;
            }

        }
    }

    private static boolean solve3(String s1, String s2) {
        int j = 0;
        String check="";
        for (int i = 0; i < s1.length(); i++) {
            char ch1 = s1.charAt(i);
            while (j < s2.length()) {
                char ch2 = s2.charAt(j);
                j++;
                if (ch1 == ch2) {
                    check+=ch2;
                    break;
                }
            }
        }
        return check.equals(s1);
    }

    private static void solve4(int[] arr,int k)
    {
        k=k%arr.length;
        k=arr.length-k;
        List<Integer>list1=new ArrayList<>();
        List<Integer>list2=new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            if(i<k)
            {
                list1.add(arr[i]);
            }else{
                list2.add(arr[i]);
            }
        }
        Collections.reverse(list1);
        Collections.reverse(list2);
        list1.addAll(list2);
        Collections.reverse(list1);
        System.out.println(list1);
    }
    
    private static void solve5(int[] arr)
    {
        //(T=>O(n),S=>O(n))
        List<Integer>list1=new ArrayList<>();
        List<Integer>list2=new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            if(arr[i]%10==0)
            {
                list2.add(arr[i]);
            }else{
                list1.add(arr[i]);
            }
        }
        list1.addAll(list2);
        System.out.println(list1);


        //second approach(T=>O(n2),S=>O(1))
        int actualLen=arr.length;
        for (int i = 0; i < actualLen; i++) {
            if(arr[i]%10==0)
            {
                int store=arr[i];
                for (int j = i; j < arr.length-1; j++) {
                    arr[j]=arr[j+1];
                }
                arr[arr.length-1]=store;
                i--;
                actualLen--;
            }
        }
        for(int x:arr)
            System.out.print(x+" ");
    }

    private static void solve6(int[] arr,int k)
    {
        List<Integer>list1=new ArrayList<>();
        List<Integer>list2=new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            if(i<k)
                 list2.add(arr[i]);
            else
                list1.add(arr[i]);
        }
        list1.addAll(list2);
        System.out.println(list1);
    }
    
    private static void solve7(int r1,int n,int r2,int m)
    {
        int h=(int)Math.ceil(m*1.0/60);
        int additionalCost=r2*((h-n)>0?h-n:0);
        int subsidiaryCost=r1*((h-n)>=0?n:h);
        System.out.println(h+" "+additionalCost+" "+subsidiaryCost);
        System.out.println(additionalCost+subsidiaryCost);
        
    }
    
    private static void solve8(String s)
    {
        int num=Integer.parseInt(s);
        int sum=0;
        while(num!=0)
        {
            sum+=num%10;
            num/=10;
        }
        int continousD=0;
        System.out.println("decreasing");
        for (int i = 0; i < s.length(); i++) {
            int toaAdd=s.charAt(i)-'0';
            for (int j = i+1; j < s.length(); j++) {
                
                if(((s.charAt(j-1)-'0')-1)!=(s.charAt(j)-'0'))
                {
                    break;
                }
                toaAdd=toaAdd*10+(s.charAt(j)-'0');
                System.out.println(toaAdd);
                continousD+=toaAdd;
            }
            System.out.println("===");
        }
        int continousI=0;
        System.out.println("Increasing");
        for (int i = 0; i < s.length(); i++) {
            int toaAdd=s.charAt(i)-'0';
            for (int j = i+1; j < s.length(); j++) {
                
                if(((s.charAt(j-1)-'0')+1)!=(s.charAt(j)-'0'))
                {
                    break;
                }
                toaAdd=toaAdd*10+(s.charAt(j)-'0');
                System.out.println(toaAdd);
                continousI+=toaAdd;
            }
            System.out.println("===");
        }
        System.out.println(sum+" "+continousD+" "+continousI);
        System.out.println(sum+continousD+continousI);
    }
    
    private static void solve9(int[] arr){
        int i=0,j=0,k=arr.length-1;
        while(i<=k)
        {
            if(arr[i]==1)
            {
                int temp=arr[i];
                arr[i]=arr[j];
                arr[j]=temp;
                i++;
                j++;
            }else if(arr[i]==2)
            {
                int temp=arr[i];
                arr[i]=arr[k];
                arr[k]=temp;
                k--;
            }else
            {
                i++;
            }
            for(int x:arr)
                System.out.print(x+" ");
            System.out.println();
        }
        // for(int x:arr)
        //     System.out.print(x+" ");
    }
    
    private static boolean check(String s)
    {
        int left=0,right=s.length()-1;
        while(left<right)
        {
            if(s.charAt(left)!=s.charAt(right))
            {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
    private static void solve10(String s){
    // nayannamantenet
    // nayan
    // naman
    // tenet
    boolean flag=false;
        String s1="";
        for (int i = 0; i < s.length(); i++) {
            s1+=s.charAt(i);
            String s2="";
            // System.out.println("----\n"+s1);
            for (int j = i+1; j < s.length()-1; j++) {
                s2+=s.charAt(j);
                String s3=s.substring(j+1);
                // System.out.println(s2+" "+s3);
                if(check(s2)==true && check(s3)==true)
                {
                    System.out.println(s1+" "+s2+" "+s3);
                    flag=true;
                    break;
                }
            }
            if(flag==true)
            {
                break;
            }
        }
    }
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        // int n=scn.nextInt();
        // int m=scn.nextInt();
        // solve1(n,m);

        // int t = scn.nextInt();
        // while (t-- > 0) {
        //     String s = scn.nextLine();
        //     solve2(s);
        // }

        // String s1=scn.next();
        // String s2=scn.next();
        // System.out.println(solve3(s1,s2));

        // int n=scn.nextInt();
        // int[] arr=new int[n];
        // for (int i = 0; i < arr.length; i++) {
        //     arr[i]=scn.nextInt();
        // }
        // int k=scn.nextInt();
        // solve4(arr,k);
        
        // int n=scn.nextInt();
        // int[] arr=new int[n];
        // for (int i = 0; i < arr.length; i++) {
            //     arr[i]=scn.nextInt();
            // }
            // solve5(arr);

        // int n=scn.nextInt();
        // int[] arr=new int[n];
        // for (int i = 0; i < arr.length; i++) {
        //     arr[i]=scn.nextInt();
        // }
        // int k=scn.nextInt();
        // solve6(arr,k);

        // int r1=scn.nextInt();
        // int n=scn.nextInt();
        // int r2=scn.nextInt();
        // int m=scn.nextInt();
        // solve7(r1,n,r2,m);

        // String s=scn.next();
        // solve8(s);

        // int n=scn.nextInt();
        // int[] arr=new int[n];
        // for (int i = 0; i < arr.length; i++) {
        //     arr[i]=scn.nextInt();
        // }
        // solve9(arr);

        // String s=scn.next();
        // solve10(s);
    }
}