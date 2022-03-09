import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Stack;

public class MergeOverlappingInterval {

    public static class Pair {
        int start;
        int end;

        Pair(int start, int end) {
            this.start = start;
            this.end = end;
        }

    }

    public static class customComparator implements Comparator<Pair> {
        public int compare(Pair p1, Pair p2) {
            if (p1.start - p2.start < 0) {
                return -1;
            } else if (p1.start - p2.start > 0) {
                return 1;
            } else {
                return p1.end - p2.end;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        // write your code here
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        ArrayList<Pair> list = new ArrayList<>();
        for (int j = 0; j < n; j++) {
            String line = br.readLine();
            list.add(new Pair(Integer.parseInt(line.split(" ")[0]), Integer.parseInt(line.split(" ")[1])));

        }
        Collections.sort(list, new customComparator());
        // for(Pair p:list)
        // {
        // System.out.println(p.start+" "+p.end);

        // }
        // System.out.println("--------");
        mergeOverlappingIntervals(list);
    }

    public static void mergeOverlappingIntervals(ArrayList<Pair> list) {
        // merge overlapping intervals and print in increasing order of start time
        Stack<Pair> st = new Stack<>();
        for (int i = 0; i < list.size(); i++) {
            int flag = 0;
            if (st.size() > 0 && st.peek().end >= list.get(i).start) {
                Pair top = st.pop();
                st.push(new Pair(top.start, Math.max(top.end, list.get(i).end)));
                flag = 1;
            }
            if (flag == 0) {
                st.push(list.get(i));
            }
        }
        Stack<Pair> res = new Stack<>();
        while (st.size() != 0) {
            res.push(st.pop());
        }
        while (res.size() > 0) {
            Pair p = res.pop();
            System.out.println(p.start + " " + p.end);
        }
    }

}