import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

public class practice {
    private static boolean Add(String a,String b,String c,int[] mapping)
    {
        int left=a.length()-1;
        int right=b.length()-1;
        int carry=0;
        ArrayList<Integer>firstHalf=new ArrayList<>();
        while(left>=0&&right>=0)
        {
            int sum=mapping[a.charAt(left)-'a']+mapping[b.charAt(right)-'a']+carry;
            firstHalf.add(sum%10);
            carry=sum/10;
            left--;
            right--;
        }
        while(left>=0)
        {
            int sum=mapping[a.charAt(left)-'a']+carry;
            firstHalf.add(sum%10);
            carry=sum/10;
            left--;
        }
        while(right>=0)
        {
            int sum=mapping[a.charAt(right)-'a']+carry;
            firstHalf.add(sum%10);
            carry=sum/10;
            right--;
        }
        if(carry>0)
        {
            firstHalf.add(carry);
        }
        Collections.reverse(firstHalf);
        for (int i = 0; i < c.length(); i++) {
            if(mapping[c.charAt(i)-'a']!=firstHalf.get(i))
            {
                return false;
            }
        }
        System.out.println(firstHalf);
        return true;
    }
    private static boolean wordBreak(String unique,String a,String b,String c, int idx,int[] mapping,boolean[] visited) {
        if (idx == unique.length()) {
            if(Add(a, b, c, mapping)==true)
            {
                return true;
            }
            return false;
        }
        for (int num = 0; num <= 9; num++) {
            // System.out.println((unique.charAt(idx)-'a'));
            // System.out.println(('s'-'a'));
            if (visited[num]==false) {
                if(idx==0&&num==0)
                {
                    continue;
                }
                char curr=unique.charAt(idx);
                visited[num]=true;
                mapping[curr-'a']=num;
                if (wordBreak(unique,a,b,c,idx + 1,mapping,visited) == true) {
                    return true;
                }
                visited[num]=false;
                mapping[curr-'a']=-1;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        //send + more = money
        String a="hello";
        String b="world";
        String c="progm";
        String z=a+b+c;
        String unique="";
        HashSet<Character>hs=new HashSet<>();
        for (int i = 0; i < z.length(); i++) {
            if(hs.contains(z.charAt(i))==false)
            {
                unique+=z.charAt(i);
            }
            hs.add(z.charAt(i));
        }
        System.out.println(unique);
        int[] mapping=new int[26];
        Arrays.fill(mapping, -1);
        boolean[] visited=new boolean[10];
        System.out.println(wordBreak(unique,a,b,c,0,mapping,visited));
        for (int i = 0; i < mapping.length; i++) {
            System.out.print((char)(i+'a')+":"+mapping[i]+" ");
        }
    }
}
