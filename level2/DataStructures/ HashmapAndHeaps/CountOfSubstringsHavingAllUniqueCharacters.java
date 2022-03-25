import java.util.*;
public class CountOfSubstringsHavingAllUniqueCharacters {

    public static int solve(String s){
        HashSet<Character>hs=new HashSet<>();
        String res="";
        int total=0;
        for(int i=0;i<s.length();i++)
        {
            if(hs.contains(s.charAt(i))==true){
                int len=res.length();
                res+=s.charAt(i);
                int idx=0;
                while(res.charAt(idx)!=s.charAt(i))
                {
                    hs.remove(res.charAt(idx));
                    idx++;
                }
                res=res.substring(idx+1);
                int rem=(len-(idx+1));
                len=(len*(len+1))/2;
                rem=(rem*(rem+1))/2;
                total+=(len-rem);
            }else
            {
                res+=s.charAt(i);
                hs.add(s.charAt(i));
            }
        }
        int len=res.length();
        len=(len*(len+1))/2;
        total+=(len);
        return total;
    }
    
	public static int solution(String str) {
		return solve(str);
	}
	
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		String str = scn.next();
		System.out.println(solution(str));
        scn.close();
	}

}