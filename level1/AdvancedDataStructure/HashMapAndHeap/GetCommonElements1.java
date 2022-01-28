import java.util.*;
public class GetCommonElements1 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n1 = scn.nextInt();
        HashMap<Integer, Integer> mp = new HashMap<>();
        for (int i = 0; i < n1; i++) {
            int a = scn.nextInt();
            if (mp.containsKey(a) == true) {
                int oldVal = mp.get(a);
                mp.put(a, oldVal + 1);
            } else {
                mp.put(a, 1);
            }
        }
        int n2 = scn.nextInt();
        for (int i = 0; i < n2; i++) {
            int a = scn.nextInt();
            if (mp.containsKey(a) == true) {
                System.out.println(a);
                mp.remove(a);
            }
        }
        scn.close();
    }

}