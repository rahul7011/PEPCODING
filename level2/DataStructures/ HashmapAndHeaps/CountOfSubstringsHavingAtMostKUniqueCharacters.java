import java.util.*;

public class CountOfSubstringsHavingAtMostKUniqueCharacters {

    public static int solution(String str, int k) {
        HashMap<Character, Integer> hm = new HashMap<>();
        int j = -1;
        int count = 0;
        int ans = 0;
        for (int i = 0; i < str.length(); i++) {
            if (hm.containsKey(str.charAt(i)) == false) {
                hm.put(str.charAt(i), 1);
                count++;
            } else {
                int freq = hm.get(str.charAt(i));
                freq += 1;
                hm.put(str.charAt(i), freq);
            }

            if (count > k) {
                j += 1;
                while (j <= i) {
                    int val = hm.get(str.charAt(j));
                    val -= 1;
                    hm.put(str.charAt(j), val);
                    if (val == 0) {
                        hm.remove(str.charAt(j));
                        count--;
                        break;
                    }
                    j++;
                }
                ans += (i - j);
            } else {
                ans += (i - j);
            }
        }

        return ans;
    }
	
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
        String str = scn.next();
        int k = scn.nextInt();
		System.out.println(solution(str,k));
        scn.close();
	}

}
