import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;

public class MergeKSortedLists {
    public static class Pair implements Comparable<Pair> {
        int data;
        int listNum;
        int index;

        // Constructor
        Pair(int data, int lnum, int idx) {
            this.data = data;
            this.listNum = lnum;
            this.index = idx;
        }

        // -1 for this
        // 1 for that
        // 0 for equal
        public int compareTo(Pair that) {
            return this.data - that.data;
        }
    }

    public static ArrayList<Integer> mergeKSortedLists(ArrayList<ArrayList<Integer>> lists) {
        ArrayList<Integer> rv = new ArrayList<>();
        // Min-Heap
        // Since we are using user-defined data-type so we have to implement our own
        // custom sort method
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        for (int i = 0; i < lists.size(); i++) {
            pq.add(new Pair(lists.get(i).get(0), i, 0));
        }
        while (pq.size() != 0) {
            Pair top = pq.peek();
            pq.remove();
            rv.add(top.data);
            int nextIndex = top.index + 1;
            int currentListNum = top.listNum;
            if (nextIndex < lists.get(currentListNum).size()) {
                pq.add(new Pair(lists.get(currentListNum).get(nextIndex), currentListNum, nextIndex));
            }
        }

        return rv;
    }

    public static void main(String[] args) {
        Scanner scn=new Scanner(System.in);
        int n=scn.nextInt();
        ArrayList<ArrayList<Integer>>lists=new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int size=scn.nextInt();
            ArrayList<Integer>list=new ArrayList<>();
            for (int j = 0; j < size; j++) {
                list.add(scn.nextInt());
            }
            lists.add(list);
        }
        ArrayList<Integer> mlist = mergeKSortedLists(lists);
        for (int val : mlist) {
            System.out.print(val + " ");
        }
        System.out.println();
        scn.close();
    }
}
