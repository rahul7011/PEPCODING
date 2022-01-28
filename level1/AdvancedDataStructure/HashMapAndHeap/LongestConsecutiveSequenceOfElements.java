import java.util.*;
public class LongestConsecutiveSequenceOfElements {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int[] arr = new int[n];
        HashMap<Integer, Boolean> mp = new HashMap<>();
        for (int i = 0; i < n; i++) {
            arr[i] = scn.nextInt();
            mp.put(arr[i], true);
        }
        // Eliminating all the Non-Starting point
        for (int i = 0; i < n; i++) {
            if (mp.containsKey(arr[i] - 1) == true) {
                mp.put(arr[i], false);
            }
        }
        // counting length of each consective sequence
        // and simulataneously finding the max out of them
        int maxStartPoint = 0;
        int maxSequenceLength = 0;

        for (int i = 0; i < n; i++) {
            if (mp.get(arr[i]) == true) {
                int lengthCount = 1;
                while (mp.containsKey(arr[i] + lengthCount) == true) {
                    // removing them as well
                    lengthCount++;
                }
                if (maxSequenceLength < lengthCount) {
                    maxSequenceLength = lengthCount;
                    maxStartPoint = arr[i];
                }
                mp.put(arr[i], false);
            }
        }
        for (int i = 0; i < maxSequenceLength; i++) {
            System.out.println(maxStartPoint + i);
        }
        scn.close();
    }
}
