import java.util.*;

public class L001 {
    static int lower_bound(int array[], int key) {

        int index = Arrays.binarySearch(array, key);

        // If key is not present in the array
        if (index < 0) {

            // Index specify the position of the key
            // when inserted in the sorted array
            // so the element currently present at
            // this position will be the lower bound
            return Math.abs(index) - 1;
        }

        // If key is present in the array
        // we move leftwards to find its first occurrence
        else {

            // Decrement the index to find the first
            // occurrence of the key

            while (index > 0) {

                // If previous value is same
                if (array[index - 1] == key)
                    index--;

                // Previous value is different which means
                // current index is the first occurrence of
                // the key
                else
                    return index;
            }

            return index;
        }
    }

    static void upper_bound(int arr[], int key) {
        int index = Arrays.binarySearch(arr, key);
        int n = arr.length;

        // If key is not present in the array
        if (index < 0) {

            // Index specify the position of the key
            // when inserted in the sorted array
            // so the element currently present at
            // this position will be the upper bound
            int upperBound = Math.abs(index) - 1;
            if (upperBound < n)
                System.out.println("The upper bound of " + key
                        + " is " + arr[upperBound]
                        + " at index "
                        + upperBound);
            else
                System.out.println("The upper bound of " + key
                        + " does not exists."+ upperBound);
            return;
        }

        // If key is present in the array
        // we move rightwards to find next greater value
        else {

            // Increment the index until value is equal to
            // key

            while (index < n) {

                // If current value is same
                if (arr[index] == key)
                    index++;

                // Current value is different which means
                // it is the greater than the key
                else {
                    System.out.println(
                            "The upper bound of " + key + " is "
                                    + arr[index] + " at index "
                                    + index);
                    return;
                }
            }
            System.out.println("The upper bound of " + key
                    + " does not exist.");
        }
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        List<Integer> A = Arrays.asList(1, 4, 5, 9);

        // Q1:Check if X exists in the sorted Array
        int idx = Collections.binarySearch(A, 91);
        if (idx >= 0) {
            System.out.println("Element found and its idx is: " + idx);
        } else {
            System.out
                    .println("Element not present but if it was present then its idx would be: " + (Math.abs(idx + 1)));
        }

        // Lower Bound
        int idx1 = lower_bound(new int[] { 1, 4, 4, 4, 5, 9 }, 4);
        System.out.println("lower bound of " + 4 + " is " + idx1);
        int idx2 = lower_bound(new int[] { 1, 4, 4, 4, 5, 9 }, 6);
        System.out.println("lower bound of " + 6 + " is " + idx2);

        // Upper Bound
        upper_bound(new int[] { 1, 4, 4, 4, 5, 9 }, 4);
        upper_bound(new int[] { 1, 4, 4, 4, 5, 9 }, 6);
        upper_bound(new int[] { 1, 4, 4, 4, 5, 9 }, 11);
        System.out.println(1e-6);
        System.out.println(-1e6);
    }
}