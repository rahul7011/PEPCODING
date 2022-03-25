import java.util.*;
public class FindItineraryFromTickets {
    public static void solve(HashMap<String, String> hm) {
        HashSet<String> arrivals = new HashSet<>();
        for (String key : hm.keySet()) {
            arrivals.add(key);
        }
        for (String key : hm.keySet()) {
            if (arrivals.contains(hm.get(key)) == true) {
                arrivals.remove(hm.get(key));
            }
        }

        String check = "";
        for (String start : arrivals) {
            check = start;
        }

        int size = hm.size();
        ArrayList<String> list = new ArrayList<>();
        list.add(check);
        while (size != 0) {
            list.add(hm.get(check));
            check = hm.get(check);
            size--;
        }
        // System.out.println(list);
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i));
            if (i != list.size() - 1) {
                System.out.print(" -> ");
            }
        }
        System.out.print(".");
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int noofpairs_src_des = scn.nextInt();
        HashMap<String, String> map = new HashMap<>();
        for (int i = 0; i < noofpairs_src_des; i++) {
            String s1 = scn.next();
            String s2 = scn.next();
            map.put(s1, s2);
        }
        solve(map);
        scn.close();
    }
}
