import java.util.*;

public class HighestFrequencyCharacter {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        String s = scn.next();
        HashMap<Character, Integer> mp = new HashMap<>();
        char maxFq = s.charAt(0);
        int maxF = 0;
        for (int i = 0; i < s.length(); i++) {
            if (mp.containsKey(s.charAt(i)) == true) {
                int value = mp.get(s.charAt(i));
                mp.put(s.charAt(i), value + 1);
            } else {
                mp.put(s.charAt(i), 1);
            }
            if (maxF <= mp.get(s.charAt(i))) {
                maxF = mp.get(s.charAt(i));
                maxFq = s.charAt(i);
            }
        }
        System.out.println(maxFq);
        scn.close();
    }

}