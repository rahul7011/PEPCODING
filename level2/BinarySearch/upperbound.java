public class upperbound {
    public static int upper_bound(int arr[], int key) {
        int mid, N = arr.length;
        int low = 0;
        int high = N-1;
        while (low <= high) {
            mid = low + (high - low) / 2;
            if (key >= arr[mid]) {
                low = mid + 1;
            }
            else {
                high = mid-1;
            }
        }
        return low;
    }

    public static void main(String[] args) {
        System.out.println("Array Length is:" + 10);
        System.out.println(upper_bound(new int[] { 4, 6, 10, 12, 18, 18, 20, 20, 30, 45 }, 18));
        System.out.println(upper_bound(new int[] { 4, 6, 10, 12, 18, 18, 20, 20, 30, 45 }, 21));
        System.out.println(upper_bound(new int[] { 4, 6, 10, 12, 18, 18, 20, 20, 30, 45 }, 2));
        System.out.println(upper_bound(new int[] { 4, 6, 10, 12, 18, 18, 20, 20, 30, 45 }, 50));
    }
}

/*
 * Input : 10 20 30 30 40 50
 * Output : upper_bound for element 30 is 40 at index 4
 * Input : 10 20 30 40 50
 * Output : upper_bound for element 45 is 50 at index 4
 * Input : 10 20 30 40 50
 * Output : upper_bound for element 60 does not exists
 */
